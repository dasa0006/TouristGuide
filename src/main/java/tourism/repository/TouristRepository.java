package com.example.touristguideapi.repository;
import com.example.touristguideapi.model.TouristAttraction;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final ArrayList<TouristAttraction> data = new ArrayList<>();

    public TouristRepository() {
        data.add(new TouristAttraction("Tivoli", "Forlystelspark midt i KBH, og DK's mest besøgte turistattraktion med 4m årlige besøg.", "cph"));
        data.add(new TouristAttraction("Strøget", "Berømt og livlig gågade i indre by.", "cph"));
        data.add(new TouristAttraction("Eiffeltårnet", "Populært turistmål, opført i 1889 på 312m, og var verdens højeste bygning over 40 år.", "paris"));
        data.add(new TouristAttraction("Louvre", "Enormt museum i Paris, med en samling på 38000+ genstande fra fortiden til det 21. århundrede, som fx Mona Lisa.", "paris"));
    }

    public ArrayList<TouristAttraction> getAllAttractions() {
        return data;
    }

    public List<TouristAttraction> getByName(String name) {
        List<TouristAttraction> result = new ArrayList<>();
        for (TouristAttraction a : data) {
            if (a.getName().equalsIgnoreCase(name)) {
                result.add(a);
            }
        }
        return result; //
    }

    public List<TouristAttraction> getByCity(String city) { //metode find attraktion med by. fx attractions/cph > "by" = cph
        List<TouristAttraction> result = new ArrayList<>(); //opretter ny arraylist vi bruger til at gemme de specifikke attractions med den by der er søgt >>>
        for (TouristAttraction a : data) { //iterer igennem vores arraylist "data" som holder alle vores attraktioner
            if (a.getCity().equalsIgnoreCase(city)) { // hvis på det index for loopet er på, at a.getcity er det samme som den der er søgt efter
                result.add(a); // så tilføjes den attraktion til den nye liste
            }
        }
        return result; // og vi returnerer den nye liste
    }

    public TouristAttraction createAttraction(TouristAttraction attraction) {
        data.add(attraction);
        return attraction;
    }

    // Nedenstående metode
    public TouristAttraction updateAttraction(String name, TouristAttraction updatedAttraction) {
        for (TouristAttraction a : data) {
            if (a.getName().equalsIgnoreCase(name)) {
                a.setName(updatedAttraction.getName());
                a.setDescription(updatedAttraction.getDescription());
                a.setCity(updatedAttraction.getCity());
                return a; // returnér den opdaterede
            }
        }
        return null; // hvis ikke fundet
    }

    public boolean deleteAttraction(String name) { // vi bruger {name} til at finde den specifikke attraktion vi vil fjerne
        for (int i = 0; i < data.size(); i++) { // iterier igenne vores array med attraktion objekter (data)
            TouristAttraction a = data.get(i); // TA objekt a sættes på data.get(i)
            if (a.getName().equalsIgnoreCase(name)) { // hvis, a.getName = name (fra parameter), altså den name vi vil fjerne
                data.remove(a); // så fjernes det objekt (a)
                return true;
            }
        }
        return false; // hvis ikke det kunne findes, return false
    }
}