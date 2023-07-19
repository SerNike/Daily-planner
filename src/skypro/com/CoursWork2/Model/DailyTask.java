package skypro.com.CoursWork2.Model;

import skypro.com.CoursWork2.exception.IncorreckArgumentException;

import java.time.LocalDateTime;

public class DailyTask extends Task{
    public DailyTask(String title, String description, TaskType type, LocalDateTime taskTime) throws IncorreckArgumentException {
        super(title, description, type, taskTime);
    }

    @Override
    public LocalDateTime getTimeNext(LocalDateTime dateTime) {
        return dateTime.plusDays(1);
    }
}
