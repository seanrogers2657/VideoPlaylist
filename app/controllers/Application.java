package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import com.avaje.ebean.Ebean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Task;
import java.util.List;

@org.springframework.stereotype.Controller
public class Application extends Controller {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static Result index() {
        log.info("Getting Index Page");
        List<Task> taskList = Task.getAllTasks();
        return ok(index.render("Tasks", taskList, play.data.Form.form(models.Task.class)));
    }

    public static Result addTask() {
        log.info("Adding Task");
        play.data.Form<models.Task> form = play.data.Form.form(models.Task.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(index.render("Tasks", Task.getAllTasks(), form));
        }
        else {
            models.Task task = form.get();
            task.save();
            return redirect(routes.Application.index());
        }
    }

    public static Result deleteTask(int taskid) {
        log.info(String.format("Deleting Task: %s", taskid));
        Task task = Ebean.find(Task.class, taskid);
        task.delete();
        return redirect(routes.Application.index());
    }
}
