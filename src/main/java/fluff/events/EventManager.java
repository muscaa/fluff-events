package fluff.events;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fluff.functions.gen.obj.obj.VoidFunc2;

public class EventManager {
	
	private final Map<Class<? extends IEventListener>, List<IEventListener>> reg = new HashMap<>();
	
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
	
	public void unsubscribe(Object obj) {
		for (Class<?> interfaceClass : obj.getClass().getInterfaces()) {
			if (!IEventListener.class.isAssignableFrom(interfaceClass)) continue;
			
			Class<? extends IEventListener> listenerClass = (Class<? extends IEventListener>) interfaceClass;
			if (!reg.containsKey(listenerClass)) continue;
			
			reg.get(listenerClass).remove((IEventListener) obj);
		}
	}
	
	public <V extends IEventListener, E extends AbstractEvent> E call(Class<V> listenerClass, VoidFunc2<V, E> func, E event) {
		if (!reg.containsKey(listenerClass)) return event;
		
		for (IEventListener obj : reg.get(listenerClass)) {
			V listener = (V) obj;
			
			func.invoke(listener, event);
		}
		
		return event;
	}
}
