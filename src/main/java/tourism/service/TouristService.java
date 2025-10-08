package tourism.service;
import org.springframework.stereotype.Service;
import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;
import java.util.List;

@Service
public class TouristService {

    private final TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public List<TouristAttraction> getAttractions() {
        return repository.getAllAttractions();
    }

    public TouristAttraction getByName(String name) {
        TouristAttraction a = repository.getByName(name);
        if (a == null) {
            throw new IllegalArgumentException("Attraction not found: " + name); //placeholder
        }
        return a;
    }

    public List<String> getCities() {
        return repository.listCities();
    }

    public List<String> getTags() {
        return repository.listTags();
    }

    public void addAttraction(TouristAttraction a){
        if (a.getName()==null || a.getName().isBlank()) throw new IllegalArgumentException("Navn mangler");
        if (a.getDescription()==null || a.getDescription().isBlank()) throw new IllegalArgumentException("Beskrivelse mangler");
        repository.insertAttraction(a);
    }

    public void updateAttraction(TouristAttraction a) {
        repository.updateAttraction(a);
    }

    public void deleteAttraction(String name) {
        boolean deleted = repository.deleteAttraction(name);
        if (!deleted) {
            throw new IllegalArgumentException("Attraction not found: " + name);
        }
    }
}
