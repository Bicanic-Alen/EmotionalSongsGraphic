package com.example.emotionalsongsgraphic;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

/** Classe che permette la gestione di Login e Registrazione.
 * @author Alen Bicanic
 * @author abicanic@studenti.uninsubria.it
 * @version alpha 1.0.0.
 * @since alpha 1.0.0.
 */

public class RegistraUtente {

    private List<Utente> listaUtenti;
    private boolean enabled;
    private static int i;
    private static String uri = "src/main/resources/com/example/emotionalsongsgraphic/UtentiRegistrati.dati.csv";


    public RegistraUtente(){
        listaUtenti = new LinkedList<Utente>();
        enabled = false;

    }

    public String hashMD5(String toHashString) throws NoSuchAlgorithmException {
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(toHashString.getBytes(),0,toHashString.length());
        return new BigInteger(1,m.digest()).toString(16);
    }


    /**
     * Permette la lettura di un file CSV e la converte in una lista contente ogetti di tipo GestioneUtenti.Utente
     * @return restituisce beans che è una lista contente un oggetti di tipo GestioneUtenti.Utente.
     * @throws FileNotFoundException
     */
    public List<Utente> readCsvUtente() throws FileNotFoundException {
        //costruisco una lista contente i dati trovati nel file csv.
        List<Utente> beans = new CsvToBeanBuilder(new FileReader(uri)).withType(Utente.class).build().parse();
        return beans;
    }


    /*
    Il metedo esegue una copia di ReadCsv(), nella LinkedList dataLines
     */
    private void setListaUtenti(List<Utente> e) throws FileNotFoundException {
        //cancello i dati precedenti
        this.listaUtenti.clear();
        //inserisco i dati aggiornati
        for (Utente utente : e) {
            this.listaUtenti.add(new Utente(utente.getId(),utente.getNome(), utente.getCognome(), utente.getCodiceFiscale(),
                    utente.getIndirizzo(), utente.getNumeroCivico(), utente.getCap(), utente.getComune(),
                    utente.getProvincia(), utente.getEmail(), utente.getUserId(), utente.getPassword()));
        }
    }

    /**
     * Restituisce l'elenco dei dati all'interno del file CSV
     * @return restituisce un ArrayList
     */
    public List<Utente> getListaUtenti() {
        return listaUtenti;
    }

    /**
     * Restituisce un valore booleano in base se l'untente ha fatto il login o meno
     * @return restituisce un booleano
     */
    public boolean isEnabled() {
        return enabled;
    }


    /**
     * il seguente metodo indica se un codice fiscale è gia presente nel sistema
     * @param cf paramtro di tipo stringa
     * @return restitusice un valore booleano
     * @throws FileNotFoundException viene eseguito se il file non è stato trovato
     */
    public Boolean isUsedCF(String cf) throws FileNotFoundException {
        for (Utente ut: readCsvUtente()) {
            if(cf.toUpperCase().equals(ut.getCodiceFiscale())){
                return true;
            }
        }
        return false;
    }


    /**
     * il seguente metodo indica se un dato userid è gia stato uttilizato.
     * @param userId parametro di tipo stringa
     * @return restituisce un valore booleano
     * @throws FileNotFoundException viene eseguito se il file non è stato trovato
     */
    public Boolean isUsedUserID(String userId) throws FileNotFoundException {
        for (Utente ut: readCsvUtente()) {
            if (userId.toUpperCase().equals(ut.getUserId())){
                return true;
            }
        }
        return false;
    }

    /**
     * il seguente metodo indica se un indirizzo email e gia presente nel sistema
     * @param email parametro di tipo stringa
     * @return restitusice un valore booleano
     * @throws FileNotFoundException viene eseguito se il file non è stato trovato
     */
    public Boolean isUsedEmail(String email) throws FileNotFoundException {
        for (Utente ut: readCsvUtente()){
            if(email.toUpperCase().equals(ut.getEmail())){
                return true;
            }
        }
        return false;
    }


    /**
     * il seguente metodo restituisce ultimo id presente all'interno del csv
     * @return restituisce un intero
     * @throws FileNotFoundException viene eseguito se il file non è stato trovato
     */
    public int getLastID() throws FileNotFoundException {
        setListaUtenti(readCsvUtente());
        int lastID = listaUtenti.get(0).getId();
        for (Utente ut: readCsvUtente()) {
            if(lastID < ut.getId()){
                lastID = ut.getId();
            }
        }
        return lastID;
    }

