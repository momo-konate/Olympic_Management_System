package apiprojet.olympic_management_system.Service.ServiceImpl;

import apiprojet.olympic_management_system.Dto.ResultatDto;
import apiprojet.olympic_management_system.Entity.Athlete;
import apiprojet.olympic_management_system.Entity.Epreuve;
import apiprojet.olympic_management_system.Entity.Resultat;
import apiprojet.olympic_management_system.Mapper.EntityMapper;
import apiprojet.olympic_management_system.Repositories.AthleteRepository;
import apiprojet.olympic_management_system.Repositories.EpreuveRepository;
import apiprojet.olympic_management_system.Repositories.ResultatRepository;
import apiprojet.olympic_management_system.Service.ResultatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultatServiceImpl implements ResultatService {

    private final ResultatRepository resultatRepository;
    private final AthleteRepository athleteRepository;
    private final EpreuveRepository epreuveRepository;
    private final EntityMapper entityMapper;

    @Override
    @Transactional
    public ResultatDto.Response enregistrerResultat(ResultatDto.Request request) {
        Athlete athlete = athleteRepository.findById(request.idAthlete())
                .orElseThrow(() -> new RuntimeException("Athlète non trouvé avec l'ID: " + request.idAthlete()));
        Epreuve epreuve = epreuveRepository.findById(request.idEpreuve())
                .orElseThrow(() -> new RuntimeException("Épreuve non trouvée avec l'ID: " + request.idEpreuve()));

        Resultat resultat = entityMapper.toResultatEntity(request, athlete, epreuve);

        // Attribution automatique des médailles selon la position
        if (request.position() != null) {
            switch (request.position()) {
                case 1 -> resultat.setMedaille("GOLD");
                case 2 -> resultat.setMedaille("SILVER");
                case 3 -> resultat.setMedaille("BRONZE");
                default -> resultat.setMedaille(null);
            }
        }

        Resultat savedResultat = resultatRepository.save(resultat);
        return entityMapper.toResultatResponse(savedResultat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultatDto.Response> getPodiumByEpreuve(Long idEpreuve) {
        return resultatRepository.findByEpreuveIdEpreuveOrderByPositionAsc(idEpreuve)
                .stream()
                .filter(r -> r.getPosition() != null && r.getPosition() <= 3)
                .map(entityMapper::toResultatResponse)
                .toList();
    }
}