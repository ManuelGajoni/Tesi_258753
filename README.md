# Tesi_258753
## **TITOLO DELLA PROPOSTA:** Gestione della prenotazione di lezioni: il caso di un’associazione per DSA.
### ***STUDENTE:*** Gajoni Manuel s258753
#### **DESCRIZIONE DEL TEMA PROPOSTO:**
Lavorando in un’associazione che fornisce tra i vari servizi quello di tutoraggio e ripetizioni per gli studenti,
ho riscontrato come la gestione delle prenotazioni possa essere problematica. E’ presente una richiesta
altamente variabile da parte degli studenti che dipende dal periodo scolastico in cui ci si trova. I tutor, a loro
volta, garantiscono delle disponibilità che possono variare da settimana a settimana e non garantiscono di
poter seguire tutte le materie scolastiche. Ad oggi le prenotazioni sono gestite dal solo utilizzo di un foglio di
calcolo di Excel. Questo metodo presenta alcuni problemi tra cui: la mancanza di programmazione, visto che
il calendario viene redatto giornalmente aumentando così il rischio di imprevisti, il tempo richiesto e la
difficoltà nel reperire informazioni su studenti e tutor.
#### **DESCRIZIONE DELLA RILEVANZA GESTIONALE DEL PROBLEMA:**
Lo scopo del progetto è quello di confrontare in 5/6 mesi i tre metodi di prenotazione delle lezioni: quello
utilizzato fino ad oggi dall’associazione, quello con l’utilizzo di questo programma in modalità ‘manuale’ e in
modalità ‘automatica’ con lo scopo di capire le reali funzionalità e limiti del metodo automatico.
#### **DESCRIZIONE DEI DATA-SET UTILIZZATI:**
I Data-Set utilizzati saranno creati da zero grazie ai dati reali che mi verranno forniti dall’associazione. Le
dimensioni del database saranno di circa 100/150 studenti, 15/20 tutor e lezioni prenotate lungo tutto
l’anno scolastico 2023/24.
#### **DESCRIZIONE DEGLI ALGORITMI COINVOLTI:**
Nell’applicazione saranno presenti 4 schede: Gestisci lezioni, Gestisci studenti, Gestisci tutor e Resoconto. Le
sezioni ‘Gestisci studenti’ e ‘Gestisci Tutor’ sono dedicate all’inserimento e alla gestione dei dati degli
studenti e tutor nel database. La sezione ‘Resoconto’ è dedicata alla stampa delle lezioni ed ore dei tutor (o
studenti) nel mese selezionato. La sezione ‘Gestisci lezione’ è pensata per le prenotazioni, e può essere
utilizzata in versione ‘manuale’, dove sarà l’operatore a decidere gli abbinamenti tutor-studente, o nella
versione ‘automatica’. In quest’ultima, il programma calcolerà i migliori abbinamenti possibili con i tutor
tramite una funzione ricorsiva nella quale il peso sarà determinato grazie ad una tabella dei
punteggi: si andrà dal punteggio più basso (0) assegnato ad un tutor che non ha mai seguito lo studente e
non è in grado di seguire la materia richiesta per la lezione, fino al punteggio più alto (6) nel quale il tutor
non solo è in grado di seguire la materia indicata ma ha già seguito lo studente sulla medesima materia. 
Lo scopo dell’algoritmo sarà quello di massimizzare quindi il punteggio delle
assegnazioni. L’applicazione è pensata per essere utilizzata dalla persona preposta alle prenotazioni delle
lezioni e dal tesoriere dell’associazione per compilare il resoconto mensile riguardante i dipendenti.
