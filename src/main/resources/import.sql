

--Plate
insert into tb_plate (plate_number) values ('aaa-123'),('bbb-456'),('ccc-000')


--User
insert into tb_user (first_name,last_name,email,cpf, plate_id) values ('Breno','Araujo','breno.souza.araujo@hotmail.com','12132123', 1);
insert into tb_user (first_name,last_name,email,cpf, plate_id) values ('Gustavo','Sousa','gustavo@hotmail.com','12132123', 2);
insert into tb_user (first_name,last_name,email,cpf, plate_id) values ('Guilherme','Andrade','guilherme@hotmail.com','12132123', 3);


--Parking spot
insert into tb_parking_spot (name, available) values ('A1',true),('A2',true),('A3',true),('A4',true),('A5',true),('A6',true);

-- Parking rental
insert into tb_parking_rental (plate_id, parking_spot_id, start_date, end_date, is_registered) values (1,1,'2022-11-05T13:19:59', '2022-11-05T14:19:59', true);
insert into tb_parking_rental (plate_id, parking_spot_id, start_date, end_date, is_registered) values (2,2,'2022-11-05T14:19:59', '2022-11-05T17:19:59', true);
insert into tb_parking_rental (plate_id, parking_spot_id, start_date, is_registered) values (3,3,'2022-11-05T13:19:59', true);
