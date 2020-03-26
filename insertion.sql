insert into modele values (2,5,50);
insert into modele values (3,2,110);
insert into modele values (4,6,150);
insert into modele values (5,1,500);
insert into modele values (6,8,200);
insert into modele values (7,9,77);
insert into modele values (8,3,750);
insert into modele values (9,4,10);


insert into avionpassager values (12,50,10,111,2);
insert into avionpassager values (13,700,22,122,3);
insert into avionpassager values (22,800,11,222,4);
insert into avionpassager values (32,200,33,333,5);
insert into avionpassager values (42,200,44,444,5);
insert into avionpassager values (52,300,55,211,6);
insert into avionpassager values (62,400,66,551,6);
insert into avionpassager values (72,500,55,322,7);

insert into place values (11,'hublot','eco',900,TO_DATE('20-03-2019','DD-MM-YYYY'),2);
insert into place values (12,'hublot','affaire',800,TO_DATE('22-03-2019','DD-MM-YYYY'),3);
insert into place values (13,'couloir','eco',9000,TO_DATE('26-03-2019','DD-MM-YYYY'),4);
insert into place values (14,'couloir','affaire',500,TO_DATE('20-03-2019','DD-MM-YYYY'),5);
insert into place values (15,'hublot','eco',2200,TO_DATE('25-03-2019','DD-MM-YYYY'),6);
insert into place values (16,'centre','affaire',700,TO_DATE('24-03-2019','DD-MM-YYYY'),7);
insert into place values (17,'centre','eco',200,TO_DATE('23-03-2019','DD-MM-YYYY'),8);


