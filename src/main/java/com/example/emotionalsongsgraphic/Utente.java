package com.example.emotionalsongsgraphic;

import com.opencsv.bean.*;


/** Rapresenta un utente.
 * @author Alen Bicanic
 * @author abicanic@studenti.uninsubria.it
 * @version 1.0
 * @since 1.0
 */

public class Utente {
    @CsvBindByName(column = "ID", required = true)
    private int id;
    @CsvBindByName(column = "Nome", required = true)
    private String nome;
    @CsvBindByName(column = "Cognome", required = true)
    private String cognome;
    @CsvBindByName(column = "Codice_Fiscale", required = true)
    private String codiceFiscale;
    @CsvBindByName(column = "Indirizzo", required = true)
    private String indirizzo;
    @CsvBindByName(column = "Numero_Civico", required = true)
    private int numeroCivico;
    @CsvBindByName(column = "Cap", required = true)
    private int cap;
    @CsvBindByName(column = "Comune", required = true)
    private String comune;
    @CsvBindByName(column = "Provincia", required = true)
    private String provincia;
    @CsvBindByName(column = "Email", required = true)
    private String email;
    @CsvBindByName(column = "UserID", required = true)
    private String userId;
    @CsvBindByName(column = "Password", required = true)
    private String password;


    /**
     * la classe GestioneUtenti.Utente rapresenta un utente.
     */
    public Utente(){
        this.nome = null;
        this.cognome = null;
        this.codiceFiscale = null;
        this.indirizzo = null;
        this.numeroCivico = 0;
        this.cap = 0;
        this.comune = null;
        this.provincia = null;
        this.email = null;
        this.userId = null;
        this.password = null;
        this.id = 0;

    }
    /**Crea un nuovo utente rapresentato dai seguenti paramtri:
     * @param nome rapresenta il nome del utente
     * @param cognome rapresenta il cognome del utente
     * @param codiceFiscale rapresenta il codice fiscale del utente
     * @param indirizzo rapresenta l'indirizzo del utente (piazza o via)
     * @param numeroCivico rapresenta il numero civico del utente
     * @param cap rapresenta il codice postale del utente
     * @param comune rapresenta il comune nel quale l'utente risiede
     * @param provincia rapresenta la provinca nella quale l'utente risiede
     * @param email rapresenta l'indirizzo di posta eletronica del'utente
     * @param userId rapresenta il nickname del'utente
     * @param password rapresenta la password scelta dal utente.
     *
     */
    public Utente(int id, String nome, String cognome, String codiceFiscale, String indirizzo, int numeroCivico,
                  int cap, String comune, String provincia, String email, String userId, String password){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.indirizzo = indirizzo;
        this.numeroCivico = numeroCivico;
        this.cap = cap;
        this.comune = comune;
        this.provincia = provincia;
        this.email = email;
        this.userId = userId;
        this.password = password;

    }

    /**Restiusce il nome dell'utente
     * @return restituisce il nome in formato String.
     */
    public String getNome() {
        return nome;
    }
    /**Restiusce il cognome dell'utente
     * @return restituisce il cognome in formato String.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Restituisce il codice fiscale dell'utente
     * @return restiusce il codice fiscale in formato stringa
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Restituisce l'indirizzo dell'utente
     * @return restituisce l'indirizzo in formato String
     */

    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Restitusice il numero civico dell'utente
     * @return restituisce il numero civico in formato Int
     */
    public int getNumeroCivico() {
        return numeroCivico;
    }

    /**
     * Restitusice il codice postale (cap/zip) dell'utente
     * @return restituisce il cap in formato Int
     */

    public int getCap() {
        return cap;
    }

    /**
     * Restituisce il comune di residenza dell'utente
     * @return restiusce il comune in formato String
     */
    public String getComune() {
        return comune;
    }

    /**
     * Restituisce la provincia di residenza dell'utente
     * @return restituisce la provincia in formato String
     */

    public String getProvincia() {
        return provincia;
    }

    /**
     * Restituisce l'e-mail dell'utente
     * @return restituisce l'email in formato String
     */

    public String getEmail() {
        return email;
    }

    /**
     * Restituisce l'user ID (o nickname) dell'utente
     * @return restituisce l'user ID in formato String
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Restituisce la password dell'utente
     * @return restituisce la password in formato String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Restituisce l'id utente
     * @return restituisce un intero
     */

    public int getId() {
        return id;
    };

    /**
     * Restituisce tutti i dati dell'utente
     * @return restituisce tutti i dati in una striga( formato restituito: String), separando i dati con una virgola.
     */

    @Override
    public String toString(){
        return nome+","+cognome+","+codiceFiscale+","+indirizzo+","+numeroCivico+","+cap+","+comune+","+provincia+","+email+","+userId+","+password;
    }

    /**
     * Restisce l'indirizzo completo dell' utente
     * @return ritorna una stringa.
     */
    public String getIndirizzoFisicoCompleto(){
        return indirizzo+", "+numeroCivico+", "+cap+", "+comune+", "+provincia;
    }

    /**
     * Il metodo permette di modificare la password all'utente
     * @param password rapresenta la nuova password in formato Stringa.
     */
    protected void setPassword(String password) {
        this.password = password;
    }
}
