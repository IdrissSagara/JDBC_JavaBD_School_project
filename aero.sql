
DROP TABLE reservation;
DROP TABLE client;
DROP TABLE hotesse_Vol;
DROP TABLE hotesse;
DROP TABLE qualificationModelAvion;
DROP TABLE pilote_Vol;
DROP TABLE pilote;
DROP TABLE volpassager;
DROP TABLE avionpassager;
DROP TABLE place;
DROP TABLE modele;

drop sequence HOT_VOL;
drop sequence PILVOL_SEQ;


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
	dateEnregistrementVol DATE,
	datedepart TIMESTAMP,
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
	localisationActuellePilote VARCHAR(30),
	CONSTRAINT pk_numpilote PRIMARY KEY (numpilote)

);
CREATE TABLE qualificationModelAvion(
	numQualifPilote INTEGER,
	nummodel INTEGER,
	numpilote INTEGER,
	nbheurepilote INTEGER,
	CONSTRAINT pk_qualifAvion PRIMARY KEY (numQualifPilote),
	CONSTRAINT fk_model_qualification FOREIGN KEY (nummodel) REFERENCES modele (nummodel),
	CONSTRAINT fk_pilote_qualif FOREIGN KEY (numpilote) REFERENCES pilote (numpilote)

);

CREATE TABLE pilote_Vol(
	numPiloteVol INTEGER,
	numvolpassager INTEGER,
	numpilote INTEGER,
	CONSTRAINT pk_qualification PRIMARY KEY (numPiloteVol),
	CONSTRAINT fk_volpassager_qualification FOREIGN KEY (numvolpassager) REFERENCES volpassager (numvolpassager),
	CONSTRAINT fk_pilote_qualification FOREIGN KEY (numpilote) REFERENCES pilote (numpilote)

);

CREATE TABLE hotesse(
	numhotesse INTEGER not null,
	nompersonnelHotesse VARCHAR(30),
	prenompersonnelHotesse VARCHAR(30),
	languematernelle VARCHAR(30),
	deuxiemelangue VARCHAR(30),
	troisiemelangue VARCHAR(30),
	localisationActuelleHotesse VARCHAR(30),
	nbheurehotesse INTEGER,
	CONSTRAINT pk_numhotesse PRIMARY KEY (numhotesse)

);

CREATE TABLE hotesse_Vol(
	numHotess_Vol INTEGER,
	numvolpassager INTEGER,
	numhotesse INTEGER,
	CONSTRAINT pk_hotese_vol PRIMARY KEY (numHotess_Vol),
	CONSTRAINT fk_vol_hotess_num FOREIGN KEY (numvolpassager) REFERENCES volpassager (numvolpassager),
	CONSTRAINT fk_vol_hotess_numPil FOREIGN KEY (numhotesse) REFERENCES hotesse (numhotesse)

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

--sequence PILOTE_VOL
create sequence PILVOL_SEQ start with 10;

create or replace trigger AUTO_INCREMENT_PILVOL_SEQ
    before insert
    on PILOTE_VOL
    for each row
    when (new.NUMPILOTEVOL IS NULL)
BEGIN
    SELECT pilvol_seq.NEXTVAL
    INTO :new.NUMPILOTEVOL
    FROM dual;
END;


--sequence hotesse_vol
create sequence HOT_VOL start with 30;
create or replace trigger AUTO_INCREMENT_HOT_VOL
    before insert
    on HOTESSE_VOL
    for each row
    when (new.NUMHOTESS_VOL IS NULL)
BEGIN
    SELECT HOT_VOL.NEXTVAL
    INTO :new.NUMHOTESS_VOL
    FROM dual;
END;


create or replace PROCEDURE updatePilote (
    IN_NOMPERSONNELPILOTE     in     PILOTE.NOMPERSONNELPILOTE%type,
    IN_PRENOMPERSONNELPILOTE  in     PILOTE.PRENOMPERSONNELPILOTE%type,
    IN_RUEPERSONNELPILOTE     in     PILOTE.RUEPERSONNELPILOTE%type,
    IN_PAYSPERSONNELPILOTE    in     PILOTE.PAYSPERSONNELPILOTE%type,
    IN_LOCALISATIONACTUELLEPILOTE in PILOTE.LOCALISATIONACTUELLEPILOTE%type,
    IN_NUMPILOTE              in     PILOTE.NUMPILOTE%type
) is

begin
    UPDATE PILOTE
        SET NOMPERSONNELPILOTE = IN_NOMPERSONNELPILOTE,
            PRENOMPERSONNELPILOTE = IN_PRENOMPERSONNELPILOTE,
            RUEPERSONNELPILOTE = IN_RUEPERSONNELPILOTE,
            PAYSPERSONNELPILOTE = IN_PAYSPERSONNELPILOTE,
            LOCALISATIONACTUELLEPILOTE = IN_LOCALISATIONACTUELLEPILOTE
        WHERE NUMPILOTE = IN_NUMPILOTE;
end;
/