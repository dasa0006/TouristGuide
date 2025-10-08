package tourism.repository;
import org.springframework.jdbc.core.JdbcTemplate;
import tourism.model.TouristAttraction;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

    public List<TouristAttraction> getAllAttractions() {
        String baseSql = """
    SELECT tourist_attraction.name,
           city.name AS city,
           tourist_attraction.description
    FROM tourist_attraction
    LEFT JOIN city ON tourist_attraction.city_id = city.city_id
    ORDER BY tourist_attraction.name
  """;

        List<TouristAttraction> list = jdbcTemplate.query(baseSql, (rs, i) ->
                new TouristAttraction(
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getString("description"),
                        new java.util.ArrayList<>()
                )
        );

        String tagSql = """
    SELECT tag.name
    FROM tag
    JOIN attraction_tag ON tag.tag_id = attraction_tag.tag_id
    JOIN tourist_attraction ON tourist_attraction.tourist_attraction_id = attraction_tag.tourist_attraction_id
    WHERE LOWER(tourist_attraction.name) = LOWER(?)
    ORDER BY tag.name
  """;

        for (TouristAttraction a : list) {
            List<String> tags = jdbcTemplate.query(tagSql, (rs, i) -> rs.getString(1), a.getName());
            a.setTags(tags);
        }

        return list;
    }

    public TouristAttraction getByName(String name) {
        String oneSql = """
    SELECT tourist_attraction.name,
           city.name AS city,
           tourist_attraction.description
    FROM tourist_attraction
    LEFT JOIN city ON tourist_attraction.city_id = city.city_id
    WHERE LOWER(tourist_attraction.name) = LOWER(?)
  """;

        List<TouristAttraction> found = jdbcTemplate.query(oneSql, (rs, i) ->
                new TouristAttraction(
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getString("description"),
                        new java.util.ArrayList<>()
                ), name);

        if (found.isEmpty()) return null;

        String tagSql = """
    SELECT tag.name
    FROM tag
    JOIN attraction_tag ON tag.tag_id = attraction_tag.tag_id
    JOIN tourist_attraction ON tourist_attraction.tourist_attraction_id = attraction_tag.tourist_attraction_id
    WHERE LOWER(tourist_attraction.name) = LOWER(?)
    ORDER BY tag.name
  """;

        List<String> tags = jdbcTemplate.query(tagSql, (rs, i) -> rs.getString(1), name);
        found.get(0).setTags(tags);

        return found.get(0);
    }

    public List<String> listCities() {
        return jdbcTemplate.query(
                "SELECT name FROM city ORDER BY name",
                (rs, i) -> rs.getString(1)
        );
    }

    public List<String> listTags() {
        return jdbcTemplate.query(
                "SELECT name FROM tag ORDER BY name",
                (rs, i) -> rs.getString(1)
        );
    }

    public void insertAttraction(TouristAttraction a) {
        if (a.getCity() == null || a.getCity().isBlank()) {
            String sql = """
        INSERT INTO tourist_attraction(name, description)
        VALUES (?, ?)
      """;
            jdbcTemplate.update(sql, a.getName(), a.getDescription());
        } else {
            String sql = """
        INSERT INTO tourist_attraction(name, description, city_id)
        VALUES (?, ?, (SELECT city_id FROM city WHERE LOWER(name)=LOWER(?)))
      """;
            jdbcTemplate.update(sql, a.getName(), a.getDescription(), a.getCity());
        }

        if (a.getTags() != null) {
            for (String tag : a.getTags()) {
                if (tag == null || tag.isBlank()) continue;
                String tagSql = """
          INSERT INTO attraction_tag(tourist_attraction_id, tag_id)
          VALUES (
            (SELECT tourist_attraction_id FROM tourist_attraction WHERE LOWER(name)=LOWER(?)),
            (SELECT tag_id FROM tag WHERE LOWER(name)=LOWER(?))
          )
        """;
                jdbcTemplate.update(tagSql, a.getName(), tag);
            }
        }
    }

    public void updateAttraction(TouristAttraction a) {
        if (a.getCity() == null || a.getCity().isBlank()) {
            String sql = """
          UPDATE tourist_attraction
          SET description = ?
          WHERE LOWER(name) = LOWER(?)
        """;
            jdbcTemplate.update(sql, a.getDescription(), a.getName());
        } else {
            String sql = """
          UPDATE tourist_attraction
          SET description = ?, city_id = (
            SELECT city_id FROM city WHERE LOWER(name)=LOWER(?)
          )
          WHERE LOWER(name) = LOWER(?)
        """;
            jdbcTemplate.update(sql, a.getDescription(), a.getCity(), a.getName());
        }

        // slet gamle tags
        jdbcTemplate.update("""
      DELETE FROM attraction_tag
      WHERE tourist_attraction_id = (
        SELECT tourist_attraction_id FROM tourist_attraction WHERE LOWER(name)=LOWER(?)
      )
    """, a.getName());

        // indsæt nye tags
        if (a.getTags() != null) {
            for (String tag : a.getTags()) {
                if (tag == null || tag.isBlank()) continue;
                jdbcTemplate.update("""
              INSERT INTO attraction_tag(tourist_attraction_id, tag_id)
              VALUES (
                (SELECT tourist_attraction_id FROM tourist_attraction WHERE LOWER(name)=LOWER(?)),
                (SELECT tag_id FROM tag WHERE LOWER(name)=LOWER(?))
              )
            """, a.getName(), tag);
            }
        }
    }

    public boolean deleteOneNamedAttractionFromList(String attractionName) {
        return attractions.removeIf(namedAttractionToRemove ->
                namedAttractionToRemove.getName().equals(attractionName));
    }
}
