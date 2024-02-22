# HAI704ITP3_REST_JAVA
cours HAI704I Architectures logicielles distribuées - une pratique de REST  
TP3 comprend trois projets (Maven Project, Spring Boot 2.6.2, Packaging Jar, Java 8, Dependencies : Spring Web, Spring Data JPA, H2 Database):  
- Projet HotelREST : est le serveur, port 8080, qui crée et fait sauvegarder les données des hôtels et agences par JPA, dans H2 Database. Dans les controllers, HotelController1 comprend les APIs (URL commence par http://localhost:8080/hotelsearch/api) pour agence login et cherche les propositions des hôtels et leurs chambres.  
HotelController2 comprend les APIs (URL commence par http://localhost:8080/hotelbook/api) pour agence login et faire des réservations. AgenceController comprend l'API (URL commence par http://localhost:8080/comparateur/api) qui donne toutes les propositions par agences pour la comparaison.
- Projet HotelRESTClient : est le client, port 8084, qui consomme les services de HotelController1 et HotelController2, pour faire recherche et réservation.
- Projet Comparateur : est le client, port 8088, qui consomme le service de AgenceController, pour faire la comparaison.

### Comparaison entre SOAP et REST
SOAP et REST tous les deux définissent comment créer des APIs, qui permettent aux données d'être communiquées entre les applications Web.  

L'idée principale de la conception de SOAP était de garantir que les programmes construits sur différentes plates-formes et langages de programmation puissent échanger des données de manière simple.  
SOAP utilise des interfaces de service pour exposer ses fonctionnalités aux applications clientes. Dans SOAP, le fichier WSDL fournit au client les informations nécessaires qui peuvent être utilisées pour comprendre quels services le service Web peut offrir.  
Quand une demande de données est envoyée à une API SOAP, elle peut être gérée via l'un des protocoles de couche d'application : HTTP (pour les navigateurs Web), SMTP (pour la messagerie électronique), TCP et autres. Cependant, une fois qu'une demande est reçue, les messages SOAP de retour doivent être renvoyés sous forme de documents XML.  

REST utilise des localisateurs Uniform Service pour accéder à la ressource.  
Quand une demande de données est envoyée à une API REST, elle se fait généralement via un protocole de transfert hypertexte (HTTP). Une fois qu'une demande est reçue, les API conçues pour REST peuvent renvoyer des messages dans divers formats : HTML, XML, texte brut et JSON.  
Pour REST, communication client-serveur sans état, aucun contenu client n'est stocké sur le serveur entre les demandes. Les informations sur l'état de la session sont plutôt conservées avec le client.  

Les APIs REST sont légères et plus flexibles. Les services Web SOAP offrent une sécurité intégrée et une conformité des transactions, mais cela les rend également plus lourds.
