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

//    addOneNamedAttraction
    public TouristAttraction addOneNamedAttractionToList(TouristAttraction touristAttraction) {
        boolean isAddOpSuccess = attractions.add(touristAttraction);
        return isAddOpSuccess ? touristAttraction : null;
    }

//    updateOneNamedAttraction
    public TouristAttraction updateOneNamedAttraction(int index, TouristAttraction updatedTouristAttraction) {
        return attractions.set(index, updatedTouristAttraction);
    }

//    deleteOneNamedAttractionFromList
    public boolean deleteOneNamedAttractionFromList(String attractionName) {
        return attractions.removeIf(namedAttractionToRemove -> namedAttractionToRemove.getName().equals(attractionName));

    }

}