package apiprojet.olympic_management_system.Service.ServiceImpl;



import apiprojet.olympic_management_system.Dto.EpreuveDto;
import apiprojet.olympic_management_system.Dto.PageResponse;
import apiprojet.olympic_management_system.Entity.Discipline;
import apiprojet.olympic_management_system.Entity.Epreuve;
import apiprojet.olympic_management_system.Mapper.EntityMapper;
import apiprojet.olympic_management_system.Repositories.DisciplineRepository;
import apiprojet.olympic_management_system.Repositories.EpreuveRepository;
import apiprojet.olympic_management_system.Service.EpreuveService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpreuveServiceImpl implements EpreuveService {

    private final EpreuveRepository epreuveRepository;
    private final DisciplineRepository disciplineRepository;
    private final EntityMapper entityMapper;

    @Override
    @Transactional
    public EpreuveDto.Response createEpreuve(EpreuveDto.Request request) {
        Discipline discipline = disciplineRepository.findById(request.idDiscipline())
                .orElseThrow(() -> new RuntimeException("Discipline introuvable avec l'ID: " + request.idDiscipline()));

        Epreuve epreuve = entityMapper.toEpreuveEntity(request, discipline);
        Epreuve savedEpreuve = epreuveRepository.save(epreuve);
        return entityMapper.toEpreuveResponse(savedEpreuve);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<EpreuveDto.Response> getAllEpreuves(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Epreuve> page = epreuveRepository.findAll(pageable);

        List<EpreuveDto.Response> content = page.getContent().stream()
                .map(entityMapper::toEpreuveResponse)
                .toList();

        return PageResponse.<EpreuveDto.Response>builder()
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
    public EpreuveDto.Response getEpreuveById(Long id) {
        Epreuve epreuve = epreuveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Épreuve introuvable avec l'ID: " + id));
        return entityMapper.toEpreuveResponse(epreuve);
    }

    @Override
    @Transactional
    public void deleteEpreuve(Long id) {
        if (!epreuveRepository.existsById(id)) {
            throw new RuntimeException("Épreuve introuvable avec l'ID: " + id);
        }
        epreuveRepository.deleteById(id);
    }
}
