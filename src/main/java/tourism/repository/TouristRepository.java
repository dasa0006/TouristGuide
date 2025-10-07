package tourism.repository;
import org.springframework.jdbc.core.JdbcTemplate;
import tourism.model.TouristAttraction;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing a list of tourist attractions.
 * Provides basic CRUD operations on an in-memory list of {@link TouristAttraction} objects.
 */
@Repository
public class TouristRepository {
    private final JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*** In-memory list of tourist attractions initialized with predefined data.*/
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
            new TouristAttraction("Legoland", "Billund", "Forlystelsespark i Billund bygget af LEGO-klodser.", List.of("forlystelser", "familie", "leg"))
    ));

    public java.util.List<TouristAttraction> getAllAttractions() {
        String sql = """
    SELECT ta.name, c.name AS city, ta.description, t.name AS tag_name
    FROM tourist_attraction ta
    LEFT JOIN city c ON ta.city_id = c.city_id
    LEFT JOIN attraction_tag at ON ta.tourist_attraction_id = at.tourist_attraction_id
    LEFT JOIN tag t ON at.tag_id = t.tag_id
    ORDER BY ta.name, t.name
  """;

        return jdbcTemplate.query(sql, rs -> {
            java.util.Map<String, TouristAttraction> map = new java.util.LinkedHashMap<>();
            while (rs.next()) {
                String name = rs.getString("name");
                TouristAttraction a = map.get(name);
                if (a == null) {
                    a = new TouristAttraction(
                            name,
                            rs.getString("city"),
                            rs.getString("description"),
                            new java.util.ArrayList<>()
                    );
                    map.put(name, a);
                }
                String tag = rs.getString("tag_name");
                if (tag != null) a.getTags().add(tag);
            }
            return new java.util.ArrayList<>(map.values());
        });
    }

    public TouristAttraction getByName(String name) {
        String sql = """
    SELECT ta.name, c.name AS city, ta.description, t.name AS tag_name
    FROM tourist_attraction ta
    LEFT JOIN city c ON ta.city_id = c.city_id
    LEFT JOIN attraction_tag at ON ta.tourist_attraction_id = at.tourist_attraction_id
    LEFT JOIN tag t ON at.tag_id = t.tag_id
    WHERE LOWER(ta.name) = LOWER(?)
  """;

        return jdbcTemplate.query(sql, rs -> {
            String aName = null, city = null, desc = null;
            java.util.List<String> tags = new java.util.ArrayList<>();
            boolean found = false;

            while (rs.next()) {
                if (!found) {
                    aName = rs.getString("name");
                    city  = rs.getString("city");
                    desc  = rs.getString("description");
                    found = true;
                }
                String tag = rs.getString("tag_name");
                if (tag != null) tags.add(tag);
            }
            return found ? new TouristAttraction(aName, city, desc, tags) : null;
        }, name);
    }







    /*** Adds a new tourist attraction to the list.*
     * * @param touristAttraction the {@link TouristAttraction} to add.
     * @return the added attraction if successful, otherwise {@code null}.*/
    public TouristAttraction addOneNamedAttractionToList(TouristAttraction touristAttraction) {
        boolean isAddOpSuccess = attractions.add(touristAttraction);
        return isAddOpSuccess ? touristAttraction : null;
    }

    /**
     * Updates an existing tourist attraction at the specified index.
     *
     * @param index the index of the attraction to update.
     * @param updatedTouristAttraction the new {@link TouristAttraction} data.
     * @return the previous attraction that was replaced.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public TouristAttraction updateOneNamedAttraction(int index, TouristAttraction updatedTouristAttraction) {
        return attractions.set(index, updatedTouristAttraction);
    }

    /**
     * Deletes a tourist attraction from the list by its name.
     *
     * @param attractionName the name of the attraction to remove.
     * @return {@code true} if an attraction was removed, {@code false} otherwise.
     */
    public boolean deleteOneNamedAttractionFromList(String attractionName) {
        return attractions.removeIf(namedAttractionToRemove ->
                namedAttractionToRemove.getName().equals(attractionName));
    }
}
