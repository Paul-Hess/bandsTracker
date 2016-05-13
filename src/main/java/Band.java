import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Band {
	private int id;
	private String band_name;
	private String band_genre;

	public Band(String band_name, String band_genre) {
		this.band_name = band_name;
		this.band_genre = band_genre;
	}

	@Override 
	public boolean equals(Object otherBand) {
		if(!(otherBand instanceof Band)) {
			return false;
		} else {
			Band newBand = (Band) otherBand;
			return newBand.getName().equals(this.getName()) &&
			newBand.getGenre().equals(this.getGenre()) &&
			newBand.getId() == this.getId();
		}
	}

	// getters

	public String getName() {
		return this.band_name;
	}

	public String getGenre() {
		return this.band_genre;
	}

	public int getId() {
		return this.id;
	}

	// create

	public void save() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO bands (band_name, band_genre) VALUES (:band_name, :band_genre);";
			this.id = (int) con.createQuery(sql, true)
				.addParameter("band_name", this.band_name)
				.addParameter("band_genre", this.band_genre)
				.executeUpdate()
				.getKey();
		}
	} 

	public void addToVenue(Venue newVenue) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id);";
			con.createQuery(sql)
				.addParameter("band_id", this.id)
				.addParameter("venue_id", newVenue.getId())
				.executeUpdate();
		}
	}

	// read 

	public static List<Band> all() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT id, band_name, band_genre FROM bands;";
			return con.createQuery(sql)
				.executeAndFetch(Band.class);
		}
	}

	public static Band findById(int id) {
		try(Connection con = 	DB.sql2o.open()) {
			String sql = "SELECT * FROM bands WHERE id=:id;";
			return con.createQuery(sql)
				.addParameter("id", id)
				.executeAndFetchFirst(Band.class);
		}
	}

	public static List<Band> findByParameter(String searchParam, String query) {
		try(Connection con = 	DB.sql2o.open()) {
			String sql = "";
			if(searchParam == "band_name") {
				sql = "SELECT * FROM bands WHERE band_name LIKE :query;";
			} else if (searchParam == "band_genre") {
				sql = "SELECT * FROM bands WHERE band_genre LIKE :query;";
			} else {
				sql = sql;
			}
			return con.createQuery(sql)
				.addParameter("query", "%" + query + "%" )
				.executeAndFetch(Band.class);
		}
	}

	public List<Venue> getVenues() {
		try(Connection con = DB.sql2o.open()) {
			String joinQuery = "SELECT venues.* FROM bands JOIN bands_venues ON (bands.id = bands_venues.band_id) JOIN venues ON (bands_venues.venue_id = venues.id) WHERE bands.id=:id;";
			return con.createQuery(joinQuery)
				.addParameter("id", this.id)
				.executeAndFetch(Venue.class);
		}
	}

	// update

	public void update(String updateParam, String newValue) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "";
			if(updateParam == "band_name") {
				sql = "UPDATE bands SET band_name=:new_value WHERE id=:id;";
			} else if (updateParam == "band_genre") {
				sql = "UPDATE bands SET band_genre=:new_value WHERE id=:id;";
			} else {
				sql = sql;
			}
			con.createQuery(sql)
				.addParameter("id", this.id)
				.addParameter("new_value", newValue)
				.executeUpdate();
		}
	}

	// delete

	public void remove() {
		try(Connection con = DB.sql2o.open()) {
			String deleteJoinQuery = "DELETE FROM bands_venues WHERE band_id=:id;";
			con.createQuery(deleteJoinQuery)
				.addParameter("id", this.id)
				.executeUpdate();
		}
		try(Connection con = DB.sql2o.open()) {
			String deleteQuery = "DELETE FROM bands WHERE id=:id;";
			con.createQuery(deleteQuery)
				.addParameter("id", this.id)
				.executeUpdate();
		}
	}

}