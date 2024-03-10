package me.cohe.john_mod.mixin;

import me.cohe.john_mod.JohnRenderType;
import me.cohe.john_mod.JohnUtils;
import me.cohe.john_mod.config.JohnConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(EntityRenderer.class)
public abstract class JohnEntityRendererMixin {
    @Shadow
    protected abstract boolean hasLabel(Entity entity);

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderer;hasLabel(Lnet/minecraft/entity/Entity;)Z"))
    private boolean renderJohn(EntityRenderer<Entity> instance, Entity entity) {
        if (JohnUtils.isJohn(entity)) {
            assert MinecraftClient.getInstance().player != null;
            return JohnConfig.getConfigData().renderType != JohnRenderType.WhenVisible || MinecraftClient.getInstance().player.canSee(entity);
        }

        return this.hasLabel(entity);
    }
}