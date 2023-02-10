package skypro.com.CoursWork2.exception;

public class NotTaskException extends Exception{
    public NotTaskException(Integer taskID) {
        super("Задача с ID =" + taskID + " не найдена");
    }

}
