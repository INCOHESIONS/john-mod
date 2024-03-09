package me.cohe.john_mod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
public abstract class JohnEntityMixin extends Entity implements Nameable {
    protected JohnEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public Text getName() {
        return Text.of("John ").copy().append(Text.translatable(this.getType().getTranslationKey()));
    }
}
