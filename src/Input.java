import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.util.List;
import java.util.Scanner;

public class Input {
    ConferenceRoomManagementSystem system;
    Scanner sc;

    public Input() {
        this.system = new ConferenceRoomManagementSystem();
        this.sc = new Scanner(System.in);
    }

    public void run(){
        while(true){
            String command = sc.nextLine();
            String[] parts = command.split(" ");
            switch (parts[0]){
                case "ADD":
                    if(parts[1].equals("BUILDING")){
                        addBuilding(parts[2]);
                    } else if(parts[1].equals("FLOOR")){
                        addFloor(parts[2], Integer.parseInt(parts[3]));
                    } else if (parts[1].equals("CNFR")) {
                        addConferenceRoom(parts[2], Integer.parseInt(parts[3]), parts[4]);
                    }
                    break;
                case "BOOK":
                    String[] hours = parts[1].split(":");
                    int startHour = Integer.parseInt(hours[0]);
                    int endHour =  Integer.parseInt(hours[1]);
                    book(startHour, endHour, parts[2], parts[3], Integer.parseInt(parts[4]), parts[5]);
                    break;
                case "CANCEL":
                    hours = parts[1].split(":");
                    startHour = Integer.parseInt(hours[0]);
                    endHour = Integer.parseInt(hours[1]);
                    cancel(startHour, endHour, parts[2], (parts[3]), Integer.parseInt(parts[4]), parts[5]);
                    break;
                case "LIST":
                    if(parts[1].equals("BOOKING")){
                        listBooking(parts[2]);
                    }
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private void listBooking(String userId) {
        for (Building building : system.buildings.values()) {
            for (Floor floor : building.floors) {
                for (ConferenceRoom conferenceRoom : floor.conferenceRooms) {
                    List<int[]> bookings = conferenceRoom.getBookingsByUser(userId);
                    for (int[] booking : bookings) {
                        System.out.println(booking[0] + ":" + booking[1] + " " + floor.number + " " + building.name + " " + conferenceRoom.name);
                    }
                }
            }
        }
    }

    private void cancel(int startHour, int endHour, String userId, String buildingName, int floorNumber, String roomName) {
        Building building = system.buildings.get(buildingName);
        if (building != null) {
            Floor floor = building.getFloor(floorNumber);
            if (floor != null) {
                ConferenceRoom conferenceRoom = floor.getConferenceRoom(roomName);
                if (conferenceRoom != null) {
                    conferenceRoom.cancel(startHour, endHour, userId);
                    System.out.println("Canceled booking for conference room " + roomName + " from hour " + startHour + " to hour " + endHour);
                } else {
                    System.out.println("Invalid conference room name");
                }
            } else {
                System.out.println("Invalid floor number");
            }
        } else {
            System.out.println("Invalid building name");
        }
    }

    private void book(int startHour, int endHour, String userId, String buildingName, int floorNumber, String roomName) {
        Building building = system.buildings.get(buildingName);
        if(building != null){
            Floor floor = building.getFloor(floorNumber);
            if(floor != null){
                ConferenceRoom room = floor.getConferenceRoom(roomName);
                if(room != null){
                    if(room.isAvailable(startHour, endHour)){
                        room.book(startHour, endHour, userId);
                        System.out.println("Booked conference room " + roomName + " from hour " + startHour + " to hour " + endHour);
                    } else {
                        System.out.println("Conference room is not available at this hour");
                    }
                }else{
                    System.out.println("Invalid conference room");
                }
            }else{
                System.out.println("Invalid floor number");
            }
        }else{
            System.out.println("Invalid building name");
        }
    }

    private void addConferenceRoom(String buildingName, int floorNumber, String roomName) {
        system.addConferenceRoom(buildingName, floorNumber, roomName);
        System.out.println("Added conference room " + roomName + " on floor " + floorNumber + " in building " + buildingName);
    }

    private void addFloor(String buildingName, int floorNumber) {
        system.addFloor(buildingName, floorNumber);
        System.out.println("Added floor " + floorNumber + " into the building " + buildingName);
    }

    private void addBuilding(String buildingName) {
        system.addBuilding(buildingName);
        System.out.println("Added building " + buildingName + " into the system.");
    }

}
