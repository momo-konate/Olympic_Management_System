package apiprojet.olympic_management_system.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nation")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Nation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nation")
    private Long idNation;

    @NotBlank(message = "Le nom de la nation est obligatoire")
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotBlank(message = "Le code ISO est obligatoire")
    @Size(min = 3, max = 3, message = "Le code ISO doit contenir exactement 3 caractères")
    @Column(name = "code_iso", nullable = false, length = 3, unique = true)
    private String codeIso;

    // Relation 1-N : Une nation possède plusieurs athlètes
    @OneToMany(mappedBy = "nation", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude // pour eviter les boucles infinies dans la méthode toString() de Lombok
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<Athlete> athletes = new ArrayList<>();
}