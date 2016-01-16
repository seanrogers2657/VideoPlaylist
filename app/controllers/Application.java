package controllers;

import org.springframework.transaction.annotation.Transactional;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import models.Task;
import java.util.List;

import services.PlaylistManager;
import services.UrlConverter;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class Application extends play.mvc.Controller {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static String titleName = "Video Playlist";

    @Autowired
    private PlaylistManager manager;
    private UrlConverter converter;

    public Result index() {
        log.info("Getting Index Page");
        if(manager == null) {
            log.error("PlaylistManager injection is null");
        }
        log.info("Manager Info: " + manager.toString());
        List<Task> taskList = manager.getAllTasks();
        return ok(index.render(titleName, taskList, play.data.Form.form(models.Task.class)));
    }

    @Transactional
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

    public Result deleteTask(int taskid) {
        log.info(String.format("Deleting Task: %s", taskid));
        manager.removeTask(taskid);
        return redirect(routes.Application.index());
    }

    public Result convert(String url) {
        String converted = converter.convert(url);
        return ok();
    }
}
