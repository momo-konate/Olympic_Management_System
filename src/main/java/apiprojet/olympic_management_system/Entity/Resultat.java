package apiprojet.olympic_management_system.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "resultat")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultat")
    private Long idResultat;

    @Min(value = 1, message = "La position doit être au moins égale à 1")
    @Column(name = "position")
    private Integer position;

    @Column(name = "performance")
    private Double performance; // Temps en secondes, distance en mètres, points, etc.

    @Column(name = "unite")
    private String unite; // Ex: "sec", "m", "points"

    @Column(name = "medaille")
    private String medaille; // Ex: "GOLD", "SILVER", "BRONZE" ou null

    // Relation N-1 : Plusieurs résultats concernent un seul athlète
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_athlete", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Athlete athlete;

    // Relation N-1 : Plusieurs résultats sont liés à une seule épreuve
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_epreuve", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Epreuve epreuve;
}