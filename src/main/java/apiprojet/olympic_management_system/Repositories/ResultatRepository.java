package apiprojet.olympic_management_system.Repositories;

import apiprojet.olympic_management_system.Entity.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, Long> {
    List<Resultat> findByEpreuveIdEpreuveOrderByPositionAsc(Long idEpreuve);
    @Query("SELECT r FROM Resultat r LEFT JOIN FETCH r.epreuve WHERE r.athlete.idAthlete = :idAthlete")
    List<Resultat> findByAthleteIdAthlete(@Param("idAthlete") Long idAthlete);
}