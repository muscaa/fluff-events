package fluff.events;

@FunctionalInterface
public interface IEventCaller<L extends IEventListener, E extends IEvent> {
	
	void call(L listener, E event);
	
	@FunctionalInterface
	interface Status<L extends IEventListener, E extends IEvent> {
		
		void call(L listener, E event, EventStatus status);
	}
	
	@FunctionalInterface
	interface Stage<L extends IEventListener, E extends IEvent> {
		
		void call(L listener, E event, EventStage stage);
	}
	
	@FunctionalInterface
	interface StatusStage<L extends IEventListener, E extends IEvent> {
		
		void call(L listener, E event, EventStatus status, EventStage stage);
	}
}
