package apiprojet.olympic_management_system.Service.ServiceImpl;

import apiprojet.olympic_management_system.Dto.AthleteDto;
import apiprojet.olympic_management_system.Dto.PageResponse;
import apiprojet.olympic_management_system.Entity.Athlete;
import apiprojet.olympic_management_system.Entity.Discipline;
import apiprojet.olympic_management_system.Entity.Nation;
import apiprojet.olympic_management_system.Mapper.EntityMapper;
import apiprojet.olympic_management_system.Repositories.AthleteRepository;
import apiprojet.olympic_management_system.Repositories.DisciplineRepository;
import apiprojet.olympic_management_system.Repositories.NationRepository;
import apiprojet.olympic_management_system.Service.AthleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AthleteServiceImpl implements AthleteService {

    private final AthleteRepository athleteRepository;
    private final NationRepository nationRepository;
    private final DisciplineRepository disciplineRepository;
    private final EntityMapper entityMapper;

    @Override
    @Transactional
    public AthleteDto.Response createAthlete(AthleteDto.Request request) {
        Nation nation = nationRepository.findById(request.idNation())
                .orElseThrow(() -> new RuntimeException("Nation non trouvée avec l'ID: " + request.idNation()));
        Discipline discipline = disciplineRepository.findById(request.idDiscipline())
                .orElseThrow(() -> new RuntimeException("Discipline non trouvée avec l'ID: " + request.idDiscipline()));

        Athlete athlete = entityMapper.toAthleteEntity(request, nation, discipline);
        Athlete savedAthlete = athleteRepository.save(athlete);
        return entityMapper.toAthleteResponse(savedAthlete);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<AthleteDto.Response> getAllAthletes(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Athlete> athletesPage = athleteRepository.findAll(pageable);

        List<AthleteDto.Response> content = athletesPage.getContent().stream()
                .map(entityMapper::toAthleteResponse)
                .toList();

        return PageResponse.<AthleteDto.Response>builder()
                .content(content)
                .pageNo(athletesPage.getNumber())
                .pageSize(athletesPage.getSize())
                .totalElements(athletesPage.getTotalElements())
                .totalPages(athletesPage.getTotalPages())
                .last(athletesPage.isLast())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public AthleteDto.Response getAthleteById(Long id) {
        Athlete athlete = athleteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Athlète introuvable avec l'ID: " + id));
        return entityMapper.toAthleteResponse(athlete);
    }

    @Override
    @Transactional
    public void deleteAthlete(Long id) {
        if (!athleteRepository.existsById(id)) {
            throw new RuntimeException("Athlète introuvable avec l'ID: " + id);
        }
        athleteRepository.deleteById(id);
    }
}