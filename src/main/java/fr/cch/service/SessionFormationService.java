package fr.cch.service;

import fr.cch.entity.CentreFormation;
import fr.cch.entity.Formations;
import fr.cch.entity.SessionFormation;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SessionFormationService {

    /**
     * L'EntityManagerFactory utilisé pour créer l'EntityManager.
     */
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("filrouge-unit");

    /**
     * La Session associée à l'EntityManager, utilisée pour les opérations sur la base de données.
     */
    Session session = emf.createEntityManager().unwrap(Session.class);

    /**
     * Enregistre une nouvelle session de formation dans la base de données.
     *
     * @param dateDebutStr   La date de début au format String.
     * @param dateFinStr     La date de fin au format String.
     * @param statut         Le statut de la session.
     * @param idFormation    L'ID de la formation associée à la session.
     * @param idCentre       L'ID du centre de formation associé à la session.
     */
    public void save(String dateDebutStr, String dateFinStr, String statut, Long idFormation, Long idCentre) {
        try{
            session.beginTransaction();

            // Conversion des chaînes de date en objets Date
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date dateDebut = null;
            Date dateFin = null;

            try {
                dateDebut = simpleDateFormat.parse(dateDebutStr);
                dateFin = simpleDateFormat.parse(dateFinStr);
            } catch (ParseException e) {
                System.out.println("Impossible de transformer les dates en objet !");
                e.printStackTrace();
            }

            // Récupération des Formations et CentreFormation à partir de leurs IDs
            Formations formation = session.find(Formations.class, idFormation);
            CentreFormation centreFormation = session.find(CentreFormation.class, idCentre);

            // Création de la nouvelle session de formation
            SessionFormation sessionFormation = new SessionFormation(dateDebut, dateFin, statut, formation, centreFormation);

            // Envoi des données dans la base de données
            session.persist(sessionFormation);
        } catch (RuntimeException e) {
            System.out.println("Session de formation non créée !");
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
     * Affiche la session de formation recherchée et tous les critères qui la composent
     * @param id, l'identifiant de la session recherchée
     * @return la session de formation
     */
    public SessionFormation findSession(Long id) {
        return session.find(SessionFormation.class, id);
    }

}
