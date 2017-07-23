package diff.rest.proxy;

import diff.rest.DiffResponse;
import diff.rest.EncodedRevision;

/**
 * Interface used by ControllerProxy
 * 
 * @author Dias, Edison
 */
public interface Controller {
	
	public Boolean  createLeft(String id, EncodedRevision left);
	
	public Boolean createRight(String id, EncodedRevision right);

	public DiffResponse viewDiff(String id);
	
	public Boolean removeDiff(String id);
	
	public static Controller getInstance() {
		return new ControllerProxy();
	}
	
}
