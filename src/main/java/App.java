import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bands/new", (request, response) -> {
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("template", "templates/band-new.vtl");
    	return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands/new", (request, response) -> {
    	String name = request.queryParams("band-name");
    	String genre = request.queryParams("band-genre");
    	Band newBand = new Band(name, genre);
    	newBand.save();
    	response.redirect("/band/" + newBand.getId());
    	return null;
    });

    get("/bands", (request, response) -> {
    	Map<String, Object> model = new HashMap<String, Object>();

    	model.put("bands", Band.all());
    	model.put("template", "templates/bands.vtl");
    	return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/band/:band_id", (request, response) -> {
    	Map<String, Object> model = new HashMap<String, Object>();
    	
    	int band_id = Integer.parseInt(request.params("band_id"));
    	Band currentBand = Band.findById(band_id);
    	model.put("currentBand", currentBand);

    	model.put("bandVenues", currentBand.getVenues());

    	model.put("template", "templates/band.vtl");
    	return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/band/:band_id/edit", (request, response) -> {
    	Map<String, Object> model = new HashMap<String, Object>();

    	int band_id = Integer.parseInt(request.params("band_id"));
    	Band currentBand = Band.findById(band_id);
    	model.put("currentBand", currentBand);

    	model.put("venues", Venue.all());

    	model.put("template", "templates/band-edit.vtl");
    	return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine()); 


    post("/band/:band_id/edit-name", (request, response) -> {
    	int band_id = Integer.parseInt(request.params("band_id"));
    	Band currentBand = Band.findById(band_id);
    	String name = request.queryParams("band-name");
    	currentBand.update("band_name", name);
    	response.redirect(request.url());
    	return null;
    }); 

    post("/band/:band_id/edit-genre", (request, response) -> {
    	int band_id = Integer.parseInt(request.params("band_id"));
    	Band currentBand = Band.findById(band_id);
    	String genre = request.queryParams("band-genre");
    	currentBand.update("band_genre", genre);
    	response.redirect(request.url());
    	return null;
    }); 

    post("/band/:band_id/delete", (request, response) -> {
    	int band_id = Integer.parseInt(request.params("band_id"));
    	Band currentBand = Band.findById(band_id);
    	currentBand.remove();
    	response.redirect("/");
    	return null;
    });

    post("/band/:band_id/add-venue", (request, response) -> {
    	int band_id = Integer.parseInt(request.params("band_id"));
    	Band currentBand = Band.findById(band_id);
    	String[] venuesToAdd = request.queryParamsValues("venue-boxes");
    	for(String venueId : venuesToAdd) {
    		int id = Integer.parseInt(venueId);
    		Venue currentVenue = Venue.findById(id);
    		currentBand.addToVenue(currentVenue);
    	}
    	response.redirect("/band/" + currentBand.getId());
    	return null;
    }); 


    post("/band/:band_id/delete-venue", (request, response) -> {
    	int band_id = Integer.parseInt(request.params("band_id"));
    	Band currentBand = Band.findById(band_id);
    	String[] venuesToRemove = request.queryParamsValues("venue-boxes-delete");
    	for(String venueId : venuesToRemove) {
    		int id = Integer.parseInt(venueId);
    		Venue currentVenue = Venue.findById(id);
    		currentBand.removeVenue(currentVenue);
    	}
    	response.redirect("/bands/" + currentBand.getId());
    	return null;
    });

    get("/venues/new", (request, response) -> {
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("template", "templates/venue-new.vtl");
    	return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/venues/new", (request, response) -> {
    	String venueName = request.queryParams("venue-name");
    	String location = request.queryParams("location");
    	Venue newVenue = new Venue(venueName, location);
    	newVenue.save();
    	response.redirect("/bands");
    	return null;
    });
  }

}