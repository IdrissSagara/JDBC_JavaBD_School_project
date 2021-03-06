
drop table PlaceVolResa;
drop table reservation;
drop table client;
drop table hotesse_Vol;
drop table hotesse;
drop table qualificationModelAvion;
drop table pilote_Vol;
drop table pilote;
drop table volpassager;
drop table avionpassager;
drop table place;
drop table modele;

drop sequence HOT_VOL;
drop sequence PILVOL_SEQ;
drop sequence PILOTE_SEQ;
drop sequence HOTESSE_SEQ;




/**
alter session set NLS_DATE_FORMAT='yyyy-mm-dd';
*/
create TABLE modele(
	nummodel INTEGER not null,
	nbpiloteneccessaire INTEGER,
	rayonaction INTEGER,
	CONSTRAINT pk_nummodel PRIMARY KEY (nummodel)
);

create TABLE avionpassager(
	numavionpassager INTEGER not null,
	nombreplaceeco INTEGER,
	nombreplaceaffaire INTEGER,
	nombreplacepremiere INTEGER,
	nummodel INTEGER,
	CONSTRAINT pk_numavionpass PRIMARY KEY (numavionpassager),
	CONSTRAINT fk_avionpassager_modele FOREIGN KEY (nummodel) REFERENCES modele (nummodel) ON delete CASCADE
);

create TABLE place(
	numplace INTEGER not null,
	position VARCHAR(30),
	typeclasse VARCHAR(30),
	prixplace INTEGER,
	datechangementplace TIMESTAMP,
	nummodel INTEGER,
	CONSTRAINT position_check check (position in ('hublot', 'couloir', 'centre')),
	CONSTRAINT typeclasse_check check (typeclasse in ('eco', 'premiere', 'affaire')),
	CONSTRAINT pk_numplace PRIMARY KEY (numplace),
	CONSTRAINT fk_place_modele FOREIGN KEY (nummodel) REFERENCES modele (nummodel) ON delete CASCADE
);

create TABLE volpassager(
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
	etatVol VARCHAR(10) DEFAULT 'EN_SERVICE',
	terminer VARCHAR(3) DEFAULT 'OUI',
	CONSTRAINT pk_numvolpassager PRIMARY KEY (numvolpassager),
	CONSTRAINT etat_check CHECK (etatVol in('EN_SERVICE', 'SUPPRIMER')),
	CONSTRAINT terminer_check CHECK (terminer in ('OUI','NON')),
	CONSTRAINT fk_volpassager_avionpassager FOREIGN KEY (numavionpassager) REFERENCES avionpassager (numavionpassager) ON delete CASCADE
);

create TABLE pilote(
	numpilote INTEGER not null,
	nompersonnelPilote VARCHAR(30),
	prenompersonnelPilote VARCHAR(30),
	ruepersonnelPilote VARCHAR(30),
	payspersonnelPilote VARCHAR(30),
	localisationActuellePilote VARCHAR(30),
	CONSTRAINT pk_numpilote PRIMARY KEY (numpilote)

);
create TABLE qualificationModelAvion(
	numQualifPilote INTEGER,
	nummodel INTEGER,
	numpilote INTEGER,
	nbheurepilote INTEGER,
	CONSTRAINT pk_qualifAvion PRIMARY KEY (numQualifPilote),
	CONSTRAINT fk_model_qualification FOREIGN KEY (nummodel) REFERENCES modele (nummodel) ON delete CASCADE,
	CONSTRAINT fk_pilote_qualif FOREIGN KEY (numpilote) REFERENCES pilote (numpilote) ON delete CASCADE

);

create TABLE pilote_Vol(
	numPiloteVol INTEGER,
	numvolpassager INTEGER,
	numpilote INTEGER,
	CONSTRAINT pk_qualification PRIMARY KEY (numPiloteVol),
	CONSTRAINT fk_volpassager_qualification FOREIGN KEY (numvolpassager) REFERENCES volpassager (numvolpassager) ON delete CASCADE,
	CONSTRAINT fk_pilote_qualification FOREIGN KEY (numpilote) REFERENCES pilote (numpilote) ON delete CASCADE

);

