package diff.rest.unit.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DocumentTest.class, ControllerImplTest.class,
		OffSetTest.class, RestServiceTest.class, DecodedRevisionTest.class})
public class AllTests {

}
