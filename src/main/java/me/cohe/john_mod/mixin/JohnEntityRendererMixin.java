package me.cohe.john_mod.mixin;

import me.cohe.john_mod.JohnUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityRenderer.class)
public abstract class JohnEntityRendererMixin {
    @Shadow
    protected abstract boolean hasLabel(Entity entity);

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderer;hasLabel(Lnet/minecraft/entity/Entity;)Z"))
    private boolean renderJohn(EntityRenderer<Entity> instance, Entity entity) {
        if (entity instanceof LivingEntity && JohnUtils.all(entity.getArmorItems(), JohnEntityRendererMixin::predicate)) {
            assert MinecraftClient.getInstance().player != null;
            final var isEntityVisible = MinecraftClient.getInstance().player.canSee(entity);

            if (isEntityVisible) {
                return true;
            }
        }

        return this.hasLabel(entity);
    }

    @Unique
    private static boolean predicate(final ItemStack itemStack) {
        if (itemStack != null && itemStack != ItemStack.EMPTY && itemStack.getItem() instanceof ArmorItem item) {
            return item.getMaterial().equals(ArmorMaterials.GOLD);
        }

        return false;
    }
}
