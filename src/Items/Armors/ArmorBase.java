package Items.Armors;

import Characters.CharacterBase;
import Items.Bases.Equipable;
import Items.Bases.ItemBase;
import Items.Bases.ItemSlot;

public class ArmorBase extends ItemBase implements Equipable {

    protected int armorBonus = 0;

    public ArmorBase(String name, ItemSlot itemSlot) {
        super(name, itemSlot);
    }
    public ArmorBase(String name, ItemSlot itemSlot, int armorBonus) {
        super(name, itemSlot);
        this.armorBonus = armorBonus;
    }

    @Override
    public void onEquip(CharacterBase character) {
        character.addArmor(armorBonus);
    }

    @Override
    public void onUnequip(CharacterBase character) {
        character.removeArmor(armorBonus);
    }
}
