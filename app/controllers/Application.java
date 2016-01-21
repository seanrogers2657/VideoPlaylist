package controllers;

import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import models.Task;
import java.util.List;
import services.PlaylistManager;
import org.springframework.beans.factory.annotation.Autowired;
import forms.TaskForm;

@org.springframework.stereotype.Controller
public class Application extends play.mvc.Controller {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static String titleName = "Video Playlist";

    @Autowired
    private PlaylistManager manager;

    public Result index() {
        log.info("Getting Index Page");
        if(manager == null) {
            log.error("PlaylistManager injection is null");
            return ok(error.render("PlaylistManager injection is null"));
        } else {
            log.info("Manager Info: " + manager.toString());
            List<Task> taskList = manager.getAllTasks();
            return ok(index.render(titleName, taskList, Form.form(TaskForm.class)));
        }
    }

    public Result addTask() {
        log.info("Adding Task");
        Form<TaskForm> form = Form.form(TaskForm.class).bindFromRequest();
        if (form.hasErrors()) {
            log.info("Form has errors");
            return badRequest(index.render(titleName, manager.getAllTasks(), form));
        }
        else {
            Task task = new Task();
            task.setContents(form.get().getContents());
            log.info("Task: " + task);
            manager.addTask(task);
            return redirect(routes.Application.index());
        }
    }

    public Result getAllTasks() {
        return ok(Json.toJson(manager.getAllTasks()));
    }

    public Result deleteTask(int taskId) {
        log.info(String.format("Deleting Task: %s", taskId));
        manager.removeTask(taskId);
        return redirect(routes.Application.index());
    }

    public static play.api.mvc.Call routesDelete(int taskId) {
        return routes.Application.deleteTask(taskId);
    }
}
