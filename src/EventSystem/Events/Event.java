package EventSystem.Events;

public class Event {

    protected String name;
    protected Class fromClass;
    protected String eventMsg;
    protected String fromClassName;

    public Event(String name, Class from)
    {
        this.name = name;
        this.fromClass = from;
        this.eventMsg = "";
    }

    /*public Event(String name, Object from)
    {
        this.name = name;
        this.fromClass = from.getClass();
        this.eventMsg = "";
    }*/

    public Event(String name, Class from, String eventMsg)
    {
        this.name = name;
        this.fromClass = from;
        this.eventMsg = eventMsg;
    }
    /*public Event(String name, Object from, String eventMsg)
    {
        this.name = name;
        this.fromClass = from.getClass();
        this.eventMsg = eventMsg;
    }*/

    public String getName()
    {
        return name;
    }

    public Class getFromClass()
    {
        return fromClass;
    }

    public String getEventMsg()
    {
        return eventMsg;
    }
}
