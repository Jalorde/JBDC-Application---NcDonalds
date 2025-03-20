


CREATE DATABASE Songs;
GO
USE [Songs]
GO
CREATE TABLE Genres (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    Genre VARCHAR(100) NOT NULL
);
GO
CREATE TABLE Songs (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    Title VARCHAR(100) NOT NULL,
    Artist VARCHAR(100) NOT NULL,
    ReleaseYear INT NOT NULL,
    GenreID INT NOT NULL,
    Album VARCHAR(100) NULL,
    TrackNumber INT NULL,
    Duration INT NULL,
    Label VARCHAR(100) NULL,
    FOREIGN KEY (GenreID) REFERENCES Genres(ID)
);
GO
INSERT INTO Genres (Genre) VALUES
('R&B'),
('Rock'),
('Hip Hop'),
('Country'),
('Pop'),
('Reggae');

INSERT INTO Songs (Title, Artist, ReleaseYear, GenreID, Album, TrackNumber, Duration, Label)
VALUES 
('Fallin''', 'Alicia Keys', 2001, 1, 'Songs in A Minor', 1, 220, 'J Records'),
('How You Remind Me', 'Nickelback', 2001, 2, 'Silver Side Up', 3, 214, 'Roadrunner Records'),
('Family Affair', 'Mary J. Blige', 2001, 1, 'No More Drama', 1, 222, 'MCA Records'),
('Lose Yourself', 'Eminem', 2002, 3, '8 Mile Soundtrack', 1, 213, 'Shady Records/Interscope Records'),
('Wherever You Will Go', 'The Calling', 2001, 2, 'Camino Palmero', 1, 227, 'RCA Records'),
('Ms. Jackson', 'Outkast', 2000, 3, 'Stankonia', 4, 296, 'LaFace Records'),
('Breathe', 'Faith Hill', 2000, 4, 'Breathe', 1, 239, 'Warner Bros. Records'),
('Hanging by a Moment', 'Lifehouse', 2001, 2, 'No Name Face', 3, 214, 'Dreamworks Records'),
('Get the Party Started', 'Pink', 2001, 5, 'M!ssundaztood', 1, 213, 'Arista Records'),
('Hey Ya!', 'Outkast', 2003, 3, 'Speakerboxxx/The Love Below', 1, 259, 'LaFace Records');



USE [master]
GO
CREATE LOGIN [javaApps] WITH PASSWORD=N'Java233', DEFAULT_DATABASE=[Songs], CHECK_EXPIRATION=ON, CHECK_POLICY=ON
GO
ALTER SERVER ROLE [sysadmin] ADD MEMBER [javaApps]
GO