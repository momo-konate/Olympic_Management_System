package apiprojet.olympic_management_system.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

public class EpreuveDto {

    public record Request(
            @NotBlank(message = "Le nom de l'épreuve est obligatoire")
            String nom,

            @NotNull(message = "La date de l'épreuve est obligatoire")
            LocalDateTime dateEpreuve,

            @NotBlank(message = "Le lieu est obligatoire")
            String lieu,

            @NotBlank(message = "Le statut est obligatoire")
            String statut, //  "PROGRAMMEE", "EN_COURS", "TERMINEE"

            @NotNull(message = "L'ID de la discipline est obligatoire")
            Long idDiscipline
    ) {}

    @Builder
    public record Response(
            Long idEpreuve,
            String nom,
            LocalDateTime dateEpreuve,
            String lieu,
            String statut,
            DisciplineDto.Response discipline
    ) {}
}