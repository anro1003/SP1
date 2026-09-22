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

}
