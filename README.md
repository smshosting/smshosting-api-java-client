java_smshostingApiTest
========================
Semplice progetto Java per richiamare le API smshosting.

Cartella jar 
============
Contiene l'applicazione pronta da usare

Cartella smshostingApiTest
==========================
Contiene un progetto Maven con gli esempi delle chiamate più usate di smshosting.
Per ogni risorsa sono porposti, ma sempre modificabili per testare gli errori:
* url
* parametri
* tipo di risposta voluta (json o xml)

La sintassi ed i comandi sono scritti volutamente in modo semplice per facilitare la comprensione.
Con l'obbiettivo di rendere utilizzabile l'applicazione sono state inserite due funzionalità extra:
* il savataggio delle credenziali (nella cartella utente senza crittografia)
* la formattazione (json e xml) della risposta