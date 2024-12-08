package fluff.events;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Manages event listeners and allows subscribing, unsubscribing, and calling events.
 */
public class EventManager {
	
    private final Map<Class<? extends IEventListener>, List<IEventListener>> reg = new HashMap<>();
	
    /**
     * Subscribes an object to the event manager. The object must implement one or more interfaces
     * that extend {@link IEventListener}.
     *
     * @param obj the object to subscribe
     */
    public void subscribe(Object obj) {
        for (Class<?> interfaceClass : obj.getClass().getInterfaces()) {
            if (!IEventListener.class.isAssignableFrom(interfaceClass)) continue;
			
            Class<? extends IEventListener> listenerClass = (Class<? extends IEventListener>) interfaceClass;
			
            List<IEventListener> list = reg.get(listenerClass);
            if (list == null) {
                reg.put(listenerClass, list = new LinkedList<>());
            }
			
            list.add((IEventListener) obj);
        }
    }
	
    /**
     * Unsubscribes an object from the event manager. The object must have been previously subscribed.
     *
     * @param obj the object to unsubscribe
     */
    public void unsubscribe(Object obj) {
        for (Class<?> interfaceClass : obj.getClass().getInterfaces()) {
            if (!IEventListener.class.isAssignableFrom(interfaceClass)) continue;
			
            Class<? extends IEventListener> listenerClass = (Class<? extends IEventListener>) interfaceClass;
            if (!reg.containsKey(listenerClass)) continue;
			
            reg.get(listenerClass).remove((IEventListener) obj);
        }
    }
	
    /**
     * Calls an event on all subscribed listeners of the specified type.
     *
     * @param <L> the type of the event listener
     * @param <E> the type of the event
     * @param listenerClass the class of the event listener
     * @param caller the function to invoke on each listener
     * @param event the event
     * @return the event after being processed by all listeners
     */
    public <L extends IEventListener, E extends IEvent> E call(Class<L> listenerClass, IEventCaller<L, E> caller, E event) {
        if (!reg.containsKey(listenerClass)) return event;
		
        for (IEventListener obj : reg.get(listenerClass)) {
            L listener = (L) obj;
			
            caller.call(listener, event);
        }
		
        return event;
    }
    
    /**
     * Calls an event with a status on all subscribed listeners of the specified type.
     *
     * @param <L> the type of the event listener
     * @param <E> the type of the event
     * @param listenerClass the class of the event listener
     * @param caller the function to invoke on each listener
     * @param event the event
     * @param status the event status
     * @return the event after being processed by all listeners
     */
    public <L extends IEventListener, E extends IEvent> E call(Class<L> listenerClass, IEventCaller.Status<L, E> caller, E event, EventStatus status) {
        if (!reg.containsKey(listenerClass)) return event;
		
        for (IEventListener obj : reg.get(listenerClass)) {
            L listener = (L) obj;
			
            caller.call(listener, event, status);
        }
		
        return event;
    }
    
    /**
     * Calls an event with a stage on all subscribed listeners of the specified type.
     *
     * @param <L> the type of the event listener
     * @param <E> the type of the event
     * @param listenerClass the class of the event listener
     * @param caller the function to invoke on each listener
     * @param event the event
     * @param stage the event stage
     * @return the event after being processed by all listeners
     */
    public <L extends IEventListener, E extends IEvent> E call(Class<L> listenerClass, IEventCaller.Stage<L, E> caller, E event, EventStage stage) {
        if (!reg.containsKey(listenerClass)) return event;
		
        for (IEventListener obj : reg.get(listenerClass)) {
            L listener = (L) obj;
			
            caller.call(listener, event, stage);
        }
		
        return event;
    }
    
    /**
     * Calls an event with a status and stage on all subscribed listeners of the specified type.
     *
     * @param <L> the type of the event listener
     * @param <E> the type of the event
     * @param listenerClass the class of the event listener
     * @param caller the function to invoke on each listener
     * @param event the event
     * @param status the event status
     * @param stage the event stage
     * @return the event after being processed by all listeners
     */
    public <L extends IEventListener, E extends IEvent> E call(Class<L> listenerClass, IEventCaller.StatusStage<L, E> caller, E event, EventStatus status, EventStage stage) {
        if (!reg.containsKey(listenerClass)) return event;
		
        for (IEventListener obj : reg.get(listenerClass)) {
            L listener = (L) obj;
			
            caller.call(listener, event, status, stage);
        }
		
        return event;
    }
}
