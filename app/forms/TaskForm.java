package forms;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

public class TaskForm {

    @Required(message="You must put in a url")
    @MaxLength(value=43, message="The maximum length of a url is 43")
    @MinLength(value=34, message="The minimum length of a url is 34")
    private String contents;

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}