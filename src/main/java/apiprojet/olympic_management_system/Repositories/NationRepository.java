package apiprojet.olympic_management_system.Repositories;

import apiprojet.olympic_management_system.Dto.TableauMedaillesDto;
import apiprojet.olympic_management_system.Entity.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NationRepository extends JpaRepository<Nation, Long> {

    Optional<Nation> findByCodeIso(String codeIso);

    @Query("SELECT new apiprojet.olympic_management_system.Dto.TableauMedaillesDto(" +
            "n.idNation, n.nom, n.codeIso, " +
            "SUM(CASE WHEN r.medaille = 'GOLD' THEN 1L ELSE 0L END), " +
            "SUM(CASE WHEN r.medaille = 'SILVER' THEN 1L ELSE 0L END), " +
            "SUM(CASE WHEN r.medaille = 'BRONZE' THEN 1L ELSE 0L END), " +
            "COUNT(r.idResultat), " +
            "SUM(CASE WHEN r.medaille = 'GOLD' THEN 7L WHEN r.medaille = 'SILVER' THEN 4L WHEN r.medaille = 'BRONZE' THEN 1L ELSE 0L END)) " +
            "FROM Nation n " +
            "LEFT JOIN n.athletes a " +
            "LEFT JOIN a.resultats r " +
            "GROUP BY n.idNation, n.nom, n.codeIso " +
            "ORDER BY SUM(CASE WHEN r.medaille = 'GOLD' THEN 1L ELSE 0L END) DESC, " +
            "         SUM(CASE WHEN r.medaille = 'SILVER' THEN 1L ELSE 0L END) DESC, " +
            "         SUM(CASE WHEN r.medaille = 'BRONZE' THEN 1L ELSE 0L END) DESC")
    List<TableauMedaillesDto> getTableauMedailles();
}