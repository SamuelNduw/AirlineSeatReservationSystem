use airlinereservationsystem;

DELIMITER //
CREATE PROCEDURE CreateBooking(IN p_user_id INT, IN p_flight_id INT, IN p_seat_id INT)
BEGIN
    INSERT INTO Bookings (user_id, flight_id, seat_id, booking_date, payment_status)
    VALUES (p_user_id, p_flight_id, p_seat_id, NOW(), 'pending');
    
    UPDATE Seats
    SET status = 'booked'
    WHERE seat_id = p_seat_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE CancelBooking(IN p_booking_id INT)
BEGIN
    UPDATE Bookings
    SET payment_status = 'cancelled'
    WHERE booking_id = p_booking_id;
    
    UPDATE Seats
    SET status = 'available'
    WHERE seat_id = (SELECT seat_id FROM Bookings WHERE booking_id = p_booking_id);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE SearchFlights(IN p_departure_airport VARCHAR(50), IN p_arrival_airport VARCHAR(50), IN p_departure_date DATE)
BEGIN
    SELECT * FROM Flights
    WHERE departure_airport = p_departure_airport
    AND arrival_airport = p_arrival_airport
    AND DATE(departure_time) = p_departure_date;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE CheckIn(IN p_booking_id INT)
BEGIN
    UPDATE Bookings
    SET payment_status = 'checked-in'
    WHERE booking_id = p_booking_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE ProcessPayment(IN p_booking_id INT, IN p_amount DECIMAL(10,2), IN p_payment_method VARCHAR(50))
BEGIN
    INSERT INTO Payments (booking_id, amount, payment_date, payment_method)
    VALUES (p_booking_id, p_amount, NOW(), p_payment_method);
    
    UPDATE Bookings
    SET payment_status = 'paid'
    WHERE booking_id = p_booking_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE UpdateFlightDetails(IN p_flight_id INT, IN p_airline VARCHAR(100), IN p_departure_airport VARCHAR(50), IN p_arrival_airport VARCHAR(50), IN p_departure_time DATETIME, IN p_arrival_time DATETIME)
BEGIN
    UPDATE Flights
    SET airline = p_airline,
        departure_airport = p_departure_airport,
        arrival_airport = p_arrival_airport,
        departure_time = p_departure_time,
        arrival_time = p_arrival_time
    WHERE flight_id = p_flight_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE AddFlight(IN p_airline VARCHAR(100), IN p_departure_airport VARCHAR(50), IN p_arrival_airport VARCHAR(50), IN p_departure_time DATETIME, IN p_arrival_time DATETIME, IN p_total_seats INT)
BEGIN
    INSERT INTO Flights (airline, departure_airport, arrival_airport, departure_time, arrival_time, total_seats, available_seats)
    VALUES (p_airline, p_departure_airport, p_arrival_airport, p_departure_time, p_arrival_time, p_total_seats, p_total_seats);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteFlight(IN p_flight_id INT)
BEGIN
    DELETE FROM Flights
    WHERE flight_id = p_flight_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE RegisterUser(IN p_name VARCHAR(100), IN p_email VARCHAR(100), IN p_password VARCHAR(100), IN p_contact_number VARCHAR(15))
BEGIN
    INSERT INTO Users (name, email, password, contact_number)
    VALUES (p_name, p_email, p_password, p_contact_number);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE LoginUser(IN p_email VARCHAR(100), IN p_password VARCHAR(100))
BEGIN
    SELECT * FROM Users
    WHERE email = p_email AND password = p_password;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE UpdateUserDetails(IN p_user_id INT, IN p_name VARCHAR(100), IN p_email VARCHAR(100), IN p_contact_number VARCHAR(15))
BEGIN
    UPDATE Users
    SET name = p_name, email = p_email, contact_number = p_contact_number
    WHERE user_id = p_user_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE ViewBookingHistory(IN p_user_id INT)
BEGIN
    SELECT * FROM Bookings
    WHERE user_id = p_user_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GenerateBookingReport(IN p_start_date DATE, IN p_end_date DATE)
BEGIN
    SELECT * FROM Bookings
    WHERE booking_date BETWEEN p_start_date AND p_end_date;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE ManageSeatAllocation(IN p_booking_id INT, IN p_seat_id INT)
BEGIN
    UPDATE Bookings
    SET seat_id = p_seat_id
    WHERE booking_id = p_booking_id;
    
    UPDATE Seats
    SET status = 'booked'
    WHERE seat_id = p_seat_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE UpdatePaymentStatus(IN p_booking_id INT, IN p_payment_status ENUM('pending', 'paid', 'cancelled'))
BEGIN
    UPDATE Bookings
    SET payment_status = p_payment_status
    WHERE booking_id = p_booking_id;
END //users
DELIMITER ;
bookingsseats
DELIMITER //
CREATE PROCEDURE GetAvailableSeats(IN p_flight_id INT)
BEGIN
    SELECT * FROM Seats
    WHERE flight_id = p_flight_id AND status = 'available';
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetPassword(IN p_email VARCHAR(100))
BEGIN
    SELECT password FROM Users WHERE email = p_email;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetSeatsByClass(
    IN p_flight_id INT,
    IN p_class VARCHAR(50)
)
BEGIN
    SELECT *
    FROM Seats
    WHERE flight_id = p_flight_id AND class = p_class;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetBookingID(
    IN p_user_id INT,
    IN p_flight_id INT,
    IN p_seat_id INT
)
BEGIN
    SELECT booking_id
    FROM Bookings
    WHERE user_id = p_user_id AND flight_id = p_flight_id AND seat_id = p_seat_id;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE UpdateSeatClasses(
    IN p_flight_id INT,
    IN p_first_class INT,
    IN p_business_class INT,
    IN p_economy_class INT
)
BEGIN
    DECLARE total_seats INT;
    DECLARE updated_seats INT DEFAULT 0;

    -- Update First Class seats
    SET total_seats = p_first_class;
    WHILE updated_seats < total_seats DO
        UPDATE Seats
        SET class = 'First'
        WHERE flight_id = p_flight_id AND class IS NULL
        LIMIT 1;
        SET updated_seats = updated_seats + 1;
    END WHILE;

    -- Update Business Class seats
    SET total_seats = p_business_class;
    SET updated_seats = 0;
    WHILE updated_seats < total_seats DO
        UPDATE Seats
        SET class = 'Business'
        WHERE flight_id = p_flight_id AND class IS NULL
        LIMIT 1;
        SET updated_seats = updated_seats + 1;
    END WHILE;

    -- Update Economy Class seats
    SET total_seats = p_economy_class;
    SET updated_seats = 0;
    WHILE updated_seats < total_seats DO
        UPDATE Seats
        SET class = 'Economy'
        WHERE flight_id = p_flight_id AND class IS NULL
        LIMIT 1;
        SET updated_seats = updated_seats + 1;
    END WHILE;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetUserBookingHistory(
    IN p_user_id INT
)
BEGIN
    SELECT 
        F.flight_id,
        F.airline,
        F.departure_airport,
        F.arrival_airport,
        F.departure_time,
        F.arrival_time,
        S.seat_number,
        P.amount,
        P.payment_method,
        P.payment_date
    FROM Bookings B
    JOIN Flights F ON B.flight_id = F.flight_id
    JOIN Seats S ON B.seat_id = S.seat_id
    JOIN Payments P ON B.booking_id = P.booking_id
    WHERE B.user_id = p_user_id;
END;
//
DELIMITER ;




