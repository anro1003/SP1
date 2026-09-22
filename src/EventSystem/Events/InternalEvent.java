package EventSystem.Events;


import java.util.Arrays;

//Horrible name Anton...
public class InternalEvent {

    protected String eventCategory;
    protected String eventName;
    protected String eventMsg;

    public InternalEvent(Event event)
    {
        String[] eventParts = event.getName().split("\\.");
        eventCategory = eventParts[0];
        for (int i = 1; i < eventParts.length; i++)
        {
            eventName =  eventParts[i];
            if(i != eventParts.length-1)
            {
                eventName += ".";
            }
        }
        eventMsg = event.getEventMsg();
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventMsg() {
        return eventMsg;
    }

    public String getFullEventName()
    {
        return eventCategory + "." +  eventName;
    }


}
