package DungeonClasses;

import Characters.CharacterBase;

public abstract class DungeonBaseClass {
    protected String name;
    protected char classShortName;
    protected int classInitialHp;
    protected int classInitialDamage;
    protected int classExtraRequiredXpPerLevel;
    protected int classInitialMp;
    protected int classInitialArmor;

    public String getName()
    {
        return name;
    }

    public char getClassShortName()
    {
        return classShortName;
    }

    public int  getClassInitialHp()
    {
        return classInitialHp;
    }

    public int getClassInitialDamage()
    {
        return classInitialDamage;
    }

    public int getClassExtraRequiredXpPerLevel()
    {
        return classExtraRequiredXpPerLevel;
    }

    public int getClassInitialMp()
    {
        return classInitialMp;
    }

    public int getClassInitialArmor()
    {
        return classInitialArmor;
    }


    public abstract void onLevelUp(CharacterBase character);
    public abstract void onAttackStart(CharacterBase attacker, CharacterBase target);
    public abstract void onDamageDealt(CharacterBase character);
    public abstract void onAttackEnd(CharacterBase character);

}
