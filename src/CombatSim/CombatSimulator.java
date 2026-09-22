package CombatSim;

import Characters.CharacterBase;
import EventSystem.EventSystem;
import EventSystem.Events.Event;

public class CombatSimulator {

    CharacterBase currentFighter1 = null;
    CharacterBase currentFighter2 = null;
    public CombatSimulator()
    {

    }

    public void oneOnOne(CharacterBase fighter1, CharacterBase fighter2)
    {
        currentFighter1 = fighter1;
        currentFighter2 = fighter2;
        combatLoop();
    }



    protected void combatLoop()
    {
        EventSystem.globalEventSystemSingleton.dispatch(new Event("CombatSim.start",this.getClass()));
        EventSystem.globalEventSystemSingleton.dispatch(new Event("CombatSim.begin",this.getClass(),"==== " + currentFighter1.getName() + "(" + currentFighter1.getDungeonClass().getName() + ") VS. " +  currentFighter2.getName() + "(" + currentFighter2.getDungeonClass().getName() + ") ====" ));

        CharacterBase starter = null;

        if(Math.random() < 0.5)
        {
            starter = currentFighter1;
        }
        else
        {
            //Reorder the fighters
            starter = currentFighter2;
            currentFighter2 = currentFighter1;
            currentFighter1 = starter;
        }
        EventSystem.quickDispatch(new Event("CombatSim.begin.coinflip",this.getClass(),starter.getName()+" won the coinflip, and will be starting!"));


        int roundCounter = 1;
        while(currentFighter1.isAlive() && currentFighter2.isAlive())
        {
            EventSystem.quickDispatch(new Event("CombatSim.newround",this.getClass(),"==== ROUND " +  roundCounter + " ====" ));
            currentFighter1.startAttack(currentFighter2);
            currentFighter1.dealDamage(currentFighter2);
            if(currentFighter2.isAlive())
            {
                currentFighter2.startAttack(currentFighter1);
                currentFighter2.dealDamage(currentFighter1);
            }
            roundCounter++;

        }

        String winner = currentFighter1.isAlive() ? currentFighter1.getName() : currentFighter2.getName();
        EventSystem.quickDispatch(new Event("CombatSim.end",this.getClass(),"\n========\nThe winner is " + winner + "!!!!!"));

    }


}
