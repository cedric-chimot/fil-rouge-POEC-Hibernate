package fr.cch.entity;

import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "pseudo", nullable = false)
    private String pseudo;

    @Column(name = "mdp", nullable = false)
    private String mdp;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Participation> participations;

    public Users(String nom, String prenom, String telephone, String email, String pseudo, String mdp, UserRole role) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.pseudo = pseudo;
        setMdp(mdp);
        this.role = role;
    }

    /**
     * Définit le mot de passe haché pour l'utilisateur.
     * Utilise la bibliothèque BCrypt pour générer un sel aléatoire et hacher le mot de passe
     * avant de le stocker dans l'attribut 'mdp'.
     *
     * @param plainPassword Le mot de passe en clair à hasher et à définir pour l'utilisateur.
     */
    public void setMdp(String plainPassword) { this.mdp = BCrypt.hashpw(plainPassword, BCrypt.gensalt()); }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", mdp='" + mdp + '\'' +
                ", role=" + role +
                '}';
    }
}
