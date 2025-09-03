package com.example.touristguideapi.repository;
import com.example.touristguideapi.model.TouristAttraction;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import tourism.model.TouristAttraction;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
//    attr - arrList
    private ArrayList<TouristAttraction> attractions;

//    constructor - populate arrList
TouristRepository(){
    attractions = new ArrayList<>();

    String[][] data = {
            {"Tivoli", "København", "Forlystelsespark i hjertet af København.", "forlystelser, familie, kultur"},
            {"Nyhavn", "København", "Farverig havnepromenade med restauranter og barer.", "havn, restauranter, historie"},
            {"Den Lille Havfrue", "København", "Berømt statue inspireret af H.C. Andersen.", "statue, kultur, historie"},
            {"ARoS", "Aarhus", "Kunstmuseum i Aarhus med regnbuepanorama.", "kunst, museum, arkitektur"},
            {"Egeskov Slot", "Kværndrup", "Renæssanceslot på Fyn omgivet af voldgrav.", "slot, historie, have"},
            {"Aalborg Zoo", "Aalborg", "Dyrepark med mere end 100 forskellige arter.", "dyr, familie, natur"},
            {"Moesgaard Museum", "Aarhus", "Museum i Aarhus med arkæologi og kulturhistorie.", "museum, historie, arkæologi"},
            {"Kronborg Slot", "Helsingør", "Renæssanceslot i Helsingør, kendt fra Shakespeares Hamlet.", "slot, kultur, historie"},
            {"Odense Zoo", "Odense", "Familievenlig zoologisk have på Fyn.", "dyr, familie, natur"},
            {"Hammershus", "Bornholm", "Nordeuropas største borgruin på Bornholm.", "ruin, historie, arkitektur"},
            {"Grenen", "Skagen", "Danmarks nordligste punkt, hvor to have mødes.", "natur, strand, geografi"},
            {"Legoland", "Billund", "Forlystelsespark i Billund bygget af LEGO-klodser.", "forlystelser, familie, leg"}
    };

    // Loop makes objects and adds to collection
    for (String[] entry : data) {
        TouristAttraction attraction = new TouristAttraction();
        attraction.setName(entry[0]);
        attraction.setCity(entry[1]);
        attraction.setDescription(entry[1]);
        attraction.setTags(entry[1]);
        attractions.add(attraction);
    }
}

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

//    getTags
    public List<String> getTags(){
        List<String> listOfTags = new ArrayList<String>();

        for (TouristAttraction attraction : attractions){
            String attractionCityName = attraction.getTags();
            listOfTags.add(attractionCityName);
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