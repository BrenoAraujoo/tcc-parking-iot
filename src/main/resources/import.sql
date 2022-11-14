insert into tb_user (first_name,last_name,email,cpf) values ('Breno','Araujo','breno.souza.araujo@hotmail.com','12132123');
insert into tb_user (first_name,last_name,email,cpf) values ('Gustavo','Sousa','gustavo@hotmail.com','12132123');
insert into tb_user (first_name,last_name,email,cpf) values ('Guilherme','Andrade','guilherme@hotmail.com','12132123');


insert into tb_plate (plate_number) values ('aaa-123'),('bbb-456'),('ccc-000')

insert into tb_vehicle (plate_id, model) values (1,'Nissan versa');
insert into tb_vehicle (plate_id, model) values (2,'Ford Focus');
insert into tb_vehicle (plate_id, model, user_id) values (3,'Nissan versa', 1);


insert into tb_parking_spot (name, available) values ('p1',false),('p2',false),('p3',false),('p4',false);

--
insert into tb_parking_rental (id, plate_id, parking_spot_id, start_date, end_date) values (1,1,1,'2022-11-05T13:19:59', '2022-11-05T14:19:59');
insert into tb_parking_rental (id, plate_id, parking_spot_id, start_date, end_date) values (2,2,2,'2022-11-05T14:19:59', '2022-11-05T17:19:59');
insert into tb_parking_rental (id, plate_id, parking_spot_id, start_date) values (3,3,3,'2022-11-05T13:19:59');