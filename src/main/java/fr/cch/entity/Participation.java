package fr.cch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "participer")
public class Participation {

    @Id
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private SessionFormation session;

    @Id
    @ManyToOne
    @JoinColumn(name = "stagiaire_id", nullable = false)
    private Users user;

    @Id
    @ManyToOne
    @JoinColumn(name = "formateur_id", nullable = false)
    private Formateurs formateur;

    @Column(name = "noteAccueil",nullable = true)
    private int noteAccueil;

    @Column(name = "noteMateriel", nullable = true)
    private int noteMateriel;

    @Column(name = "recommandation", nullable = true)
    private Boolean recommandation;

    @Column(name = "noteMaitrise", nullable = true)
    private int noteMaitrise;

    @Column(name = "noteDisponibilite", nullable = true)
    private int noteDisponibilite;

    @Column(name = "noteReponseQuestion", nullable = true)
    private int noteReponseQuestion;

    @Column(name = "noteAnimation", nullable = true)
    private int noteAnimation;

    public Participation(SessionFormation session, Users user, Formateurs formateur, int noteAccueil, int noteMateriel,
                         Boolean recommandation, int noteMaitrise, int noteDisponibilite, int noteReponseQuestion,
                         int noteAnimation) {
        this.session = session;
        this.user = user;
        this.formateur = formateur;
        this.noteAccueil = noteAccueil;
        this.noteMateriel = noteMateriel;
        this.recommandation = recommandation;
        this.noteMaitrise = noteMaitrise;
        this.noteDisponibilite = noteDisponibilite;
        this.noteReponseQuestion = noteReponseQuestion;
        this.noteAnimation = noteAnimation;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "session=" + session +
                ", user=" + user +
                ", formateur=" + formateur +
                ", noteAccueil=" + noteAccueil +
                ", noteMateriel=" + noteMateriel +
                ", recommandation=" + recommandation +
                ", noteMaitrise=" + noteMaitrise +
                ", noteDisponibilite=" + noteDisponibilite +
                ", noteReponseQuestion=" + noteReponseQuestion +
                ", noteAnimation=" + noteAnimation +
                '}';
    }

}
