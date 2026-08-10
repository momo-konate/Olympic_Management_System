package apiprojet.olympic_management_system.Service.ServiceImpl;

import apiprojet.olympic_management_system.Dto.DisciplineDto;
import apiprojet.olympic_management_system.Dto.PageResponse;
import apiprojet.olympic_management_system.Entity.Discipline;
import apiprojet.olympic_management_system.Mapper.EntityMapper;
import apiprojet.olympic_management_system.Repositories.DisciplineRepository;
import apiprojet.olympic_management_system.Service.DisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final EntityMapper entityMapper;

    @Override
    @Transactional
    public DisciplineDto.Response createDiscipline(DisciplineDto.Request request) {
        Discipline discipline = entityMapper.toDisciplineEntity(request);
        Discipline savedDiscipline = disciplineRepository.save(discipline);
        return entityMapper.toDisciplineResponse(savedDiscipline);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<DisciplineDto.Response> getAllDisciplines(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Discipline> page = disciplineRepository.findAll(pageable);

        List<DisciplineDto.Response> content = page.getContent().stream()
                .map(entityMapper::toDisciplineResponse)
                .toList();

        return PageResponse.<DisciplineDto.Response>builder()
                .content(content)
                .pageNo(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public DisciplineDto.Response getDisciplineById(Long id) {
        Discipline discipline = disciplineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discipline introuvable avec l'ID: " + id));
        return entityMapper.toDisciplineResponse(discipline);
    }

    @Override
    @Transactional
    public void deleteDiscipline(Long id) {
        if (!disciplineRepository.existsById(id)) {
            throw new RuntimeException("Discipline introuvable avec l'ID: " + id);
        }
        disciplineRepository.deleteById(id);
    }
}