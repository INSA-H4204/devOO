package test;
import Modele.Zone;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZoneTest {

	@Before
	public void setUp() throws Exception {
		Zone zone = new Zone();
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	  @Test
	  public void testAssertEquals() {
	    org.junit.Assert.assertEquals("failure - strings are not equal", "text", "text");
	  }

	  @Test
	  public void testAssertFalse() {
	    org.junit.Assert.assertFalse("failure - should be false", false);
	  }

	  @Test
	  public void testAssertNotNull() {
	    org.junit.Assert.assertNotNull("should not be null", new Object());
	  }

	  @Test
	  public void testAssertNotSame() {
	    org.junit.Assert.assertNotSame("should not be same Object", new Object(), new Object());
	  }

	  @Test
	  public void testAssertNull() {
	    org.junit.Assert.assertNull("should be null", null);
	  }

	  @Test
	  public void testAssertSame() {
	    Integer aNumber = Integer.valueOf(768);
	    org.junit.Assert.assertSame("should be same", aNumber, aNumber);
	  }
}
