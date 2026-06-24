package com.irctc;

import java.io.File;
import java.util.*;

import com.irctc.entities.Train;
import com.irctc.entities.User;
import com.irctc.util.UserServiceUtil;
import com.irctc.services.TrainService;
import com.irctc.services.UserBookingService;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String selectedSource = null;
        String selectedDestination = null;
        System.out.println("Running Train Booking System");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        UserBookingService userBookingService;
        try{
            userBookingService = new UserBookingService();
        }catch(IOException ex){
            System.out.println("There is something wrong");
            ex.printStackTrace();
            return;
        }
        Train trainSelectedForBooking = new Train();
        while(option!=7){
            System.out.println("Choose option");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");
            option = scanner.nextInt();
            switch (option){
                case 1:
                    System.out.println("Enter the username to signup");
                    String nameToSignUp = scanner.next();
                    System.out.println("Enter the password to signup");
                    String passwordToSignUp = scanner.next();
                    User userToSignup = new User(nameToSignUp, passwordToSignUp, UserServiceUtil.hashPassword(passwordToSignUp), new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUp(userToSignup);
                    break;
                case 2:
                    System.out.println("Enter the username to Login");
                    String nameToLogin = scanner.next();
                    System.out.println("Enter the password to signup");
                    String passwordToLogin = scanner.next();
                    User userToLogin = new User(nameToLogin, passwordToLogin, UserServiceUtil.hashPassword(passwordToLogin), new ArrayList<>(), UUID.randomUUID().toString());
                    try {
                        userBookingService = new UserBookingService(userToLogin);

                        if(userBookingService.loginUser()){
                            System.out.println("Login successful");
                        } else {
                            System.out.println("Invalid credentials");
                        }

                    } catch(IOException ex){
                        return;
                    }
                    break;
                case 3:
                    if(trainSelectedForBooking == null){
                        System.out.println("Please search and select a train first");
                        break;
                    }
                    System.out.println("Fetching your bookings");
                    userBookingService.fetchBookings();
                    break;
                case 4:
                    System.out.println("Type your source station");
                    String source = scanner.next();
                    System.out.println("Type your destination station");
                    String dest = scanner.next();
                    selectedSource = source;
                    selectedDestination = dest;
                    List<Train> trains = userBookingService.getTrains(source, dest);
                    int index = 1;
                    for (Train t: trains){
                        System.out.println(index+" Train id : "+t.getTrainId());
                        index++;
                        for (Map.Entry<String, String> entry: t.getStationSchedules().entrySet()){
                            System.out.println("station "+entry.getKey()+" time: "+entry.getValue());
                        }
                    }
                    if (trains.isEmpty()) {
                        System.out.println("No trains found");
                        break;
                    }
                    System.out.println("Select a train by typing 1,2,3...");
                    int choice = scanner.nextInt();

                    if(choice < 1 || choice > trains.size()){
                        System.out.println("Invalid choice");
                        break;
                    }

                    trainSelectedForBooking = trains.get(choice - 1);
                    break;
                case 5:
                    System.out.println("Select a seat out of these seats");
                    List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);
                    for (List<Integer> row: seats){
                        for (Integer val: row){
                            System.out.print(val+" ");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the seat by typing the row and column");
                    System.out.println("Enter the row");
                    int row = scanner.nextInt();
                    System.out.println("Enter the column");
                    int col = scanner.nextInt();
                    System.out.println("Booking your seat....");
                    Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col, selectedSource, selectedDestination);
                    if(booked.equals(Boolean.TRUE)){
                        System.out.println("Booked! Enjoy your journey");
                    }else{
                        System.out.println("Can't book this seat");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}