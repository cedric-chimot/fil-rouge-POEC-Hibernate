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
@Table(name = "formateurs")
public class Formateurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFormateur")
    private Long id;

    @Column(name = "nom_formateur", nullable = false)
    private String nomFormateur;

    @Column(name = "experience", nullable = false)
    private String experience;

    @Column(name = "noteMoyenne", nullable = false)
    private double noteMoyenne;

    @OneToMany(mappedBy = "formateur")
    private List<Participation> participations;

    public Formateurs(String nomFormateur, String experience, double noteMoyenne) {
        this.nomFormateur = nomFormateur;
        this.experience = experience;
        this.noteMoyenne = noteMoyenne;
    }

    @Override
    public String toString() {
        return "Formateurs{" +
                "id=" + id +
                ", nomFormateur='" + nomFormateur + '\'' +
                ", experience='" + experience + '\'' +
                ", noteMoyenne=" + noteMoyenne +
                '}';
    }

}
