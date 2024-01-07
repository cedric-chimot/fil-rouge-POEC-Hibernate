package fr.cch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "themes")
public class Themes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "idTheme")
    private Long idTheme;

    @Column(name = "nomTheme", nullable = false)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "idSousTheme")
    private SousThemes sousTheme;

    @OneToMany(mappedBy = "themes")
    private List<DomaineThemes> domaineThemes;

    public Themes(String nom, SousThemes sousTheme) {
        this.nom = nom;
        this.sousTheme = sousTheme;
    }

    @Override
    public String toString() {
        return "Themes{" +
                "idTheme=" + idTheme +
                ", nom='" + nom + '\'' +
                ", sousTheme=" + sousTheme +
                '}';
    }
}
