package DungeonClasses;

import Characters.CharacterBase;

public class MonsterClass extends DungeonBaseClass {
    protected int monsterHpScale;
    protected int monsterMpScale;
    protected int monsterArmorScale;
    protected int monsterDmgScale;
    protected int monsterGoldDropScale;
    protected int monsterXpDropScale;
    public MonsterClass(
            String name,
            int monsterInitialHp,
            int monsterHpScale,
            int monsterInitialDamage,
            int monsterDamageScale,
            int monsterMp,
            int monsterMpScale,
            int monsterInitialArmor,
            int monsterArmorScale,
            double goldDropScale,
            int xpDropScale) {
        super();
        this.name = name;
        this.classShortName = 'X';
        this.classInitialDamage = monsterInitialDamage;
        this.classInitialArmor = monsterInitialArmor;
        this.classInitialHp = monsterInitialHp;
        this.classInitialMp = monsterMp;

        this.monsterHpScale = monsterHpScale;
        this.monsterArmorScale = monsterArmorScale;
        this.monsterDmgScale = monsterDmgScale;
        this.monsterGoldDropScale = xpDropScale;
        this.monsterXpDropScale = xpDropScale;
    }


    @Override
    public void onLevelUp(CharacterBase character) {
        character.setDamageBase(character.getDamageBase() + this.monsterDmgScale);
        character.addArmor(this.monsterArmorScale);
        character.setMaxHP(character.getMaxHP() + this.monsterHpScale);
        character.addGoldDrop(this.monsterGoldDropScale);
        character.addXpDrop(this.monsterXpDropScale);
    }

    @Override
    public void onAttackStart(CharacterBase attacker, CharacterBase target) {

    }

    @Override
    public void onDamageDealt(CharacterBase character) {

    }

    @Override
    public void onAttackEnd(CharacterBase character) {

    }
}
