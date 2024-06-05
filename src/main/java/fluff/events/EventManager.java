package fluff.events;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fluff.functions.gen.obj.obj.VoidFunc2;

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
			
            List<IEventListener> list;
            if (reg.containsKey(listenerClass)) {
                list = reg.get(listenerClass);
            } else {
                list = new LinkedList<>();
                reg.put(listenerClass, list);
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
     * @param <V> the type of the event listener
     * @param <E> the type of the event
     * @param listenerClass the class of the event listener
     * @param eventFunc the function to invoke on each listener
     * @param event the event to pass to each listener
     * @return the event after being processed by all listeners
     */
    public <V extends IEventListener, E extends AbstractEvent> E call(Class<V> listenerClass, VoidFunc2<V, E> eventFunc, E event) {
        if (!reg.containsKey(listenerClass)) return event;
		
        for (IEventListener obj : reg.get(listenerClass)) {
            V listener = (V) obj;
			
            eventFunc.invoke(listener, event);
        }
		
        return event;
    }
}
