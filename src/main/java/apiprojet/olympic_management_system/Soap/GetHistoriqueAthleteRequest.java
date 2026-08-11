package apiprojet.olympic_management_system.Soap;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"idAthlete"})
@XmlRootElement(name = "GetHistoriqueAthleteRequest", namespace = "http://apiprojet.olympic_management_system/soap")
@Getter
@Setter
public class GetHistoriqueAthleteRequest {

    @XmlElement(namespace = "http://apiprojet.olympic_management_system/soap", required = true)
    private Long idAthlete;
}