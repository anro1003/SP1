import Characters.Hero;
import CombatSim.CombatSimulator;
import DungeonClasses.*;
import EventSystem.EventSystem;
import EventSystem.Events.Event;
import EventSystem.Logger.EventSystemLogger;
import Items.Armors.FrostArmor;
import Items.Armors.GenericArmorType;
import Items.Armors.GenericArmors;
import Items.Bases.ItemSlot;

public class Main {
    public static void main(String[] args)
    {
        EventSystemLogger.SetupLogger();
        EventSystem.globalEventSystemSingleton.dispatch(new Event("Game.log",Main.class,"Logger starting!"));


        Hero warriorHero = new Hero("Ragnar",8,new WarriorClass());
        Hero assasinHero = new Hero("Lokus",7,new AssassinClass());

        warriorHero.addToInventory(GenericArmors.getGenericArmor(GenericArmorType.LEATHER,ItemSlot.FEET),true);
        assasinHero.addToInventory(GenericArmors.getGenericArmor(GenericArmorType.LEATHER, ItemSlot.HEAD),true);
        assasinHero.addToInventory(new FrostArmor(),true);

        CombatSimulator combatSimulator = new CombatSimulator();
        combatSimulator.oneOnOne(warriorHero,assasinHero);


    }
}
