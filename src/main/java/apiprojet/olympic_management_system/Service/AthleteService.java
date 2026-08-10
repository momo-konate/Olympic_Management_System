package apiprojet.olympic_management_system.Service;

import apiprojet.olympic_management_system.Dto.AthleteDto;
import apiprojet.olympic_management_system.Dto.PageResponse;

public interface AthleteService {
    AthleteDto.Response createAthlete(AthleteDto.Request request);
    PageResponse<AthleteDto.Response> getAllAthletes(int pageNo, int pageSize, String sortBy, String sortDir);
    AthleteDto.Response getAthleteById(Long id);
    void deleteAthlete(Long id);
}