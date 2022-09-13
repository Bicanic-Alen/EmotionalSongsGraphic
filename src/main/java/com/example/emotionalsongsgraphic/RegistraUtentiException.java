package com.example.emotionalsongsgraphic;

public class RegistraUtentiException extends RuntimeException{
    /**
     * Metodo costruttore dell'eccezione non controllata
     * @param s richiede una stringa
     *          nella stringa si preferisce una descrizione del problema
     */
    public RegistraUtentiException(String s){
        super(s);
    }
}
