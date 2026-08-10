package apiprojet.olympic_management_system.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "epreuve")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_epreuve")
    private Long idEpreuve;

    @NotBlank(message = "Le nom de l'épreuve est obligatoire")
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull(message = "La date de l'épreuve est obligatoire")
    @Column(name = "date_epreuve", nullable = false)
    private LocalDateTime dateEpreuve;

    @NotBlank(message = "Le lieu est obligatoire")
    @Column(name = "lieu", nullable = false)
    private String lieu;

    @NotBlank(message = "Le statut est obligatoire")
    @Column(name = "statut", nullable = false)
    private String statut; // Exemple : "PROGRAMMEE", "EN_COURS", "TERMINEE"

    // Relation N-1 : Plusieurs épreuves appartiennent à une seule discipline
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_discipline", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Discipline discipline;

    // Relation 1-N : Une épreuve comporte plusieurs résultats (un par athlète participant)
    @OneToMany(mappedBy = "epreuve", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<Resultat> resultats = new ArrayList<>();
}