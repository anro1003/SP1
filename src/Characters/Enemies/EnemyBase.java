package Characters.Enemies;

import Characters.CharacterBase;
import DungeonClasses.ClassLess;
import DungeonClasses.DungeonBaseClass;
import DungeonClasses.MonsterClass;
import EventSystem.EventSystem;
import EventSystem.Events.Event;

public abstract class EnemyBase extends CharacterBase {
    public EnemyBase(String name,
                     int hp,
                     int hpScale,
                     int mp,
                     int mpScale,
                     double goldDrop,
                     double goldDropScale,
                     int xpDrop,
                     int xpDropScale,
                     int level,
                     int armor,
                     int armorScale,
                     int baseDmg,
                     int dmgScale)
    {
        super();
        this.dungeonBaseClass = new MonsterClass(name,hp,hpScale,baseDmg,dmgScale,mp,mpScale,armor,armorScale,goldDropScale,xpDropScale);
        this.name = name;
        this.maxHP = this.HP = hp;
        this.goldDrop = goldDrop;
        this.xpDrop = xpDrop;
        this.level = level;
        this.armor = armor;
        this.maxMP = this.MP = mp;
    }
    public EnemyBase()
    {
        super();
    }

    @Override
    public void onDeath(CharacterBase killer)
    {
        super.onDeath(killer);
        EventSystem.globalEventSystemSingleton.dispatch(new Event("Enemy.death",this.getClass()));
    }

    @Override
    public int variateHealth()
    {
        //Monsters should variate health - TODO
        return maxHP;
    }
}
