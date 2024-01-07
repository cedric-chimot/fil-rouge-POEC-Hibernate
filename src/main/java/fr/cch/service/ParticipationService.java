package fr.cch.service;

import fr.cch.entity.Formateurs;
import fr.cch.entity.Participation;
import fr.cch.entity.SessionFormation;
import fr.cch.entity.Users;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ParticipationService {

    /**
     * L'EntityManagerFactory utilisé pour créer l'EntityManager.
     */
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("filrouge-unit");

    /**
     * La Session associée à l'EntityManager, utilisée pour les opérations sur la base de données.
     */
    private final Session session = emf.createEntityManager().unwrap(Session.class);

    /**
     * Sauvegarde une participation à une formation dans la base de données.
     *
     * @param idSession L'objet session à sauvegarder.
     * @param idFormateur L'objet formateur à sauvegarder.
     * @param idStagiaire L'objet stagiaire à sauvegarder.
     * @param noteAccueil etc..., les différentes notes sur les critères d'évaluation.
     * @param recommandation Booléen qui indique si l'utilisateur recommande ou non la formation
     */
    public void save(Long idSession, Long idStagiaire, Long idFormateur, int noteAccueil, int noteMateriel,
                     Boolean recommandation, int noteMaitrise, int noteDisponibilite, int noteReponseQuestion,
                     int noteAnimation) {
        try {
            session.beginTransaction();

            SessionFormation sessionFormation = session.find(SessionFormation.class, idSession);
            Users stagiaire = session.find(Users.class, idStagiaire);
            Formateurs formateur = session.find(Formateurs.class, idFormateur);

            Participation participation = new Participation(sessionFormation, stagiaire, formateur, noteAccueil,
                    noteMateriel, recommandation, noteMaitrise, noteDisponibilite, noteReponseQuestion,
                    noteAnimation);

            session.persist(participation);
        } catch (Exception e) {
            System.out.println("Participation à une formation non créée !");
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
     * Méthode qui affiche une participation à une formation par rapport
     * à une session donnée, un user donné et un formateur donné
     *
     * @param idSession,   la session de formation recherchée
     * @param idStagiaire, le stagiaire recherché
     * @param idFormateur, le formateur recherché
     * @return Le résultat de la requête
     */
    public List<Participation> findParticipation(Long idSession, Long idStagiaire, Long idFormateur) {
        try {
            // Constructeur pour créer les critères de requête
            CriteriaBuilder builder = session.getCriteriaBuilder();

            // Création de la requête de critères pour afficher une participation à une formation
            CriteriaQuery<Participation> participationCriteriaQuery = builder.createQuery(Participation.class);

            // Racine de la requête
            Root<Participation> root = participationCriteriaQuery.from(Participation.class);

            // Conditions de la requête
            participationCriteriaQuery.select(root)
                    .where(
                            builder.equal(root.get("session").get("id"), idSession),
                            builder.equal(root.get("user").get("id"), idStagiaire),
                            builder.equal(root.get("formateur").get("id"), idFormateur)
                    );

            // Exécution de la requête et récupération du résultat
            return session.createQuery(participationCriteriaQuery).getResultList();

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de la participation.", e);
        }
    }

}
