package DungeonClasses;

import Characters.CharacterBase;
import EventSystem.EventSystem;
import EventSystem.Events.Event;

public class WarriorClass extends DungeonBaseClass {

    public WarriorClass()
    {
        classInitialDamage = 10;
        classExtraRequiredXpPerLevel = 5;
        classInitialHp = 85;
        classInitialMp = 30;
        this.name =  "Warrior";
        this.classShortName = 'W';
    }

    @Override
    public void onLevelUp(CharacterBase character) {
        /*Warriors gains a little more health, but a decent amount more damage per level*/
        int warriorMaxHpGain = 45;
        int warriorFlatDamageGain = 8;
        character.setDamageBase(character.getDamageBase() + warriorFlatDamageGain);
        character.setMaxHP(character.getMaxHP() + warriorMaxHpGain);

    }

    @Override
    public void onAttackStart(CharacterBase attacker, CharacterBase target) {
        /*Warriors don't gain any benifits before the attack starts*/
    }

    @Override
    public void onDamageDealt(CharacterBase character) {
        /*Warriors heal 1+level HP per successful hit*/
        int healing = (int)Math.floor(1.0 + ((double)character.getLevel()*2.25));
        character.heal(healing);
        EventSystem.quickDispatch(new Event("Combat.classability",this.getClass(),"The warriors spirit heals " + character.getName() + " for " + healing + " HP (" + character.getHP() + "/" + character.getMaxHP() + " HP)"));
    }

    @Override
    public void onAttackEnd(CharacterBase character) {
        /*Warriors do nothing special :)*/
    }
}
