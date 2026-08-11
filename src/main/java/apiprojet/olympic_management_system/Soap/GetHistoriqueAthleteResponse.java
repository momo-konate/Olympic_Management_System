package apiprojet.olympic_management_system.Soap;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"resultat"})
@XmlRootElement(name = "GetHistoriqueAthleteResponse", namespace = "http://apiprojet.olympic_management_system/soap")
public class GetHistoriqueAthleteResponse {

    @XmlElement(name = "resultat", namespace = "http://apiprojet.olympic_management_system/soap")
    private List<ResultatSoapDto> resultat;

    public List<ResultatSoapDto> getResultat() {
        if (resultat == null) {
            resultat = new ArrayList<>();
        }
        return this.resultat;
    }
}