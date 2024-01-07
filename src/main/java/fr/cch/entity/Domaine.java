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
@Table(name = "domaine")
public class Domaine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDomaine")
    private Long id;

    @Column(name = "nomDomaine", nullable = false)
    private String domaine;

    @OneToMany(mappedBy = "domaine")
    private List<DomaineThemes> domaineThemes;

    public Domaine(String domaine) {
        this.domaine = domaine;
    }

    @Override
    public String toString() {
        return "Domaine{" +
                "id=" + id +
                ", domaine='" + domaine + '\'' +
                '}';
    }

}
