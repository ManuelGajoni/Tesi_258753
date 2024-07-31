package ProvaGitt.Tesi.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ProvaGitt.Tesi.DAO.LezioniDAO;

public class Model 
{
	public LezioniDAO dao = new LezioniDAO();
	List <Lezione> LezMigliori; 
	int punteggio;
	
	// TROVO L'ID DEL TUTOR DAL NOME
	public int trovaIdTutor (List <Tutor> tutor, String nome)
	{
		for(int i=0; i<tutor.size(); i++)
		{
			if(tutor.get(i).getNome().equals(nome))
				return i;
		}
		return -1;
	}
	
	// TROVO L'ID DELLO STUDENTE DAL NOME
	public int trovaIdStudente (List <Studente> studenti, String nome)
	{
		for (int i=0; i<studenti.size(); i++)
		{
			if(studenti.get(i).getNome().equals(nome))
				return i;
		}
		return -1;
	}
	
	// TROVO LO STUDENTE DAL SUO ID
	public Studente trovaStudente (List <Studente> studenti, int id)
	{
		Studente s = null;
		for(int i=0; i<studenti.size(); i++)
		{
			if(studenti.get(i).getIdStudente() == id)
			{
				s = studenti.get(i);
			}
		}
		return s; 
	}
	
	// TROVO IL TUTOR DAL SUO ID
	public Tutor trovaTutor (List <Tutor> tutor, int id)
	{
		Tutor t = null;
		for(int i=0; i<tutor.size(); i++)
		{
			if(tutor.get(i).getIdTutor() == id)
			{
				t = tutor.get(i);
			}
		}
		return t;
	}

	// CREO L'ARRAY DEI MESI
	public List <String> RiempiMesi ()
	{
		List <String> m = new ArrayList <>();
		m.add("Gennaio");
		m.add("Febbraio");
		m.add("Marzo");
		m.add("Aprile");
		m.add("Maggio");
		m.add("Giugno");
		m.add("Luglio");
		m.add("Agosto");
		m.add("Settembre");
		m.add("Ottobre");
		m.add("Novembre");
		m.add("Dicembre");
		return m;
	}
	
	// RICAVO IL NUMERO DEL MESE DAL NOME
	public int OttieniMesi (String s)
	{
		int r = -1;
		switch (s)
		{
			case "Gennaio":
				r=1;
				return r;
			case "Febbraio":
				r=2;
				return r;
			case "Marzo":
				r=3;
				return r;
			case "Aprile":
				r=4;
				return r;
			case "Maggio":
				r=5;
				return r;
			case "Giugno":
				r=6;
				return r;
			case "Luglio":
				r=7;
				return r;
			case "Agosto":
				r=8;
				return r;
			case "Settembre":
				r=9;
				return r;
			case "Ottobre":
				r=10;
				return r;
			case "Novembre":
				r=11;
				return r;
			case "Dicembre":
				r=12;
				return r;
		}
		return r;
	}

	// CREO L'ARRAY DEGLI ORARI 
	public List <String> RiempiOrario ()
	{
		List <String> s = new ArrayList <String> ();
		s.add("9-11");
		s.add("11-13");
		s.add("15-17");
		s.add("17-19");
		return s;
	}
	
	// CREO L'ARRAY DELLE CLASSI
	public List <String> RiempiClasse ()
	{
		List <String> c = new ArrayList <> ();
		c.add("Elementari");
		c.add("Medie");
		c.add("Superiori");
		c.add("Università");
		return c;
	}

	// CONTROLLO SE E' STATA GIA' INSERITA LA LEZIONE
	public boolean isCorrettaLezione (Lezione l, List <Lezione> lez)
	{	
		for(int i=0; i<lez.size(); i++)
		{
			if( (l.getDayY() == lez.get(i).getDayY()) && (l.getAnno() == lez.get(i).getAnno()) && (l.getOrario().equals(lez.get(i).getOrario())) && (l.getIdTutor() == lez.get(i).getIdTutor()))
			{
				return false;
			}
		}
		return true;
	}

