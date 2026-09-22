package Items.Armors;

import EventSystem.*;
import EventSystem.Events.Event;
import Items.Bases.ItemSlot;

public class GenericArmors {

    public static ArmorBase getGenericArmor(GenericArmorType armorType, ItemSlot equipSlot)
    {
        if(equipSlot == null || equipSlot == ItemSlot.NONE || equipSlot == ItemSlot.HAND)
        {
            EventSystem.globalEventSystemSingleton.dispatch(new Event("Game.err",GenericArmors.class,"Attempted to generate armor for invalid equipslot: " + equipSlot));
            return null;
        }

        int armorBase = 0;
        String itemName = "";

        switch (armorType)
        {
            case LEATHER -> {
                itemName = "Leather";
                armorBase += 0;
            }
            case IRON -> {
                armorBase += 2;
                itemName = "Iron";
            }
            case STEEL -> {
                armorBase += 3;
                itemName = "Steel";
            }
            case BRONZE -> {
                armorBase += 1;
                itemName = "Bronze";
            }
            case TUNGSTEN -> {
                armorBase += 5;
                itemName = "Tungsten";
            }
        }

        switch (equipSlot)
        {
            case HEAD -> {
                armorBase += 2;
                itemName += " Helmet";
            }
            case CHEST -> {
                armorBase += 3;
                itemName += " Chestplate";
            }
            case LEGS -> {
                armorBase += 2;
                itemName += " Leggings";
            }
            case FEET -> {
                armorBase += 1;
                itemName += " Boots";
            }
        }

        return new ArmorBase(itemName, equipSlot, armorBase);
    }

}
