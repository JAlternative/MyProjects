public class WrongFormatEmailExeption extends Exception {
    private String message;

    public WrongFormatEmailExeption(String exception) {
        this.message = exception;
    }
    public String getMessage() {
        return message;
    }
}
