﻿CREATE TABLE GAME(
    id SERIAL PRIMARY KEY NOT NULL,
    gamedate Date NOT NULL,
    team_1 VARCHAR(30) NOT NULL,
    team_2 VARCHAR(30) NOT NULL,
    coefficient_1 DECIMAL NOT NULL,
    coefficient_2 DECIMAL NOT NULL
);

CREATE TABLE BET(
    id SERIAL PRIMARY KEY NOT NULL,
    game_id INT REFERENCES GAME(id),
    chosen_team VARCHAR(30) NOT NULL,
    coefficient DECIMAL NOT NULL
);

CREATE TABLE BOOK(
    id SERIAL PRIMARY KEY NOT NULL
     
);