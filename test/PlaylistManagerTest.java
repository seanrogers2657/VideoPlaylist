import configs.AppConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import services.PlaylistManager;
import models.Task;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(classes={AppConfig.class, TestDataConfig.class})
public class PlaylistManagerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private PlaylistManager manager;

    @Test
    public void addTaskTest() {
        System.out.println("\nADD TASK TEST:");
        Task task = new Task();
        task.contents = "https://www.youtube.com/watch?v=8z3h4Uv9YbE";
        System.out.println("Task: " + task);
        manager.addTask(task);
        System.out.println(manager.getAllTasks());
    }
}
