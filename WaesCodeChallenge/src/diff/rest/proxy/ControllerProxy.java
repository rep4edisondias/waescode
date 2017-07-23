package diff.rest.proxy;

import diff.rest.DiffResponse;
import diff.rest.EncodedRevision;

/**
 * This is the Proxy class, it is instantiated and provide access for API
 * to concrete implementation.
 * 
 * @author Dias, Edison
 */
public class ControllerProxy implements Controller{

	private ControllerImpl controllerImpl;
	
	public ControllerProxy() {
		this.controllerImpl = ControllerImpl.getInstance();
	}
	
	@Override
	public Boolean  createLeft(String id, EncodedRevision left) {
		if (left.getValue()==null) return false;
		return controllerImpl.createDocumentAndRevisionLeft(id, left.getValue());
	}
	
	@Override
	public Boolean createRight(String id, EncodedRevision right) {
		if (right.getValue()==null) return false;
		return controllerImpl.createDocumentAndRevisionRight(id, right.getValue());
	}

	@Override
	public DiffResponse viewDiff(String id) {
		return this.controllerImpl.viewDiff(id);
	}

	@Override
	public Boolean removeDiff(String id) {
		return this.controllerImpl.removeDiff(id);
	}
}
