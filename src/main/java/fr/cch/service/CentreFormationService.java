package fr.cch.service;

import fr.cch.entity.CentreFormation;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;

public class CentreFormationService {

    /**
     * L'EntityManagerFactory utilisé pour créer l'EntityManager.
     */
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("filrouge-unit");

    /**
     * La Session associée à l'EntityManager, utilisée pour les opérations sur la base de données.
     */
    Session session = emf.createEntityManager().unwrap(Session.class);

    /**
     * Créer un nouveau centre de formation dans la base de données.
     *
     * @param centreFormation, Les paramètres centre de formation à sauvegarder.
     */
    public void save (CentreFormation centreFormation) {
        session.beginTransaction();
        session.persist(centreFormation);
        session.getTransaction().commit();
    }

    public CentreFormation findCentreFormation(Long id) {
        return session.find(CentreFormation.class, id);
    }

}
