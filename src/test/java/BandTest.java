import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;


public class BandTest {

	@Rule
  public DatabaseRule database = new DatabaseRule();

	private Band testBand = new Band("band name", "band genre");
	private Band testBand2 = new Band("band name", "band genre");

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

	@Test 
	public void getId_returnsIdOFBand_int() {
		assertEquals(testBand.getId(), 0);
	}

	@Test 
	public void equals_returnsTrueIfInstancesAreTheSame_true() {	
		assertTrue(testBand.equals(testBand2));
	}

	@Test 
	public void save_savesInstanceOfBand_true() {
		testBand.save();
		assertTrue(Band.all().get(0).equals(testBand));
	}

	@Test 
	public void all_initializesAsEmptyListOfAllBands_Band() {
		assertEquals(Band.all().size(), 0);
	}
}