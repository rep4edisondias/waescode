package diff.rest;

/**
 * This is the JSON POJO class received in RestService methods 
 * uploadRevisionLeft and uploadRevision
 * 
 * @author Dias, Edison
 */
public class EncodedRevision {

	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public EncodedRevision() {
	}
}
