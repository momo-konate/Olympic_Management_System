package apiprojet.olympic_management_system.Service;

import apiprojet.olympic_management_system.Dto.EpreuveDto;
import apiprojet.olympic_management_system.Dto.PageResponse;

public interface EpreuveService {
    EpreuveDto.Response createEpreuve(EpreuveDto.Request request);
    PageResponse<EpreuveDto.Response> getAllEpreuves(int pageNo, int pageSize, String sortBy, String sortDir);
    EpreuveDto.Response getEpreuveById(Long id);
    void deleteEpreuve(Long id);
}