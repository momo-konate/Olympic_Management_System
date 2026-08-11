# Olympic Management System

Solution backend développée avec Spring Boot 3 pour la gestion globale des événements, athlètes, disciplines, épreuves et résultats des Jeux Olympiques. Le système gère le calcul du tableau des médailles, l'affichage des podiums et expose une double interface REST et SOAP.

## Technologies utilisées

- **Langage :** Java 17
- **Framework :** Spring Boot 3
- **Accès aux données :** Spring Data JPA / Hibernate
- **Services Web :** Spring Web (REST) & Spring Web Services (SOAP)
- **Base de données :** H2 Database (mode fichier persistant)
- **Documentation & Outils :** OpenAPI / Swagger UI, Lombok, Maven, JAXB, Spring Validation

## Architecture du projet

```
src/main/java/apiprojet/olympic_management_system/
├── Controllers/       # Contrôleurs REST
├── Dto/               # Records DTOs (Request / Response / Pagination)
├── Entity/             # Entités JPA (Nation, Athlete, Discipline, Epreuve, Resultat)
├── Exception/          # Gestion globale des erreurs (@ControllerAdvice)
├── Mappers/            # Composants de conversion Entity / DTO
├── Repositories/        # Interfaces Spring Data JPA
├── Services/            # Logique métier (calculs médailles, podiums, CRUD)
└── Soap/                # Configuration et endpoints SOAP
```

## Lancement du projet

### Prérequis

- Java 17 ou supérieur
- Maven 3.8+

### Démarrage

À la racine du projet, exécutez :

```bash
mvn spring-boot:run
```

L'application démarre par défaut sur le port `8083`.

## Documentation & Endpoints

### Console & UI REST

- Swagger UI : `http://localhost:8083/swagger-ui.html`
- Console H2 : `http://localhost:8083/h2-console`
  - JDBC URL : `jdbc:h2:file:./data/olympicdb`
  - User : `sa`
  - Password : *(vide)*

### Endpoints REST principaux

| Ressource | Endpoint |
|---|---|
| Nations | `/api/v1/nations` |
| Tableau des médailles | `GET /api/v1/nations/tableau-medailles` |
| Athlètes | `/api/v1/athletes` |
| Disciplines | `/api/v1/disciplines` |
| Épreuves | `/api/v1/epreuves` |
| Résultats | `/api/v1/resultats` |
| Podiums | `GET /api/v1/resultats/podium/{idEpreuve}` |

### Web Service SOAP

Le service Web SOAP permet de consulter l'historique complet des performances d'un athlète.

- WSDL : `http://localhost:8083/ws/resultats.wsdl`
- Endpoint SOAP : `http://localhost:8083/ws`

**Exemple de requête SOAP (Postman)**

- Méthode HTTP : `POST`
- Header : `Content-Type: text/xml`
- Body (XML) :

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:soap="http://apiprojet.olympic_management_system/soap">
   <soapenv:Header/>
   <soapenv:Body>
      <soap:GetHistoriqueAthleteRequest>
         <soap:idAthlete>1</soap:idAthlete>
      </soap:GetHistoriqueAthleteRequest>
   </soapenv:Body>
</soapenv:Envelope>
```
