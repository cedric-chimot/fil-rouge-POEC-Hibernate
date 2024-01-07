package Service;

import Entity.CentreFormation;
import Entity.Formations;
import Entity.SessionFormation;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SessionFormationService {

    /**
     * L'EntityManagerFactory utilisé pour créer l'EntityManager.
     */
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("filrouge-unit");

    /**
     * Sauvegarde une session de formation dans la base de données.
     *
     * @param dateDebutStr et autres, les différentes données à envoyer en bdd
     */
    public void save (String dateDebutStr, String dateFinStr, String statut, Formations formation, CentreFormation centreFormation) {
        Session session = null;
        try {
            session = emf.createEntityManager().unwrap(Session.class);
            session.beginTransaction();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date dateDebut = simpleDateFormat.parse(dateDebutStr);
            Date dateFin = simpleDateFormat.parse(dateFinStr);

            SessionFormation sessionFormation = new SessionFormation(dateDebut, dateFin, statut, formation, centreFormation);

            session.persist(sessionFormation);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
