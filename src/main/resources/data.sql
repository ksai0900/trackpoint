/*INSERT INTO customer (id, name) VALUES (1, 'Ellison Hellberg');
INSERT INTO customer (id, name) VALUES (2, 'Hellen Smith');
INSERT INTO customer (id, name) VALUES (3, 'Joe Johnson');
INSERT INTO customer (id, name) VALUES (4, 'Alecia Brown');
INSERT INTO customer (id, name) VALUES (5, 'Bella Miller');
INSERT INTO customer (id, name) VALUES (6, 'Charlotte Williams');
INSERT INTO customer (id, name) VALUES (7, 'Diana Davis');
INSERT INTO customer (id, name) VALUES (8, 'Felicia Miller');
INSERT INTO customer (id, name) VALUES (9, 'Gina Hernandez');
INSERT INTO customer (id, name) VALUES (10, 'Yennefer von Vengerberg');


INSERT INTO app_user (id, username, password) VALUES (1, 'jdoe', 'password');
INSERT INTO app_user (id, username, password) VALUES (2, 'heartJane101', 'password');
INSERT INTO app_user (id, username, password) VALUES (3, 'secretaryAnne', 'password');
INSERT INTO app_user (id, username, password) VALUES (4, 'doctorHodge', 'password');
INSERT INTO app_user (id, username, password) VALUES (5, 'dlaw', 'password');
INSERT INTO app_user (id, username, password) VALUES (6, 'spielberg007', 'password');

INSERT INTO secretary (id, name, user_id) VALUES (1, 'Jane Doe', 2);
INSERT INTO secretary (id, name, user_id) VALUES (2, 'Anne Lawrence', 3);

INSERT INTO doctor (id, name, specialty, secretary_id, user_id)
VALUES (1, 'Dr. Doe', 'Pediatrician', 1, 1);
INSERT INTO doctor (id, name, specialty, secretary_id, user_id)
VALUES (2, 'Dr. Hodge', 'Surgeon', 2, 4);
INSERT INTO doctor (id, name, specialty, secretary_id, user_id)
VALUES (3, 'Dr. Jennifer', 'General Practitioner', 1, 5);
INSERT INTO doctor (id, name, specialty, secretary_id, user_id)
VALUES (4, 'Dr. Spielberg', 'Anesthesiologist', 1, 6);


-- Dr. Jennifer Appointments

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (1, true, 'Checkup', '2023-08-01 10:00:00', '2023-08-01 10:30:00', 5, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (2, true, 'Skin disorders', '2023-08-01 11:00:00', '2023-08-01 12:00:00', 7, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (3, false, 'Vaccination', '2023-08-02 11:00:00', '2023-08-02 12:00:00', 3, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (4, true, 'Vaccination', '2023-08-03 10:00:00', '2023-08-03 11:00:00', 2, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (5, false, 'Hormonal issues', '2023-08-04 10:30:00', '2023-08-04 11:00:00', 6, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (6, false, 'Chest pains', '2023-08-05 10:30:00', '2023-08-05 11:30:00', 4, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (7, false, 'Prescription', '2023-08-08 10:00:00', '2023-08-08 10:30:00', 1, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (8, false, 'Checkup', '2023-08-08 11:00:00', '2023-08-08 12:00:00', 8, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (9, false, 'Skin disorders', '2023-08-09 10:30:00', '2023-08-09 11:30:00', 10, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (10, false, 'Vaccination', '2023-08-10 10:30:00', '2023-08-10 11:00:00', 9, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (11, false, 'Hormonal issues', '2023-08-11 10:00:00', '2023-08-11 10:30:00', 5, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (12, false, 'Chest pains', '2023-08-11 11:00:00', '2023-08-11 12:00:00', 7, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (13, false, 'Vaccination', '2023-08-15 10:00:00', '2023-08-15 10:30:00', 3, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (14, false, 'Skin disorders', '2023-08-15 11:00:00', '2023-08-15 12:00:00', 2, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (15, false, 'Checkup', '2023-08-16 10:00:00', '2023-08-16 10:30:00', 6, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (16, false, 'Hormonal issues', '2023-08-17 10:30:00', '2023-08-17 11:00:00', 4, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (17, false, 'Prescription', '2023-08-18 10:00:00', '2023-08-18 10:30:00', 1, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (18, false, 'Checkup', '2023-08-18 11:00:00', '2023-08-18 12:00:00', 8, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (19, false, 'Vaccination', '2023-08-22 10:30:00', '2023-08-22 11:00:00', 10, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (20, false, 'Skin disorders', '2023-08-23 10:00:00', '2023-08-23 11:00:00', 9, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (21, false, 'Checkup', '2023-08-24 10:00:00', '2023-08-24 10:30:00', 5, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (22, false, 'Hormonal issues', '2023-08-24 11:00:00', '2023-08-24 12:00:00', 7, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (23, false, 'Prescription', '2023-08-25 10:30:00', '2023-08-25 11:00:00', 3, 3);

-- Dr. Doe Appointments


INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (24, false, 'Checkup', '2023-08-25 11:30:00', '2023-08-25 12:00:00', 2, 3);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (25, false, 'Child Checkup', '2023-08-01 09:00:00', '2023-08-01 09:30:00', 1, 1);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (26, false, 'Vaccination', '2023-08-02 09:00:00', '2023-08-02 09:45:00', 2, 1);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (27, false, 'Child Sickness', '2023-08-03 09:30:00', '2023-08-03 10:00:00', 3, 1);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (28, false, 'Child Annual Checkup', '2023-08-04 09:00:00', '2023-08-04 09:30:00', 4, 1);

-- Dr. Hodge Appointments
INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (29, false, 'Surgery', '2023-08-01 10:00:00', '2023-08-01 12:00:00', 5, 2);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (30, false, 'Post-Op Checkup', '2023-08-03 10:00:00', '2023-08-03 11:00:00', 6, 2);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (31, false, 'Surgery', '2023-08-04 10:00:00', '2023-08-04 12:00:00', 7, 2);

-- Dr. Spielberg Appointments (matches surgery times)

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (32, false, 'Anesthesia for Surgery', '2023-08-01 10:00:00', '2023-08-01 12:00:00', 5, 4);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (33, false, 'Post-Op Monitoring', '2023-08-03 10:00:00', '2023-08-03 11:00:00', 6, 4);

INSERT INTO appointments (id, appointment_completed, description, start_date, end_date, customer_id, doctor_id)
VALUES (34, false, 'Anesthesia for Surgery', '2023-08-04 10:00:00', '2023-08-04 12:00:00', 7, 4);*/