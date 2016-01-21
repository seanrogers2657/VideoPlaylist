import configs.AppConfig;
import controllers.routes;
import models.Task;
import forms.TaskForm;
import org.junit.*;
import org.springframework.test.context.ContextConfiguration;
import play.data.Form;
import play.mvc.*;
import play.test.*;
import play.libs.F.*;
import play.twirl.api.Html;
import java.util.ArrayList;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

@ContextConfiguration(classes={AppConfig.class, TestDataConfig.class})
public class ApplicationTest {

    @Test
    public void renderIndexTest() {
        System.out.println("\nRENDER INDEX TEST:");
        running(fakeApplication(), new Runnable() {
            public void run() {
                Form<TaskForm> form = Form.form(TaskForm.class);
                Html html = views.html.index.render("Test", new ArrayList<Task>(), form);
                assertThat(contentType(html)).isEqualTo("text/html");
                assertThat(contentAsString(html)).contains("Test");
            }
        });
    }

    @Test
    public void addTaskPathTest() {
        System.out.println("\nADD TASK PATH TEST:");
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = route(fakeRequest(POST, routes.Application.addTask().toString()));
                assertThat(result).isNotNull();
            }
        });
    }

    @Test
    public void getTasksPathTest() {
        System.out.println("\nGET TASK PATH TEST:");
        running(fakeApplication(), new Runnable() {
           public void run() {
               Result result = route(fakeRequest(GET, routes.Application.getAllTasks().toString()));
               assertThat(result).isNotNull();
           }
        });
    }
}