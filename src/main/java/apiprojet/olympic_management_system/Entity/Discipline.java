package apiprojet.olympic_management_system.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "discipline")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discipline")
    private Long idDiscipline;

    @NotBlank(message = "Le nom de la discipline est obligatoire")
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // Relation 1-N : Une discipline regroupe plusieurs athlètes
    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<Athlete> athletes = new ArrayList<>();

    // Relation 1-N : Une discipline comprend plusieurs épreuves
    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<Epreuve> epreuves = new ArrayList<>();
}