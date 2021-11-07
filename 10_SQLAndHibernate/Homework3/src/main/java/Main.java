import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

//5.4.27.Final
public class Main {
    public static void main(String[] args) {
//        String url = "jdbc:mysql://localhost:3306/skillbox?useUnicode=true&serverTimezone=UTC";
//        String user = "root";
//        String pass = "rootroot";
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metaData = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();




        Query query = session.createQuery("FROM Student");
        List<Student> student = query.list();

            for (int a = 0; a < student.size(); a++){
            for (int b = 1; b < student.get(a).getSubscriptions().size(); a++){
                System.out.println("Имя: " + student.get(a).getName() + " №"+ b + "Курс: " + student.get(a).getSubscriptions().get(b).getCourse().getName());
            }
        }

        transaction.commit();
        sessionFactory.close();
    }
}

