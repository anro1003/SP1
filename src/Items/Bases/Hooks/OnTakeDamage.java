package Items.Bases.Hooks;

import Characters.CharacterBase;

public interface OnTakeDamage {

    public void onTakeDamage(CharacterBase wearer, CharacterBase attacker);
}
