package skypro.com.CoursWork2.Model;

import java.time.LocalDateTime;

public interface Repeatable {
    LocalDateTime getTimeNext(LocalDateTime dateTime);
}