	// CALCOLO IL PUNTEGGIO DELLA SINGOLA LEZIONE
	public int calcolaPunteggio (Lezione l)
	{
		Tutor t = this.trovaTutor(getTutor(), l.getIdTutor());
		if(t == null)
		{
			return -1;
		}
		Studente s = this.trovaStudente(getStudenti(), l.getIdStudente());
		if(s == null)
		{
			return -1;
		}
		Calendar now = Calendar.getInstance();
		List <Lezione> lez = this.getLezioniTutorStudenteFatte(s.getIdStudente(), t.getIdTutor(), now.get(Calendar.DAY_OF_YEAR) , now.get(Calendar.YEAR));
		
		if( (l.getMateria().equals("Sostegno DSA")) ||  (l.getMateria().equals("Consulenza Educatore")) )
		{
			if(t.isEducatore())
			{
				return 1;
			}
			if(!t.isEducatore())
			{
				return 0;
			}
		}
		
		if(s.getClasse().equals("Elementari") || s.getClasse().equals("Medie"))
		{
			if(s.isDSA())
			{
				if( (lez.isEmpty()) && (!t.isEducatore()))
				{
					return 1;
				}
				if( (lez.isEmpty()) && (t.isEducatore()))
				{
					return 2;
				}
				if( (!lez.isEmpty()) && (!t.isEducatore()))
				{
					return 2;
				}
				if( (!lez.isEmpty()) && (t.isEducatore()))
				{
					return 3;
				}
			} else
			{
				if( (lez.isEmpty()) && (!t.isEducatore()))
				{
					return 1;
				}
				if( (lez.isEmpty()) && (t.isEducatore()))
				{
					return 1;
				}
				if( (!lez.isEmpty()) && (!t.isEducatore()))
				{
					return 2;
				}
				if( (!lez.isEmpty()) && (t.isEducatore()))
				{
					return 2;
				}
			}
		}
		if(s.getClasse().equals("Superiori") || s.getClasse().equals("Università"))
		{
			if(s.isDSA())
			{
				if(!t.Materie.contains(l.getMateria()))
				{
					return 0;
				}
				if( (t.Materie.contains(l.getMateria())) && (lez.isEmpty()) && (!t.isEducatore())  )
				{
					return 1;
				}
				if( (t.Materie.contains(l.getMateria())) && (lez.isEmpty()) && (t.isEducatore())  )
				{
					return 2;
				}
				if( (t.Materie.contains(l.getMateria())) && (!lez.isEmpty()) && (!t.isEducatore()) && (!this.isMateriaSeguita(lez, l.getMateria()))  )
				{
					return 3;
				}
				if( (t.Materie.contains(l.getMateria())) && (!lez.isEmpty()) && (t.isEducatore()) &&  (!this.isMateriaSeguita(lez, l.getMateria())) )
				{
					return 3;
				}
				if( (t.Materie.contains(l.getMateria())) && (this.isMateriaSeguita(lez, l.getMateria())) && (!t.isEducatore())  )
				{
					return 4;
				}
				if( (t.Materie.contains(l.getMateria())) && (this.isMateriaSeguita(lez, l.getMateria())) && (t.isEducatore())  )
				{
					return 5;
				}
				
			} else
			{
				if(!t.Materie.contains(l.getMateria()))
				{
					return 0;
				}
				if( (t.Materie.contains(l.getMateria())) && (lez.isEmpty()) && (!t.isEducatore())  )
				{
					return 2;
				}
				if( (t.Materie.contains(l.getMateria())) && (lez.isEmpty()) && (t.isEducatore())  )
				{
					return 2;
				}
				if( (t.Materie.contains(l.getMateria())) && (!lez.isEmpty()) && (!t.isEducatore()) &&  (!this.isMateriaSeguita(lez, l.getMateria())) )
				{
					return 3;
				}
				if( (t.Materie.contains(l.getMateria())) && (!lez.isEmpty()) && (t.isEducatore()) &&  (!this.isMateriaSeguita(lez, l.getMateria())) )
				{
					return 3;
				}
				if( (t.Materie.contains(l.getMateria())) && (this.isMateriaSeguita(lez, l.getMateria())) && (!t.isEducatore())  )
				{
					return 4;
				}
				if( (t.Materie.contains(l.getMateria())) && (this.isMateriaSeguita(lez, l.getMateria())) && (t.isEducatore())  )
				{
					return 4;
				}
			}
			
			
		}
		
		
		return -1;
	}
	
