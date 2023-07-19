package skypro.com.CoursWork2.exception;

public class IncorreckArgumentException extends Exception {
    private final String ARGUMENT;

    public IncorreckArgumentException(String ARGUMENT) {
        this.ARGUMENT = ARGUMENT;
    }

    @Override
    public String getMessage() {
        return "Параментр " + ARGUMENT + "задан некорректно";
    }
}
