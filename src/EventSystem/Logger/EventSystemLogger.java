package EventSystem.Logger;

import EventSystem.EventSystem;
import EventSystem.Events.Event;
import EventSystem.Events.InternalEvent;

import java.lang.reflect.Method;

public class EventSystemLogger {

    public static String prefix = "[EventSystemLogger] ";
    public EventSystemLogger(){

    }

    public static void SetupLogger()
    {
        Method callBackWrite = null;
        try {
            callBackWrite = EventSystemLogger.class.getMethod("eventHandler",Event.class);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
            System.out.println(prefix + "Failed to find method: \"WriteEventMessage\" in EventSystemLogger... - Logging disabled");
            return;
        }
        EventSystem.globalEventSystemSingleton.subscribe("Game.*",null,callBackWrite);
        EventSystem.globalEventSystemSingleton.subscribe("CombatSim.*",null,callBackWrite);
        EventSystem.globalEventSystemSingleton.subscribe("Combat.*",null,callBackWrite);
        EventSystem.globalEventSystemSingleton.subscribe("Hero.*",null,callBackWrite);
    }

    public static void eventHandler(Event event)
    {
        //Events er lavet sådan at de altid er "<Event kategori>.<event_type>[.<sub_type>]
        //Så den første (index 0) er altid vores kategori :)
        String eventCategory = event.getName().split("\\.")[0];

        switch (eventCategory)
        {
            /*case "CombatSim":
                writeCombatSimMessages(event);
                break;
             */
            case "Game":
            case "Combat":
            case "CombatSim":
            case "Hero":
                writeEventMsg(event);
                break;
            default:
                System.out.println(prefix + "No handler created for event category: " + eventCategory +";");
                return;
        }
    }

    public static void writeEventMsg(Event event)
    {
        InternalEvent internalEvent = new InternalEvent(event);
        System.out.println("[" + event.getFromClass().getCanonicalName() + "] {" + internalEvent.getEventName() + "} " + event.getEventMsg());
    }

    public static void writeCombatSimMessages(Event event)
    {
        if(event.getName().equals("CombatSim.start"))
        {
            System.out.println("\n\n==== COMBAT SIMULATOR ====");
            System.out.println("Starting combat simulator...");
            return;
        }
        System.out.println(event.getEventMsg());

    }
}
