package apiprojet.olympic_management_system.Repositories;

import apiprojet.olympic_management_system.Entity.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, Long> {
    List<Resultat> findByEpreuveIdEpreuveOrderByPositionAsc(Long idEpreuve);
    List<Resultat> findByAthleteIdAthlete(Long idAthlete);
}