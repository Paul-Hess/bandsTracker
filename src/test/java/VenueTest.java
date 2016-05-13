import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;

public class VenueTest {

	@Rule
  public DatabaseRule database = new DatabaseRule();

  private Venue testVenue = new Venue("venue name", "location");

  @Test 
  public void Venue_initializesCorrectly_true() {
  	assertTrue(testVenue instanceof Venue);
  }

  @Test 
  public void getId_returnsId_int() {
  	assertEquals(testVenue.getId(), 0);
  }

  @Test 
  public void getName_returnsName_String() {
  	assertEquals(testVenue.getName(), "venue name");
	} 

  @Test 
  public void getLocation_returnsLocation_String() {
  	assertEquals(testVenue.getLocation(), "location");
  }

  @Test 
  public void equals_returnsTrueIfInstancesAreTheSame_true() {
  	Venue testVenue2 = new Venue("venue name", "location");
  	assertTrue(testVenue2.equals(testVenue));
  }

  @Test 
  public void all_initializesAsEmptyList_List() {
  	assertEquals(Venue.all().size(), 0);
  }

  @Test 
  public void save_savesInstanceOfVenue_true() {
  	testVenue.save();
  	assertEquals(Venue.all().get(0), testVenue);
  }

  @Test 
  public void save_assignsIdToObject_true() {
  	testVenue.save();
  	Venue savedVenue = Venue.all().get(0);
  	assertEquals(savedVenue.getId(), testVenue.getId());
  }

  @Test 
  public void findById_returnsSpecificInstanceOfVenue_Venue() {
  	testVenue.save();
  	assertEquals(Venue.findById(testVenue.getId()), testVenue);
  }

  @Test 
  public void findByLocation_returnsInstanceOfVenueByLocation_Venue() {
  	testVenue.save();
  	assertEquals(Venue.findByLocation("location").get(0).getName(), testVenue.getName());
  }
}