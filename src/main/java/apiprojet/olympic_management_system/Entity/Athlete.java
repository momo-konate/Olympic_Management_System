package apiprojet.olympic_management_system.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "athlete")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_athlete")
    private Long idAthlete;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotBlank(message = "Le sexe est obligatoire")
    @Pattern(regexp = "^[MF]$", message = "Le sexe doit être 'M' ou 'F'")
    @Column(name = "sexe", nullable = false, length = 1)
    private String sexe;

    @NotNull(message = "La date de naissance est obligatoire")
    @Past(message = "La date de naissance doit être dans le passé")
    @Column(name = "date_de_naissance", nullable = false)
    private LocalDate dateDeNaissance;

    @Positive(message = "La taille doit être un nombre positif")
    @Column(name = "taille")
    private Double taille;

    @Positive(message = "Le poids doit être un nombre positif")
    @Column(name = "poids")
    private Double poids;

    // Relation N-1 : Plusieurs athlètes appartiennent à une seule nation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nation", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Nation nation;

    // Relation N-1 : Plusieurs athlètes pratiquent une seule discipline principale
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_discipline", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Discipline discipline;

    // Relation 1-N : Un athlète peut participer à plusieurs épreuves via ses résultats
    @OneToMany(mappedBy = "athlete", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<Resultat> resultats = new ArrayList<>();
}