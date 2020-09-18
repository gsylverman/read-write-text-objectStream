import java.io.Serializable;

public class Location implements Serializable {
    private final long serialVersionUID = 5964081730602145278L;
    private int locationID;
    private String description;

    public Location(int locationID, String description) {
        this.locationID = locationID;
        this.description = description;
    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }
}
