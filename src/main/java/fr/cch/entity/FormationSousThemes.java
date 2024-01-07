package fr.cch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "couvrir")
public class FormationSousThemes {

    @Id
    @ManyToOne
    @JoinColumn(name = "idFormation", nullable = false)
    private Formations formations;

    @Id
    @ManyToOne
    @JoinColumn(name = "idSousThemes", nullable = false)
    private SousThemes sousThemes;

    public FormationSousThemes(Formations formations, SousThemes sousThemes) {
        this.formations = formations;
        this.sousThemes = sousThemes;
    }

    @Override
    public String toString() {
        return "FormationSousThemes{" +
                "formations=" + formations +
                ", sousThemes=" + sousThemes +
                '}';
    }

}
