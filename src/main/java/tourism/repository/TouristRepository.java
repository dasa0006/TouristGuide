package tourism.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    /**
     * In-memory list of tourist attractions initialized with predefined data.
     */
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




    private static final RowMapper<TouristAttraction> ATTRACTION_MAPPER =
            (rs, rowNum) -> new TouristAttraction(
                    rs.getString("name"),
                    rs.getString("city"),
                    rs.getString("description"),
                    List.of()  // tags, tom liste for nu
            );

    public List<TouristAttraction> getAllAttractions() {
        String sql = """
        SELECT ta.name, c.name AS city, ta.description
        FROM tourist_attraction ta
        LEFT JOIN city c ON ta.city_id = c.city_id
        ORDER BY ta.name
    """;
        return jdbcTemplate.query(sql, ATTRACTION_MAPPER);
    }


    /**
     * Adds a new tourist attraction to the list.
     *
     * @param touristAttraction the {@link TouristAttraction} to add.
     * @return the added attraction if successful, otherwise {@code null}.
     */
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
