package Characters;

import DungeonClasses.DungeonBaseClass;
import EventSystem.EventSystem;
import EventSystem.Events.Event;
import Items.Bases.Equipable;
import Items.Bases.ItemBase;
import Items.Bases.WithInventory;
import Spells.SpellBase;

import java.util.ArrayList;
import java.util.List;

public class CharacterBase extends WithInventory {
    protected String name;
    protected int HP;
    protected int maxHP;
    protected int MP;
    protected int maxMP;
    protected int level;
    protected int armor;
    protected boolean isAlive;
    protected double xp;
    protected double gold;
    protected double goldDrop;
    protected double xpDrop;
    protected int damageBase;
    protected DungeonBaseClass dungeonBaseClass;
    protected ArrayList<SpellBase> spells;
    protected int xpPerLevel;

    public CharacterBase() {
        spells = new ArrayList<>();
        name = "";
        HP = 0;
        maxHP = 0;
        level = 0;
        isAlive = false;
        xp = 0;
        gold = 0;
        goldDrop = 0;
        xpDrop = 0;
        damageBase = 0;
        dungeonBaseClass = null;
    }

    public void applyInitials()
    {
        if(this.dungeonBaseClass == null)
        {
            EventSystem.globalEventSystemSingleton.dispatch(new Event("Game.log",this.getClass(),"CharacterBase didn't have a DungeonClass... Skipping apply initials"));
            return;
        }
        this.damageBase = this.dungeonBaseClass.getClassInitialDamage();
        this.armor = this.dungeonBaseClass.getClassInitialArmor();
        this.HP = this.maxHP = this.dungeonBaseClass.getClassInitialHp();
        this.MP = this.maxMP = this.dungeonBaseClass.getClassInitialMp();
        this.xpPerLevel += this.dungeonBaseClass.getClassExtraRequiredXpPerLevel();
    }

    public void addGoldDrop(double gold)
    {
        this.goldDrop += gold;
    }
    public void addXpDrop(double xp)
    {
        this.xpDrop += xp;
    }

    public void setDamageBase(int damageBase) {
        this.damageBase = damageBase;
    }

    public int getDamageBase() {
        return damageBase;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getHP() {
        return HP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int getMP() {
        return MP;
    }

    public boolean useMP(int MP) {
        if(this.MP >= MP) {
            this.MP -= MP;
            return true;
        }
        return false;
    }

    public void restoreMP(int MP) {
        this.MP += MP;
        if(this.MP > this.maxMP) {
            this.MP = this.maxMP;
        }
    }

    public void heal(int amount) {
        this.HP += amount;
        if(this.HP > maxHP) {
            this.HP = maxHP;
        }
    }

    public void addArmor(int amount)
    {
        this.armor += amount;
    }

    public void removeArmor(int amount)
    {
        this.armor -= amount;
    }

    public int getArmor() {
        return armor;
    }

    public void takeDamageNoTrigger(int amount, CharacterBase attacker)
    {
        //Denne trigger ikke nogen effekter, og er til brug når skade ikke kan trigger effekter
        int damageTaken = applyArmorReduction(amount);
        this.HP -= damageTaken;
        if(this.HP <= 0) {
            this.HP = 0;
        }
        EventSystem.quickDispatch(new Event("Combat.takedamage",this.getClass(),attacker.getName() + " dealt " + damageTaken + " to " + this.getName()));
        EventSystem.quickDispatch(new Event("Combat.healthupdate",this.getClass(),this.getName() + ": " + this.getHP() + "/" + this.getMaxHP()));
        if(this.HP <= 0) {
            onDeath(attacker);
        }
    }

    public void takeDamage(int amount, CharacterBase attacker)
    {
        int damageTaken = applyArmorReduction(amount);
        this.HP -= damageTaken;
        if(this.HP <= 0) {
            this.HP = 0;
        }
        EventSystem.quickDispatch(new Event("Combat.takedamage",this.getClass(),attacker.getName() + " dealt " + damageTaken + " to " + this.getName()));
        EventSystem.quickDispatch(new Event("Combat.healthupdate",this.getClass(),this.getName() + ": " + this.getHP() + "/" + this.getMaxHP()));
        onTakeDamage(attacker);
        if(this.HP <= 0)
        {
            onDeath(attacker);
        }



    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getLevel() {
        return level;
    }

    public DungeonBaseClass getDungeonClass()
    {
        return  this.dungeonBaseClass;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }


    protected int applyArmorReduction(int dmg)
    {
        //reduce by 0.25 per armor
        int finalDamage = (int)Math.floor(((double)dmg-((double)this.armor*0.25)));
        return Math.max(1, finalDamage);
    }

    public void addXp(double xp)
    {
        this.xp += xp;
    }


    public void startAttack(CharacterBase target)
    {
        this.dungeonBaseClass.onAttackStart(this,target);
    }

    public void dealDamage(CharacterBase target)
    {
        target.takeDamage(variateDamage(),this);
        this.dungeonBaseClass.onDamageDealt(this);
        this.onDealDamage(target);
    }

    public void endAttack()
    {
        this.dungeonBaseClass.onAttackEnd(this);
    }

    public void levelUp()
    {
        this.dungeonBaseClass.onLevelUp(this);
        this.level++;
    }

    public int variateDamage()
    {
        //Default is 20% 10%
        double percentMin = 0.10;
        double percentMax = 0.20;
        double variatedPercent = ((Math.random() * (percentMax*100) - (percentMin*100)))/100;
        int variatedDamage = 0;
        if(variatedPercent < 0.0)
        {
            variatedDamage = (int)((double)damageBase-(damageBase*(variatedPercent*-1.0)));
        }
        else
        {
            variatedDamage = (int)((double)damageBase+(damageBase*variatedPercent));
        }

        if(variatedDamage < 0)
        {
            variatedDamage = 0;
        }

        return variatedDamage;
    }

    public int variateHealth()
    {
        return maxHP;
    }

    //Hooks

    public void onDealDamage(CharacterBase target)
    {

    }

    public void onDeath(CharacterBase killer)
    {
        killer.xp += this.xpDrop;
        killer.gold += this.goldDrop;
        isAlive = false;
    }

    public void onTakeDamage(CharacterBase attacker)
    {

    }

    @Override
    public void unEquip(ItemBase item) {

        ((Equipable)item).onUnequip(this);
        equippedItems.remove(item);
    }

    @Override
    public void equip(ItemBase item) {
        if(Equipable.class.isAssignableFrom(item.getClass()))
        {
            Equipable equip = (Equipable)item;
            List<ItemBase> itemsInSameSlot = equippedItems.stream().filter(inventoryItem -> item.getItemSlot() == inventoryItem.getItemSlot()).toList();
            if(!itemsInSameSlot.isEmpty())
            {
                unEquip(itemsInSameSlot.get(0));
            }
            equippedItems.add(item);
            ((Equipable) item).onEquip(this);
            return;
        }
        EventSystem.globalEventSystemSingleton.dispatch(new Event("Game.log",this.getClass(),"Item: " + item.getName() + " is not equipable..."));
    }
}
