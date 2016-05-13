import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Venue {
	private int id;
	private String  venue_name;

	public Venue(String venue_name) {
		this.venue_name = venue_name;
	}

	@Override 
	public boolean equals(Object otherVenue) {
		if(!(otherVenue instanceof Venue)) {
			return false;
		} else {
			Venue newVenue = (Venue) otherVenue;
			return newVenue.getName().equals(this.getName()) &&
			newVenue.getId() == this.getId();
		}
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.venue_name;
	}

	public static List<Venue> all() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT id, venue_name FROM venues;";
			return con.createQuery(sql)
				.executeAndFetch(Venue.class);
		}
	}

	public void save() {
		try(Connection con = DB.sql2o.open()) {
			String sql =  "INSERT INTO venues (venue_name) VALUES (:venue_name);";
			this.id = (int) con.createQuery(sql, true)
				.addParameter("venue_name", this.venue_name)
				.executeUpdate()
				.getKey();
		}
	}
}