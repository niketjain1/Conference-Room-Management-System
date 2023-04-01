import java.util.HashMap;
import java.util.Map;

public class ConferenceRoomManagementSystem {
    Map<String, Building> buildings;

    public ConferenceRoomManagementSystem() {
        this.buildings = new HashMap<>();
    }
    public void addBuilding(String buildingName){
        buildings.put(buildingName, new Building(buildingName));
    }

    public void addFloor(String buildingName, int floorNumber){
        Building building = buildings.get(buildingName);
        if(building != null){
            building.addFloor(new Floor(floorNumber));
        }
    }

    public void addConferenceRoom(String buildingName, int floorNumber, String conferenceRoomName){
        Building building = buildings.get(buildingName);
        if(building != null){
            Floor floor = building.getFloor(floorNumber);
            if(floor != null){
                floor.addConferenceRoom(new ConferenceRoom(conferenceRoomName));
            }
        }
    }
}
