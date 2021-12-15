<p align="center">
<img src=https://smshosting.s3.eu-west-3.amazonaws.com/cover-progetto.jpg>
</p>

# [Smshosting](https://www.smshosting.it)

[![en](https://img.shields.io/badge/lang-en-red.svg)](/README.en.md)

## La piattaforma più completa per comunicare con i tuoi clienti.

Questa libreria Java ti permette di integrare i nostri servizi nei tuoi progetti, aggiungendo la possibilità di inviare SMS, Email, gestire clienti e molto di più.

## Funzioni della libreria
Puoi usare questa libreria per:
- Inviare SMS
- Inviare campagne SMS massive
- Creare e gestire contatti
- Creare e gestire gruppi
- Inviare Email singole
- Inviare campagne Email
- Controllare lo stato e il credito dell'account... e molto di più.

## Come iniziare

#### Crea un account
- Crea un account Smshosting [qui](https://cloud.smshosting.it/sms/signupInit.ic).
- Una volta attivato l'account, vai su Sviluppatori -> API REST, HTTP e SOAP per accedere alle tue credenziali per le API.

#### Scarica la libreria

- Clona usando `git` o scarica la libreria e includila nel tuo progetto.

## Alcuni esempi

Non tutti i parametri sono obbligatori. Fai riferimento alla [Documentazione](https://help.smshosting.it/it/sms-rest-api) per maggiori informazioni.

#### Ottieni un' istanza del client
```java
SmsHostingClient clientSmsh = new SmsHostingClient("YOUR_API_KEY", "YOUR_SECRET_KEY"));
```

#### Invia singolo SMS 
```java
SendSmsResponse res = clientSmsh.sendSms("mySender", "39YYYXXXXXXX", null, "smsText", null, null, false, null, "AUTO");
```

#### Aggiungi un contatto
```java
ContactResponse res = clientSmsh.addContact("39YYYXXXXXXX", "ContactName", "ContactLastname", "contact@email.it", null, null, null);
```

#### Ottieni la lista dei contatti
```java
ContactSearchResponse res = res = clientSmsh.searchContacts(null, null, null, null, null, null, null);
```

#### Cerca contatto usando il numero di telefono
```java
ContactSearchResponse res = res = clientSmsh.searchContacts(null, "39YYYXXXXXXX", null, null, null, null, null);
```

## Documentazione
Puoi trovare la documentazione relativa alle API Smhosting [quì](https://help.smshosting.it/it/sms-rest-api)

## Assistenza
Se hai bisogno di aiuto puoi contattarci scrivendo a [assistenza@smshosting.it](mailto:assistenza@smshosting.it) o visitando la [pagina di supporto](https://www.smshosting.it/it/supporto-tecnico-e-commerciale)
