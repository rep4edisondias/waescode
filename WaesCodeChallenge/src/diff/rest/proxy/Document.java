package diff.rest.proxy;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import diff.rest.DiffResponse;
import diff.rest.OffSet;
import diff.rest.factory.Revision;

/**
 * Class abstraction for any document (String, Image or File) sent to upload and further comparison.
 * 
 * @author Dias, Edison
 */
public class Document {
	String id;
	Revision revisionRight;
	Revision revisionLeft;
	public Revision getRevisionRight() {
		return revisionRight;
	}
	public void setRevisionRight(Revision revision) {
		this.revisionRight = revision;
	}
	public boolean hasRight(){
		if ((revisionRight!=null) && (revisionRight.getDecodedValue()!=null)) return true;
		return false;
	}
	public Revision getRevisionLeft() {
		return revisionLeft;
	}
	public void setRevisionLeft(Revision revision) {
		this.revisionLeft = revision;
	}
	public boolean hasLeft(){
		if ((revisionLeft!=null) && (revisionLeft.getDecodedValue()!=null)) return true;
		return false; 		
	}
	public Document(String id) {
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	
	/**
	 * Method responsible to compare right and left byte arrays and return the string with result of the comparison.
	 * 
	 * @return
	 */
	public DiffResponse compare(){
		DiffResponse response = new DiffResponse();
		response.setResult("Comparision results: \n");
		response.setOffSet(new ArrayList<OffSet>());
		
		//Start the comparision		
		try {
			//Validate if both values are null
			if (!hasRight() && !hasLeft())
				response.setResult(response.getResult() + "   No valid data was uploaded to be compared \n");
			//Validate if only one of them is null 
			else if (!hasRight() && hasLeft())
				response.setResult(response.getResult() + "   No valid data was uploaded on Right \n");
			else if (hasRight() && !hasLeft())
				response.setResult(response.getResult() + "   No valid data was uploaded on Left \n");
			//Validate if Size is different
			else if (this.revisionLeft.getDecodedBytes().length != this.revisionRight.getDecodedBytes().length){
				response.setResult(response.getResult() + "   Uploaded data have different size \n"
					+ "   Right : " + this.revisionRight.getDecodedBytes().length + " bytes  \n"
					+ "   Left : " + this.revisionLeft.getDecodedBytes().length + " bytes");
			}else {
				//if has same size then perform offSet analysis
				response.setResult(response.getResult() + "   Uploaded data have same size (" + this.revisionRight.getDecodedBytes().length + ")");
				response.setOffSet(compareBytes(this.revisionLeft.getDecodedBytes(),this.revisionRight.getDecodedBytes()));
				if (response.getOffSet().isEmpty()){
					response.setResult(response.getResult() + "Documents are identical");
				}else{
					for (OffSet offSet : response.getOffSet())
					{
						response.setResult(response.getResult() + offSet.toString());
					}
				}
			}
			
		} catch (UnsupportedEncodingException e) {
			response.setResult(response.getResult() + "Error when performing comparison: " + e.getMessage());
		}
		return response;
	}
	
	/**
	 * Method responsible to analyze both right and left byte arrays and find the offSets interval in both left|right revisions, 
	 * return the offSet list loaded.   
	 * 
	 * @param right 
	 * @param left
	 * @return 
	 */
	private List<OffSet> compareBytes(byte[] right,byte[] left){
		List<OffSet> offSetList = new ArrayList<OffSet>();
		int currentOffSet = 0;
		int offSetIndex = 0;
		for (int i=0; i < right.length; i++)
		{
			if (right[i] != left[i]) 
			{
				//when bytes are different then need to check if is offset begin or continuation
				if (i == currentOffSet+1)
				{
					//offset continuation
					offSetList.get(offSetIndex).setEnd(i);
				}else{
					//create a new offSet object in the list, first offset byte
					offSetList.add(new OffSet());
					offSetIndex = offSetList.size()-1;
					offSetList.get(offSetIndex).setBegin(i);	
					offSetList.get(offSetIndex).setEnd(i);
				}
				currentOffSet = i;
			}
		}
		return offSetList;
	}
}
