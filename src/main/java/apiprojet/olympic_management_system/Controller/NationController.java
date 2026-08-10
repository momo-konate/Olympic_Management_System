package apiprojet.olympic_management_system.Controller;

import apiprojet.olympic_management_system.Dto.NationDto;
import apiprojet.olympic_management_system.Dto.PageResponse;
import apiprojet.olympic_management_system.Dto.TableauMedaillesDto;
import apiprojet.olympic_management_system.Service.NationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nations")
@RequiredArgsConstructor
public class NationController {

    private final NationService nationService;

    @PostMapping
    public ResponseEntity<NationDto.Response> createNation(@Valid @RequestBody NationDto.Request request) {
        return new ResponseEntity<>(nationService.createNation(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponse<NationDto.Response>> getAllNations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nom") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return ResponseEntity.ok(nationService.getAllNations(page, size, sortBy, sortDir));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NationDto.Response> getNationById(@PathVariable Long id) {
        return ResponseEntity.ok(nationService.getNationById(id));
    }

    // Endpoint requis : Tableau des médailles
    @GetMapping("/tableau-medailles")
    public ResponseEntity<List<TableauMedaillesDto>> getTableauMedailles() {
        return ResponseEntity.ok(nationService.getTableauMedailles());
    }
}