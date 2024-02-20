package it.unipv.insfw23.TicketWave.modelDomain.statistics;

import it.unipv.insfw23.TicketWave.modelDomain.user.Manager;

import java.lang.reflect.Array;
import java.util.Arrays;

public class StatisticsHandler {
	
	//costruttore di default, servono attributi??? forse un manager temporaneo dato che è un parametro
	// di tutti i metodi? Però si costruisce una volta sola, quindi non potrei istanziarlo novamente per
	// cambiare il manager
	
	//mi sono immaginato che per ogni tipo di statistica in input si riceva il filtro e il manager, 
	// e che in uscita si dia un vettore con le percentuali, il cui indice corrisponde alle enum e 
	// quindi a dei vettori in cui sono salvati i codici e i nomi.
	// esempio: tipo "concerto" ha codice 4. Allora results[4] conterrà la media delle percentuali 
	// relative a concerto. quindi la UI avrà bisogno del vettore dei nomi e di quello dei risultati
	// i cui indici coincidono con quanto deciso con il vettore dei codici.

	
	public double[] typeStats(Manager manager) {
		int typeCode[];
		String typeName[];
		double results[];
		int eventCounter = 0;
		Event list[] = manager.getEvent();
		
		typeCode = list[0].getTypeCodeArray();

		//quanto è grande questo vettore? se poi aggiungo un tipo di
		// evento, devo modificare ovunque abbia usato questo vettore
		
		
		for (int j=0; j<Array.getLength(typeCode); j++) {
			for (int i=0; i<Array.getLength(list); i++) {
				
				if(list[i].getTypeCode() == typeCode[j]) {
					
					int maxn = list[i].getMaxNumberOfSeats();
					int soldn = list[i].getTicketSoldNumber();
					
					double percResult = (soldn/maxn)*100;
					eventCounter ++;
					results[j] = results[j] + percResult;
				}
			}
			results[j] = results[j]/eventCounter;
			eventCounter = 0;
		}
		return results;
	}
	
	
	public String[] getTypeNameArray(Manager manager) {
		Event events[] = manager.getEvent();
		return events[0].getTypeNameArray();
	}
	
	
	
	

	public double[] artistStats(int typeCode, Manager manager) {
		
		Event list[] = manager.getEvent();
		String artistNames[];
		double results[];
		int artistCounter[];
		int j=0;
		
		for (int i=0; i<Array.getLength(list); i++) {
			
			if(list[i].getTypeCode() == typeCode) {
				int index = ricercaStringa(artistNames, list[i].getArtist);
				
				if(index >= 0) {
					
					int maxn = list[i].getMaxNumberOfSeats();
					int soldn = list[i].getTicketSoldNumber();
					
					double percResult = (soldn/maxn)*100;
					artistCounter[index]++;
					results[index] = results[index] + percResult;
				}
					
				else  {
					j++;
					artistNames[j] = list[i].getArtist();     		 //come prelevo l'artista se sto scorrendo un vettore di eventi?
																	//possiamo fare un interfaccia con il metodo get artist e quindi poi
								       					   			//usare un vettore di IArtistqualcosa
					int maxn = list[i].getMaxNumberOfSeats();
					int soldn = list[i].getTicketSoldNumber();
					
					double percResult = (soldn/maxn)*100;
					artistCounter[j]++;
					results[j] = results[j] + percResult;
				}
			}
		}
		for (j=0; j<Array.getLength(artistNames); j++) {
			results[j] = results[j]/artistCounter[j];
		}
	return results;
	}
		
	
		
	public int ricercaStringa(String vettore[], String parola) {
		int index = -1;
		for (int i=0; i<Array.getLength(vettore); i++) {
			if (vettore[i] == parola) {
				return i;
			}
		}
		
		return index;
	}
	
	
	
	public double[] artistStats2(int typeCode, Manager manager) {
		Event list[] = manager.getEvent();
		String artistNames[];
		double results[];
		int j=0;
		int artistCounter;
		
		for (int i=0; i<Array.getLength(list); i++) {
			if (list[i].getTypeCode() == typeCode) {
				int index = ricercaStringa(artistNames, list[i].getArtist);
				if(index == -1) {
					artistNames[j] = list[i].getArtist;
					
					for(int y=i; y<Array.getLength(list); y++) {
						if (list[i].getArtist() == list[y].getArtist()) {
							int maxn = list[i].getMaxNumberOfSeats();
							int soldn = list[i].getTicketSoldNumber();
						
							double percResult = (soldn/maxn)*100;
							artistCounter++;
							results[j] = results[j] + percResult;
					    }
					}
					results[j] = results[j]/artistCounter;
					artistCounter = 0;
					j++;
				}
			}
			
		}
		
		return results;
	}
	
	
	
	
	public double[] genreStats(int typeCode, Manager manager) {
		
		Event list[] = manager.getEvent();
		int genreCodeArray[] = list[0].getGenreCodeArray();
		double results[];
		int eventCounter = 0;
		
		for (int j=0; j<Array.getLength(genreCodeArray); j++) {
			for (int i=0; i<Array.getLength(list); i++) {
				
				if(list[i].getTypeCode() == typeCode) {
					
					int maxn = list[i].getMaxNumberOfSeats();
					int soldn = list[i].getTicketSoldNumber();
				
					double percResult = (soldn/maxn)*100;
					eventCounter++;
					results[j] = results[j] + percResult;
					
				}
			}
			results[j] = results[j]/eventCounter;
			eventCounter = 0;
		}
		return results;
	}
	
	
	public String[] getGenreNameArray(Manager manager) {
		Event events[] = manager.getEvent();
		return events[0].getGenreNameArray();
	}
	
	
	
	
	
	public double[] provinceStats(int typeCode, String artistName, Manager manager) {
		Event list[] = manager.getEvent();
		int provinceCodeArray[] = list[0].getProvinceCodeArray();
		int eventCounter = 0;
		double results[];
		Arrays.fill(results, -1.0);
		
		for(int j=0; j<Array.getLength(provinceCodeArray); j++) {
			for (int i=0; i<Array.getLength(list); i++) {
				if (list[i].getTypeCode() == typeCode) {
					if(list[i].getArtist() == artistName) {
						
						int maxn = list[i].getMaxNumberOfSeats();
						int soldn = list[i].getTicketSoldNumber();
							
						double percResult = (soldn/maxn)*100;
						eventCounter++;
						results[j] = results[j] + percResult;
					}
				}
			}
			results[j] = results[j]/eventCounter;
			eventCounter = 0;	
		}
		return results;
	}
	
	
	public String[] getProvinceNameArray(Manager manager) {
		Event events[] = manager.getEvent();
		return events[0].getProvinceNameArray();
	}
	
}
