package com.example.emotionalsongsgraphic;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;


public class Prova {
    public static void main(String[] args) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, NoSuchAlgorithmException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        RegistraUtente reg = new RegistraUtente();
        boolean continua =  true, isAuthorized = false;
        String emailOrID = "";
        String isEnabled ="";
        String psw = "";
        String pass = "";
        //lettura file
        File cookieReader = new File("src/main/resources/com/example/emotionalsongsgraphic/cookie.txt");
        Scanner cookieLine = new Scanner(cookieReader);
        //controlla se nel file sono presenti dei dati
        while(cookieLine.hasNextLine()) {
            isEnabled = cookieLine.nextLine();
        }
        if(isEnabled.equals("true")) reg.setEnabled();
        //se nel file erano presenti user e la password tenta l'accesso se reg.login restituisce true il login è effetuato.
        if(reg.isEnabled()){
            System.out.println("Login Effetuato con sucesso");
            System.out.println("vuoi fare il logout? (y/N)");
            String sel = bf.readLine().trim();
            if(sel.equals("Y")||sel.equals("y")){
                FileWriter fw = new FileWriter("Files/cookie.txt");
                fw.write("false");
                fw.flush();
                fw.close();
            }

        }
        else { //altrimenti chiedi al utente cosa vuole fare accedere o registrarsi.
            int sel = 0;
            while (continua){
                System.out.println("Segli un opzione");
                System.out.println("1-Registrati\n2-Accedi\n3-esci");
                sel = Integer.parseInt(bf.readLine().trim());
                switch (sel) {
                    case 1 -> {
                        //prelevo i dati gia presenti per confrantare con quelli nuovi per evitare rindondanze
                        List<Utente> listaUtenti = reg.readCsvUtente();
                        String cf ="", userId = "", email = "";
                        System.out.println("inserire i seguenti dati:");
                        System.out.println("inserire nome");
                        String nome = bf.readLine().trim();
                        System.out.println("inserire cognome");
                        String cognome = bf.readLine().trim();
                        //faccio re-inserire al utente il codice fiscale all' utente finche non ne inserisce uno non presente nel elenco.
                        do {
                            System.out.println("inserire codice fiscale");
                            cf = bf.readLine();
                        }while (reg.isUsedCF(cf));

                        System.out.println("inserire via, viale, o piazza di residenza");
                        String indirizzo = bf.readLine().trim();
                        System.out.println("inserire numero civico");
                        int numeroCivico  = Integer.parseInt(bf.readLine().trim());
                        System.out.println("inserire cap");
                        int cap = Integer.parseInt(bf.readLine().trim());
                        System.out.println("inserire comune di residenza");
                        String comune = bf.readLine().trim();
                        System.out.println("inserire la provincia di residenza");
                        String provincia = bf.readLine().trim();
                        //faccio re-inserire al utente il email all' utente finche non ne inserisce uno non presente nel elenco.
                        do{
                            System.out.println("inserisci email");
                            email = bf.readLine().trim();
                        }while(reg.isUsedEmail(email));

                        //faccio re-inserire al utente il nome utente all' utente finche non ne inserisce uno non presente nel elenco.
                        do{
                            System.out.println("insersci nickname");
                            userId = bf.readLine().trim();
                        }while (reg.isUsedUserID(userId));

                        System.out.println("inserire password");
                        String password = bf.readLine().trim();

                        reg.registrazione(nome, cognome,cf,indirizzo,numeroCivico,cap,
                                comune, provincia, email, userId,password);

                    }
                    case 2 -> {
                        System.out.println("Inserire il nome utente oppure la email con il quale ti sei registrato");
                        emailOrID = bf.readLine().trim();
                        System.out.println("inserire la password che hai scelto al momento della registrazione,o quella che hai scelto dopo la modifica");
                        psw = bf.readLine().trim();
                        isAuthorized = reg.login(emailOrID,psw);
                        System.out.println(isAuthorized);
                        //scrittura su file
                        if(isAuthorized){
                            FileWriter cookie = new FileWriter("Files/cookie.txt");
                            cookie.write(String.valueOf(reg.isEnabled()));
                            cookie.flush();
                            cookie.close();
                        }
                        else System.out.println("nome utente o password non coretti");

                    }
                    case 3 -> {
                        System.out.println("Uscita...");
                        continua = false;
                        break;
                    }
                    default -> {
                        System.out.println("selezione non valida sceglire un opzione in elenco.");
                        break;
                    }
                }

            }
            cookieLine.close();
        }
    }
}
