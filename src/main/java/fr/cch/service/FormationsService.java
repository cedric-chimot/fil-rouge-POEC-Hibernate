package Service;

import Entity.Formations;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;

public class FormationsService {
    /**
     * L'EntityManagerFactory utilisé pour créer l'EntityManager.
     */
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("filrouge-unit");

    /**
     * Créer une nouvelle formation dans la base de données.
     *
     * @param nom et + , La formation à sauvegarder.
     */
    public void save (Formations formation) {
        Session session = null;
        try {
            session = emf.createEntityManager().unwrap(Session.class);
            session.beginTransaction();
            session.persist(formation);
            session.getTransaction().commit();
        } catch (Exception e) {
            if(session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