create TABLE hotesse(
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

create TABLE hotesse_Vol(
	numHotess_Vol INTEGER,
	numvolpassager INTEGER,
	numhotesse INTEGER,
	CONSTRAINT pk_hotese_vol PRIMARY KEY (numHotess_Vol),
	CONSTRAINT fk_vol_hotess_num FOREIGN KEY (numvolpassager) REFERENCES volpassager (numvolpassager) ON delete CASCADE,
	CONSTRAINT fk_vol_hotess_numPil FOREIGN KEY (numhotesse) REFERENCES hotesse (numhotesse) ON delete CASCADE

);


create TABLE client(
	numclient INTEGER not null,
	nomclient VARCHAR(30),
	prenomclient VARCHAR(30),
	rueclient VARCHAR(30),
	cpclient VARCHAR(30),
	villeclient VARCHAR(30),
	paysclient VARCHAR(30),
	numpassport VARCHAR(30),
	nbPointFidelite INTEGER,
	aReduction INTEGER,
	CONSTRAINT pk_numclient PRIMARY KEY (numclient)
);

create TABLE reservation(
	numreservation INTEGER not null,
	datereservation TIMESTAMP,
	numplace INTEGER,
	numvolpassager INTEGER,
	numclient INTEGER,
	etatReservation VARCHAR(20) DEFAULT 'OK',
	CONSTRAINT pk_numreservation PRIMARY KEY (numreservation),
	CONSTRAINT etatResevation_chek CHECK (etatReservation in ('OK','ANNULER')),
	CONSTRAINT fk_reservation_place FOREIGN KEY (numplace) REFERENCES place (numplace) ON delete CASCADE,
	CONSTRAINT fk_reservation_volpassager FOREIGN KEY (numvolpassager) REFERENCES volpassager (numvolpassager) ON delete CASCADE,
	CONSTRAINT fk_reservation_client FOREIGN KEY (numclient) REFERENCES client (numclient) ON delete CASCADE
);

create TABLE PlaceVolResa(
    num_place_vol_resa INTEGER,
    numplace INTEGER,
    numvolpassager INTEGER,
    numreservation INTEGER,
    CONSTRAINT pk_numreser_place_vol PRIMARY KEY (num_place_vol_resa),
	CONSTRAINT fk_PlaceVolResa_place FOREIGN KEY (numplace) REFERENCES place (numplace) ON delete CASCADE,
	CONSTRAINT fk_PlaceVolResa_volpassager FOREIGN KEY (numvolpassager) REFERENCES volpassager (numvolpassager) ON delete CASCADE,
	CONSTRAINT fk_PlaceVolResa_reservation FOREIGN KEY (numreservation) REFERENCES reservation (numreservation) ON delete CASCADE
);

--sequence PILOTE_VOL
create sequence PILVOL_SEQ start with 10;

create or replace trigger AUTO_INCREMENT_PILVOL_SEQ
    before insert
    on PILOTE_VOL
    for each row
    when (new.NUMPILOTEVOL is null)
begin
    select pilvol_seq.nextval
    into :new.NUMPILOTEVOL
    from dual;
end;


--sequence hotesse_vol
create sequence HOT_VOL start with 30;
create or replace trigger AUTO_INCREMENT_HOT_VOL
    before insert
    on HOTESSE_VOL
    for each row
    when (new.NUMHOTESS_VOL is null)
begin
    select HOT_VOL.nextval
    into :new.NUMHOTESS_VOL
    from dual;
end;

--sequence PILOTE_SEQ
create sequence PILOTE_SEQ start with 40;
create or replace trigger AUTO_INCREMENT_PILOTE_SEQ
    before insert
    on PILOTE
    for each row
    when (new.NUMPILOTE is null)
begin
    select PILOTE_SEQ.nextval
    into :new.NUMPILOTE
    from dual;
end;

--sequence HOTESSE_SEQ
create sequence HOTESSE_SEQ start with 40;
create or replace trigger AUTO_INCREMENT_HOTESSE_SEQ
    before insert
    on HOTESSE
    for each row
    when (new.NUMHOTESSE is null)
begin
    select HOTESSE_SEQ.nextval
    into :new.NUMHOTESSE
    from dual;
end;


create or replace procedure updatePilote (
    IN_NOMPERSONNELPILOTE     in     PILOTE.NOMPERSONNELPILOTE%type,
    IN_PRENOMPERSONNELPILOTE  in     PILOTE.PRENOMPERSONNELPILOTE%type,
    IN_RUEPERSONNELPILOTE     in     PILOTE.RUEPERSONNELPILOTE%type,
    IN_PAYSPERSONNELPILOTE    in     PILOTE.PAYSPERSONNELPILOTE%type,
    IN_LOCALISATIONACTUELLEPILOTE in PILOTE.LOCALISATIONACTUELLEPILOTE%type,
    IN_NUMPILOTE              in     PILOTE.NUMPILOTE%type
) is

begin
    update PILOTE
        set NOMPERSONNELPILOTE = IN_NOMPERSONNELPILOTE,
            PRENOMPERSONNELPILOTE = IN_PRENOMPERSONNELPILOTE,
            RUEPERSONNELPILOTE = IN_RUEPERSONNELPILOTE,
            PAYSPERSONNELPILOTE = IN_PAYSPERSONNELPILOTE,
            LOCALISATIONACTUELLEPILOTE = IN_LOCALISATIONACTUELLEPILOTE
        where NUMPILOTE = IN_NUMPILOTE;
end;

create or replace procedure updateVol(IN_DATEENREGISTREMENTVOL in VOLPASSAGER.DATEENREGISTREMENTVOL%type,
                                      IN_DATEDEPART in VOLPASSAGER.DATEDEPART%type,
                                      IN_DUREEVOL in VOLPASSAGER.DUREEVOL%type,
                                      IN_DISTANCEVOL in VOLPASSAGER.DISTANCEVOL%type,
                                      IN_AEROPORTORIGINE in VOLPASSAGER.AEROPORTORIGINE%type,
                                      IN_AEROPORTDESTINATION in VOLPASSAGER.AEROPORTDESTINATION%type,
                                      IN_NOMBREPLACEDISPOECO in VOLPASSAGER.NOMBREPLACEDISPOECO%type,
                                      IN_NOMBREPLACEDISPOEAFF in VOLPASSAGER.NOMBREPLACEDISPOEAFF%type,
                                      IN_NOMBREPLACEDISPOPRE in VOLPASSAGER.NOMBREPLACEDISPOPRE%type,
                                      IN_NUMAVIONPASSAGER in VOLPASSAGER.NUMAVIONPASSAGER%type,
                                      IN_NUMVOLPASSAGER in VOLPASSAGER.NUMVOLPASSAGER%type) is

begin
    update VOLPASSAGER
    set DATEENREGISTREMENTVOL = IN_DATEENREGISTREMENTVOL,
        DATEDEPART            = IN_DATEDEPART,
        DUREEVOL              = IN_DUREEVOL,
        DISTANCEVOL           = IN_DISTANCEVOL,
        AEROPORTORIGINE       = IN_AEROPORTORIGINE,
        AEROPORTDESTINATION   = IN_AEROPORTDESTINATION,
        NOMBREPLACEDISPOECO   = IN_NOMBREPLACEDISPOECO,
        NOMBREPLACEDISPOEAFF  = IN_NOMBREPLACEDISPOEAFF,
        NOMBREPLACEDISPOPRE   = IN_NOMBREPLACEDISPOPRE,
        NUMAVIONPASSAGER      = IN_NUMAVIONPASSAGER
    where NUMVOLPASSAGER = IN_NUMVOLPASSAGER;
end;

create or replace procedure updateHotesse(IN_NOMPERSONNELHOTESSE in HOTESSE.NOMPERSONNELHOTESSE%type,
                                          IN_PRENOMPERSONNELHOTESSE in HOTESSE.PRENOMPERSONNELHOTESSE%type,
                                          IN_LANGUEMATERNELLE in HOTESSE.LANGUEMATERNELLE%type,
                                          IN_DEUXIEMELANGUE in HOTESSE.DEUXIEMELANGUE%type,
                                          IN_TROISIEMELANGUE in HOTESSE.TROISIEMELANGUE%type,
                                          IN_LOCALISATIONACTUELLEHOTESSE in HOTESSE.LOCALISATIONACTUELLEHOTESSE%type,
                                          IN_NBHEUREHOTESSE in HOTESSE.NBHEUREHOTESSE%type,
                                          IN_NUMHOTESSE in HOTESSE.NUMHOTESSE%type) is

begin
    update HOTESSE
    set NOMPERSONNELHOTESSE         = IN_NOMPERSONNELHOTESSE,
        PRENOMPERSONNELHOTESSE      = IN_PRENOMPERSONNELHOTESSE,
        LANGUEMATERNELLE            = IN_LANGUEMATERNELLE,
        DEUXIEMELANGUE              = IN_DEUXIEMELANGUE,
        TROISIEMELANGUE             = IN_TROISIEMELANGUE,
        LOCALISATIONACTUELLEHOTESSE = IN_LOCALISATIONACTUELLEHOTESSE,
        NBHEUREHOTESSE              = IN_NBHEUREHOTESSE
    where NUMHOTESSE = IN_NUMHOTESSE;
end;

create or replace  procedure terminerVolPassager(IN_NUMVOLPASSAGER in VOLPASSAGER.NUMVOLPASSAGER%type) is
    numPilote number;
    nbh number;

begin
    update VOLPASSAGER set TERMINER = 'OUI' where NUMVOLPASSAGER = IN_NUMVOLPASSAGER;
    for rec in (
        select NUMPILOTE, DUREEVOL into numPilote, nbh from PILOTE_VOL natural join VOLPASSAGER where NUMVOLPASSAGER = IN_NUMVOLPASSAGER
        )
    loop
        update QUALIFICATIONMODELAVION set NBHEUREPILOTE = NBHEUREPILOTE + rec.DUREEVOL where NUMPILOTE = rec.NUMPILOTE;
    end loop;
end;

create or replace procedure reaffecterPassagers(IN_NUMVOL_OLD_VOL in VOLPASSAGER.NUMVOLPASSAGER%type, IN_NUMVOL_new_VOL in VOLPASSAGER.NUMVOLPASSAGER%type) is
begin
    --reaffecter les passagers du vol IN_NUMVOLPASSAGER à un autre vol (IN_NUMVOL_new_VOL)
    update RESERVATION set NUMVOLPASSAGER = IN_NUMVOL_new_VOL where NUMVOLPASSAGER = IN_NUMVOL_OLD_VOL;
end;

--sequence reservation
drop sequence RES_SEQ;
create sequence RES_SEQ start with 30;

create or replace trigger AUTO_INCREMENT_RES_SEQ
    before insert
    on RESERVATION
    for each row
    when (new.NUMRESERVATION is null)
begin
    select RES_SEQ.nextval
    into :new.NUMRESERVATION
    from dual;
end;
