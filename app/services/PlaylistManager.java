package services;

import models.Task;
import java.util.List;

public interface PlaylistManager {
    void addTask(Task theTask);
    void removeTask(int theTaskId);
    List<Task> getAllTasks();
}
