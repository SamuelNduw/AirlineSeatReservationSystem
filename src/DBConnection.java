import Authentication.Auth;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.ArrayList;

public class DBConnection {

    private static String url = "jdbc:mysql://localhost:3306/airlinereservationsystem";
    private static String username = "root";
    private static String password=System.getenv("DBPassword");

    ArrayList<FlightItem> flights;
    FlightItem flight;
    UserItem user;
    SeatItem seat;
    Auth auth;
    ArrayList<SeatItem> seats;
    ArrayList<BookingItem> bookingHistory;
    ArrayList<BookingItem> bookingReport;
    BookingItem booking;


    // Testing
    public static void main(String[] args) {
        DBConnection db = new DBConnection();

    }

    // Flight Management
    // ADD FLIGHT
    public void addFlight(String airline, String departureAirport, String arrivalAirport, String departureTime, String arrivalTime, int totalSeats){
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            CallableStatement cst = conn.prepareCall("{call AddFlight(?, ?, ?, ?, ?, ?)}");
            cst.setString(1, airline);
            cst.setString(2, departureAirport);
            cst.setString(3, arrivalAirport);
            cst.setString(4, departureTime);
            cst.setString(5, arrivalTime);
            cst.setInt(6, totalSeats);

            cst.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    // UPDATE FLIGHT DETAILS
    public void updateFlightDetails(int flightId, String airline, String departureAirport, String arrivalAirport, String departureTime, String arrivalTime){
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            CallableStatement cst = conn.prepareCall(("call updateFlightDetails(?,?,?,?,?,?,?)"));
            cst.setInt(1, flightId);
            cst.setString(2, airline);
            cst.setString(3, departureAirport);
            cst.setString(4, arrivalAirport);
            cst.setString(5, departureTime);
            cst.setString(6, arrivalTime);

            cst.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    // DELETE FLIGHT
    public void deleteFlight(int flightId){
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            CallableStatement cst = conn.prepareCall(("call deleteFlight(?)"));
            cst.setInt(1, flightId);

            cst.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    // SEARCH FLIGHTS
    public ArrayList<FlightItem> searchFlights(String departureAirport, String arrivalAirport, String departureTime){
        flights = new ArrayList<FlightItem>();
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql ="{CALL searchFlights(?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, departureAirport);
            stmt.setString(2, arrivalAirport);
            stmt.setString(3, departureTime);


            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int flightNo = rs.getInt("flight_id");
                String airline = rs.getString("airline");
                String deptAirport = rs.getString("departure_airport");
                String arrAirport = rs.getString("arrival_airport");
                String deptTime = rs.getString("departure_time");
                String arrTime = rs.getString("arrival_time");
                int totSeats = rs.getInt("total_seats");
                int avlSeats = rs.getInt("available_seats");

                flight = new FlightItem(flightNo, airline, deptAirport, arrAirport, deptTime, arrTime, totSeats, avlSeats);
                flights.add(flight);
                System.out.println(flight);

            }
            rs.close();
            stmt.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return flights;
    }
    // RETRIEVE ALL FLIGHTS
    public ArrayList<FlightItem> retrieveAllFlights(){
        flights = new ArrayList<FlightItem>();
        String sql = "SELECT * FROM Flights";
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int flightNo = rs.getInt("flight_id");
                String airline = rs.getString("airline");
                String deptAirport = rs.getString("departure_airport");
                String arrAirport = rs.getString("arrival_airport");
                String deptTime = rs.getString("departure_time");
                String arrTime = rs.getString("arrival_time");
                int totSeats = rs.getInt("total_seats");
                int avlSeats = rs.getInt("available_seats");

                flight = new FlightItem(flightNo, airline, deptAirport, arrAirport, deptTime, arrTime, totSeats, avlSeats);
                flights.add(flight);
                System.out.println(flight);
            }
            rs.close();
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return flights;
    }
    // MANAGE SEAT ALLOCATION
    public void manageSeatAllocation(int bookingID, int seatID){
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "{CALL manageSeatAllocation(?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, bookingID);
            cstmt.setInt(2, seatID);

            cstmt.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    // GET AVAILABLE SEATS
    public ArrayList<SeatItem> getAvailableSeats(int flightID){
        seats = new ArrayList<SeatItem>();
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "{CALL getAvailableSeats(?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, flightID);
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()){
                int seatID = rs.getInt("seat_id");
                int flID = rs.getInt("flight_id");
                String seatNo = rs.getString("seat_number");
                String seatClass = rs.getString("class");
                String status = rs.getString("status");

                seat = new SeatItem(seatID, flID, seatNo, seatClass, status);
                seats.add(seat);
                System.out.println(seat);
            }
            rs.close();
            cstmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return seats;
    }
    // GET SEATS BY CLASS AND FLIGHT ID
    public ArrayList<SeatItem> getSeatsByClass(int flightID, String seatClass){
        seats = new ArrayList<SeatItem>();
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "{CALL getSeatsByClass(?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, flightID);
            cstmt.setString(2, seatClass);
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()){
                int seatID = rs.getInt("seat_id");
                int flID = rs.getInt("flight_id");
                String seatNo = rs.getString("seat_number");
                String stClass = rs.getString("class");
                String status = rs.getString("status");
                seat = new SeatItem(seatID, flID, seatNo, stClass, status);
                seats.add(seat);
                System.out.println(seat);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return seats;
    }

    // User Management
    // REGISTER USER
    public void registerUser(String name, String email, String userPassword, String contactNumber){
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            CallableStatement cstmt = conn.prepareCall("{CALL registerUser(?, ?, ?, ?)}");

            cstmt.setString(1, name);
            cstmt.setString(2, email);
            cstmt.setString(3, userPassword);
            cstmt.setString(4, contactNumber);

            cstmt.execute();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    // LOGIN USER
    public Auth loginUser(String email, String userPassword){
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "{CALL loginUser(?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);

            cstmt.setString(1, email);
            cstmt.setString(2, userPassword);

            ResultSet rs = cstmt.executeQuery();
            if(rs.next()){
                int userID = rs.getInt("user_id");
                String name = rs.getString("name");
                String userEmail = rs.getString("email");
                String userContact = rs.getString("contact_number");
                auth = new Auth(userID, name, userEmail, userContact);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return auth;
    }
    // GET PASSWORD INFORMATION
    public String getPasswordInfo(String email){
        String passwordRet = null;
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "CALL getPassword(?)";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setString(1, email);
            ResultSet rs = cstmt.executeQuery();
            if(rs.next()){
                passwordRet = rs.getString("password");
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return passwordRet;
    }
    // UPDATE USER DETAILS
    public void  updateUserDetails(int userID, String name, String email, String contactNumber){
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "{CALL updateUserDetails(?, ?, ?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, userID);
            cstmt.setString(2, name);
            cstmt.setString(3, email);
            cstmt.setString(4, contactNumber);
            cstmt.execute();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    // VIEW BOOKING HISTORY
    public ArrayList<BookingItem> viewBookingHistory(int userID){
        bookingHistory = new ArrayList<BookingItem>();
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "{CALL viewBookingHistory(?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, userID);
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()){
                int bookingID = rs.getInt("booking_id");
                int uID = rs.getInt("user_id");
                int flightID = rs.getInt("flight_id");
                int seatID = rs.getInt("seat_id");
                String bookingDate = rs.getString("booking_date");
                String payment_status = rs.getString("payment_status");

                booking = new BookingItem(bookingID, uID, flightID, seatID, bookingDate, payment_status);
                bookingHistory.add(booking);
                System.out.println(booking);
            }
            rs.close();
            cstmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return bookingHistory;
    }

    // Booking Management
    // CREATE BOOKING
    public void createBooking(int userID, int flightID, int seatID){
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "{CALL createBooking(?, ?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);

            cstmt.setInt(1, userID);
            cstmt.setInt(2, flightID);
            cstmt.setInt(3, seatID);

            cstmt.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    // CANCEL BOOKING
    public void cancelBooking(int bookingID){
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "{CALL cancelBooking(?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, bookingID);

            cstmt.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    // GET BOOKING ID
    public int getBookingID(int userID, int flightID, int seatID){
        int bookingID = 0;
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "{CALL getBookingID(?, ?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, userID);
            cstmt.setInt(2, flightID);
            cstmt.setInt(3, seatID);

            ResultSet rs = cstmt.executeQuery();
            if(rs.next()){
                bookingID = rs.getInt("booking_id");
            }
            rs.close();
            cstmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return bookingID;
    }
    // GET USER BOOKING HISTORY
    public ArrayList<BHistoryItem> getUserBookingHistory(int userID){
        ArrayList<BHistoryItem> retrievedBHistorys = new ArrayList<>();
        BHistoryItem bHistoryContent;
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "{CALL getUserBookingHistory(?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, userID);
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()){
                int flightId = rs.getInt("flight_id");
                String airline = rs.getString("airline");
                String departureAirport = rs.getString("departure_airport");
                String arrivalAirport = rs.getString("arrival_airport");
                String departureTime = rs.getString("departure_time");
                String arrivalTime = rs.getString("arrival_time");
                String seatNumber = rs.getString("seat_number");
                double paymentAmount = rs.getDouble("amount");
                String paymentMethod = rs.getString("payment_method");
                String paymentDate = rs.getString("payment_date");

                bHistoryContent = new BHistoryItem(flightId, airline, departureAirport, arrivalAirport, departureTime, arrivalTime, seatNumber, paymentAmount, paymentMethod, paymentDate);
                retrievedBHistorys.add(bHistoryContent);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return retrievedBHistorys;
    }
    // CHECK IN
    public void checkIn(int bookingID){
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "{CALL checkIn(?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, bookingID);

            cstmt.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    //GENERATE BOOKING REPORT
    public ArrayList<BookingItem> generateBookingReport(String startDate, String endDate){
        bookingReport = new ArrayList<BookingItem>();
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "{CALL generateBookingReport(?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setString(1, startDate);
            cstmt.setString(2, endDate);
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()){
                int bookingID = rs.getInt("booking_id");
                int uID = rs.getInt("user_id");
                int flightID = rs.getInt("flight_id");
                int seatID = rs.getInt("seat_id");
                String bookingDate = rs.getString("booking_date");
                String payment_status = rs.getString("payment_status");

                booking = new BookingItem(bookingID, uID, flightID, seatID, bookingDate, payment_status);
                bookingReport.add(booking);
                System.out.println(booking);
            }
            rs.close();
            cstmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return bookingReport;
    }

    // Payment Management
    // PROCESS PAYMENT
    public void processPayment(int bookingID, double amount, String paymentMethod){
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "{CALL processPayment(?, ?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, bookingID);
            cstmt.setDouble(2, amount);
            cstmt.setString(3, paymentMethod);
            cstmt.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    // UPDATE PAYMENT STATUS
    public void updatePaymentStatus(int bookingID, String paymentStatus){
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            String sql = "{CALL updatePaymentStatus(?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, bookingID);
            cstmt.setString(2, paymentStatus);
            cstmt.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }



}
