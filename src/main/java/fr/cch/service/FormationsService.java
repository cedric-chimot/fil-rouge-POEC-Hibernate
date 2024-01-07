package fr.cch.service;

import fr.cch.entity.Formations;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;

public class FormationsService {
    /**
     * L'EntityManagerFactory utilisé pour créer l'EntityManager.
     */
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("filrouge-unit");

    /**
     * La Session associée à l'EntityManager, utilisée pour les opérations sur la base de données.
     */
    Session session = emf.createEntityManager().unwrap(Session.class);

    /**
     * Créer une nouvelle formation dans la base de données.
     *
     * @param formation, La formation à sauvegarder.
     */
    public void save (Formations formation) {
        session.beginTransaction();
        session.persist(formation);
        session.getTransaction().commit();
    }

    public Formations findFormation(Long id) {
        return session.find(Formations.class, id);
    }

}
