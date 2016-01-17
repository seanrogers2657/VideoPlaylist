package controllers;

import play.libs.Json;
import play.mvc.*;
import views.html.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import models.Task;
import java.util.List;

import services.PlaylistManager;
import org.springframework.beans.factory.annotation.Autowired;

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
        }
        log.info("Manager Info: " + manager.toString());
        List<Task> taskList = manager.getAllTasks();
        return ok(index.render(titleName, taskList, play.data.Form.form(models.Task.class)));
    }

    public Result addTask() {
        log.info("Adding Task");
        play.data.Form<models.Task> form = play.data.Form.form(models.Task.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(index.render(titleName, manager.getAllTasks(), form));
        }
        else {
            models.Task task = form.get();
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
