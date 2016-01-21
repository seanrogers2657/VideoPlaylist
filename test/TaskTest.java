import org.junit.Test;
import org.junit.Assert;
import models.Task;

public class TaskTest {

    @Test
    public void setContents() {
        System.out.println("\nSET TASK CONTENTS TEST:");
        String url = "https://www.youtube.com/watch?v=8z3h4Uv9YbE";
        Task task = new Task();
        task.setContents(url);
        Assert.assertEquals(url, task.getContents());
    }
}