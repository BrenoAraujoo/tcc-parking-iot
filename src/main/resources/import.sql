--User
insert into tb_user (first_name,last_name,email,cpf) values ('Breno','Araujo','breno.souza.araujo@hotmail.com','12132123');
insert into tb_user (first_name,last_name,email,cpf) values ('Gustavo','Sousa','gustavo@hotmail.com','12132123');
insert into tb_user (first_name,last_name,email,cpf) values ('Guilherme','Andrade','guilherme@hotmail.com','12132123');

--Plate
insert into tb_plate (plate_number) values ('aaa-123'),('bbb-456'),('ccc-000')

insert into tb_vehicle (plate_id, model) values (1,'Nissan versa');
insert into tb_vehicle (plate_id, model) values (2,'Ford Focus');
insert into tb_vehicle (plate_id, model, user_id) values (3,'Nissan versa', 1);

--Parking spot
insert into tb_parking_spot (name, available) values ('p1',true),('p2',true),('p3',true),('p4',true);

-- Parking rental
insert into tb_parking_rental (id, plate_id, parking_spot_id, start_date, end_date, is_registered) values (1,1,1,'2022-11-05T13:19:59', '2022-11-05T14:19:59', false);
insert into tb_parking_rental (id, plate_id, parking_spot_id, start_date, end_date, is_registered) values (2,2,2,'2022-11-05T14:19:59', '2022-11-05T17:19:59', true);
insert into tb_parking_rental (id, plate_id, parking_spot_id, start_date, is_registered) values (3,3,3,'2022-11-05T13:19:59', false);

--Parking reservation
--insert into tb_park_reservation (id, plate_number, fee_charged, start_date,parking_spot_id) values (1,'aaa-123', false, '2022-11-05T14:22:15',1)

--insert into tb_park_reservation (id, plate_number, fee_charged, start_date, parking_spot_id)values (2,'bbb-000', false,'2022-04-03T14:15:30',2)
--insert into tb_park_reservation (id, plate_number, fee_charged, start_date, parking_spot_id)values (3,'aaa-123', true,'2022-04-03T14:15:30',3)
--insert into tb_park_reservation (id, plate_number, fee_charged, start_date, parking_spot_id)values (4,'aaa-123', true,'2022-04-03T14:15:30',2)
--insert into tb_park_reservation (id, plate_number, fee_charged, start_date, parking_spot_id)values (5,'aaa-123', false,'2022-04-03T14:15:30',1)
