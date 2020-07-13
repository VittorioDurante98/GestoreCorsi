package it.polito.tdp.corsi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtPeriodo;

    @FXML
    private TextField txtCorso;

    @FXML
    private Button tCorsiperPeriodo;

    @FXML
    private Button tStampaStudenti;

    @FXML
    private Button tnumeroStudenti;

    @FXML
    private Button tDivisioneStudenti;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void numeroStudenti(ActionEvent event) {

    }
    
    void corsiPerPeriodo(ActionEvent event) {
    	txtRisultato.clear();
    	String pdString = txtPeriodo.getText();
    	Integer pd;
    	
    	try {
    		pd = Integer.parseInt(pdString);
    	}catch (Exception e){
    		txtRisultato.setText("Devi inserire un numero (1 o 2)!");
    		return;
    	}
    	
    	if(!pd.equals(1) && !pd.equals(2)) {
    		txtRisultato.setText("Devi inserire un numero (1 o 2)!");
    		return;
    	}
    	
    	Map<Corso, Integer> statistiche = this.model.getIscrittiPerPeriodo(pd);
    	
    	for(Corso c: statistiche.keySet()) {
    		txtRisultato.appendText(c.getNome() +" "+ statistiche.get(c) +"\n");
    	}
    }
    

    @FXML
    void stampaDiviosioneStudenti(ActionEvent event) {

    }

    @FXML
    void stampaStudenti(ActionEvent event) {
    	txtRisultato.clear();
    	
    	String codins = txtCorso.getText();
    	
    	//controllare se il codice corrisponde a un corso esistente
    	
    	List<Studente> studenti = this.model.getStudentiPerCorso(new Corso(codins, null, null, null));
    	
    	if(studenti.size() == 0) {
    		txtRisultato.appendText("Il corso non ha studenti iscritti");
    		return;
    	}
    	
    	for(Studente s: studenti) {
    		txtRisultato.appendText(s.toString());
    	}
    }

    @FXML
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tCorsiperPeriodo != null : "fx:id=\"tCorsiperPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tStampaStudenti != null : "fx:id=\"tStampaStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tnumeroStudenti != null : "fx:id=\"tnumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tDivisioneStudenti != null : "fx:id=\"tDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    }
}


