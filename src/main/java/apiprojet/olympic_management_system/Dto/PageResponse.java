package apiprojet.olympic_management_system.Dto;

import lombok.Builder;
import java.util.List;

@Builder
public record PageResponse<T>(
        List<T> content,
        int pageNo,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last
) {}