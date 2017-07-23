package diff.rest;

import java.util.List;

/**
 * This is POJO class is responsible to report the revision comparison thru result in text 
 * and the List of offSet differences, this is sent as GET response
 * 
 * @author Dias, Edison
 */
public class DiffResponse {

	String result;
	List<OffSet> offSet;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<OffSet> getOffSet() {
		return offSet;
	}
	public void setOffSet(List<OffSet> offSet) {
		this.offSet = offSet;
	}
	public DiffResponse() {
	}
	
	
}
