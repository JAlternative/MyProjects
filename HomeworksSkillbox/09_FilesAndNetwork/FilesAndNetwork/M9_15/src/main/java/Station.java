import java.util.ArrayList;
import java.util.List;

public class Station {
    private List<String> station;

    public List<String> getStation() {
        return station;
    }

    public void setStation(List<String> station) {
        this.station = station;
    }

    public Station(List<String> station) {
        this.station = station;
    }
}
