package com.example.emotionalsongsgraphic;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/** Emotional Songs Controller
 * la seguente classe controlla tutti gli eventi
 * @author Alen Bicanic
 * @author abicanic@studenti.uninsubria.it
 * @version -
 * @since -
 */


public class EmotionalsSongsController {

    private Stage stage;

    private Scene scene;
    private Parent root;

    private RegistraUtente regUser;

    //registrazione form
    @FXML
    private TextField tF_name;
    @FXML
    private TextField tF_surname;
    @FXML
    private TextField tF_cf;
    @FXML
    private TextField tF_adress;
    @FXML
    private TextField tF_nc;
    @FXML
    private TextField tF_zip;
    @FXML
    private TextField tF_comune;
    @FXML
    private TextField tF_prov;
    @FXML
    private TextField tF_email;
    @FXML
    private TextField tF_UserID;
    @FXML
    private PasswordField jPwsField_psw;


    /**
     * evento richiama la pagina di registrazione
     * @param event
     * @throws IOException
     */

    @FXML
    public void loadFormRegistrazione(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FormRegistrazione.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Emotional Songs - Registrazione");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Evento che richiama la pagina di login
     * @param event
     * @throws IOException
     */

    //ancora non utilizzabile
    @FXML
    public void loadFormLogin(ActionEvent event) throws  IOException{
        Parent root = FXMLLoader.load(getClass().getResource("FormLogin.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Emotional Songs - Accedi");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Richiama la pagina di ricerca
     * @param event
     * @throws IOException
     */

    //ancora non funzionante la pagina di ricerca, funzioni non implementate
    @FXML
    public void loadFormSearch(ActionEvent event) throws  IOException{
        Parent root = FXMLLoader.load(getClass().getResource("SearchForm.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Emotional Songs - Ricerca brano");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Evento bottone di conferma registrazione
     * evento avenuto con successo rendirizzamento alla pagina di ricerca
     * @param event
     * @throws IOException
     * @throws CsvRequiredFieldEmptyException
     * @throws CsvDataTypeMismatchException
     * @throws NoSuchAlgorithmException
     */

    //non funzionante a causa del file RegistraUtenti
    public void confirmRegistration(ActionEvent event) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, NoSuchAlgorithmException {
        String name = tF_name.getText();
        String surname = tF_surname.getText();
        String cf = tF_cf.getText();
        String address = tF_adress.getText();
        int numCiv = Integer.parseInt(tF_nc.getText().trim());
        int zip = Integer.parseInt(tF_zip.getText().trim());
        String comune = tF_comune.getText();
        String provincia = tF_prov.getText();
        String email = tF_email.getText();
        String userID = tF_UserID.getText();
        String password = jPwsField_psw.getText();

        if(name != null && surname != null && cf != null && address != null && tF_nc.getText() != null &&
                tF_zip.getText() != null && comune != null && provincia != null && email != null &&
                userID != null && password != null){

            if(regUser.isUsedUserID(userID)) throw new RegistraUtentiException("nome utente gia usato");
            if(regUser.isUsedCF(cf)) throw new RegistraUtentiException("codice fiscale gia usato");
            if(regUser.isUsedEmail(email)) throw new RegistraUtentiException("e-mail gia usata");

            regUser.registrazione(name, surname, cf, address, numCiv, zip, comune, provincia, email, userID, password);

        }
        else throw new RegistraUtentiException("ci sono dei campi vuoti");
    }

    /**
     * Evento bottone ritorna alla pagina principale
     * @param event
     * @throws IOException
     */

    public void backToHomeForm(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("homeForm.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Emotional Songs - Registrazione");
        stage.setScene(scene);
        stage.show();
    }




}