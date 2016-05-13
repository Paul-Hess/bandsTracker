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

    // post("/bands/new", (request, response) -> {
    // 	return null;

    // });

    get("/bands", (request, response) -> {
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("template", "templates/bands.vtl");
    	return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/band/:band_id", (request, response) -> {
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("template", "templates/band.vtl");
    	return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/band/:band_id/edit", (request, response) -> {
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("template", "templates/band-edit.vtl");
    	return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine()); 


    post("/band/:band_id/edit", (request, response) -> {
    	return null;
	
    }); 

    post("/band/:band_id/delete", (request, response) -> {
    	return null;

    });

    post("/band/:band_id/add-venue", (request, response) -> {
    	return null;

    }); 


    post("/band/:band_id/delete-venue", (request, response) -> {
    	return null;

    });

    get("/venues/new", (request, response) -> {
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("template", "templates/venue-new.vtl");
    	return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/venues/new", (request, response) -> {
    	return null;

    });
  }

}