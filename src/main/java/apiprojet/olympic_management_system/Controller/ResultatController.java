package apiprojet.olympic_management_system.Controller;

import apiprojet.olympic_management_system.Dto.ResultatDto;
import apiprojet.olympic_management_system.Service.ResultatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resultats")
@RequiredArgsConstructor
public class ResultatController {

    private final ResultatService resultatService;

    @PostMapping
    public ResponseEntity<ResultatDto.Response> enregistrerResultat(@Valid @RequestBody ResultatDto.Request request) {
        return new ResponseEntity<>(resultatService.enregistrerResultat(request), HttpStatus.CREATED);
    }

    @GetMapping("/podium/{idEpreuve}")
    public ResponseEntity<List<ResultatDto.Response>> getPodiumByEpreuve(@PathVariable Long idEpreuve) {
        return ResponseEntity.ok(resultatService.getPodiumByEpreuve(idEpreuve));
    }
}