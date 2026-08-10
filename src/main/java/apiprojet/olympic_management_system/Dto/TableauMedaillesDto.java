package apiprojet.olympic_management_system.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableauMedaillesDto {
    private Long idNation;
    private String nomNation;
    private String codeIso;
    private Long or;
    private Long argent;
    private Long bronze;
    private Long totalMedailles;
    private Long points;
}