	// CALCOLO IL PUNTEGGIO DELLE LEZIONI 
	public int calcolaPunteggioTot (List <Lezione> l)
	{
		int tot= 0; 
		for(int i=0; i<l.size(); i++)
		{
			tot += this.calcolaPunteggio(l.get(i));
		}
		return tot;
	}
	
	// CALCOLO IL NUMERO DI INTERNI NELLE PRENOTAZIONI
	public int calcolaInterno (List <Lezione> l)
	{
		int result = 0;
		for(int i=0; i<l.size(); i++)
		{
			Tutor t = this.trovaTutor(getTutor(), l.get(i).getIdTutor());
			if(t.isInterno())
			{
				result++;
			}
		}
		return result;
	}
	
	// CONTROLLO SE LE LEZIONI DA PRENOTARE NON CONTENGANO LEZIONI NON PRENOTABILI
	public boolean isNonZero (List<Lezione> l)
	{
		for(int i=0; i<l.size(); i++)
		{
			if(this.calcolaPunteggio(l.get(i)) == 0)
			{
				return false;
			}
		}
		return true;
	}
	
	// CONTROLLO SE IL TUTOR HA FATTO UNA LEZIONE CON LO STUDENTE SULLA MEDESIMA MATERIA
	public boolean isMateriaSeguita (List <Lezione> l, String m)
	{
		for(int i=0; i<l.size(); i++)
		{
			if(l.get(i).getMateria().equals(m))
				return true;
		}
		return false;
	}
	
	// TROVO LE LEZIONI MENSILI DEL TUTOR
	public List <Lezione> getResocontoLezioniTutor (int mese, int id, int anno)
	{
		return dao.getResocontoLezioniTutor(mese, id,anno);
	}
	
	// TROVO LE LEZIONI DEL TUTOR PRENOTATE DA OGGI
	public List <Lezione> getResocontoLezioniPrenotateTutor(int DayY, int id, int anno)
	{
		return dao.getResocontoLezioniPrenotateTutor(DayY, id, anno);
	}
	
	// TROVO LE LEZIONI MENSILI DELLO STUDENTE
	public List<Lezione> getResocontoLezioniStudente (int mese, int id, int anno)
	{
		return dao.getResocontoLezioniStudente(mese, id, anno);
	}
	
	// TROVO TUTTE LE LEZIONI PRENOTATE DA OGGI
	public List <Lezione> getResocontoLezioniPrenotate (int anno, int DayY)
	{
		return dao.getResocontoLezioniPrenotate(anno, DayY);
	}
	
	// TROVO LE LEZIONI PRENOTATE NEL MESE SELEZIONATO
	public List <Lezione> getResocontoLezioniPrenotateMese (int mese, int day)
	{
		return dao.getResocontoLezioniPrenotateMese(mese, day);
	}
	
	// SELEZIONO TUTTI I TUTOR
	public List <Tutor> getTutor()
	{
		return dao.getTutor();
	}
	
	// SELEZIONO TUTTE LE MATERIE
	public List <String> getMaterie()
	{
		return dao.getMaterie();
	}
	
	// SELEZIONO TUTTI GLI STUDENTI
	public List <Studente> getStudenti()
	{
		return dao.getStudenti();
	}

