package tourism.model;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String city;
    private String description;
    private List<String> tags;

    public TouristAttraction(){}

    public TouristAttraction(String name, String city, String description, List<String> tags) {
        this.name = name;
        this.city = city;
        this.description = description;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
