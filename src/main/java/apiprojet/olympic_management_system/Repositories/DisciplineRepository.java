package apiprojet.olympic_management_system.Repositories;

import apiprojet.olympic_management_system.Entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}