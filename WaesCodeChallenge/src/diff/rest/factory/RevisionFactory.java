package diff.rest.factory;

/**
 * Class responsible to build new DecodeRevison objects.
 * 
 * @author Dias, Edison
 */
public class RevisionFactory extends AbstractRevisionFactory {

	/* 
	 * This method returns a new DecodedRevision
	 */
	@Override
	public Revision createNewRevision(String value) {
		return new DecodedRevision(value);
	}

}
