import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useUnicode=true&serverTimezone=UTC";
        String user = "root";
        String pass = "rootroot";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            ResultSet courseName = statement.executeQuery("SELECT * FROM Courses");
            Double averageSales;
            while (courseName.next()) {
                String coursesName = courseName.getString("name");
                try {
                    Statement statementCourseName = connection.createStatement();
                    ResultSet dateSubscriptionAvarageMonth = statementCourseName.executeQuery("SELECT course_name, timestampdiff(month, min(subscription_date), max(subscription_date)) as avarageMonth  " +
                            "FROM PurchaseList WHERE course_name = \"" + coursesName + "\" group by course_name");
                    double midMonth = 0.0;
                    while (dateSubscriptionAvarageMonth.next()) {
                        midMonth = dateSubscriptionAvarageMonth.getInt("avarageMonth");
                    }
                    Statement statementDateSubscription = connection.createStatement();
                    ResultSet dateSubscription = statementDateSubscription.executeQuery("SELECT course_name, subscription_date FROM PurchaseList " +
                            "WHERE course_name = \"" + coursesName + "\" ORDER BY subscription_date");
                    double countAllCourseSubscription = 0;
                    while (dateSubscription.next()) {
                        countAllCourseSubscription = ++countAllCourseSubscription;
                    }

                    averageSales = countAllCourseSubscription / midMonth;
                    System.out.println("Курс: " + coursesName + " Среднее значение продаж: " + String.format("%.2f", averageSales));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
