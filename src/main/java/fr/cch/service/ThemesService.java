package fr.cch.service;

import fr.cch.entity.SousThemes;
import fr.cch.entity.Themes;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;

public class ThemesService {

    /**
     * L'EntityManagerFactory utilisé pour créer l'EntityManager.
     */
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("filrouge-unit");

    /**
     * La Session associée à l'EntityManager, utilisée pour les opérations sur la base de données.
     */
    Session session = emf.createEntityManager().unwrap(Session.class);

    /**
     * Créer un nouveau thème dans la base de données.
     *
     * @param nom, Le nom du thème à sauvegarder.
     * @param idSousThemes, le sous-thème lié au thème
     */
    public void save (String nom, Long idSousThemes) {
        try {
            session.beginTransaction();

            SousThemes sousThemes = session.find(SousThemes.class, idSousThemes);

            Themes themes = new Themes(nom, sousThemes);

            session.persist(themes);
        } catch (Exception e) {
            System.out.println("Thème non créé !");
            // Log de l'exception
            e.printStackTrace();
            // Gestion de l'exception et rollback en cas d'erreur
            session.getTransaction().rollback();
        } finally {
            if (session.getTransaction().isActive()) {
                // On commit la transaction si tout est ok
                session.getTransaction().commit();
            }
        }
    }

    public Themes findThemes(Long id) {
        return session.find(Themes.class, id);
    }

}
