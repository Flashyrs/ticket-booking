    package com.irctc.services;

    import com.fasterxml.jackson.databind.DeserializationFeature;
    import com.fasterxml.jackson.databind.PropertyNamingStrategies;
    import com.irctc.entities.Ticket;
    import com.irctc.entities.Train;
    import com.irctc.util.UserServiceUtil;

    import com.fasterxml.jackson.core.type.TypeReference;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.irctc.entities.User;

    import java.io.File;
    import java.io.IOException;
    import java.lang.reflect.Type;
    import java.util.*;

    public class UserBookingService {
        private User user;

        private List<User> userList;

        private ObjectMapper objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        private static final String USERS_PATH = "app/src/main/java/com/irctc/localDb/users.json";

        public UserBookingService(User user) throws IOException {
            this.user = user;
            loadUserListFromFile();
        }

        public UserBookingService() throws IOException {
            loadUserListFromFile();
        }

        private void loadUserListFromFile() throws IOException {
            userList = objectMapper.readValue(new File(USERS_PATH), new TypeReference<List<User>>() {});
        }

        public Boolean loginUser(){
            Optional<User> foundUser = userList.stream()
                    .filter(user1 ->
                            user1.getName().equalsIgnoreCase(user.getName()) &&
                                    UserServiceUtil.checkPassword(
                                            user.getPassword(),
                                            user1.getHashedPassword()
                                    ))
                    .findFirst();

            if(foundUser.isPresent()){
                this.user = foundUser.get();
                return true;
            }

            return false;
        }

        public Boolean signUp(User user1){
            try{
                userList.add(user1);
                saveUserListToFile();
                return Boolean.TRUE;
            }catch(IOException ex){
                return Boolean.FALSE;
            }
        }

        private void saveUserListToFile() throws IOException{
            File usersFile = new File(USERS_PATH);
            objectMapper.writeValue(usersFile, userList);
        }

        public void fetchBookings(){
            if(user == null){
                System.out.println("Please login first");
                return;
            }
            user.printTickets();
        }

        public Boolean cancelBooking(String ticketId){
            Scanner s = new Scanner(System.in);
            System.out.println("Enter the ticket id to cancel");
            ticketId = s.next();

            if (ticketId == null || ticketId.isEmpty()) {
                System.out.println("Ticket ID cannot be null or empty.");
                return Boolean.FALSE;
            }

            String finalTicketId1 = ticketId;  //Because strings are immutable
            boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId1));

            String finalTicketId = ticketId;
            user.getTicketsBooked().removeIf(Ticket -> Ticket.getTicketId().equals(finalTicketId));
            try {
                saveUserListToFile();
            }catch(IOException ignored) {
            }
            if (removed) {
                System.out.println("Ticket with ID " + ticketId + " has been canceled.");
                return Boolean.TRUE;
            }else{
                System.out.println("No ticket found with ID " + ticketId);
                return Boolean.FALSE;
            }

        }

        public List<Train> getTrains(String source, String destination){
            try{
                TrainService trainService = new TrainService();
                return trainService.searchTrains(source, destination);
            }catch(IOException ex){
                return new ArrayList<>();
            }
        }

        public List<List<Integer>> fetchSeats(Train train){
            return train.getSeats();
        }

        public Boolean bookTrainSeat(Train train, int row, int seat, String source, String dest) {
            try{
                TrainService trainService = new TrainService();
                List<List<Integer>> seats = train.getSeats();
                if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                    if (seats.get(row).get(seat) == 0) {
                        seats.get(row).set(seat, 1);
                        train.setSeats(seats);
                        trainService.addTrain(train);

                        Ticket ticket = new Ticket(
                                UUID.randomUUID().toString(),
                                user.getUserId(),
                                source,
                                dest,
                                new Date().toString(),
                                train
                        );

                        user.getTicketsBooked().add(ticket);
                        try {
                            saveUserListToFile();
                        }
                        catch (IOException e) {

                        }
                        return true;
                    } else {
                        return false; // Seat is already booked
                    }
                } else {
                    return false; // Invalid row or seat index
                }
            }catch (IOException ex){
                return Boolean.FALSE;
            }
        }
    }
