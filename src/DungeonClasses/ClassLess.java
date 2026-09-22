package DungeonClasses;

import Characters.CharacterBase;

public class ClassLess extends DungeonBaseClass{

    @Override
    public void onLevelUp(CharacterBase character) {
        //Do nothing
    }

    @Override
    public void onAttackStart(CharacterBase attacker, CharacterBase target) {

    }

    @Override
    public void onDamageDealt(CharacterBase character) {
        //Do nothing
    }

    @Override
    public void onAttackEnd(CharacterBase character) {

    }
}
