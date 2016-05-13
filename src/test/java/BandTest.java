import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;


public class BandTest {
	private Band testBand = new Band("band name", "band genre");

	@Test 
	public void Band_initializesCorrectly_true() {
		assertTrue(testBand instanceof Band);
	}

	@Test 
	public void getName_returnsNameOfBand_String() {
		assertEquals(testBand.getName(), "band name");
	}

	@Test 
	public void getGenre_returnsGenreOfBand_String() {
		assertEquals(testBand.getGenre(), "band genre");
	}
}