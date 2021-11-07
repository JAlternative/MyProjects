import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Metro {


    private ArrayList<Line> lines = new ArrayList<>();
    private Map<String, ArrayList<String>> stations = new HashMap<>(); // Map нужна для создания корректного json'a как это требуется в SPBMetro :)
    private List<List<Connection>>  connections = new ArrayList<>();

    public Metro(ArrayList<Line> lines, Map<String, ArrayList<String>> stations, List<List<Connection>>  connections) {
        this.lines = lines;
        this.stations = stations;
        this.connections = connections;
    }

    public List<List<Connection>>  getConnections() {
        return connections;
    }

    public void setConnections(List<List<Connection>>  connections) {
        this.connections = connections;
    }

    public ArrayList<Line> getLines() {
        return lines;

    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }

    public Map<String, ArrayList<String>> getStations() {
        return stations;
    }

    public void setStations(Map<String, ArrayList<String>> stations) {
        this.stations = stations;
    }
}
