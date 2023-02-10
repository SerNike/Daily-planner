package skypro.com.CoursWork2.Model;

import skypro.com.CoursWork2.exception.IncorreckArgumentException;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task implements Repeatable {
    private static int idGeneration;
    private int id;
    private String title;
    private String description;
    private TaskType type;
    private LocalDateTime taskTime;

    public Task(String title, String description, TaskType type, LocalDateTime taskTime) throws IncorreckArgumentException {
        this.id = id;
        setDescription(description);
        setTaskTime(taskTime);
        setType(type);
        setTitle(title);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getIdGeneration() {
        return idGeneration;
    }

    public static void setIdGeneration(int idGeneration) {
        Task.idGeneration = idGeneration;
    }

    public void setTitle(String title) throws IncorreckArgumentException {
        if (title != null || !title.isEmpty()|| !title.isBlank()) {
            this.title = title;
        } else {
            throw new IncorreckArgumentException("Заголовок задан не верно");
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IncorreckArgumentException {
        if (description != null || !description.isEmpty() || !description.isBlank()) {
            this.description = description;
        } else {
            throw new IncorreckArgumentException("Описание задано не верно");
        }
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) throws IncorreckArgumentException {
        if (type != null) {
            this.type = type;
        } else {
            throw new IncorreckArgumentException("Тип задачи задан не верно");
        }
    }

    public LocalDateTime getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(LocalDateTime taskTime) throws IncorreckArgumentException {
        if (taskTime != null) {
            this.taskTime = taskTime;
        } else {
            throw new IncorreckArgumentException("Дата и время заданы не верно");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && Objects.equals(description, task.description) && type == task.type && Objects.equals(taskTime, task.taskTime);
    }

    @Override
    public String toString() {
        return "Задача: id: " + id +
                " Название: " + title +
                " Описание: " + description +
                " Тип: " + type +
                " Время: " + taskTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, type, taskTime);
    }
}

