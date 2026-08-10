package apiprojet.olympic_management_system.Controller;

import apiprojet.olympic_management_system.Dto.EpreuveDto;
import apiprojet.olympic_management_system.Dto.PageResponse;
import apiprojet.olympic_management_system.Service.EpreuveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/epreuves")
@RequiredArgsConstructor
public class EpreuveController {

    private final EpreuveService epreuveService;

    @PostMapping
    public ResponseEntity<EpreuveDto.Response> createEpreuve(@Valid @RequestBody EpreuveDto.Request request) {
        return new ResponseEntity<>(epreuveService.createEpreuve(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponse<EpreuveDto.Response>> getAllEpreuves(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nom") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return ResponseEntity.ok(epreuveService.getAllEpreuves(page, size, sortBy, sortDir));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpreuveDto.Response> getEpreuveById(@PathVariable Long id) {
        return ResponseEntity.ok(epreuveService.getEpreuveById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpreuve(@PathVariable Long id) {
        epreuveService.deleteEpreuve(id);
        return ResponseEntity.noContent().build();
    }
}