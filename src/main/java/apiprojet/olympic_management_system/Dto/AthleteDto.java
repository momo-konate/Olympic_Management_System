package apiprojet.olympic_management_system.Dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import java.time.LocalDate;

public class AthleteDto {

    public record Request(
            @NotBlank(message = "Le nom est obligatoire")
            String nom,

            @NotBlank(message = "Le prénom est obligatoire")
            String prenom,

            @NotBlank(message = "Le sexe est obligatoire")
            @Pattern(regexp = "^[MF]$", message = "Le sexe doit être 'M' ou 'F'")
            String sexe,

            @NotNull(message = "La date de naissance est obligatoire")
            @Past(message = "La date de naissance doit être dans le passé")
            LocalDate dateDeNaissance,

            @Positive Double taille,
            @Positive Double poids,

            @NotNull(message = "L'ID de la nation est obligatoire")
            Long idNation,

            @NotNull(message = "L'ID de la discipline est obligatoire")
            Long idDiscipline
    ) {}

    @Builder
    public record Response(
            Long idAthlete,
            String nom,
            String prenom,
            String sexe,
            LocalDate dateDeNaissance,
            Double taille,
            Double poids,
            NationDto.Response nation,
            DisciplineDto.Response discipline
    ) {}
}