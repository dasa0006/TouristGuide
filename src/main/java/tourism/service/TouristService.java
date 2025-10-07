package tourism.service;
import org.springframework.stereotype.Service;
import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;

import java.util.ArrayList;
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



    /*** Retrieves a list of all tags associated with the tourist attractions.
     ** @return a list of tags from all attractions*/
    public List<String> getTags() {
        List<String> listOfTags = new ArrayList<>();
        for (TouristAttraction attraction : repository.getAllAttractions()) {
            if (attraction.getTags() != null) {   // avoid NPE
                listOfTags.addAll(attraction.getTags());
            }
        }
        return listOfTags.stream().distinct().toList();
    }

    /**
     * Retrieves a list of all cities where the tourist attractions are located.
     *
     * @return a list of city names from all attractions
     */
    public List<String> getCities() {
        List<String> listOfCities = new ArrayList<>();
        for (TouristAttraction attraction : repository.getAllAttractions()) {
            listOfCities.add(attraction.getCity());
        }
        return listOfCities.stream().distinct().toList();
    }

    /**
     * Adds a new tourist attraction to the repository.
     *
     * @param attraction the {@link TouristAttraction} to add
     * @return the added attraction if successful, otherwise {@code null}
     */
    public TouristAttraction addNamedAttraction(TouristAttraction attraction) {
        return repository.addOneNamedAttractionToList(attraction);
    }

    /**
     * Updates an existing tourist attraction by matching its name.
     *
     * @param updatedTouristAttraction the updated {@link TouristAttraction} object
     * @return the previous attraction that was replaced, or {@code null} if no match was found
     */
    public TouristAttraction updateAttraction(TouristAttraction updatedTouristAttraction) {
        ArrayList<TouristAttraction> attractionsList = (ArrayList<TouristAttraction>) repository.getAllAttractions();
        for (int i = 0; i < attractionsList.size(); i++) {
            TouristAttraction oldAttraction = attractionsList.get(i);
            if (oldAttraction.getName().equals(updatedTouristAttraction.getName())) {
                return repository.updateOneNamedAttraction(i, updatedTouristAttraction);
            }
        }
        return null;
    }

    /**
     * Deletes a tourist attraction by its name.
     *
     * @param attractionName the name of the attraction to delete
     * @return {@code true} if the attraction was successfully deleted, {@code false} otherwise
     */
    public boolean deleteAttraction(String attractionName) {
        return repository.deleteOneNamedAttractionFromList(attractionName);
    }
}
