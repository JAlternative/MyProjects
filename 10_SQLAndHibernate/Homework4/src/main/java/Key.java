import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Embeddable
public class Key implements Serializable {

    @Column(name = "student_id", nullable = true)
    private Integer studentId;

    @Column(name = "course_id", nullable = true)
    private Integer courseId;

    public Key(){

    }

    public Key(Integer studentId, Integer courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }



    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object obj) {
        return equals(obj);
    }

    @Override
    public int hashCode() {
        return hashCode();
    }


}
