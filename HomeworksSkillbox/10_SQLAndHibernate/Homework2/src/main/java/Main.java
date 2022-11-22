import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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
        Teachers teachers = session.get(Teachers.class, 1);
        System.out.println(teachers.getName());
        sessionFactory.close();
    }
}

