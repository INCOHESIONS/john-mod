package me.cohe.john_mod;

import net.minecraft.item.ArmorMaterials;

public enum JohnArmorMaterials {
    Leather,
    Chain,
    Iron,
    Gold,
    Diamond,
    Netherite;

    public static ArmorMaterials toArmorMaterial(JohnArmorMaterials johnMaterial) {
        return Enum.valueOf(ArmorMaterials.class, johnMaterial.name().toUpperCase());
    }
}
