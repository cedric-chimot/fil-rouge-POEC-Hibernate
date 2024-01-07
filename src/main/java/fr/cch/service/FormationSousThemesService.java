package fr.cch.service;

import fr.cch.entity.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class FormationSousThemesService {
    /**
     * L'EntityManagerFactory utilisé pour créer l'EntityManager.
     */
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("filrouge-unit");

    /**
     * La Session associée à l'EntityManager, utilisée pour les opérations sur la base de données.
     */
    Session session = emf.createEntityManager().unwrap(Session.class);

    /**
     * Regrouper les formations liées aux sous-thèmes dans la BDD
     *
     * @param ifFormation, La formation liée au sous-thème.
     * @param idSousThemes, le sous-thème lié à la formation
     */
    public void save (Long ifFormation, Long idSousThemes) {
        try {
            session.beginTransaction();

            Formations formations = session.find(Formations.class, ifFormation);
            SousThemes sousThemes = session.find(SousThemes.class, idSousThemes);

            FormationSousThemes FormationSousThemes = new FormationSousThemes(formations, sousThemes);

            session.persist(FormationSousThemes);
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
     * Méthode qui affiche les sous-thèmes liés à une formation donnée
     *
     * @param idFormation, la formation recherchée
     * @param idSousThemes, le sous-thème associé à la formation recherchée
     * @return Le résultat de la requête
     */
    public List<FormationSousThemes> findFormationSousThemes(Long idFormation, Long idSousThemes) {
        try {
            // Constructeur pour créer les critères de requête
            CriteriaBuilder builder = session.getCriteriaBuilder();

            // Création de la requête de critères pour afficher les thèmes associés à un domaine spécifique
            CriteriaQuery<FormationSousThemes> criteriaQuery = builder
                    .createQuery(FormationSousThemes.class);

            // Racine de la requête
            Root<FormationSousThemes> root = criteriaQuery.from(FormationSousThemes.class);

            // Conditions de la requête
            criteriaQuery.select(root)
                    .where(
                            builder.equal(root.get("formations").get("id"), idFormation),
                            builder.equal(root.get("sousThemes").get("id"), idSousThemes)
                    );

            // Exécution de la requête et récupération du résultat
            return session.createQuery(criteriaQuery).getResultList();

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche !", e);
        }
    }

}