	// INSERISCO UNO STUDENTE NEL DATABASE
	public boolean InserisciStudente (Studente s)
	{
		return dao.InserisciStudente(s);
	}
	
	// CANCELLO UNO STUDENTE DAL DATABASE
	public boolean CancellaStudente (int id)
	{
		return dao.CancellaStudente(id);
	}
	
	// INSERISCO UN TUTOR NEL DATABASE
	public boolean InserisciTutor (Tutor t)
	{
		return dao.InserisciTutor(t);
	}
	
	// CANCELLO UN TUTOR DAL DATABASE
	public boolean CancellaTutor (int id)
	{
		return dao.CancellaTutor(id);
	}
	
	// INSERISCO UNA NUOVA DISPONIBILITA' DEL TUTOR
	public boolean InserisciDisponibilita (Disponibilita d)
	{
		return dao.InserisciDisponibilita(d);
	}
	
	// CANCELLO UNA DISPONIBILITA' DAL DATABASE
	public boolean CancellaDisponibilita (int id)
	{
		return dao.CancellaDisponibilita(id);
	}
	
	// INSERISCO UNA NUOVA LEZIONE NEL DATABASE
	public boolean InserisciLezione (Lezione l)
	{
		return dao.InserisciLezione(l);
	}
	
	// CANCELLO UNA LEZIONE DAL DATABASE
	public boolean CancellaLezione (int id)
	{
		return dao.CancellaLezione(id);
	}
	
	// TROVO LE DISPONIBILITA' FUTURE DEL TUTOR SELEZIONATO 
	public List <Disponibilita> getDisponibilitaFuture (int DayY, int anno, int id)
	{
		return dao.getDisponibilitaFuture(DayY, anno, id);
	}
	
	// RICAVO LO STUDENTE DAL NOME
	public Studente getStudenteByNome (String nome)
	{
		return dao.getStudenteByNome(nome);
	}
	
	// TROVO LE LEZIONI FATTE TRA LO STUDENTE E IL TUTOR
	public List <Lezione> getLezioniTutorStudenteFatte (int idStud, int idTutor, int DayY, int anno)
	{
		return dao.getLezioniTutorStudenteFatte(idStud, idTutor, DayY, anno);
	}
	
	// TROVO L'ID DI TUTTI I TUTOR DISPONIBILI PER UNA LEZIONE
	public List<Integer> getTutorDisponibili (int DayY, int anno, String ora)
	{
		return dao.getTutorDisponibili(DayY, anno, ora);
	}
	
	// TROVO L'ULTIMO ID DELLE LEZIONI NEL DATABASE
	public int getContoLezioni ()
	{
		return dao.getContoLezioni();
	}
	
	// TROVO L'ULTIMO ID DEI TUTOR NEL DATABASE
	public int getContoTutor()
	{
		return dao.getContoTutor();
	}
	
	// TROVO L'ULTIMO ID DEGLI STUDENTI NEL DATABASE
	public int getContoStudenti ()
	{
		return dao.getContoStudenti();
	}
	
	// TROVO L'ULTIMO ID DELLE DISPONIBILITA' NEL DATABASE
	public int getContoDisponibilita ()
	{
		return dao.getContoDisponibilita();
	}
	
	// CERCO LA LEZIONE CONOSCENDO L'ORARIO, IL GIORNO E L'ID DEL TUTOR
	public int TrovaLezione (int id, int DayY, int anno, String orario)
	{
		return dao.TrovaLezione(id, DayY, anno, orario);
	}
	
	// CONTROLLO SE IL TUTOR E' STATO INSERITO NELLE LEZIONI (RICORSIONE)
	public boolean isTutorAggiunto (List <Lezione> lez, int id)
	{
		for(int i=0; i<lez.size(); i++)
		{
			if(lez.get(i).getIdTutor() == id)
				return true;
		}
		return false;
	}
	
