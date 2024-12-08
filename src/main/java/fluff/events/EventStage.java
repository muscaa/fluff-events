package fluff.events;

/**
 * Represents the stage of an event.
 */
public class EventStage {
	
	public static final EventStage PRE = new EventStage(0, "PRE");
	public static final EventStage EXECUTION = new EventStage(1, "EXECUTION");
	public static final EventStage POST = new EventStage(2, "POST");
	
	private final int id;
	private final String name;
	
	/**
	 * Constructs a new EventStage with the specified id and name.
	 * 
	 * @param id the event stage id
	 * @param name the event stage name
	 */
	public EventStage(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Gets the id of the event stage.
	 * 
	 * @return the id of the event stage
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Gets the name of the event stage.
	 * 
	 * @return the name of the event stage
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof EventStage es && es.id == id;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
}
