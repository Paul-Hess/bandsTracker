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
	// read 
	// update
	// delete

}