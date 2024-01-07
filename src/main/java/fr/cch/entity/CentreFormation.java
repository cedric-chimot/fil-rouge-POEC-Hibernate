package Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "centreFormation")
public class CentreFormation {

    @Id
    @GeneratedValue
    @JoinColumn(name = "idCentre")
    private Long idCentre;

    @Column(name = "nomCentre", nullable = false)
    private String nom;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @OneToMany(mappedBy = "centreFormation")
    private List<SessionFormation> sessionsFormation;

    public CentreFormation(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

}
