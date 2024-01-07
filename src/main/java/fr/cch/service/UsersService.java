package fr.cch.service;

import fr.cch.entity.SessionFormation;
import fr.cch.entity.Users;
import org.hibernate.Session;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UsersService {

    /**
     * L'EntityManagerFactory utilisé pour créer l'EntityManager.
     */
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("filrouge-unit");

    /**
     * La Session associée à l'EntityManager, utilisée pour les opérations sur la base de données.
     */
    private final Session session = emf.createEntityManager().unwrap(Session.class);

    /**
     * Sauvegarde un utilisateur dans la base de données.
     *
     * @param users L'utilisateur à sauvegarder.
     */
    public void save (Users users) {
        session.beginTransaction();
        session.persist(users);
        session.getTransaction().commit();
    }

    /**
     * Affiche l'utilisateur recherché et ses différents attributs
     * @param id, l'identifiant de l'utilisateur recherché
     * @return l'utilisateur
     */
    public Users findUser(Long id) {
        return session.find(Users.class, id);
    }

}
