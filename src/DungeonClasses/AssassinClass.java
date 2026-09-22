package DungeonClasses;

import Characters.CharacterBase;
import EventSystem.EventSystem;
import EventSystem.Events.Event;

import java.lang.Math;

public class AssassinClass extends DungeonBaseClass {

    public AssassinClass()
    {
        classInitialDamage = 16;
        classExtraRequiredXpPerLevel = 8;
        classInitialHp = 45;
        classInitialMp = 60;
        this.name = "Assassin";
        this.classShortName = 'A';
    }

    @Override
    public void onLevelUp(CharacterBase character) {
        /*Assasins gain a little health but a huge damage bonus*/
        int assasinMaxHpGain = 30;
        int assasinFlatDamageGain = 15;
        character.setDamageBase(character.getDamageBase() + assasinFlatDamageGain);
        character.setMaxHP(character.getMaxHP() + assasinMaxHpGain);

    }

    @Override
    public void onAttackStart(CharacterBase attacker, CharacterBase target) {
        /*Assasin gains a 10~% critical damage chance*/
        if(Math.random() <= 0.10)
        {
            attacker.setDamageBase(attacker.getDamageBase()*2);
            EventSystem.quickDispatch(new Event("Combat.classability",this.getClass(),attacker.getName() + "'s eyes spot a critical weakness in " + target.getName() + "'s defences"));
        }
    }

    @Override
    public void onDamageDealt(CharacterBase character) {
        /*Nothing*/
    }

    @Override
    public void onAttackEnd(CharacterBase character)
    {
        character.setDamageBase(character.getDamageBase()/2);
    }
}
