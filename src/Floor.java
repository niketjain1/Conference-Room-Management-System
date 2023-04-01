import java.util.ArrayList;
import java.util.List;

public class Floor {
    int number;
    List<ConferenceRoom> conferenceRooms;

    public Floor(int number) {
        this.number = number;
        this.conferenceRooms = new ArrayList<>();
    }

    public void addConferenceRoom(ConferenceRoom conferenceRoom){
        conferenceRooms.add(conferenceRoom);
    }

    public ConferenceRoom getConferenceRoom(String conferenceRoomName) {
        for(ConferenceRoom cfr: conferenceRooms){
            if(cfr.name.equals(conferenceRoomName)){
                return cfr;
            }
        }
        return null;
    }
}
