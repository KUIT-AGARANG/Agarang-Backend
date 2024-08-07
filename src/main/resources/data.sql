-- Delete existing records
DELETE FROM memory_playlist;
DELETE FROM memory_bookmark;
DELETE FROM music_bookmark;
DELETE FROM hashtag;

DELETE FROM playlist;
DELETE FROM memory;
DELETE FROM member;
DELETE FROM baby;


-- Insert new baby record
INSERT INTO baby (id, character_id, code, name, due_date, weight, created_at, updated_at)
VALUES (1, NULL, 'DXW1234', '아가', '2025-01-01', 1.8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert new member record
INSERT INTO member (id, baby_id, refresh_token_id, role, created_at, updated_at)
VALUES (1, 1, NULL, '아빠', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 1, NULL, '엄마', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert new memory records
INSERT INTO memory (id, created_at, updated_at, status, genre, image_url, music_title, music_url, instrument, mood, tempo, text, baby_id, member_id)
VALUES
    (1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 'ACOUSTIC', 'https://example.com/image1.jpg', '노래는 불빛처럼 달린다','https://example.com/music1.mp3','PIANO', 'HAPPY', 'FAST', 'Memory 1', 1, 1),
    (2, '2024-07-02 00:00:00', '2024-07-02 00:00:00', 'ACTIVE', 'BALLAD', 'https://example.com/image2.jpg', '노래는 불빛처럼 달린다' ,'https://example.com/music2.mp3','BASE_GUITAR', 'BEAUTIFUL', 'MID', 'Memory 2', 1, 1),
    (3, '2024-07-03 00:00:00', '2024-07-03 00:00:00', 'ACTIVE', 'ELECTRONIC', 'https://example.com/image3.jpg', '노래는 불빛처럼 달린다','https://example.com/music3.mp3','VIOLIN', 'ENERGETIC', 'SLOW', 'Memory 3', 1, 1),
    (4, '2024-07-04 00:00:00', '2024-07-04 00:00:00', 'ACTIVE', 'HIPHOP', 'https://example.com/image4.jpg', '노래는 불빛처럼 달린다','https://example.com/music4.mp3','FLUTE', 'BRIGHT', 'FAST', 'Memory 4', 1, 1),
    (5, '2024-07-05 00:00:00', '2024-07-05 00:00:00', 'ACTIVE', 'INDIE', 'https://example.com/image5.jpg', '노래는 불빛처럼 달린다','https://example.com/music5.mp3','SAXOPHONE', 'LOVELY', 'MID', 'Memory 5', 1, 1),
    (6, '2024-07-06 00:00:00', '2024-07-06 00:00:00', 'ACTIVE', 'JAZZ', 'https://example.com/image6.jpg', '노래는 불빛처럼 달린다','https://example.com/music6.mp3','TRUMPET', 'FANTASTIC', 'SLOW', 'Memory 6', 1, 1),
    (7, '2024-07-07 00:00:00', '2024-07-07 00:00:00', 'ACTIVE', 'POP', 'https://example.com/image7.jpg', '노래는 불빛처럼 달린다','https://example.com/music7.mp3','VIOLA', 'PEACEFUL', 'FAST', 'Memory 7', 1, 1),
    (8, '2024-07-08 00:00:00', '2024-07-08 00:00:00', 'ACTIVE', 'RNB', 'https://example.com/image8.jpg', '노래는 불빛처럼 달린다','https://example.com/music8.mp3','CLARINET', 'TOUCHING', 'MID', 'Memory 8', 1, 1),
    (9, '2024-07-09 00:00:00', '2024-07-09 00:00:00', 'ACTIVE', 'ROCK', 'https://example.com/image9.jpg', '노래는 불빛처럼 달린다','https://example.com/music9.mp3','XYLOPHONE', 'WARM', 'SLOW', 'Memory 9', 1, 1),
    (10, '2024-07-10 00:00:00', '2024-07-10 00:00:00', 'ACTIVE', 'ACOUSTIC', 'https://example.com/image10.jpg', '노래는 불빛처럼 달린다','https://example.com/music10.mp3','HARP', 'HAPPY', 'FAST', 'Memory 10', 1, 1),
    (11, '2024-07-05 00:00:00', '2024-07-05 00:00:00', 'ACTIVE', 'INDIE', 'https://example.com/image11.jpg', '노래는 불빛처럼 달린다','https://example.com/music11.mp3','TRUMPET', 'PEACEFUL', 'SLOW', 'Memory 10', 1, 2);

-- Insert new playlist records
INSERT INTO memory_bookmark (id, created_at, updated_at, memory_id, member_id)
values (1,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1);

-- Insert new playlist records
INSERT INTO music_bookmark (id, created_at, updated_at, memory_id, member_id)
values (1,'2024-07-01 00:00:01', '2024-07-01 00:00:01', 1, 1);

-- Insert new playlist records
INSERT INTO playlist (id, created_at, updated_at, status, name, image_url)
VALUES
    (1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 'Entire Playlist', 'https://example.com/images/1.jpg'),
    (2, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 'Songs I Liked', 'https://example.com/images/2.jpg'),
    (3, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 'When Feeling Fetal Movements', 'https://example.com/images/3.jpg'),
    (4, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 'Winding Down the Day', 'https://example.com/images/4.jpg'),
    (5, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 'Starting the Morning', 'https://example.com/images/5.jpg'),
    (6, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 'While Exercising', 'https://example.com/images/6.jpg'),
    (7, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 'For Relaxing the Mind', 'https://example.com/images/7.jpg'),
    (8, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 'For Solitude', 'https://example.com/images/8.jpg');


-- Insert new hashtag records
INSERT INTO hashtag (id, created_at, updated_at, status, name, memory_id)
VALUES
    (1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 'HAPPY', 1),
    (2, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 'BEAUTIFUL', 2),
    (3, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 'LOVELY', 3);

-- Insert new memoryplaylist records
INSERT INTO memory_playlist (id, created_at, updated_at, status, memory_id,playlist_id)
VALUES
    (1, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 1, 1),
    (2, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 1, 5),
    (3, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 2, 1),
    (4, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 2, 3),
    (5, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 3, 1),
    (6, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 3, 6),
    (7, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 4, 1),
    (8, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 4, 4),
    (9, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 5, 1),
    (10, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 5, 3),
    (11, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 11, 1),
    (12, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 11, 4),
    (13, '2024-07-01 00:00:00', '2024-07-01 00:00:00', 'ACTIVE', 11, 7);