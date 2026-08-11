package apiprojet.olympic_management_system.Soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultatSoapDto", namespace = "http://apiprojet.olympic_management_system/soap", propOrder = {
        "idResultat", "nomEpreuve", "position", "performance", "unite", "medaille"
})
@Getter
@Setter
public class ResultatSoapDto {
    @XmlElement(namespace = "http://apiprojet.olympic_management_system/soap")
    private Long idResultat;

    @XmlElement(namespace = "http://apiprojet.olympic_management_system/soap")
    private String nomEpreuve;

    @XmlElement(namespace = "http://apiprojet.olympic_management_system/soap")
    private Integer position;

    @XmlElement(namespace = "http://apiprojet.olympic_management_system/soap")
    private Double performance;

    @XmlElement(namespace = "http://apiprojet.olympic_management_system/soap")
    private String unite;

    @XmlElement(namespace = "http://apiprojet.olympic_management_system/soap")
    private String medaille;
}