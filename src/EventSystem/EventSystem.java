package EventSystem;

import EventSystem.Events.Event;
import EventSystem.Events.InternalEvent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class EventSystem {
    private HashMap<String, ArrayList<Subscriber>> dispatchMap;
    public static EventSystem globalEventSystemSingleton = new EventSystem();
    private EventSystem()
    {
        dispatchMap =  new HashMap<>();
    }

    //Bare for at gøre det hurtigere at skrive xD
    public static void quickDispatch(Event event)
    {
        globalEventSystemSingleton.dispatch(event);
    }

    public void dispatch(Event e)
    {
        //Siden at det ville være pænt åndsvagt at skrive "Game.err", "Game.log", "Game.info", osv...
        //Bruger jeg "*" til at indikere at jeg vil have ALLE events med categorien, så fx
        //"Game.*" subsciber til "Game.log" men også til "Game.info" og "Game.err"
        try{
            InternalEvent internalEvent = new InternalEvent(e);
            ArrayList<Subscriber> subscribers = getSubscribers(internalEvent);
            for(Subscriber subscriber : subscribers)
            {
                subscriber.invoke(e);
            }
        }
        catch (RuntimeException ex)
        {
            System.out.println("Failed to dispatch event: " + e.getName() + " - No key found, assuming no subscribers");
        }
    }

    public Subscriber subscribe(String eventName, Object subscriberInstance, Method invokationMethod)
    {
        if(!dispatchMap.containsKey(eventName))
        {
            dispatchMap.put(eventName, new ArrayList<>());
        }

        Subscriber subscriber = new Subscriber(dispatchMap.get(eventName),subscriberInstance,invokationMethod,eventName);
        dispatchMap.get(eventName).add(subscriber);
        return subscriber;
    }

    public void purgeEvent(String eventName)
    {
        if(dispatchMap.containsKey(eventName))
        {
            for (Subscriber subscriber : getSubscribers(eventName))
            {
                subscriber.unsubscribe();
            }
        }
    }

    public ArrayList<Subscriber> getSubscribers(String eventName) throws RuntimeException
    {
        if(!dispatchMap.containsKey(eventName))
        {
            throw new RuntimeException("No subscribers registered for event " + eventName);
        }
        return dispatchMap.get(eventName);
    }
    public ArrayList<Subscriber> getSubscribers(InternalEvent event) throws RuntimeException
    {
        ArrayList<Subscriber> subscribers = new ArrayList<>();
        String starKey = event.getEventCategory() + ".*";
        if(dispatchMap.containsKey(starKey)) //Check for the star operator
        {
            subscribers.addAll(dispatchMap.get(starKey));
            if(dispatchMap.containsKey(event.getEventName()))
            {
                subscribers.addAll(dispatchMap.get(event.getFullEventName()));
            }
        }
        return subscribers;
    }

}
