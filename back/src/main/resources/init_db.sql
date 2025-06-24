
CREATE TABLE Agence (
    agenceId INTEGER PRIMARY KEY,
    nom VARCHAR(255),
    adresse VARCHAR(255)
);

CREATE TABLE CategorieVehicule (
    categorieId INTEGER PRIMARY KEY,
    nom VARCHAR(255)
);

CREATE TABLE Utilisateur (
    userId INTEGER PRIMARY KEY,
    nom VARCHAR(255),
    email VARCHAR(255),
    dateCreation DATE
);

CREATE TABLE OffreLocation (
    offreId INTEGER PRIMARY KEY,
    villeDepart VARCHAR(255),
    villeRetour VARCHAR(255),
    dateDebut DATE,
    dateRetour DATE,
    tarif DECIMAL(10, 2),
    categorieId INTEGER,
    agenceDepartId INTEGER,
    agenceArriveId INTEGER,
    FOREIGN KEY (categorieId) REFERENCES CategorieVehicule(categorieId),
    FOREIGN KEY (agenceDepartId) REFERENCES Agence(agenceId),
    FOREIGN KEY (agenceArriveId) REFERENCES Agence(agenceId)
);

CREATE TABLE Reservation (
    reservationId INTEGER PRIMARY KEY,
    userId INTEGER,
    offreId INTEGER,
    statut VARCHAR(50),
    FOREIGN KEY (userId) REFERENCES Utilisateur(userId),
    FOREIGN KEY (offreId) REFERENCES OffreLocation(offreId)
);

CREATE TABLE TicketSupport (
    ticketId INTEGER PRIMARY KEY,
    userEmail VARCHAR(255),
    contenu TEXT,
    dateCreation DATE,
    FOREIGN KEY (userEmail) REFERENCES Utilisateur(email)
);
