package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import play.data.validation.Constraints.Required;

@Entity
public class Task {

    @Id
    @GeneratedValue
    public int id;

    @Required
    @Column(columnDefinition="text")
    public String contents;

    @Override
    public String toString() {
        return "(Task: " + id + ", " + contents + ")";
    }
}
