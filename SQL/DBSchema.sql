-- Create database
CREATE DATABASE AirlineReservationSystem;
USE AirlineReservationSystem;

-- Create Flights table
CREATE TABLE Flights (
    flight_id INT PRIMARY KEY AUTO_INCREMENT,
    airline VARCHAR(100),
    departure_airport VARCHAR(50),
    arrival_airport VARCHAR(50),
    departure_time DATETIME,
    arrival_time DATETIME,
    total_seats INT,
    available_seats INT
);

-- Create Seats table
CREATE TABLE Seats (
    seat_id INT PRIMARY KEY AUTO_INCREMENT,
    flight_id INT,
    seat_number VARCHAR(10),
    class VARCHAR(50),
    status ENUM('available', 'booked'),
    FOREIGN KEY (flight_id) REFERENCES Flights(flight_id)
);

-- Create Bookings table
CREATE TABLE Bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    flight_id INT,
    seat_id INT,
    booking_date DATETIME,
    payment_status ENUM('pending', 'paid'),
    FOREIGN KEY (flight_id) REFERENCES Flights(flight_id),
    FOREIGN KEY (seat_id) REFERENCES Seats(seat_id)
);

-- Create Users table
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    contact_number VARCHAR(15)
);

-- Create Payments table
CREATE TABLE Payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT,
    amount DECIMAL(10, 2),
    payment_date DATETIME,
    payment_method VARCHAR(50),
    FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id)
);

