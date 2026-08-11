# Olympic Management System - API REST et Web Service SOAP
###Solution backend développée avec Spring Boot 3 permettant de gérer les événements, athlètes, disciplines, épreuves et résultats des Jeux Olympiques. Le système gère le calcul du tableau des médailles, les podiums et expose une double interface REST et SOAP.

##Technologies utilisées
Java 17

Spring Boot 3

Spring Data JPA / Hibernate

Spring Web (API REST)

Spring Web Services (Web Service SOAP)

Spring Validation

Base de données H2 (mode fichier persistant)

OpenAPI / Swagger UI

Lombok, Maven, JAXB

##Architecture du projet
src/main/java/apiprojet/olympic_management_system/
├── Controllers/       # Contrôleurs REST
├── Dto/               # Records DTOs (Request / Response / Pagination)
├── Entity/            # Entités JPA (Nation, Athlete, Discipline, Epreuve, Resultat)
├── Exception/         # Gestion globale des erreurs (@ControllerAdvice)
├── Mappers/           # Composants de conversion Entity / DTO
├── Repositories/      # Interfaces Spring Data JPA
├── Services/          # Logique métier (Calculs médailles, podiums, CRUD)
└── Soap/              # Configuration et Endpoints SOAP
##Lancement du projet
Prérequis
Java 17 ou supérieur installé

Maven 3.8+

Démarrage
À la racine du projet, exécutez :

Bash
mvn spring-boot:run
L'application démarre par défaut sur le port 8083.

Documentation et Endpoints
Documentation REST
Swagger UI : http://localhost:8083/swagger-ui.html

Console H2 Web : http://localhost:8083/h2-console

JDBC URL : jdbc:h2:file:./data/olympicdb

User : sa | Password : (vide)

Endpoints REST principaux
Nations : /api/v1/nations

Tableau des médailles : GET /api/v1/nations/tableau-medailles

Athlètes : /api/v1/athletes

Disciplines : /api/v1/disciplines

Épreuves : /api/v1/epreuves

Résultats : /api/v1/resultats

Podiums : GET /api/v1/resultats/podium/{idEpreuve}

##Web Service SOAP
Le service SOAP permet de consulter l'historique des performances d'un athlète.

WSDL : http://localhost:8083/ws/resultats.wsdl

Endpoint SOAP : http://localhost:8083/ws

Exemple de requête SOAP (Postman)
Méthode : POST

Header : Content-Type: text/xml

Body (XML) :

XML
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:soap="http://apiprojet.olympic_management_system/soap">
   <soapenv:Header/>
   <soapenv:Body>
      <soap:GetHistoriqueAthleteRequest>
         <soap:idAthlete>1</soap:idAthlete>
      </soap:GetHistoriqueAthleteRequest>
   </soapenv:Body>
</soapenv:Envelope>
