package fluff.events;

public abstract class CancellableEvent extends AbstractEvent {
	
	protected boolean cancelled = false;
	
	public boolean isCancelled() {
		return cancelled;
	}
	
	public void cancel() {
		cancelled = true;
	}
}
