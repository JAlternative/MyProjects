public class WrongFormatTelephoneExeption extends Throwable {
    private String message;

    public WrongFormatTelephoneExeption(String exception) {
        this.message = exception;
    }
    public String getMessage() {
        return message;
    }
}