insert into volpassager values (144,TO_DATE('20-03-2019','DD-MM-YYYY'),TO_DATE('20-03-2019','DD-MM-YYYY'),TO_TIMESTAMP('2014-07-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),8,3323,'Mali','France',400,20,30,12);
insert into volpassager values (145,TO_DATE('20-03-2019','DD-MM-YYYY'),TO_DATE('20-03-2019','DD-MM-YYYY'),TO_TIMESTAMP('2014-07-05 07:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),33,5223,'ALGERIE','France',400,20,30,13);
insert into volpassager values (146,TO_DATE('20-03-2019','DD-MM-YYYY'),TO_DATE('20-03-2019','DD-MM-YYYY'),TO_TIMESTAMP('2014-07-02 08:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),22,1223,'MAROC','France',400,20,30,32);
insert into volpassager values (147,TO_DATE('20-03-2019','DD-MM-YYYY'),TO_DATE('20-03-2019','DD-MM-YYYY'),TO_TIMESTAMP('2014-07-03 03:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),8,2623,'CAMEROUNE','France',400,20,30,22);
insert into volpassager values (148,TO_DATE('20-03-2019','DD-MM-YYYY'),TO_DATE('20-03-2019','DD-MM-YYYY'),TO_TIMESTAMP('2014-07-05 02:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),8,2223,'Mali','France',400,20,30,42);
insert into volpassager values (149,TO_DATE('20-03-2019','DD-MM-YYYY'),TO_DATE('20-03-2019','DD-MM-YYYY'),TO_TIMESTAMP('2014-07-06 01:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),8,2923,'Mali','France',400,20,30,52);
insert into volpassager values (140,TO_DATE('20-03-2019','DD-MM-YYYY'),TO_DATE('20-03-2019','DD-MM-YYYY'),TO_TIMESTAMP('2014-07-07 09:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),44,8223,'TUNIS','France',400,20,30,52);
insert into volpassager values (141,TO_DATE('20-03-2019','DD-MM-YYYY'),TO_DATE('20-03-2019','DD-MM-YYYY'),TO_TIMESTAMP('2014-07-08 09:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),8,2223,'GUINNE','France',400,20,30,62);
insert into volpassager values (142,TO_DATE('20-03-2019','DD-MM-YYYY'),TO_DATE('20-03-2019','DD-MM-YYYY'),TO_TIMESTAMP('2014-07-09 09:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),66,2333,'Mali','France',400,20,30,12);
insert into volpassager values (143,TO_DATE('20-03-2019','DD-MM-YYYY'),TO_DATE('20-03-2019','DD-MM-YYYY'),TO_TIMESTAMP('2014-07-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),8,1123,'USA','France',400,20,30,12);


insert into pilote values (10,'momo','toure','rue askia modibo','Mali',500,'Marseille',2,140);
insert into pilote values (11,'SALIF','toure','rue stalingrad ','France',900,'libreville',3,142);
insert into pilote values (12,'drissa','cisse','rue valie ','USA',500,'bko',4,143);
insert into pilote values (13,'cisse','toure','rue mohamed IV ','Mali',5000,'dijon',2,145);
insert into pilote values (14,'fatim','moi','rue  modibo keita','ALGER',500,'bandiagara',5,145);
insert into pilote values (15,'kadi','coul','rue alpha','TUNIS',5008,'toulon',6,146);
insert into pilote values (16,'soufiane','kabad','rue beta ','Mali',600,'kati',7,146);
insert into pilote values (17,'idriss','sagara','rue askia ','ALGER',300,'vienne',8,147);
insert into pilote values (18,'yacouba','sagara','rue bu ','Mali',100,'gao',4,148);
insert into pilote values (19,'addrahamane','ab','rue as be','TUNIS',50,'kidal',5,148);

insert into hotesse values (20,'mousou','prpe','Arabe','Français','espagnol','Marseille',500,2,140);
insert into hotesse values (21,'BATOS','toi','bambara','Français','espagnol','egypte',500,3,141);
insert into hotesse values (22,'kadi','moi','peul','Français','espagnol','france',500,3,142);
insert into hotesse values (23,'dene','prpe','dogon','Français','espagnol','renne',500,4,140);
insert into hotesse values (24,'totuot','il','Arabe','Français','espagnol','lyon',500,4,144);
insert into hotesse values (25,'mousou','ou','senoufo','Français','espagnol','bko',500,5,145);
insert into hotesse values (26,'ramos','prpe','Arabe','Français','espagnol','kidal',500,6,146);
insert into hotesse values (27,'mousou','ellle','marakakan','Français','espagnol','alger',500,7,147);
insert into hotesse values (28,'cristiano','preepe','Arabe','Français','espagnol','sikasso',5008,8,148);
insert into hotesse values (29,'ronaldo','sonppa','songhoi','Français','espagnol','mopti',500,8,141);


insert into client values (30,'ronaldo','sonppa','139 RUE songhoi','38000','madrid','espagne','500AAZ');
insert into client values (31,'FRDeric','kanoute','139 RUE songhoi','38000','Mali','bko','600AAZZ');
insert into client values (32,'moulaye','diarra','139 RUE songhoi','38000','senegal','dkr','700AAZZ');
insert into client values (33,'idriss','sagara','139 RUE songhoi','38000','Mali','mopti','200AAZZ');
insert into client values (34,'yacouba','sagara','139 RUE songhoi','38000','france','paris','100AAZZ');
insert into client values (35,'rokia','sagara','139 RUE songhoi','38000','france','grenoble','300AAZZ');
insert into client values (36,'kadi','berthe','139 RUE songhoi','38000','usa','new york','400AAZZ');
insert into client values (37,'moi','toi','139 RUE songhoi','38000','usa','geogie','800AAZZ');
insert into client values (38,'lui','elle','139 RUE songhoi','38000','usa','texas','900AAZZ');
insert into client values (39,'nous','vous','139 RUE songhoi','38000','maroc','casa','45500AAZZ');

insert into reservation values (100,TO_TIMESTAMP('2014-07-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),11,144,30);
insert into reservation values (101,TO_TIMESTAMP('2014-07-03 01:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),12,140,31);
insert into reservation values (102,TO_TIMESTAMP('2014-07-04 02:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),13,141,32);
insert into reservation values (103,TO_TIMESTAMP('2014-07-05 03:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),14,142,33);
insert into reservation values (104,TO_TIMESTAMP('2014-07-06 04:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),15,143,34);
insert into reservation values (105,TO_TIMESTAMP('2014-07-07 05:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),16,144,35);
insert into reservation values (106,TO_TIMESTAMP('2014-07-08 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),17,145,36);
insert into reservation values (107,TO_TIMESTAMP('2014-07-09 07:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),12,146,37);
insert into reservation values (108,TO_TIMESTAMP('2014-07-10 08:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),17,146,38);
insert into reservation values (109,TO_TIMESTAMP('2014-07-09 09:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),11,147,39);

