package Characters;

import DungeonClasses.DungeonBaseClass;
import EventSystem.Events.Event;
import EventSystem.EventSystem;
import Items.Bases.Equipable;
import Items.Bases.Hooks.OnDealDamage;
import Items.Bases.ItemBase;
import Items.Bases.Hooks.OnTakeDamage;
import Items.Bases.WithInventory;

import java.util.ArrayList;
import java.util.List;

public class Hero extends CharacterBase {

    public Hero(String name, int startingLevel, DungeonBaseClass heroClass)
    {
        super();
        this.name = name;
        this.dungeonBaseClass = heroClass;
        this.applyInitials();
        for(int i = 0; i < startingLevel; i++)
        {
            levelUp();
        }
        this.isAlive = true;
        this.HP = this.maxHP;
    }

    @Override
    public void onDeath(CharacterBase killer)
    {
        super.onDeath(killer);
        EventSystem.globalEventSystemSingleton.dispatch(new Event("Hero.death",this.getClass(), this.getName() + " has fallen at the hands of " + killer.getName()));
    }

    @Override
    public void onTakeDamage(CharacterBase attacker)
    {
        ArrayList<ItemBase> equippedItems = this.getEquipedItems();
        for(ItemBase item : equippedItems)
        {
            if(OnTakeDamage.class.isAssignableFrom(item.getClass()))
            {
                ((OnTakeDamage)item).onTakeDamage(this, attacker);
            }
        }
    }

    @Override
    public void onDealDamage(CharacterBase target)
    {
        ArrayList<ItemBase> equippedItems = this.getEquipedItems();
        for(ItemBase item : equippedItems)
        {
            if(OnDealDamage.class.isAssignableFrom(item.getClass()))
            {
                ((OnDealDamage)item).onDealDamage(this, target);
            }
        }
    }

    public void printCharacterSheet()
    {
        ArrayList<ItemBase> equipped = getEquipedItems();
        System.out.println("==== CHARACTER SHEET ====\nName: " + this.name + "\nLevel: " + this.level + "\nHP: " + this.HP + "/" + this.maxHP + "\nMP: " + this.MP + "/" + this.maxMP + "\nXP: " + this.xp + "\nClass: " + this.dungeonBaseClass.getName() + " (" + this.dungeonBaseClass.getClassShortName() + ")");
        System.out.println("== Inventory ==");
        for(ItemBase item : items)
        {
            System.out.print("- " + item.getName());
            if(Equipable.class.isAssignableFrom(item.getClass()))
            {
                if(equipped.stream().filter( equippedItem -> equippedItem.getName().equals(item.getName())).count() > 0)
                {
                    System.out.print(" [Equipped in:" + item.getItemSlot() + " Slot]");
                }
                else
                {
                    System.out.print(" [Equipable]");
                }
            }
            System.out.print("\n");
        }
        System.out.println("====");
        System.out.println("========");        
    }

}
