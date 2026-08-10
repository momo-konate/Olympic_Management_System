package apiprojet.olympic_management_system.Service;

import apiprojet.olympic_management_system.Dto.NationDto;
import apiprojet.olympic_management_system.Dto.PageResponse;
import apiprojet.olympic_management_system.Dto.TableauMedaillesDto;

import java.util.List;

public interface NationService {
    NationDto.Response createNation(NationDto.Request request);
    PageResponse<NationDto.Response> getAllNations(int pageNo, int pageSize, String sortBy, String sortDir);
    NationDto.Response getNationById(Long id);
    List<TableauMedaillesDto> getTableauMedailles();
}
