import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Band {
	private String band_name;
	private String band_genre;

	public Band(String band_name, String band_genre) {
		this.band_name = band_name;
		this.band_genre = band_genre;
	}

}