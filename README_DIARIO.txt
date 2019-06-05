QUESTO È IL README GLOBALE: per leggere in dettaglio cosa fa ogni singolo programma, si prega di consultare i README che si trovano nelle loro cartelle corrispondenti.

Progresso 27/05:
Pianificazione del progetto per la prima settimana, siamo riusciti a far funzionare il sensore di umidità e di temperatura, abbiamo iniziato a scrivere un programma in java che legga i suoi valori.

Progresso 28/05:
Siamo riusciti a far funzionare il sensore di movimento (quando viene rilevato un movimento il sens. di temp. e um. legge un valore), inizio programma java lettura da seriale.

Progresso 29/05:
Abbiamo finito di scrivere il programma java che legge i valori da seriale, inoltre quest'ultimo adesso chiede all'utente quale valore vuole leggere (temperatura, umidità oppure entrambe).

Progresso 30/05:
Oggi siamo riusciti a far comunicare due Antenne wireless ognuna collegata al proprio Arduino, TX invia i dati della temperatura e dell'umidità, RX li riceve e li trasmette a sua volta al PC (i valori ricevuti possono essere letti sia da monitor seriale di arduino che dal programma java).

Progresso 31/05: 
Abbiamo adottato ed implementato un protocollo standard per la trasmissione seriale da Arduino RX, per poterla rendere più omogenea.

Progresso 01/06:
Inizio sito web per la visualizzazione della temperatura e dell'umidità, punto della situazione dopo una settimana di lavoro.

Progresso 03/06:
Pianificazione del lavoro per la settimana, sito web: abbiamo scritto un programma in java che sovrascrive la riga del file html corrispondente alla temperatura e all'umidità, in questo modo quando viene aggiornata la pagina web si aggiornano anche i valori della temperatura e dell'umidita'.

Progresso 04/06:
Abbiamo scritto un progrmma java che ricevuta la temperatura e l'umidita' attraverso Arduino RX, li scrive, insieme al numero di rilevazione, su un foglio CSV, per poterli analizzare successivamente.
