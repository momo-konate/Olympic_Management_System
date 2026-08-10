package apiprojet.olympic_management_system.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public class NationDto {

    public record Request(
            @NotBlank(message = "Le nom de la nation est obligatoire")
            String nom,

            @NotBlank(message = "Le code ISO est obligatoire")
            @Size(min = 3, max = 3, message = "Le code ISO doit contenir exactement 3 caractères")
            String codeIso
    ) {}

    @Builder
    public record Response(
            Long idNation,
            String nom,
            String codeIso
    ) {}
}