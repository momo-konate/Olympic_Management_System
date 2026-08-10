package apiprojet.olympic_management_system.Repositories;

import apiprojet.olympic_management_system.Entity.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EpreuveRepository extends JpaRepository<Epreuve, Long> {
    List<Epreuve> findByDisciplineIdDiscipline(Long idDiscipline);
    List<Epreuve> findByDateEpreuveBetween(LocalDateTime debut, LocalDateTime fin);
}