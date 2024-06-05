package fluff.events;

/**
 * Represents an event that can be cancelled.
 */
public abstract class CancellableEvent extends AbstractEvent {
	
    protected boolean cancelled = false;
	
    /**
     * Checks if the event is cancelled.
     *
     * @return true if the event is cancelled, false otherwise
     */
    public boolean isCancelled() {
        return cancelled;
    }
	
    /**
     * Cancels the event.
     */
    public void cancel() {
        cancelled = true;
    }
}
