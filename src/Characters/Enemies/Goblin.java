package Characters.Enemies;

import DungeonClasses.MonsterClass;

public class Goblin extends EnemyBase{

    public Goblin(int level)
    {
        String name = "Goblin";
        double goldDrop = 2.1;
        double goldDropScale = 3.3;
        int xpDrop = 3;
        int xpDropScale = 5;
        int armor = 1;
        int armorScale = 1;
        int maxMP = 5;
        int mpScale = 0;
        int maxHP = 13;
        int hpScale = 13;
        int baseDmg = 3;
        int dmgScale = 3;
        super("Goblin",maxHP,hpScale,maxMP,mpScale,goldDrop,goldDropScale,xpDrop,xpDropScale,level,armor,armorScale,baseDmg,dmgScale);
    }
}
