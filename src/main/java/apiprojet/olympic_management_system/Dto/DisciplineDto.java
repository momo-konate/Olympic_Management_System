package apiprojet.olympic_management_system.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public class DisciplineDto {

    public record Request(
            @NotBlank(message = "Le nom de la discipline est obligatoire")
            String nom,

            String description
    ) {}

    @Builder
    public record Response(
            Long idDiscipline,
            String nom,
            String description
    ) {}
}