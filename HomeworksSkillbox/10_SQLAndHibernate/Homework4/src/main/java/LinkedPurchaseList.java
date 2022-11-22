

import javax.persistence.*;
import java.io.Serializable;

@Entity
 public class LinkedPurchaseList implements Serializable
{

    @EmbeddedId
    private Key id;

    public LinkedPurchaseList(Key id) {
        this.id = id;
    }


    @Column(name = "student_id", insertable = false, updatable = false, nullable = true)
    private Integer studentId;

    @Column(name = "course_id", insertable = false, updatable = false, nullable = true)
    private Integer courseId;

    public LinkedPurchaseList(){

}


    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
