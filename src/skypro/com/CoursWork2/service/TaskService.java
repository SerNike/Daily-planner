package skypro.com.CoursWork2.service;

import skypro.com.CoursWork2.Model.Task;
import skypro.com.CoursWork2.exception.NotTaskException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private final Map<Integer, Task> taskMap = new HashMap<>();

    public void add(Task task) {
        this.taskMap.put(task.getId(), task);
    }

    public void remove(Integer taskID) throws NotTaskException {
        if (this.taskMap.containsKey(taskID)) {
            this.taskMap.remove(taskID);
        } else {
            throw new NotTaskException(taskID);
        }
    }

    public Collection<Task> getAllByDate(LocalDate date) {
        Collection<Task> taskByDay = new ArrayList<>();
        Collection<Task> allTasks = taskMap.values();
        for (Task task : allTasks) {
            LocalDateTime currentDateTime = task.getTaskTime();
            if (currentDateTime.toLocalDate().equals(date)) {
                taskByDay.add(task);
                break;
            }
            LocalDateTime taskNextTime = currentDateTime;
            do {
                taskNextTime = task.getTimeNext(taskNextTime);
                if (taskNextTime == null) {
                    break;
                }
                if (taskNextTime.toLocalDate().equals(date)) {
                    taskByDay.add(task);
                    break;
                }
            } while (taskNextTime.toLocalDate().isBefore(date));
        }
        return taskByDay;
    }
}

