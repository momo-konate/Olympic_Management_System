package apiprojet.olympic_management_system.Service.ServiceImpl;

import apiprojet.olympic_management_system.Dto.NationDto;
import apiprojet.olympic_management_system.Dto.PageResponse;
import apiprojet.olympic_management_system.Dto.TableauMedaillesDto;
import apiprojet.olympic_management_system.Entity.Nation;
import apiprojet.olympic_management_system.Mapper.EntityMapper;
import apiprojet.olympic_management_system.Repositories.NationRepository;
import apiprojet.olympic_management_system.Service.NationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NationServiceImpl implements NationService {

    private final NationRepository nationRepository;
    private final EntityMapper entityMapper;

    @Override
    @Transactional
    public NationDto.Response createNation(NationDto.Request request) {
        Nation nation = entityMapper.toNationEntity(request);
        Nation savedNation = nationRepository.save(nation);
        return entityMapper.toNationResponse(savedNation);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<NationDto.Response> getAllNations(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Nation> nationsPage = nationRepository.findAll(pageable);

        List<NationDto.Response> content = nationsPage.getContent().stream()
                .map(entityMapper::toNationResponse)
                .toList();

        return PageResponse.<NationDto.Response>builder()
                .content(content)
                .pageNo(nationsPage.getNumber())
                .pageSize(nationsPage.getSize())
                .totalElements(nationsPage.getTotalElements())
                .totalPages(nationsPage.getTotalPages())
                .last(nationsPage.isLast())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public NationDto.Response getNationById(Long id) {
        Nation nation = nationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nation non trouvée avec l'ID: " + id));
        return entityMapper.toNationResponse(nation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TableauMedaillesDto> getTableauMedailles() {
        return nationRepository.getTableauMedailles();
    }
}