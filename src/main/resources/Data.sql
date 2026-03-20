INSERT INTO USERS (voorNaam, achterNaam, email, password, role)
VALUES
    ('Adam', 'User', 'admin@example.com', '$2a$10$iletpdKiTRfS9evszK8tQ.oMSWbhy1FPlYup/Z.QpWCwdiIzK3oXW', 'Admin'),
    ('Student', 'Van Den Berg', 'student@example.com', '$2a$10$qgfM9AimMHe1GDG9yGoIg.S6c2o3vPmPhAV2DdpUFAtKh5fUuc52u', 'USER'),
    ('Business', 'Owner', 'business@example.com', '$2a$10$I0hILdkFYlfQmPb7dPQfPeIUQebt9zQXc0tO8CKFGczTv1oqagsrm', 'USER'),
    ('Freelancer', 'Smith', 'freelancer@example.com', '$2a$10$XHhN17WB5rkACG.oa/x0aeb4H7hD8B.IKnqu5lC9/e4KpjVDV6ALC', 'USER'),
    ('Retired', 'Johnson', 'retired@example.com', '$2a$10$clM6da.0QP2VNwQY/WFlDeYaB0wc6goHiFdypr6aicMeAa3IbefP.', 'USER'),
    ('Regular', 'User', 'user@example.com', '$2a$10$W.SXbElstMrzRfI.OU.oy.6oUQJ5H/QrvVSznFyqnRLmQFYk4MMNe', 'USER');

-- Insert sample belastingen (tax records)
INSERT INTO BELASTINGEN (userid, belastingsoort, belastingJaar, inkomen, belastingBedrag)
VALUES
    (1, 'Inkomen', 2023, 50000.0, 10000.0),
    (1, 'Inkomen', 2024, 48000.0, 9500.0),
    (1, 'Inkomen', 2025, 60000.0, 12000.0),
    (1, 'Inkomen', 2026, 15000.0, 3000.0),

    (2, 'Inkomen', 2025, 42000.0, 7500.0),
    (2, 'Inkomen', 2026, 45000.0, 8000.0),

    (3, 'Inkomen', 2026, 35000.0, 6000.0),

    (4, 'Inkomen', 2025, 8000.0, 1600.0),

    (5, 'Inkomen', 2022, 38000.0, 6500.0),
    (5, 'Inkomen', 2024, 10000.0, 2000.0);
