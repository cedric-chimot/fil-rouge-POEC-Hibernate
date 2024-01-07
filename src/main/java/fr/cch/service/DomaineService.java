package fr.cch.service;

import fr.cch.entity.Domaine;
import fr.cch.entity.SousThemes;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;

public class DomaineService {

    /**
     * L'EntityManagerFactory utilisé pour créer l'EntityManager.
     */
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("filrouge-unit");

    /**
     * La Session associée à l'EntityManager, utilisée pour les opérations sur la base de données.
     */
    private final Session session = emf.createEntityManager().unwrap(Session.class);

    /**
     * Sauvegarde un domaine dans la base de données.
     *
     * @param domaine Le domaine à sauvegarder.
     */
    public void save(Domaine domaine) {
        session.beginTransaction();
        session.persist(domaine);
        session.getTransaction().commit();
    }

    public Domaine findDomaine(Long id) {
        return session.find(Domaine.class, id);
    }

}
