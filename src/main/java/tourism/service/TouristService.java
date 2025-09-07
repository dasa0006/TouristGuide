package tourism.service;
import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;
import java.util.List;

public class TouristService {
    TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public List<TouristAttraction> getAttractions() {
        return repository.getAllAttractions();
    }

    public TouristAttraction getOneNamedAttraction(String name) {
        return repository.getOneNamedAttraction(name);
    }

    public List<String> getTagsFromOneNamedAttraction(String attractionName) {
        for (TouristAttraction attraction : repository.getAllAttractions()) {
            if (attraction.getName().equalsIgnoreCase(attractionName)) {
                return attraction.getTags();
            }
        }
        return null;
    }

    public List<String> getTags() {
        return repository.getAllAttractionsTags();
    }

    public List<String> getCities() {
        return repository.getCities();
    }

    public TouristAttraction addNamedAttraction(TouristAttraction attraction) {
        return repository.addOneNamedAttractionToList(attraction);
    }

    public TouristAttraction updateAttraction(TouristAttraction attraction) {
         return repository.updateOneNamedAttraction(attraction);
    }

    public boolean deleteAttraction(String name) {
        repository.deleteOneNamedAttractionFromList(name);
    }
}
