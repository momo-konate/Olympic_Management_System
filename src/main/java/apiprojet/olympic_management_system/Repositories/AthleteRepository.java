package apiprojet.olympic_management_system.Repositories;

import apiprojet.olympic_management_system.Entity.Athlete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    // Recherche paginée de tous les athlètes
    Page<Athlete> findAll(Pageable pageable);

    // Filtrage par discipline avec pagination
    Page<Athlete> findByDisciplineIdDiscipline(Long idDiscipline, Pageable pageable);
}