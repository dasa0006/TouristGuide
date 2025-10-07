-- ====================================
-- Table: city
-- ====================================
CREATE TABLE city (
    city_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- ====================================
-- Table: tourist_attraction
-- ====================================
CREATE TABLE tourist_attraction (
    tourist_attraction_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    city_id INT,
    FOREIGN KEY (city_id)
        REFERENCES city(city_id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

-- ====================================
-- Table: tag
-- ====================================
CREATE TABLE tag (
    tag_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- ====================================
-- Table: attraction_tag (junction table)
-- ====================================
CREATE TABLE attraction_tag (
    tourist_attraction_id INT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (tourist_attraction_id, tag_id),
    FOREIGN KEY (tourist_attraction_id)
    REFERENCES tourist_attraction(tourist_attraction_id)
    ON DELETE CASCADE,
    FOREIGN KEY (tag_id)
    REFERENCES tag(tag_id)
    ON DELETE CASCADE
);
