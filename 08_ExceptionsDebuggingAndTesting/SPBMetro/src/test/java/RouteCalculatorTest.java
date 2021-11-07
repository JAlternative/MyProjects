import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class RouteCalculatorTest extends TestCase {
    List<Station> route;
    StationIndex stationIndex = new StationIndex();

    @Override
    protected void setUp() throws Exception {
        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");
        route = new ArrayList<>();
        Station golden = new Station("Золотая", line1);
        Station silver = new Station("Серебряная", line1);
        Station bronze = new Station("Бронзовая", line1);
        Station emerald = new Station("Изумрудная", line1);
        

        Station sapphire = new Station("Сапфировая", line2);
        Station crystal = new Station("Кристаловая", line2);
        Station ruby = new Station("Рубиновая", line2);
        Station diamond = new Station("Алмазовая", line2);

        Station topaz = new Station("Топазовая", line3);
        Station pearl = new Station("Жемчужная", line3);
        Station brilliant = new Station("Бриллиантовая", line3);
        Station beryl = new Station("Берилловая", line3);

        List<Station> connection = new ArrayList<>();

        line1.addStation(golden);
        line1.addStation(silver);
        line1.addStation(bronze);
        line1.addStation(emerald);

        line2.addStation(sapphire);
        line2.addStation(crystal);
        line2.addStation(ruby);
        line2.addStation(diamond);

        line3.addStation(topaz);
        line3.addStation(pearl);
        line3.addStation(brilliant);
        line3.addStation(beryl);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(golden);
        stationIndex.addStation(silver);
        stationIndex.addStation(bronze);
        stationIndex.addStation(emerald);

        stationIndex.addStation(sapphire);
        stationIndex.addStation(crystal);
        stationIndex.addStation(ruby);
        stationIndex.addStation(diamond);

        stationIndex.addStation(topaz);
        stationIndex.addStation(pearl);
        stationIndex.addStation(brilliant);
        stationIndex.addStation(beryl);

        connection.add(stationIndex.getStation("Изумрудная"));
        connection.add(stationIndex.getStation("Сапфировая"));

        List<Station> connection2 = new ArrayList<>();
        connection2.add(stationIndex.getStation("Алмазовая"));
        connection2.add(stationIndex.getStation("Топазовая"));

        stationIndex.addConnection(connection);
        stationIndex.addConnection(connection2);






    }

    public void testGetRouteOnTheLine() {
        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List<Station> expected;
        List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Золотая", 1), stationIndex.getStation("Изумрудная", 1));
        expected = new ArrayList<>();
        expected.add(stationIndex.getStation("Золотая", 1));
        expected.add(stationIndex.getStation("Серебряная", 1));
        expected.add(stationIndex.getStation("Бронзовая", 1));
        expected.add(stationIndex.getStation("Изумрудная", 1));
        assertEquals(expected, actual);
    }

    public void testGetRouteWithOneConnection() {
        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Золотая", 1), stationIndex.getStation("Алмазовая", 2));
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("Золотая", 1));
        expected.add(stationIndex.getStation("Серебряная", 1));
        expected.add(stationIndex.getStation("Бронзовая", 1));
        expected.add(stationIndex.getStation("Изумрудная", 1));

        expected.add(stationIndex.getStation("Сапфировая", 2));
        expected.add(stationIndex.getStation("Кристаловая", 2));
        expected.add(stationIndex.getStation("Рубиновая", 2));
        expected.add(stationIndex.getStation("Алмазовая", 2));
        assertEquals(expected, actual);


    }
    public void testGerRouteWithTwoConnection(){
       RouteCalculator calculator = new RouteCalculator(stationIndex);
        List<Station> actual = calculator.getShortestRoute(stationIndex.getStation("Золотая", 1), stationIndex.getStation("Берилловая", 3));
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("Золотая", 1));
        expected.add(stationIndex.getStation("Серебряная", 1));
        expected.add(stationIndex.getStation("Бронзовая", 1));
        expected.add(stationIndex.getStation("Изумрудная", 1));

        expected.add(stationIndex.getStation("Сапфировая", 2));
        expected.add(stationIndex.getStation("Кристаловая", 2));
        expected.add(stationIndex.getStation("Рубиновая", 2));
        expected.add(stationIndex.getStation("Алмазовая", 2));

        expected.add(stationIndex.getStation("Топазовая", 3));
        expected.add(stationIndex.getStation("Жемчужная", 3));
        expected.add(stationIndex.getStation("Бриллиантовая", 3));
        expected.add(stationIndex.getStation("Берилловая", 3));

        assertEquals(expected,actual);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
