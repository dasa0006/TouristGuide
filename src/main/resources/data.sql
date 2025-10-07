-- Cities
INSERT INTO city (name) VALUES
    ('København'),
    ('Aarhus'),
    ('Odense'),
    ('Aalborg'),
    ('Helsingør'),
    ('Billund'),
    ('Rønne'),
    ('Skagen');

-- Tags
INSERT INTO tag (name) VALUES
    ('museum'),
    ('kultur'),
    ('familie'),
    ('forlystelser'),
    ('natur'),
    ('slot'),
    ('statue'),
    ('strand');

-- Attractions
INSERT INTO tourist_attraction (name, description, city_id) VALUES
    ('Tivoli', 'Forlystelsespark i hjertet af København.', (SELECT city_id FROM city WHERE name='København')),
    ('Nyhavn', 'Farverig havnepromenade med restauranter og barer.', (SELECT city_id FROM city WHERE name='København')),
    ('Den Lille Havfrue', 'Berømt statue inspireret af H.C. Andersen.', (SELECT city_id FROM city WHERE name='København')),
    ('ARoS', 'Kunstmuseum med regnbuepanorama.', (SELECT city_id FROM city WHERE name='Aarhus')),
    ('Moesgaard Museum', 'Arkæologi og kulturhistorie.', (SELECT city_id FROM city WHERE name='Aarhus')),
    ('Kronborg Slot', 'Renæssanceslot kendt fra Shakespeares Hamlet.', (SELECT city_id FROM city WHERE name='Helsingør')),
    ('Legoland', 'Forlystelsespark bygget af LEGO.', (SELECT city_id FROM city WHERE name='Billund')),
    ('Hammershus', 'Nordeuropas største borgruin.', (SELECT city_id FROM city WHERE name='Rønne'));

-- Attraction ↔ Tag
-- Tivoli 
INSERT INTO attraction_tag (tourist_attraction_id, tag_id)
SELECT ta.tourist_attraction_id, t.tag_id
FROM tourist_attraction ta, tag t
WHERE ta.name='Tivoli' AND t.name IN ('forlystelser','familie','kultur');

-- Nyhavn
INSERT INTO attraction_tag (tourist_attraction_id, tag_id)
SELECT ta.tourist_attraction_id, t.tag_id
FROM tourist_attraction ta, tag t
WHERE ta.name='Nyhavn' AND t.name IN ('kultur','strand');

-- Den Lille Havfrue
INSERT INTO attraction_tag (tourist_attraction_id, tag_id)
SELECT ta.tourist_attraction_id, t.tag_id
FROM tourist_attraction ta, tag t
WHERE ta.name='Den Lille Havfrue' AND t.name IN ('statue','kultur');

-- ARoS
INSERT INTO attraction_tag (tourist_attraction_id, tag_id)
SELECT ta.tourist_attraction_id, t.tag_id
FROM tourist_attraction ta, tag t
WHERE ta.name='ARoS' AND t.name IN ('museum','kultur');

-- Moesgaard Museum
INSERT INTO attraction_tag (tourist_attraction_id, tag_id)
SELECT ta.tourist_attraction_id, t.tag_id
FROM tourist_attraction ta, tag t
WHERE ta.name='Moesgaard Museum' AND t.name IN ('museum','kultur','natur');

-- Kronborg Slot
INSERT INTO attraction_tag (tourist_attraction_id, tag_id)
SELECT ta.tourist_attraction_id, t.tag_id
FROM tourist_attraction ta, tag t
WHERE ta.name='Kronborg Slot' AND t.name IN ('slot','kultur');

-- Legoland
INSERT INTO attraction_tag (tourist_attraction_id, tag_id)
SELECT ta.tourist_attraction_id, t.tag_id
FROM tourist_attraction ta, tag t
WHERE ta.name='Legoland' AND t.name IN ('forlystelser','familie');

-- Hammershus
INSERT INTO attraction_tag (tourist_attraction_id, tag_id)
SELECT ta.tourist_attraction_id, t.tag_id
FROM tourist_attraction ta, tag t
WHERE ta.name='Hammershus' AND t.name IN ('natur','kultur','slot');