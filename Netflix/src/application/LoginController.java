package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.IsteMysql.Util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LoginController {
	
	static String Programm_Adi;
	static String girilen_email;
	static String bolum;
	static String izleme_suresi;
	int a =0;
	String sql;
	
	Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen = null;
	ResultSet getirilen1 =null;

	public LoginController() {
		
		baglanti = VeritabaniUtil.Baglan();		
	}

    @FXML
    private AnchorPane TumSayfa;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField KullaniciText;

    @FXML
    private TextField SifreText;

    @FXML
    private Label etiket;

    @FXML
    private Button KayitBtn;
    
    @FXML
    private Button LoginBtn;
    
    @FXML
    void KayitOlClick(ActionEvent event) {
    	
    	try {
			AnchorPane pane1 = (AnchorPane) FXMLLoader.load(getClass().getResource("KullaniciForm.fxml"));
			TumSayfa.getChildren().setAll(pane1);
			
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    }
    
    @FXML
    void LoginBtnClick(ActionEvent event) {
    	
    	sql = "select *from kullanici where Email=? and kullaniciSifre=?";
    	try {
    		
    		sorguIfadesi = baglanti.prepareStatement(sql);
    		sorguIfadesi.setString(1,KullaniciText.getText().trim());
    		sorguIfadesi.setString(2,SifreText.getText().trim());
    		getirilen = sorguIfadesi.executeQuery();
    		girilen_email = KullaniciText.getText();
    		
    		if(!getirilen.next()) {
    			
    			etiket.setText("email veya sifre hatali");	
    			etiket.setTextFill(Color.web("#f5f3f5"));
    			etiket.setAlignment(Pos.CENTER);
    			etiket.setFont(Font.font("Calibri", 22));
    					
    		}
    		else
    		{    						
    				String sql_sorgu = "select kp.ProgramAdi from kullaniciprogram as kp, program as p where kp.Email = '"+girilen_email+"' and kp.ProgramAdi = p.ProgramAdi and p.BolumSayisi != kp.KaldigiBolum and p.ProgramTipi = 'Dizi' order by ProgramAdi asc"; 				
			     					
    				sorguIfadesi = baglanti.prepareStatement(sql_sorgu);
    				ResultSet getirilen1 = sorguIfadesi.executeQuery();    				
    				

    				while(getirilen1.next())
    				{    					
    					Programm_Adi = getirilen1.getString("ProgramAdi");   
        				   a++;        				
    				}
    				
    				if(a == 0) {    					
            			AnchorPane pane5 = (AnchorPane) FXMLLoader.load(getClass().getResource("ProgramForm.fxml"));
            			TumSayfa.getChildren().setAll(pane5); 
    				}
    				
    				else {    					

    					AnchorPane pane6 = (AnchorPane) FXMLLoader.load(getClass().getResource("IzleForm.fxml"));
            			TumSayfa.getChildren().setAll(pane6); 
   
    				}
    		}    		  			
    	}
    	catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    }

    @FXML
    void initialize() {    	
    	
        assert KullaniciText != null : "fx:id=\"KullaniciText\" was not injected: check your FXML file 'Login.fxml'.";
        assert SifreText != null : "fx:id=\"SifreText\" was not injected: check your FXML file 'Login.fxml'.";
        assert etiket != null : "fx:id=\"etiket\" was not injected: check your FXML file 'Login.fxml'.";
        assert LoginBtn != null : "fx:id=\"LoginBtn\" was not injected: check your FXML file 'Login.fxml'.";
        assert KayitBtn != null : "fx:id=\"KayitBtn\" was not injected: check your FXML file 'Login.fxml'.";

    }
}
