package fr.cch.service;

import fr.cch.entity.Formateurs;
import org.hibernate.Session;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class FormateursService {

    /**
     * L'EntityManagerFactory utilisé pour créer l'EntityManager.
     */
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("filrouge-unit");

    /**
     * La Session associée à l'EntityManager, utilisée pour les opérations sur la base de données.
     */
    private final Session session = emf.createEntityManager().unwrap(Session.class);

    /**
     * Sauvegarde un formateur dans la base de données.
     *
     * @param formateur Le formateur à sauvegarder.
     */
    public void save(Formateurs formateur) {
        session.beginTransaction();
        session.persist(formateur);
        session.getTransaction().commit();
    }

    public Formateurs findFormateur(Long id) {
        return session.find(Formateurs.class, id);
    }

}
