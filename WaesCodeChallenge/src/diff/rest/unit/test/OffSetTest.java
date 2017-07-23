package diff.rest.unit.test;

import org.junit.Assert; 
import org.junit.Test;
import diff.rest.OffSet;

public class OffSetTest {

	@Test
	public void testToString() {
		OffSet offSet = new OffSet();
		offSet.setBegin(10);
		offSet.setEnd(10);
		String testString = "=OffSet= \n" +
				   "Begin: 10\n" +
				   "End: 10\n";
		Assert.assertEquals(offSet.toString().equals(testString),true);
	}

}
