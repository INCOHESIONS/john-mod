package me.cohe.john_mod.mixin;

import me.cohe.john_mod.JohnUtils;
import me.cohe.john_mod.config.JohnConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(LivingEntity.class)
public abstract class JohnEntityMixin extends Entity implements Nameable {
    protected JohnEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public Text getName() {
        if (this.getCustomName() == null && JohnUtils.isJohn(this)) {
            final var mobName = Text.translatable(this.getType().getTranslationKey()).getString();
            return Text.of(JohnConfig.config.nameTagText.replace("{mob_name}", mobName));
        }

        return super.getName();
    }
}
