package fr.cch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "regrouper")
public class DomaineThemes {

    @Id
    @ManyToOne
    @JoinColumn(name = "idDomaine", nullable = false)
    private Domaine domaine;

    @Id
    @ManyToOne
    @JoinColumn(name = "idThemes", nullable = false)
    private Themes themes;

    public DomaineThemes(Domaine domaine, Themes themes) {
        this.domaine = domaine;
        this.themes = themes;
    }

    @Override
    public String toString() {
        return "DomaineThemes{" +
                "domaine=" + domaine +
                ", themes=" + themes +
                '}';
    }

}
