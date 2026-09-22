package Items.Bases;

import Characters.CharacterBase;

public interface Equipable {
    public void onEquip(CharacterBase character);
    public void onUnequip(CharacterBase character);
}
