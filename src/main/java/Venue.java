import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Venue {
	private int id;
	private String  venue_name;
	private String location;

	public Venue(String venue_name, String location) {
		this.venue_name = venue_name;
		this.location = location;
	}

	@Override 
	public boolean equals(Object otherVenue) {
		if(!(otherVenue instanceof Venue)) {
			return false;
		} else {
			Venue newVenue = (Venue) otherVenue;
			return newVenue.getName().equals(this.getName()) &&
			newVenue.getLocation().equals(this.getLocation()) &&
			newVenue.getId() == this.getId();
		}
	}

// getters

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.venue_name;
	}

	public String getLocation() {
		return this.location;
	}

// create

	public void save() {
		try(Connection con = DB.sql2o.open()) {
			String sql =  "INSERT INTO venues (venue_name, location) VALUES (:venue_name, :location);";
			this.id = (int) con.createQuery(sql, true)
				.addParameter("venue_name", this.venue_name)
				.addParameter("location", this.location)
				.executeUpdate()
				.getKey();
		}
	}

// read

	public static List<Venue> all() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT id, venue_name, location FROM venues;";
			return con.createQuery(sql)
				.executeAndFetch(Venue.class);
		}
	}

	public static Venue findById(int id) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM venues WHERE id=:id;";
			return con.createQuery(sql)
				.addParameter("id", id)
				.executeAndFetchFirst(Venue.class);
		}
	}

	public static List<Venue> findByLocation(String query) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM venues WHERE location=:location;";
			return con.createQuery(sql)
				.addParameter("location", query)
				.executeAndFetch(Venue.class);

		}
	}

 

}