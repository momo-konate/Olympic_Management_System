package apiprojet.olympic_management_system.Service;

import apiprojet.olympic_management_system.Dto.ResultatDto;

import java.util.List;

public interface ResultatService {
    ResultatDto.Response enregistrerResultat(ResultatDto.Request request);
    List<ResultatDto.Response> getPodiumByEpreuve(Long idEpreuve);
}