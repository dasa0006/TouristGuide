package tourism.service;
import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;

import java.util.ArrayList;
import java.util.List;

public class TouristService {
    TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public List<TouristAttraction> getAttractions() {
        return repository.getAllAttractions();
    }

    public TouristAttraction getOneNamedAttraction(String attractionName) {
        for (TouristAttraction attraction : repository.getAllAttractions()) {
            if (attraction.getName().equals(attractionName)) {
                return attraction;
            }
        }
        return null;
    }

    public List<String> getTags(){
        List<String> listOfTags = new ArrayList<String>();

        for (TouristAttraction attraction : repository.getAllAttractions()){
            List<String> attractionTags = attraction.getTags();
            listOfTags.addAll(attractionTags);
        }
        return listOfTags;
    }

    public List<String> getCities(){
        List<String> listOfCities = new ArrayList<String>();

        for (TouristAttraction attraction : repository.getAllAttractions()){
            String attractionCityName = attraction.getCity();
            listOfCities.add(attractionCityName);
        }
        return listOfCities;
    }

    public TouristAttraction addNamedAttraction(TouristAttraction attraction) {
        return repository.addOneNamedAttractionToList(attraction);
    }

    public TouristAttraction updateAttraction(TouristAttraction updatedTouristAttraction) {
        ArrayList<TouristAttraction> attractionsList = repository.getAllAttractions();
        for (int i = 0; i < attractionsList.size(); i++) {
            TouristAttraction oldAttraction = attractionsList.get(i);
            if (oldAttraction.getName().equals(updatedTouristAttraction.getName())) {
                return repository.updateOneNamedAttraction(i, updatedTouristAttraction); // replace old object with updated one
            }
        }
        return null;
    }

    public boolean deleteAttraction(String attractionName) {
        return repository.deleteOneNamedAttractionFromList(attractionName);
    }
}
