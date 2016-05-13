import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Venue {
	private int id;
	private String  venue_name;

	public Venue(String venue_name) {
		this.venue_name = venue_name;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.venue_name;
	}
}