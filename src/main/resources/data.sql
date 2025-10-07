-- ====================================
-- Insert cities
-- ====================================
INSERT INTO city (name) VALUES
    ('København'),
    ('Aarhus'),
    ('Kværndrup'),
    ('Aalborg'),
    ('Helsingør'),
    ('Odense'),
    ('Bornholm'),
    ('Skagen'),
    ('Billund');

-- ====================================
-- Insert tags
-- ====================================
INSERT INTO tag (name) VALUES
    ('forlystelser'),
    ('familie'),
    ('kultur'),
    ('havn'),
    ('restauranter'),
    ('historie'),
    ('kunst'),
    ('museum'),
    ('arkitektur'),
    ('slot'),
    ('have'),
    ('dyr'),
    ('natur'),
    ('arkæologi'),
    ('ruin'),
    ('strand'),
    ('geografi'),
    ('leg');

-- ====================================
-- Insert tourist attractions
-- ====================================
INSERT INTO tourist_attraction (name, description, city_id) VALUES
    ('Tivoli', 'Forlystelsespark i hjertet af København.', 1),
    ('Nyhavn', 'Farverig havnepromenade med restauranter og barer.', 1),
    ('Den Lille Havfrue', 'Berømt statue inspireret af H.C. Andersen.', 1),
    ('ARoS', 'Kunstmuseum i Aarhus med regnbuepanorama.', 2),
    ('Egeskov Slot', 'Renæssanceslot på Fyn omgivet af voldgrav.', 3),
    ('Aalborg Zoo', 'Dyrepark med mere end 100 forskellige arter.', 4),
    ('Moesgaard Museum', 'Museum i Aarhus med arkæologi og kulturhistorie.', 2),
    ('Kronborg Slot', 'Renæssanceslot i Helsingør, kendt fra Shakespeares Hamlet.', 5),
    ('Odense Zoo', 'Familievenlig zoologisk have på Fyn.', 6),
    ('Hammershus', 'Nordeuropas største borgruin på Bornholm.', 7),
    ('Grenen', 'Danmarks nordligste punkt, hvor to have mødes.', 8),
    ('Legoland', 'Forlystelsespark i Billund bygget af LEGO-klodser.', 9);

-- ====================================
-- Link tags to attractions (many-to-many)
-- ====================================
-- Tivoli
INSERT INTO attraction_tag VALUES
    (1, 1), (1, 2), (1, 3);

-- Nyhavn
INSERT INTO attraction_tag VALUES
    (2, 4), (2, 5), (2, 6);

-- Den Lille Havfrue
INSERT INTO attraction_tag VALUES
    (3, 3), (3, 6);

-- ARoS
INSERT INTO attraction_tag VALUES
    (4, 7), (4, 8), (4, 9);

-- Egeskov Slot
INSERT INTO attraction_tag VALUES
    (5, 10), (5, 6), (5, 11);

-- Aalborg Zoo
INSERT INTO attraction_tag VALUES
    (6, 12), (6, 2), (6, 13);

-- Moesgaard Museum
INSERT INTO attraction_tag VALUES
    (7, 8), (7, 6), (7, 14);

-- Kronborg Slot
INSERT INTO attraction_tag VALUES
    (8, 10), (8, 3), (8, 6);

-- Odense Zoo
INSERT INTO attraction_tag VALUES
    (9, 12), (9, 2), (9, 13);

-- Hammershus
INSERT INTO attraction_tag VALUES
    (10, 15), (10, 6), (10, 9);

-- Grenen
INSERT INTO attraction_tag VALUES
    (11, 13), (11, 16), (11, 17);

-- Legoland
INSERT INTO attraction_tag VALUES
    (12, 1), (12, 2), (12, 18);
