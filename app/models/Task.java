package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Task {

    @Id
    @GeneratedValue
    public int id;

    public String contents;

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return this.contents;
    }

    @Override
    public String toString() {
        return "(Task: " + id + ", " + contents + ")";
    }
}
