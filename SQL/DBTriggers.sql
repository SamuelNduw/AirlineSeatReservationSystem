use airlinereservationsystem;

DELIMITER //
CREATE TRIGGER AfterFlightAdded AFTER INSERT ON Flights
FOR EACH ROW
BEGIN
    DECLARE seat_number INT DEFAULT 1;
    WHILE seat_number <= NEW.total_seats DO
        INSERT INTO Seats (flight_id, seat_number, status)
        VALUES (NEW.flight_id, seat_number, 'available');
        SET seat_number = seat_number + 1;
    END WHILE;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER BeforeFlightDeleted BEFORE DELETE ON Flights
FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM Bookings WHERE flight_id = OLD.flight_id) > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot delete flight with existing bookings';
    END IF;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER AfterBookingCreated AFTER INSERT ON Bookings
FOR EACH ROW
BEGIN
    UPDATE Flights
    SET available_seats = available_seats - 1
    WHERE flight_id = NEW.flight_id;
    
    UPDATE Seats
    SET status = 'booked'
    WHERE seat_id = NEW.seat_id;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER AfterBookingCancelled AFTER UPDATE ON Bookings
FOR EACH ROW
BEGIN
    IF NEW.payment_status = 'cancelled' THEN
        UPDATE Flights
        SET available_seats = available_seats + 1
        WHERE flight_id = OLD.flight_id;
        
        UPDATE Seats
        SET status = 'available'
        WHERE seat_id = OLD.seat_id;
    END IF;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER AfterPaymentProcessed AFTER INSERT ON Payments
FOR EACH ROW
BEGIN
    UPDATE Bookings
    SET payment_status = 'paid'
    WHERE booking_id = NEW.booking_id;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER BeforeUserDeleted BEFORE DELETE ON Users
FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM Bookings WHERE user_id = OLD.user_id) > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot delete user with existing bookings';
    END IF;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER AfterSeatStatusChanged AFTER UPDATE ON Seats
FOR EACH ROW
BEGIN
    IF NEW.status = 'booked' AND (SELECT COUNT(*) FROM Bookings WHERE seat_id = NEW.seat_id) = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Seat status changed to booked without a corresponding booking';
    END IF;
END;
//
DELIMITER ;

