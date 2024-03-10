package me.cohe.john_mod;

import me.cohe.john_mod.config.JohnConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;

import java.util.function.Predicate;

public final class JohnUtils {

    public static MutableText translate(String key) {
        return MutableText.of(new TranslatableTextContent(key, key, new Object[]{}));
    }

    public static <T extends Entity> boolean isJohn(T entity) {
        return entity instanceof LivingEntity && JohnUtils.all(entity.getArmorItems(), JohnUtils::predicate) && (JohnConfig.config.overrideCustomNames || !(entity.hasCustomName()));
    }

    private static boolean predicate(final ItemStack itemStack) {
        if (itemStack != null && itemStack != ItemStack.EMPTY && itemStack.getItem() instanceof ArmorItem item) {
            final var armorMaterial = JohnArmorMaterials.toArmorMaterial(JohnConfig.config.johnMaterial);
            return item.getMaterial().equals(armorMaterial);
        }

        return false;
    }

    public static <T> boolean all(Iterable<T> iterable, Predicate<T> predicate) {
        for (T item : iterable) {
            if (!predicate.test(item)) {
                return false;
            }
        }

        return true;
    }
}
