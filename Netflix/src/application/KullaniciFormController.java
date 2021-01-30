package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.IsteMysql.Util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.*;

public class KullaniciFormController extends LoginController {	
	
	static ArrayList <String> secilenTurler = new ArrayList();
	static String email=null;	
	static int gir = 1;
	
	String sql ="";
	Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen = null;
	
	public KullaniciFormController() {
		
		baglanti = VeritabaniUtil.Baglan();			
	}
	
    @FXML
    private AnchorPane KayitSayfa;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private ComboBox<String> turSec;

    @FXML
    private TextField KullaniciText;

    @FXML
    private TextField SifreText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField dogumTarihiText;

    @FXML
    private Button KayitOlBtn;
   
    @FXML
    void KayitOlBtnClick(ActionEvent event) throws SQLException {    	
    
    	try {	
    	
    	sql = "insert into kullanici(KullaniciAdi,Email,KullaniciSifre,DogumTarihi,Turler) values (?,?,?,?,?)";
    	sorguIfadesi = baglanti.prepareStatement(sql);
    	
    	sorguIfadesi.setString(1,KullaniciText.getText().trim());	
    	sorguIfadesi.setString(2,emailText.getText().trim());
   	    email = emailText.getText();
    	sorguIfadesi.setString(3,SifreText.getText().trim());
    	sorguIfadesi.setString(4,dogumTarihiText.getText().trim());
    	sorguIfadesi.setString(5,secilenTurler.toString());
    	sorguIfadesi.executeUpdate();
  	    gir = 2;
		AnchorPane pane2 = (AnchorPane) FXMLLoader.load(getClass().getResource("ProgramForm.fxml"));
		pane2.setMaxSize(1415.0, 801.0);
		KayitSayfa.getChildren().setAll(pane2);
		       
    	}
    	catch(Exception e) {    		
    		System.out.println(e.getMessage().toString());
    	}   	
    }
       
    @FXML
    void turSecClick(ActionEvent event) {
	
    	String secilen =  turSec.getSelectionModel().getSelectedItem().toString();
    	secilenTurler.add(secilen);     	
    }
    
   public static String[] SecilenTurler()
    {
	 
	String [] dizi = new String[3];
	
    	for(int i=0;i<3;i++)
    	{
    		 dizi[i] = secilenTurler.get(i);
    	}    	
    	
    	return dizi;    	
    }
   
    @FXML
    void initialize() {
    	
        assert KullaniciText != null : "fx:id=\"KullaniciText\" was not injected: check your FXML file 'KullaniciForm.fxml'.";
        assert SifreText != null : "fx:id=\"SifreText\" was not injected: check your FXML file 'KullaniciForm.fxml'.";
        assert emailText != null : "fx:id=\"emailText\" was not injected: check your FXML file 'KullaniciForm.fxml'.";
        assert dogumTarihiText != null : "fx:id=\"dogumTarihiText\" was not injected: check your FXML file 'KullaniciForm.fxml'.";
        assert KayitOlBtn != null : "fx:id=\"KayitOlBtn\" was not injected: check your FXML file 'KullaniciForm.fxml'.";
        assert turSec != null : "fx:id=\"turSec\" was not injected: check your FXML file 'KullaniciForm.fxml'.";
        
    	String [] turler = {"Aksiyon","Aksiyon ve Macera","Anime","Belgesel","Bilim Kurgu","Bilim Kurgu ve Fantastik Yapýmlar","Bilim ve Doða","Çocuk ve Aile","Drama","Gerilim","Komedi","Korku","Reality Show","Romantik"};
    	turSec.getItems().addAll(turler);    	
  }
}