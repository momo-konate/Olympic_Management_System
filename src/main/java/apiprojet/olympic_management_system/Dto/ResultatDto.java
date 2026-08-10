package apiprojet.olympic_management_system.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public class ResultatDto {

    public record Request(
            @Min(value = 1, message = "La position doit être supérieure ou égale à 1")
            Integer position,

            Double performance,

            String unite, // Ex: "sec", "m", "points"

            @NotNull(message = "L'ID de l'athlète est obligatoire")
            Long idAthlete,

            @NotNull(message = "L'ID de l'épreuve est obligatoire")
            Long idEpreuve
    ) {}

    @Builder
    public record Response(
            Long idResultat,
            Integer position,
            Double performance,
            String unite,
            String medaille, // Récupéré automatiquement (GOLD, SILVER, BRONZE ou null)
            AthleteDto.Response athlete,
            EpreuveDto.Response epreuve
    ) {}
}