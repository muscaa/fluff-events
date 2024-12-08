package fluff.events;

/**
 * Represents the status of an event.
 */
public class EventStatus {
	
    private boolean cancelled = false;
	
    /**
     * Checks if the event is cancelled.
     *
     * @return true if the event is cancelled, false otherwise
     */
    public boolean isCancelled() {
        return cancelled;
    }
    
    /**
     * Sets the cancel status of the event.
     * 
     * @param cancelled the cancel status
     */
    public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
    /**
     * Cancels the event.
     */
    public void cancel() {
        setCancelled(true);
    }
}
