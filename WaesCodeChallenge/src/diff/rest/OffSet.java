package diff.rest;

/**
 * This is a POJO class used internally in DiffResponse and is part of GET response.
 * 
 * @author Dias, Edison
 */
public class OffSet {

	int begin;
	int end;

	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "=OffSet= \n" +
			   "Begin: " + begin + "\n" +
			   "End: " + end + "\n";		   
	}
	
}
