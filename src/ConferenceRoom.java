import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConferenceRoom {
    String name;
    Map<Integer, Booking> bookings;

    public ConferenceRoom(String name) {
        this.name = name;
        this.bookings = new HashMap<>();
    }
    public boolean isAvailable(int startHour, int endHour){
        for(int i = startHour; i < endHour; i++){
            if(bookings.containsKey(i)){
                return false;
            }
        }
        return true;
    }

    public void book(int startHour, int endHour, String uid){
        for(int i = startHour; i < endHour; i++){
            bookings.put(i, new Booking(uid));
        }
    }

    public void cancel(int startHour, int endHour, String uid){
        for(int i = startHour; i < endHour; i++){
            if(bookings.containsKey(i)){
                Booking booking = bookings.get(i);
                if(booking.userId.equals(uid)){
                    bookings.remove(i);
                }
            }
        }
    }

    public List<int[]> getBookingsByUser(String uID) {
        List<int[]> result = new ArrayList<>();
        int startHour = -1;
        for (int i = 0; i <= 24; i++) {
            if (bookings.containsKey(i) && bookings.get(i).userId.equals(uID)) {
                if (startHour == -1) {
                    startHour = i;
                }
            } else {
                if (startHour != -1) {
                    result.add(new int[]{startHour, i});
                    startHour = -1;
                }
            }
        }
        return result;
    }
}
