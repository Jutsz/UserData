

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UserService {

    private static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public static void registerUser(String username, Long processID, int expirationMinutes) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = new User();
            user.setUserName(username);
            user.setProcessID(processID);
            user.setEnterTime(new Date());
            user.setExpirationTime(new Date(System.currentTimeMillis() + expirationMinutes * 60 * 1000));

            session.save(user);
            transaction.commit();
        }
    }

    public static void startExpirationUpdater(int updateIntervalMinutes) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> updateExpirationTimestamps(), 0, updateIntervalMinutes, TimeUnit.MINUTES);
    }

    private static void updateExpirationTimestamps() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.createQuery("UPDATE User SET expirationTimestamp = :newExpirationTime WHERE expirationTimestamp < CURRENT_TIMESTAMP")
                    .setParameter("newExpirationTime", new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                    .executeUpdate();

            transaction.commit();
        }
    }

    public static void logoutUser(String username) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.createQuery("DELETE FROM User WHERE username = :username")
                    .setParameter("username", username)
                    .executeUpdate();

            transaction.commit();
        }
    }

	
}

