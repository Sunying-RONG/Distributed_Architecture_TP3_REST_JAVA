# HAI704ITP3_REST_JAVA
Une pratique de REST  
TP3 comprend trois projets (Maven Project, Spring Boot 2.6.2, Packaging Jar, Java 8, Dependencies : Spring Web, Spring Data JPA, H2 Database):  
- Projet HotelREST : est le serveur, port 8080, qui crée et fait sauvegarder les données des hôtels et agences par JPA, dans H2 Database. Dans les controllers, HotelController1 comprend les APIs (URL commence par http://localhost:8080/hotelsearch/api) pour agence login et cherche les propositions des hôtels et leurs chambres.  
HotelController2 comprend les APIs (URL commence par http://localhost:8080/hotelbook/api) pour agence login et faire des réservations. AgenceController comprend l'API (URL commence par http://localhost:8080/comparateur/api) qui donne toutes les propositions par agences pour la comparaison.
- Projet HotelRESTClient : est le client, port 8084, qui consomme les services de HotelController1 et HotelController2, pour faire recherche et réservation.
- Projet Comparateur : est le client, port 8088, qui consomme le service de AgenceController, pour faire la comparaison.
