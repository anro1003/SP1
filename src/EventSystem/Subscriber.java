package EventSystem;

import EventSystem.Events.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Subscriber {

    private ArrayList<Subscriber> parentList = null;
    private Object subscriberInstance = null;
    private Method methodToCall = null;
    private String subscribedEventName;

    public Subscriber(ArrayList<Subscriber> parent, Object caller, Method method, String event)
    {
        subscriberInstance = caller;
        methodToCall = method;
        subscribedEventName = event;
        this.parentList = parent;
    }

    public void invoke(Event event)
    {
        try {
            methodToCall.invoke(subscriberInstance,event);
        }
        catch (IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
            System.out.println("Failed to invoke method: " + methodToCall.getName() + " on " + subscriberInstance.getClass().getName());
        }
    }

    public void unsubscribe()
    {
        if(parentList != null)
        {
            parentList.remove(this);
        }
    }
}
