insert into user (id,first_name,last_name,email,cpf) values (1,'Breno','Araujo','breno.souza.araujo@hotmail.com','12132123');
insert into user (id,first_name,last_name,email,cpf) values (2,'Gustavo','Sousa','gustavo@hotmail.com','12132123');
insert into user (id,first_name,last_name,email,cpf) values (3,'Guilherme','Andrade','guilherme@hotmail.com','12132123');



insert into vehicle (id, plate, model) values (1, 'aaa-123','Nissan versa');
insert into vehicle (id, plate, model) values (2, 'bbb-456','Ford Focus');
insert into vehicle (id, plate, model, user_id) values (3, 'ftx-000','Nissan versa', 1);


insert into parking_spot (id, name, available) values (1,'p1',1),(2,'p2',0),(3,'p3',1),(4,'p4',0);


insert into parking_rental (id, vehicle_id, parking_spot_id, start_date, end_date) values (1,1,1,'2022-11-05T13:19:59', '2022-11-05T14:19:59');
insert into parking_rental (id, vehicle_id, parking_spot_id, start_date, end_date) values (2,2,2,'2022-11-05T14:19:59', '2022-11-05T17:19:59');
insert into parking_rental (id, vehicle_id, parking_spot_id, start_date) values (3,3,3,'2022-11-05T13:19:59');