package apiprojet.olympic_management_system.Controller;

import apiprojet.olympic_management_system.Dto.DisciplineDto;
import apiprojet.olympic_management_system.Dto.PageResponse;
import apiprojet.olympic_management_system.Service.DisciplineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/disciplines")
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineService disciplineService;

    @PostMapping
    public ResponseEntity<DisciplineDto.Response> createDiscipline(@Valid @RequestBody DisciplineDto.Request request) {
        return new ResponseEntity<>(disciplineService.createDiscipline(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponse<DisciplineDto.Response>> getAllDisciplines(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nom") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return ResponseEntity.ok(disciplineService.getAllDisciplines(page, size, sortBy, sortDir));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplineDto.Response> getDisciplineById(@PathVariable Long id) {
        return ResponseEntity.ok(disciplineService.getDisciplineById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id) {
        disciplineService.deleteDiscipline(id);
        return ResponseEntity.noContent().build();
    }
}