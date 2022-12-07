

--Plate
insert into tb_plate (plate_number) values ('BRA2E19'),('BEE4R22')


--User
insert into tb_user (first_name,last_name,email,cpf, plate_id) values ('Breno','Araujo','breno.souza.araujo@hotmail.com','12132123', 1);
insert into tb_user (first_name,last_name,email,cpf, plate_id) values ('Gustavo','Sousa','gustavo@hotmail.com','12132123', 2);

--Parking spot
insert into tb_parking_spot (name, available) values ('A1',true),('A2',true),('A3',true);

-- Parking rental
insert into tb_parking_rental (plate_id, parking_spot_id, start_date, end_date, is_registered) values (1,1,'2022-11-05T13:19:59', '2022-11-05T14:19:59', true);
insert into tb_parking_rental (plate_id, parking_spot_id, start_date, end_date, is_registered) values (2,2,'2022-11-05T14:19:59', '2022-11-05T17:19:59', true);
