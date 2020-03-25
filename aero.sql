
DROP TABLE reservation;
DROP TABLE client;
DROP TABLE hotesse;
DROP TABLE pilote;
DROP TABLE volpassager;
DROP TABLE avionpassager;
DROP TABLE place;
DROP TABLE modele;


/**
alter session set NLS_DATE_FORMAT='yyyy-mm-dd';
*/
CREATE TABLE modele(
	nummodel INTEGER not null,
	nbpiloteneccessaire INTEGER,
	rayonaction INTEGER,
	CONSTRAINT pk_nummodel PRIMARY KEY (nummodel)
);

CREATE TABLE avionpassager(
	numavionpassager INTEGER not null,
	nombreplaceeco INTEGER,
	nombreplaceaffaire INTEGER,
	nombreplacepremiere INTEGER,
	nummodel INTEGER,
	CONSTRAINT pk_numavionpass PRIMARY KEY (numavionpassager),
	CONSTRAINT fk_avionpassager_modele FOREIGN KEY (nummodel) REFERENCES modele (nummodel)
);

CREATE TABLE place(
	numplace INTEGER not null,
	position VARCHAR(30),
	typeclasse VARCHAR(30),
	prixplace INTEGER,
	datechangementplace TIMESTAMP, 
	nummodel INTEGER,
	CONSTRAINT position_check check (position in ('hublot', 'couloir', 'centre')),
	CONSTRAINT typeclasse_check check (typeclasse in ('eco', 'premiere', 'affaire')),
	CONSTRAINT pk_numplace PRIMARY KEY (numplace),
	CONSTRAINT fk_place_modele FOREIGN KEY (nummodel) REFERENCES modele (nummodel)
);

CREATE TABLE volpassager(
	numvolpassager INTEGER not null,
	heuredepart TIMESTAMP,
	dureevol INTEGER,
	distancevol INTEGER,	
	aeroportorigine VARCHAR(30),
	aeroportdestination VARCHAR(30),
	nombreplacedispoeco INTEGER,
	nombreplacedispoeaff INTEGER,
	nombreplacedispopre INTEGER,
	numavionpassager INTEGER,
	CONSTRAINT pk_numvolpassager PRIMARY KEY (numvolpassager),
	CONSTRAINT fk_volpassager_avionpassager FOREIGN KEY (numavionpassager) REFERENCES avionpassager (numavionpassager)
);

CREATE TABLE pilote(
	numpilote INTEGER not null,
	nompersonnelPilote VARCHAR(30),
	prenompersonnelPilote VARCHAR(30),
	ruepersonnelPilote VARCHAR(30),
	payspersonnelPilote VARCHAR(30),
	nbheurepilote INTEGER,
	nummodel INTEGER,
	numvolpassager INTEGER,
	CONSTRAINT pk_numpilote PRIMARY KEY (numpilote),
	CONSTRAINT fk_pilote_modele FOREIGN KEY (nummodel) REFERENCES modele (nummodel),
	CONSTRAINT fk_personnel_volpassager FOREIGN KEY (numvolpassager) REFERENCES volpassager (numvolpassager)

);

CREATE TABLE hotesse(
	numhotesse INTEGER not null,
	nompersonnelHotesse VARCHAR(30),
	prenompersonnelHotesse VARCHAR(30),
	languematernelle VARCHAR(30),
	deuxiemelangue VARCHAR(30),
	troisiemelangue VARCHAR(30),
	nbheurehotesse INTEGER,
	nummodel INTEGER,
	numvolpassager INTEGER,
	CONSTRAINT pk_numhotesse PRIMARY KEY (numhotesse),
	CONSTRAINT fk_pilote_model FOREIGN KEY (nummodel) REFERENCES modele (nummodel),
	CONSTRAINT fk_personnel_volpass FOREIGN KEY (numvolpassager) REFERENCES volpassager (numvolpassager)

);

CREATE TABLE client(
	numclient INTEGER not null, 
	nomclient VARCHAR(30), 
	prenomclient VARCHAR(30),  
	rueclient VARCHAR(30),
	cpclient VARCHAR(30),
	villeclient VARCHAR(30),
	paysclient VARCHAR(30),
	numpassport VARCHAR(30),
	CONSTRAINT pk_numclient PRIMARY KEY (numclient)
);

CREATE TABLE reservation(
	numreservation INTEGER not null,  
	datereservation TIMESTAMP, 
	numplace INTEGER,
	numvolpassager INTEGER,
	numclient INTEGER,
	CONSTRAINT pk_numreservation PRIMARY KEY (numreservation),
	CONSTRAINT fk_reservation_place FOREIGN KEY (numplace) REFERENCES place (numplace),
	CONSTRAINT fk_reservation_volpassager FOREIGN KEY (numvolpassager) REFERENCES volpassager (numvolpassager),
	CONSTRAINT fk_reservation_client FOREIGN KEY (numclient) REFERENCES client (numclient)
);