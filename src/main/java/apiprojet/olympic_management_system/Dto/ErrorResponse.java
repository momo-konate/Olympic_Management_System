package apiprojet.olympic_management_system.Dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp,
        Map<String, String> errors // Détails des erreurs de validation
) {
    public ErrorResponse(int status, String message) {
        this(status, message, LocalDateTime.now(), null);
    }

    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this(status, message, LocalDateTime.now(), errors);
    }
}