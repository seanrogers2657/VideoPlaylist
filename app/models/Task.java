package models;

import com.avaje.ebean.Ebean;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Task extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Required
    public String contents;

    public static List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();
        tasks = Ebean.find(Task.class).findList();
        return tasks;
    }

}
