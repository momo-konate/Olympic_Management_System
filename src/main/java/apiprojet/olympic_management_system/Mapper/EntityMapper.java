package apiprojet.olympic_management_system.Mapper;

import apiprojet.olympic_management_system.Dto.*;
import apiprojet.olympic_management_system.Entity.*;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    // --- NATION ---
    public NationDto.Response toNationResponse(Nation entity) {
        if (entity == null) return null;
        return NationDto.Response.builder()
                .idNation(entity.getIdNation())
                .nom(entity.getNom())
                .codeIso(entity.getCodeIso())
                .build();
    }

    public Nation toNationEntity(NationDto.Request request) {
        if (request == null) return null;
        return Nation.builder()
                .nom(request.nom())
                .codeIso(request.codeIso())
                .build();
    }

    // --- DISCIPLINE ---
    public DisciplineDto.Response toDisciplineResponse(Discipline entity) {
        if (entity == null) return null;
        return DisciplineDto.Response.builder()
                .idDiscipline(entity.getIdDiscipline())
                .nom(entity.getNom())
                .description(entity.getDescription())
                .build();
    }

    public Discipline toDisciplineEntity(DisciplineDto.Request request) {
        if (request == null) return null;
        return Discipline.builder()
                .nom(request.nom())
                .description(request.description())
                .build();
    }

    // --- ATHLETE ---
    public AthleteDto.Response toAthleteResponse(Athlete entity) {
        if (entity == null) return null;
        return AthleteDto.Response.builder()
                .idAthlete(entity.getIdAthlete())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .sexe(entity.getSexe())
                .dateDeNaissance(entity.getDateDeNaissance())
                .taille(entity.getTaille())
                .poids(entity.getPoids())
                .nation(toNationResponse(entity.getNation()))
                .discipline(toDisciplineResponse(entity.getDiscipline()))
                .build();
    }

    public Athlete toAthleteEntity(AthleteDto.Request request, Nation nation, Discipline discipline) {
        if (request == null) return null;
        return Athlete.builder()
                .nom(request.nom())
                .prenom(request.prenom())
                .sexe(request.sexe())
                .dateDeNaissance(request.dateDeNaissance())
                .taille(request.taille())
                .poids(request.poids())
                .nation(nation)
                .discipline(discipline)
                .build();
    }

    // --- EPREUVE ---
    public EpreuveDto.Response toEpreuveResponse(Epreuve entity) {
        if (entity == null) return null;
        return EpreuveDto.Response.builder()
                .idEpreuve(entity.getIdEpreuve())
                .nom(entity.getNom())
                .dateEpreuve(entity.getDateEpreuve())
                .lieu(entity.getLieu())
                .statut(entity.getStatut())
                .discipline(toDisciplineResponse(entity.getDiscipline()))
                .build();
    }

    public Epreuve toEpreuveEntity(EpreuveDto.Request request, Discipline discipline) {
        if (request == null) return null;
        return Epreuve.builder()
                .nom(request.nom())
                .dateEpreuve(request.dateEpreuve())
                .lieu(request.lieu())
                .statut(request.statut())
                .discipline(discipline)
                .build();
    }

    // --- RESULTAT ---
    public ResultatDto.Response toResultatResponse(Resultat entity) {
        if (entity == null) return null;
        return ResultatDto.Response.builder()
                .idResultat(entity.getIdResultat())
                .position(entity.getPosition())
                .performance(entity.getPerformance())
                .unite(entity.getUnite())
                .medaille(entity.getMedaille())
                .athlete(toAthleteResponse(entity.getAthlete()))
                .epreuve(toEpreuveResponse(entity.getEpreuve()))
                .build();
    }

    public Resultat toResultatEntity(ResultatDto.Request request, Athlete athlete, Epreuve epreuve) {
        if (request == null) return null;
        return Resultat.builder()
                .position(request.position())
                .performance(request.performance())
                .unite(request.unite())
                .athlete(athlete)
                .epreuve(epreuve)
                .build();
    }
}