    /**
     * il seguente metodo scrive sul file "UtentiRegistrati.dati.csv" una lista passata per parametro che deve essere di tipo utente.
     * @param listaUtentiAggiornata richiede un parametro di tipo List contenente GestioneUtenti.Utente.
     * @throws IOException avviene a causa di un errore generale
     * @throws CsvRequiredFieldEmptyException avviene se i dati che si tentano di scrivere sul file sono vuoti
     * @throws CsvDataTypeMismatchException avviene se il tipo di dati non corispondono alle colonne del csv
     */
    public void scritturaCsv(List<Utente> listaUtentiAggiornata) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        //costruisco il file CSV è lo scrivo su file
        Writer writer = new FileWriter(uri);
        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
        beanToCsv.write(listaUtentiAggiornata); //scrivo sul file CSV i dati contenuti in dataLines
        writer.flush();
        writer.close();
    }



    /**
     * Il seguente metodo permette l'utente di registrarsi al sistema di accesso
     * @param nome rapresenta il nome dell'utente (type String)
     * @param cognome rapresenta il cognome dell'utente (type String)
     * @param codiceFiscale rapresenta il codice fiscale dell'utente (type String)
     * @param indirizzo rapresenta l'indirizzo fisico dell'utente (type String)
     * @param numeroCivico rapressenta il numero civico dell'utente
     * @param cap rapresenta il codice postale dell' utente, (cap / zip)
     * @param comune rapresenta il comune nella quale risiede l'utente (type String)
     * @param provincia raprensenta la provicia nella quale l'utente risiede (type String)
     * @param email rapresenta la mail dell' utente (type String)
     * @param userId rapresenta il nome utente dell'utente (type String)
     * @param password rapresenta la password dell'utente (type String)
     * @throws FileNotFoundException viene eseguita l'eccezione se non viene trovato il file
     * @throws IOException eccezione della classe java.io
     * @throws CsvRequiredFieldEmptyException eccezione della classe openCSV
     * @throws CsvDataTypeMismatchException eccezione della classe openCSV
     */



    public void registrazione(String nome, String cognome, String codiceFiscale, String indirizzo, int numeroCivico, int cap, String comune, String provincia, String email, String userId, String password) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, NoSuchAlgorithmException {

        setListaUtenti(readCsvUtente());
        int id = getLastID();
        id += 1;


        Utente utente = new Utente(id,nome.toUpperCase(),cognome.toUpperCase(),codiceFiscale.toUpperCase(),indirizzo.toUpperCase(),
                numeroCivico,cap,comune.toUpperCase(),provincia.toUpperCase(),email.toUpperCase(),userId.toUpperCase(),hashMD5(password));
        //aggiungo l'utente alla lista dei dati provenienti dal file CSV
        this.listaUtenti.add(utente);
        this.scritturaCsv(listaUtenti);

    }

    /**
     * Il seguente metodo permette di effetuare l'accesso al sistema
     * @param email_UserId è il parametro che deve contenere o l'email dell' utente o il nome utente dell'utente
     * @param psw è il parametro che deve contenere la password dell'utente
     * @return restituisce un valore booleano in base se l'utente con queste creddenziali viene trovato.
     * @throws FileNotFoundException viene eseguito se il file non è stato trovato
     */
    public Boolean login(String email_UserId, String psw ) throws IOException, NoSuchAlgorithmException {
        this.enabled = false;
        email_UserId = email_UserId.toUpperCase();
        psw = hashMD5(psw);
        for (Utente ut: this.readCsvUtente()) {
            //controllo che sia presente un nome utente o email con quella specifica password.
            if ((email_UserId.equals(ut.getEmail()) && psw.equals(ut.getPassword())) ||
                    (email_UserId.equals(ut.getUserId()) && psw.equals(ut.getPassword()))) {
                //se si restituisco true
                this.enabled = true;
                break;
            }
            i++;
        }
        return isEnabled();
    }

    /**
     * modifica il campo isEnabled da true a false e viceversa
     */
    public void setEnabled() {
        enabled = !isEnabled();
    }


    /**
     * Permette la modifica della password
     * @param nomeUtente varaibile di tipo String rapresentate il nome utente o email
     * @param oldPsw variaible di tipo String rapresentante la vecchia password
     * @param newPws variabile di tipo String rapresentate la nuava password che sostituisce la vecchia password
     * @throws FileNotFoundException viene richiamata la seguente eccezione se il file non viene trovato.
     * @throws IOException viene richiamata a causa di un errore generale della libreria java io
     * @throws CsvDataTypeMismatchException viene richiamata a causa di una non coerenza con i dati e le rispetive colonne nel csv
     * @throws CsvRequiredFieldEmptyException   viene richiamata a causa se vengono cercati di scrivere dei campi vuoti nel csv
     */
    public String changePassword(String nomeUtente, String oldPsw, String newPws) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, NoSuchAlgorithmException {
        setListaUtenti(readCsvUtente());
        boolean check = false;
        if(login(nomeUtente, oldPsw)){
                    listaUtenti.get(i).setPassword(hashMD5(newPws));
                    check = true;
        }
        if(check) {
            this.scritturaCsv(listaUtenti);
            return "la modifica alla password è aventua con successo";
        }else {
            return "la modifica non è riuscita, controlla se hai messo il nome utente e password corretti";
        }
    }


}
