import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Embeddable
class SubscriptionKey implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;


}

@Entity
@IdClass(SubscriptionKey.class)
@Table(name = "Subscriptions")
public class Subscription
{

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
