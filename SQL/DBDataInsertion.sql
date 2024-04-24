use airlinereservationsystem;

INSERT INTO Flights (airline, departure_airport, arrival_airport, departure_time, arrival_time, total_seats, available_seats)
VALUES
    ('Air France', 'Charles de Gaulle Airport', 'JFK', STR_TO_DATE('25-04-2024 08:20:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-04-2024 11:50:00', '%d-%m-%Y %H:%i:%s'), 300, 250),
    ('British Airways', 'LHR', 'San Francisco International Airport', STR_TO_DATE('22-04-2024 10:40:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-04-2024 13:10:00', '%d-%m-%Y %H:%i:%s'), 280, 220),
    ('Emirates', 'DXB', 'JFK', STR_TO_DATE('26-04-2024 09:15:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-04-2024 17:45:00', '%d-%m-%Y %H:%i:%s'), 350, 300),
    ('Lufthansa', 'Frankfurt Airport', 'LAX', STR_TO_DATE('23-04-2024 13:30:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-04-2024 16:00:00', '%d-%m-%Y %H:%i:%s'), 320, 280),
    ('Qatar Airways', 'DOH', 'London Heathrow Airport', STR_TO_DATE('21-04-2024 06:45:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-04-2024 11:15:00', '%d-%m-%Y %H:%i:%s'), 310, 270),
    ('Singapore Airlines', 'SIN', 'SFO', STR_TO_DATE('24-04-2024 11:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-04-2024 09:30:00', '%d-%m-%Y %H:%i:%s'), 290, 250),
    ('United Airlines', 'ORD', 'LHR', STR_TO_DATE('27-04-2024 14:20:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-04-2024 17:50:00', '%d-%m-%Y %H:%i:%s'), 330, 290),
    ('Air Canada', 'YYZ', 'FRA', STR_TO_DATE('22-04-2024 07:15:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-04-2024 09:45:00', '%d-%m-%Y %H:%i:%s'), 270, 230),
    ('Cathay Pacific', 'HKG', 'LAX', STR_TO_DATE('25-04-2024 12:45:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-04-2024 11:15:00', '%d-%m-%Y %H:%i:%s'), 340, 310),
    ('Turkish Airlines', 'IST', 'JFK', STR_TO_DATE('23-04-2024 10:55:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-04-2024 15:25:00', '%d-%m-%Y %H:%i:%s'), 300, 260),
    ('American Airlines', 'DFW', 'LHR', STR_TO_DATE('26-04-2024 13:25:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-04-2024 16:55:00', '%d-%m-%Y %H:%i:%s'), 310, 280),
    ('Delta Air Lines', 'ATL', 'AMS', STR_TO_DATE('21-04-2024 06:30:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-04-2024 10:00:00', '%d-%m-%Y %H:%i:%s'), 290, 250),
    ('KLM', 'Amsterdam Airport Schiphol', 'JFK', STR_TO_DATE('24-04-2024 12:10:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-04-2024 14:40:00', '%d-%m-%Y %H:%i:%s'), 320, 290),
    ('Qantas', 'SYD', 'LHR', STR_TO_DATE('22-04-2024 10:25:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-04-2024 20:55:00', '%d-%m-%Y %H:%i:%s'), 360, 330),
    ('Swiss Air', 'ZRH', 'JFK', STR_TO_DATE('27-04-2024 14:40:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-04-2024 18:10:00', '%d-%m-%Y %H:%i:%s'), 280, 240),
    ('Air New Zealand', 'AKL', 'SFO', STR_TO_DATE('23-04-2024 07:50:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('23-04-2024 09:20:00', '%d-%m-%Y %H:%i:%s'), 300, 270),
    ('Japan Airlines', 'NRT', 'LAX', STR_TO_DATE('25-04-2024 13:15:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('25-04-2024 10:45:00', '%d-%m-%Y %H:%i:%s'), 330, 300),
    ('Korean Air', 'ICN', 'JFK', STR_TO_DATE('21-04-2024 08:40:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-04-2024 11:10:00', '%d-%m-%Y %H:%i:%s'), 310, 280),
    ('Virgin Atlantic', 'LHR', 'LAX', STR_TO_DATE('26-04-2024 15:05:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('26-04-2024 18:35:00', '%d-%m-%Y %H:%i:%s'), 290, 250),
    ('Etihad Airways', 'AUH', 'JFK', STR_TO_DATE('24-04-2024 09:35:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('24-04-2024 15:05:00', '%d-%m-%Y %H:%i:%s'), 320, 290),
    ('Scandinavian Airlines', 'CPH', 'LAX', STR_TO_DATE('27-04-2024 13:55:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('27-04-2024 16:25:00', '%d-%m-%Y %H:%i:%s'), 280, 240);

INSERT INTO Flights (airline, departure_airport, arrival_airport, departure_time, arrival_time, total_seats, available_seats)
VALUES
('Delta Airlines', 'JFK', 'LAX', STR_TO_DATE('21-04-2024 08:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-04-2024 12:00:00', '%d-%m-%Y %H:%i:%s'), 200, 200),
('American Airlines', 'LAX', 'ORD', STR_TO_DATE('21-04-2024 09:30:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-04-2024 13:30:00', '%d-%m-%Y %H:%i:%s'), 180, 180),
('United Airlines', 'ORD', 'SFO', STR_TO_DATE('21-04-2024 10:45:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-04-2024 14:45:00', '%d-%m-%Y %H:%i:%s'), 220, 220),
('Southwest Airlines', 'SFO', 'DFW', STR_TO_DATE('21-04-2024 12:15:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-04-2024 16:15:00', '%d-%m-%Y %H:%i:%s'), 190, 190),
('JetBlue Airways', 'DFW', 'ATL', STR_TO_DATE('21-04-2024 13:45:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-04-2024 17:45:00', '%d-%m-%Y %H:%i:%s'), 210, 210),
('Alaska Airlines', 'ATL', 'DEN', STR_TO_DATE('21-04-2024 15:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-04-2024 19:00:00', '%d-%m-%Y %H:%i:%s'), 230, 230),
('Frontier Airlines', 'DEN', 'SEA', STR_TO_DATE('21-04-2024 16:30:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-04-2024 20:30:00', '%d-%m-%Y %H:%i:%s'), 170, 170),
('Spirit Airlines', 'SEA', 'MCO', STR_TO_DATE('21-04-2024 18:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-04-2024 22:00:00', '%d-%m-%Y %H:%i:%s'), 250, 250),
('Virgin America', 'MCO', 'PHX', STR_TO_DATE('21-04-2024 19:45:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-04-2024 23:45:00', '%d-%m-%Y %H:%i:%s'), 160, 160),
('Hawaiian Airlines', 'PHX', 'BOS', STR_TO_DATE('21-04-2024 21:15:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-04-2024 01:15:00', '%d-%m-%Y %H:%i:%s'), 270, 270),
('Allegiant Air', 'BOS', 'MIA', STR_TO_DATE('22-04-2024 00:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-04-2024 04:00:00', '%d-%m-%Y %H:%i:%s'), 180, 180),
('Sun Country Airlines', 'MIA', 'LAS', STR_TO_DATE('22-04-2024 02:30:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-04-2024 06:30:00', '%d-%m-%Y %H:%i:%s'), 200, 200),
('Breeze Airways', 'LAS', 'IAH', STR_TO_DATE('22-04-2024 04:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-04-2024 08:00:00', '%d-%m-%Y %H:%i:%s'), 210, 210),
('SkyWest Airlines', 'IAH', 'MDW', STR_TO_DATE('22-04-2024 05:45:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-04-2024 09:45:00', '%d-%m-%Y %H:%i:%s'), 220, 220),
('ExpressJet', 'MDW', 'BWI', STR_TO_DATE('22-04-2024 07:15:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-04-2024 11:15:00', '%d-%m-%Y %H:%i:%s'), 230, 230),
('Compass Airlines', 'BWI', 'PDX', STR_TO_DATE('22-04-2024 08:30:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-04-2024 12:30:00', '%d-%m-%Y %H:%i:%s'), 240, 240),
('Republic Airways', 'PDX', 'CLT', STR_TO_DATE('22-04-2024 10:00:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-04-2024 14:00:00', '%d-%m-%Y %H:%i:%s'), 250, 250),
('GoJet Airlines', 'CLT', 'TPA', STR_TO_DATE('22-04-2024 11:45:00', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-04-2024 15:45:00', '%d-%m-%Y %H:%i:%s'), 260, 260);

