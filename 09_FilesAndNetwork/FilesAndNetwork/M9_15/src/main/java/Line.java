public class Line {
    private String numberLine;
    private String lineName;

    public Line(String lineName, String numberLine) {
        this.lineName = lineName;
        this.numberLine = numberLine;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getNumberLine() {
        return numberLine;
    }

    public void setNumberLine(String numberLine) {
        this.numberLine = numberLine;
    }


}
