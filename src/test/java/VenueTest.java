import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;

public class VenueTest {

	@Rule
  public DatabaseRule database = new DatabaseRule();

  private Venue testVenue = new Venue("venue name");

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
}