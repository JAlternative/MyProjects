import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.util.HashMap;
import java.util.List;

//5.4.27.Final
public class Main {
    public static void main(String[] args) throws PersistenceException {
//        String url = "jdbc:mysql://localhost:3306/skillbox?useUnicode=true&serverTimezone=UTC";
//        String user = "root";
//        String pass = "rootroot";
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metaData = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        NativeQuery query = session.createSQLQuery("select * from purchaselist");
        List<Object[]> rows = query.list();

        for (int a = 0; a < rows.size(); a++)
        {
            NativeQuery studentId;
            NativeQuery courseID;

            String studentsName = rows.get(a)[0].toString();
            studentId = session.createSQLQuery("Select id FROM Students Where name = " + "\"" + studentsName + "\"");
            List<Integer> studentIdOfList = studentId.list();
            Integer studentNumberId = Integer.valueOf(studentIdOfList.get(0).toString());
            System.out.println("Айди студента " + studentNumberId);

            String courseNames = rows.get(a)[1].toString();
            courseID = session.createSQLQuery("Select id FROM Courses Where name = " + "\"" + courseNames + "\"");
            List<Integer> courseIdOfList = courseID.list();
            Integer courseNumberId = Integer.valueOf(courseIdOfList.get(0).toString());
            System.out.println("Айди курса " + courseNumberId);


            LinkedPurchaseList linkedPurchaseLis = new LinkedPurchaseList();
            Key key = new Key();
            key.setStudentId(studentNumberId);
            key.setCourseId(courseNumberId);

            linkedPurchaseLis.setId(key);
            session.save(linkedPurchaseLis);



        }

        session.close();
    }

}

