package fr.cch.service;

import fr.cch.entity.Formateurs;
import fr.cch.entity.SousThemes;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;

public class SousThemesService {

    /**
     * L'EntityManagerFactory utilisé pour créer l'EntityManager.
     */
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("filrouge-unit");

    /**
     * La Session associée à l'EntityManager, utilisée pour les opérations sur la base de données.
     */
    private final Session session = emf.createEntityManager().unwrap(Session.class);

    /**
     * Sauvegarde un sous-thème dans la base de données.
     *
     * @param sousThemes Le sous-thème à sauvegarder.
     */
    public void save(SousThemes sousThemes) {
        session.beginTransaction();
        session.persist(sousThemes);
        session.getTransaction().commit();
    }

    public SousThemes findSousThemes(Long id) {
        return session.find(SousThemes.class, id);
    }

}
