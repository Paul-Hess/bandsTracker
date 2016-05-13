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
}