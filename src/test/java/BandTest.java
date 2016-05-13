import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;


public class BandTest {
	private Band testBand = new Band("band name", "band genre");

	@Test 
	public void Band_initializesCorrectly_true() {
		assertTrue(testBand instanceof Band);
	}
}