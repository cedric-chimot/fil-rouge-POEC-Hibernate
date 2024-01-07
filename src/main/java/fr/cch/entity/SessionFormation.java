package fr.cch.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "session")
public class SessionFormation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSession")
    private Long idSession;

    @Column(name = "date_debut", nullable = false)
    //@Temporal : Pour spécifier si un champ de type "Date" doit être mappé en tant que date
    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Column(name = "date_fin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @Column(name = "statut", nullable = false)
    private String statut;

    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    private Formations formation;

    @ManyToOne
    @JoinColumn(name = "centre_formation_id", nullable = true)
    private CentreFormation centreFormation;

    @OneToMany(mappedBy = "session")
    private List<Participation> participations;

    public SessionFormation(Date dateDebut, Date dateFin, String statut, Formations formation, CentreFormation centreFormation) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.formation = formation;
        this.centreFormation = centreFormation;
    }

    @Override
    public String toString() {
        return "SessionFormation{" +
                "idSession=" + idSession +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", statut='" + statut + '\'' +
                ", formation=" + formation +
                ", centreFormation=" + centreFormation +
                '}';
    }
}
