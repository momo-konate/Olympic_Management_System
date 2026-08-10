package apiprojet.olympic_management_system.Controller;

import apiprojet.olympic_management_system.Dto.AthleteDto;
import apiprojet.olympic_management_system.Dto.PageResponse;
import apiprojet.olympic_management_system.Service.AthleteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/athletes")
@RequiredArgsConstructor
public class AthleteController {

    private final AthleteService athleteService;

    @PostMapping
    public ResponseEntity<AthleteDto.Response> createAthlete(@Valid @RequestBody AthleteDto.Request request) {
        return new ResponseEntity<>(athleteService.createAthlete(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponse<AthleteDto.Response>> getAllAthletes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nom") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return ResponseEntity.ok(athleteService.getAllAthletes(page, size, sortBy, sortDir));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AthleteDto.Response> getAthleteById(@PathVariable Long id) {
        return ResponseEntity.ok(athleteService.getAthleteById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAthlete(@PathVariable Long id) {
        athleteService.deleteAthlete(id);
        return ResponseEntity.noContent().build();
    }
}