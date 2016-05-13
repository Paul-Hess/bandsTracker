import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;


public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  private Band testBand = new Band("band name", "genre");

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Band Tracker");
    assertThat(pageSource()).contains("Do you know where your bands are?");
  }
  // test routes
  @Test 
  public void createBandTestRoute() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a band"));
    assertThat(pageSource()).contains("Add your band here: ");
  }


  @Test 
  public void allBandsRoute() {
    goTo("http://localhost:4567/");
    click("a", withText("Browse bands"));
    assertThat(pageSource()).contains("Browse all bands");
  }

  @Test public void createVenueRoute() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a venue"));
    assertThat(pageSource()).contains("Add your venue here: ");
  }

  @Test 
  public void dynamicBandRoute() {
    testBand.save();
    String url = String.format("http://localhost:4567/band/%d", testBand.getId());
    goTo(url);
    assertThat(pageSource()).contains("band name");
  }

  @Test 
  public void editBandRoute() {
    testBand.save();
    String url = String.format("http://localhost:4567/band/%d", testBand.getId());
    goTo(url);
    click("a", withText("edit this band"));
    assertThat(pageSource()).contains("Edit band name details: ");
  }

  @Test 
  public void allBandsLinkToDynamicRoute() {
    testBand.save();
    goTo("http://localhost:4567/bands");
    click("a", withText("band name"));
    assertThat(pageSource()).contains("genre");  
  }


// test actions
  @Test 
  public void bandIsCreated() {
    goTo("http://localhost:4567/bands/new");
    fill("#band-name").with("bandName");
    fill("#band-genre").with("bandGenre");
    submit("#create-band");
    assertThat(pageSource()).contains("bandName");
  }

  @Test 
  public void venueIsCreated() {
    testBand.save();
    goTo("http://localhost:4567/venues/new");
    fill("#venue-name").with("venueName");
    fill("#location").with("location");
    submit("#create-venue");
    goTo("http://localhost:4567/band/" + testBand.getId() + "/edit");
    assertThat(pageSource()).contains("venueName");
  }

  @Test 
  public void allBandsAreListed() {
    testBand.save();
    goTo("http://localhost:4567");
    click("a", withText("Browse bands"));
    assertThat(pageSource()).contains("band name");  
  }

  @Test 
  public void updatesBandName() {
    testBand.save();
    String url = String.format("http://localhost:4567/band/%d/edit", testBand.getId());
    goTo(url);
    fill("#band-name").with("new name");
    submit("#edit-name");
    goTo("http://localhost:4567/band/" + testBand.getId());
    assertThat(pageSource()).contains("new name");
  }

  @Test 
  public void updatesBandGenre() {
    testBand.save();
    String url = String.format("http://localhost:4567/band/%d/edit", testBand.getId());
    goTo(url);
    fill("#band-genre").with("new genre");
    submit("#edit-genre");
    goTo("http://localhost:4567/band/" + testBand.getId());
    assertThat(pageSource()).contains("new genre");
  }

  @Test
  public void deletesBand() {
    testBand.save();
    String url = String.format("http://localhost:4567/band/%d/edit", testBand.getId());
    goTo(url);
    submit("#delete-band");
    goTo("http://localhost:4567/bands");
    assertThat(pageSource()).doesNotContain("band name");
  }

  @Test 
  public void addsVenueToBand() {
    testBand.save();
    Venue testVenue = new Venue("venue name", "location");
    testVenue.save();
    String url = String.format("http://localhost:4567/band/%d/edit", testBand.getId());
    goTo(url);
    find("#venue-name").click();
    submit("#add-band-venue");
    assertThat(pageSource()).contains("venue name");
  }

  @Test 
  public void removesVenueFromBand() {
    testBand.save();
    Venue testVenue = new Venue("venue name", "location");
    testVenue.save();
    testBand.addToVenue(testVenue);
    String url = String.format("http://localhost:4567/band/%d/edit", testBand.getId());
    find("#venue-name-delete");
    submit("#remove-venue");
    assertThat(pageSource()).doesNotContain("venue name");
  }
}
