package diff.rest.factory;

/**
 * Abstract class that defines the method to build new objects
 * 
 * @author Dias, Edison
 */
public abstract class AbstractRevisionFactory {
	public abstract Revision createNewRevision(String value);
}
