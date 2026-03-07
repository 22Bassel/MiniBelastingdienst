INSERT INTO USERS (voorNaam, achterNaam, email, password, isAdmin)
VALUES
    ('Adam', 'User', 'admin@example.com', 'password123', false),
    ('Student', 'Van Den Berg', 'student@example.com', 'student123', false),
    ('Business', 'Owner', 'business@example.com', 'businesspass', false),
    ('Freelancer', 'Smith', 'freelancer@example.com', 'freelance123', false),
    ('Retired', 'Johnson', 'retired@example.com', 'retiredpass', false),
    ('Regular', 'User', 'user@example.com', 'password456', false);

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
