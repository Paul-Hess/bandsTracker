import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;


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
	public void save_assignsIdToObject_true() {
		testBand.save();
		Band savedBand = Band.all().get(0);
		assertEquals(savedBand.getId(), testBand.getId());
	}
	@Test 
	public void all_initializesAsEmptyListOfAllBands_Band() {
		assertEquals(Band.all().size(), 0);
	}

	@Test 
	public void findById_returnsInstanceOfBand_Band() {
		testBand.save();
		assertEquals(Band.findById(testBand.getId()), testBand);
	}

	@Test 
	public void findByGenre_returnsListOfBandsByGenre_List() {
		Band testBand3 = new Band("rocka", "rolla");
		testBand3.save();
		Band testBand4 = new Band("not it", "genre4");
		testBand4.save();
		List<Band> testSearch = Band.findByGenre("rol");
		assertEquals(testSearch.get(0), testBand3);
		assertEquals(testSearch.size(), 1);
	}

	@Test 
	public void findByName_returnsListOfBandsByName_List() {
		Band testBand3 = new Band("rocka", "rolla");
		testBand3.save();
		Band testBand4 = new Band("not it", "genre4");
		testBand4.save();
		List<Band> testSearch = Band.findByName("roc");
		assertEquals(testSearch.get(0), testBand3);
		assertEquals(testSearch.size(), 1);
	}

	@Test 
	public void update_setsNewValueForName_String() {
		testBand.save();
		testBand.update("band_name", "new name");
		Band updatedBand = Band.findById(testBand.getId());
		assertEquals(updatedBand.getName(), "new name");
	}

	@Test 
	public void update_setsNewValueForGenre_String() {
		testBand.save();
		testBand.update("band_genre", "new genre");
		Band updatedBand = Band.findById(testBand.getId());
		assertEquals(updatedBand.getGenre(), "new genre");
	}

	@Test 
	public void remove_deletesInstanceOfBand_0() {	
		testBand.save();
		Venue testVenue = new Venue("venue name", "location");
		testVenue.save();
		testBand.addToVenue(testVenue);
		testBand.remove();
		assertEquals(Band.all().size(), 0);
		assertEquals(testBand.getVenues().size(), 0);
	}


	@Test 
	public void addToVenue_addInstanceAssociationWithVenue_bands_venues() {
		testBand.save();
		Venue testVenue = new Venue("venue name", "location");
		testVenue.save();
		testBand.addToVenue(testVenue);
		assertEquals(testBand.getVenues().get(0), testVenue);
	}

	@Test 
	public void getVenues_returnsListOfAsociatedVenues_List() {
		testBand.save();
		Venue testVenue = new Venue("venue name", "location");
		testVenue.save();
		testBand.addToVenue(testVenue);
		assertEquals(testBand.getVenues().size(), 1);
	}

	@Test 
	public void removeVenue_removesVenueAssociation_0() {
		testBand.save();
		Venue testVenue = new Venue("venue name", "location");
		testVenue.save();
		testBand.addToVenue(testVenue);
		testBand.removeVenue(testVenue);
		assertEquals(testBand.getVenues().size(), 0);
	}
}