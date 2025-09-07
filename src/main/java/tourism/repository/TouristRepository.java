package tourism.repository;

import tourism.model.TouristAttraction;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TouristRepository {
//    attr - arrList
    private final ArrayList<TouristAttraction> attractions = new ArrayList<>(List.of(
            new TouristAttraction("Tivoli", "København", "Forlystelsespark i hjertet af København.", List.of("forlystelser", "familie", "kultur")),
            new TouristAttraction("Nyhavn", "København", "Farverig havnepromenade med restauranter og barer.", List.of("havn", "restauranter", "historie")),
            new TouristAttraction("Den Lille Havfrue", "København", "Berømt statue inspireret af H.C. Andersen.", List.of("statue", "kultur", "historie")),
            new TouristAttraction("ARoS", "Aarhus", "Kunstmuseum i Aarhus med regnbuepanorama.", List.of("kunst", "museum", "arkitektur")),
            new TouristAttraction("Egeskov Slot", "Kværndrup", "Renæssanceslot på Fyn omgivet af voldgrav.", List.of("slot", "historie", "have")),
            new TouristAttraction("Aalborg Zoo", "Aalborg", "Dyrepark med mere end 100 forskellige arter.", List.of("dyr", "familie", "natur")),
            new TouristAttraction("Moesgaard Museum", "Aarhus", "Museum i Aarhus med arkæologi og kulturhistorie.", List.of("museum", "historie", "arkæologi")),
            new TouristAttraction("Kronborg Slot", "Helsingør", "Renæssanceslot i Helsingør, kendt fra Shakespeares Hamlet.", List.of("slot", "kultur", "historie")),
            new TouristAttraction("Odense Zoo", "Odense", "Familievenlig zoologisk have på Fyn.", List.of("dyr", "familie", "natur")),
            new TouristAttraction("Hammershus", "Bornholm", "Nordeuropas største borgruin på Bornholm.", List.of("ruin", "historie", "arkitektur")),
            new TouristAttraction("Grenen", "Skagen", "Danmarks nordligste punkt, hvor to have mødes.", List.of("natur", "strand", "geografi")),
            new TouristAttraction("Legoland", "Billund", "Forlystelsespark i Billund bygget af LEGO-klodser.", List.of("forlystelser", "familie", "leg")
            )));




//    getAttractions
    public ArrayList<TouristAttraction> getAllAttractions() {
        return attractions;
    }

//    getOneNamedAttraction
    public TouristAttraction getOneNamedAttraction(String attractionName) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equals(attractionName)) {
                return attraction;
            }
        }
        return null;
    }

//    getAllAttractionsTags
    public List<String> getAllAttractionsTags(){
        List<String> listOfTags = new ArrayList<String>();

        for (TouristAttraction attraction : attractions){
            List<String> attractionTags = attraction.getTags();
            listOfTags.addAll(attractionTags);
        }
        return listOfTags;
    }

//    getCities
    public List<String> getCities(){
        List<String> listOfCities = new ArrayList<String>();

        for (TouristAttraction attraction : attractions){
            String attractionCityName = attraction.getCity();
            listOfCities.add(attractionCityName);
        }
        return listOfCities;
    }

//    addOneNamedAttraction
    public void addOneNamedAttractionToList(TouristAttraction touristAttraction) {
        attractions.add(touristAttraction);
    }

//    updateOneNamedAttraction
    public void updateOneNamedAttraction(TouristAttraction touristAttraction) {
        for (int i = 0; i < attractions.size(); i++) {
            TouristAttraction attraction = attractions.get(i);
            if (attraction.getName().equals(touristAttraction.getName())) {
                attractions.set(i, touristAttraction); // replace old object with updated one
                break; // stop after first match
            }
        }
    }

//    deleteOneNamedAttractionFromList
    public void deleteOneNamedAttractionFromList(String namedAttractionToRemove) {
        attractions.removeIf(namedAttraction -> namedAttraction.getName().equals(namedAttractionToRemove));
    }

}