	// CONTROLLO SE LA LEZIONE E' STATA GIA' AGGIUNTA (RICORSIONE)
	public boolean isLezioneAggiunta (List <Lezione> lez, int id)
	{
		for(int i=0; i<lez.size(); i++)
		{
			if(lez.get(i).getIdLezione() == id)
				return true;
		}
		return false;
	}
	
	// CONTROLLO SE LA SOLUZIONE PARZIALE E' MEGLIO DELLA MIGLIORE (RICORSIONE)
	public boolean isMeglio (List <Lezione> parz, List <Lezione> tot)
	{
		if(tot.isEmpty())
		{
			return true;
		}
		if(this.calcolaPunteggioTot(parz)>this.calcolaPunteggioTot(tot))
		{
			return true;
		}
		if(this.calcolaPunteggioTot(parz)==this.calcolaPunteggioTot(tot))
		{
			if(this.calcolaInterno(parz)>this.calcolaInterno(tot))
			{
				return true;
			}
		}
		return false;
	}
	
	// CONTROLLO SE LA LISTA DI LEZIONI NON CONTIENE DOPPIONI (RICORSIONE)
	public boolean isCorretto (List <Lezione> parz)
	{
		List <Integer> id = new ArrayList <Integer> ();
		for(int i = 0; i<parz.size(); i++)
		{
			id.add(parz.get(i).getIdLezione());
		}
		for(int i=0; i<id.size(); i++)
		{
			int cont = 0; 
			for(int j=0; j<id.size(); j++)
			{
				if(id.get(i)==id.get(j))
				{
					cont++;
				}
			}
			if(cont>1)
				return false;
		}
		return true;
	}
	
	// ALGORITMO RICORSIVO PER LA RICERCA DELLE LEZIONI MIGLIORI DELLA GIORNATA
	public void cerca(List <Lezione> parziale, int cont, List <Lezione> tutte)
	{
		if(cont == tutte.size())
		{
			if((isMeglio(parziale,LezMigliori) == true) && (isCorretto(parziale) == true))
			{
				
				if(isNonZero(parziale) == true)
				{
					LezMigliori = new ArrayList <> (parziale);
					punteggio = this.calcolaPunteggioTot(LezMigliori);
				}
			}
		}
		if(cont>tutte.size())
		{
			return;
		}
		
		for(int i=0; i<tutte.size(); i++)
		{
			List <Integer> id = this.getTutorDisponibili(tutte.get(i).getDayY(), tutte.get(i).getAnno(), tutte.get(i).getOrario());
			for(int j=0;j<id.size();j++)
			{
				if(  (!isTutorAggiunto(parziale, id.get(j))) && (!isLezioneAggiunta(parziale, tutte.get(i).getIdLezione()))   )
				{
					Lezione l = new Lezione (tutte.get(i).getIdLezione(), tutte.get(i).getIdStudente(),id.get(j),tutte.get(i).getData(),tutte.get(i).getOrario(),tutte.get(i).getMateria(),tutte.get(i).getGiorno(),tutte.get(i).getMese(),tutte.get(i).getAnno(),tutte.get(i).getDayY(),tutte.get(i).getNumGiorno());
					parziale.add(l);
					cerca(parziale, cont+1,tutte);
					parziale.remove(parziale.size()-1);
				}
			}
		}
	}

	// RICHIAMO DELL'ALGORITMO RICORSIVO
	public List <Lezione> getSoluzioneMigliore (List <Lezione> tutte)
	{
		List <Lezione> parziale = new ArrayList <Lezione> ();
		this.LezMigliori = new ArrayList <Lezione> (); 
		punteggio = 0; 
		cerca(parziale,0,tutte);
		return LezMigliori;
	}

	// TROVO LE LEZIONI DEL GIORNO (RICORSIONE)
	public List <Lezione> getLezioniGiorno (int DayY, int anno)
	{
		return dao.getLezioniGiorno(DayY, anno);
	}

	
}
