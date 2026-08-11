package apiprojet.olympic_management_system.Soap;

import apiprojet.olympic_management_system.Repositories.ResultatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional; // Import Transactional
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
@RequiredArgsConstructor
public class ResultatSoapEndpoint {

    private static final String NAMESPACE_URI = "http://apiprojet.olympic_management_system/soap";
    private final ResultatRepository resultatRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetHistoriqueAthleteRequest")
    @ResponsePayload
    @Transactional(readOnly = true) // <-- Maintenir la session ouverte pour le Fetch LAZY
    public GetHistoriqueAthleteResponse getHistoriqueAthlete(@RequestPayload GetHistoriqueAthleteRequest request) {
        GetHistoriqueAthleteResponse response = new GetHistoriqueAthleteResponse();

        if (request.getIdAthlete() == null) {
            return response;
        }

        List<ResultatSoapDto> soapResults = resultatRepository.findByAthleteIdAthlete(request.getIdAthlete())
                .stream()
                .map(r -> {
                    ResultatSoapDto dto = new ResultatSoapDto();
                    dto.setIdResultat(r.getIdResultat());
                    dto.setNomEpreuve(r.getEpreuve() != null ? r.getEpreuve().getNom() : "");
                    dto.setPosition(r.getPosition() != null ? r.getPosition() : 0);
                    dto.setPerformance(r.getPerformance() != null ? r.getPerformance() : 0.0);
                    dto.setUnite(r.getUnite());
                    dto.setMedaille(r.getMedaille());
                    return dto;
                })
                .toList();

        response.getResultat().addAll(soapResults);
        return response;
    }
}