import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Connection {
    public String nameLine;
    public List<String> connection;

    public Connection(String nameLine, List<String> connection) {
        this.nameLine = nameLine;
        this.connection = connection;
    }

    public String getNameLine() {
        return nameLine;
    }

    public void setNameLine(String nameLine) {
        this.nameLine = nameLine;
    }

    public List<String> getConnection() {
        return connection;
    }

    public void setConnection(List<String> connection) {
        this.connection = connection;
    }


}
