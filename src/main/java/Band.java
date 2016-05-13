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
	// read 

	public static List<Band> all() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT id, band_name, band_genre FROM bands;";
			return con.createQuery(sql)
				.executeAndFetch(Band.class);
		}
	}
	// update
	// delete

}