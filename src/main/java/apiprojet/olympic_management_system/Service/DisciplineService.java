package apiprojet.olympic_management_system.Service;

import apiprojet.olympic_management_system.Dto.DisciplineDto;
import apiprojet.olympic_management_system.Dto.PageResponse;

public interface DisciplineService {
    DisciplineDto.Response createDiscipline(DisciplineDto.Request request);
    PageResponse<DisciplineDto.Response> getAllDisciplines(int pageNo, int pageSize, String sortBy, String sortDir);
    DisciplineDto.Response getDisciplineById(Long id);
    void deleteDiscipline(Long id);
}