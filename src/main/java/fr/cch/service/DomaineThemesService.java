package fr.cch.service;

import fr.cch.entity.Domaine;
import fr.cch.entity.DomaineThemes;
import fr.cch.entity.Participation;
import fr.cch.entity.Themes;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class DomaineThemesService {
    /**
     * L'EntityManagerFactory utilisé pour créer l'EntityManager.
     */
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("filrouge-unit");

    /**
     * La Session associée à l'EntityManager, utilisée pour les opérations sur la base de données.
     */
    Session session = emf.createEntityManager().unwrap(Session.class);

    /**
     * Regrouper les domaines liés aux thèmes dans la BDD
     *
     * @param idDomaine, Le domaine lié au thème.
     * @param idThemes, le thème lié au domaine
     */
    public void save (Long idDomaine, Long idThemes) {
        try {
            session.beginTransaction();

            Domaine domaine = session.find(Domaine.class, idDomaine);
            Themes themes = session.find(Themes.class, idThemes);

            DomaineThemes domaineThemes = new DomaineThemes(domaine, themes);

            session.persist(domaineThemes);
        } catch (Exception e) {
            System.out.println("Données non envoyées !");
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

    /**
     * Méthode qui affiche les thèmes liés à un domaine donné
     *
     * @param idDomaine, le domaine recherché
     * @param idThemes, le thème associé au domaine recherché
     * @return Le résultat de la requête
     */
    public List<DomaineThemes> findDomaineThemes(Long idDomaine, Long idThemes) {
        try {
            // Constructeur pour créer les critères de requête
            CriteriaBuilder builder = session.getCriteriaBuilder();

            // Création de la requête de critères pour afficher les thèmes associés à un domaine spécifique
            CriteriaQuery<DomaineThemes> domaineThemesCriteriaQuery = builder.createQuery(DomaineThemes.class);

            // Racine de la requête
            Root<DomaineThemes> root = domaineThemesCriteriaQuery.from(DomaineThemes.class);

            // Conditions de la requête
            domaineThemesCriteriaQuery.select(root)
                    .where(
                            builder.equal(root.get("domaine").get("id"), idDomaine),
                            builder.equal(root.get("themes").get("id"), idThemes)
                    );

            // Exécution de la requête et récupération du résultat
            return session.createQuery(domaineThemesCriteriaQuery).getResultList();

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche !", e);
        }
    }

}
