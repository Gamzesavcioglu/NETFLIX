package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;

import com.IsteMysql.Util.VeritabaniUtil;


public class IzleFormController extends ProgramFormController{

	String sql ="";
	String sql_sorgu1 = "";
	String sql_sorgu = "";
	Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen = null;
	String tarih;
	int k_bolum;
	
	String kalinan_film;
	String e_sure;
	String giris_mail;
	static int kalinan_bolum;
	int a;
		
	public IzleFormController() {
		
		baglanti = VeritabaniUtil.Baglan();			
	}	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane FilmSayfa;

    @FXML
    private TextField Izlenen_film;
    
    @FXML
    private TextField kaldigi_bolum;

    @FXML
    private TextField izleme_sure;

    @FXML
    private Button bitir_btn;
    
    @FXML
    private Button devamBtn;
    
    @FXML
    private Button GeriBtn;
    
    @FXML
    void GeriBtnClick(ActionEvent event) {
    	
    	try {
    		gir=3;
    		AnchorPane pane4 = (AnchorPane) FXMLLoader.load(getClass().getResource("ProgramForm.fxml"));
    		FilmSayfa.getChildren().setAll(pane4);    		
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
    }
    
    @FXML
    void devamBtnClick(ActionEvent event) {
    	
	    DateTimeFormatter formatter1 = DateTimeFormatter.ISO_TIME;
		formattedTimee = formatter1.format(LocalTime.now());  	
    	
    }

    @FXML
    void bitir_btnClick(ActionEvent event) {

    	DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
	    String formattedDate = formatter.format(LocalDate.now());
	    
		DateTimeFormatter frmatter = DateTimeFormatter.ISO_TIME;
		String formattedTime = frmatter.format(LocalTime.now());
		DateTimeFormatter frmttr = DateTimeFormatter.ISO_TIME;
		
		LocalTime Time1 = LocalTime.parse(formattedTimee,frmttr);
		LocalTime Time2 = LocalTime.parse(formattedTime,frmttr);
		
		long diffInSeconds = java.time.Duration.between(Time1, Time2).getSeconds();
		long diffInMinutes = java.time.Duration.between(Time1, Time2).toMinutes();
		long diffInHours= java.time.Duration.between(Time1, Time2).toHours();
		
		String sure =Long.valueOf(diffInHours).toString()+':'+Long.valueOf(diffInMinutes).toString()+':'+Long.valueOf(diffInSeconds).toString();
		
		int sayi = (int) ((diffInHours*60)+(diffInMinutes/60)+(diffInSeconds/60));
		kalinan_bolum =(sayi/40)+1;    	
    
    	sql = "select ProgramAdi from kullaniciprogram where Email ='"+giris_mail+"' and ProgramAdi = '"+kalinan_film+"'";
    	
    	try {
    	
    		sorguIfadesi = baglanti.prepareStatement(sql);
        	ResultSet getirilen = sorguIfadesi.executeQuery();
        	
        
    		if(getirilen.next())
        	{   
    			sql = "select KaldigiBolum from kullaniciprogram where Email ='"+giris_mail+"' and ProgramAdi = '"+kalinan_film+"'";
        		sorguIfadesi = baglanti.prepareStatement(sql);
            	ResultSet getirilen1 = sorguIfadesi.executeQuery();
            	
            	while(getirilen1.next()) {
            		
            		a = getirilen1.getInt("KaldigiBolum");
            	}
              	
            	String sql_sorgu1 = "select IzlemeSure from kullaniciprogram where Email ='"+giris_mail+"' and ProgramAdi = '"+kalinan_film+"'";
        		sorguIfadesi = baglanti.prepareStatement(sql_sorgu1);
            	ResultSet getirilen2 = sorguIfadesi.executeQuery();
            	
            	while(getirilen2.next()) {
            		
            	    e_sure = getirilen2.getString("IzlemeSure");          
            	}            	
            	
        		LocalTime Time3 = LocalTime.parse(formattedTimee,frmttr);
        		LocalTime Time4 = LocalTime.parse(formattedTime,frmttr);
        		
        		long diffInSeconds1 = java.time.Duration.between(Time3, Time4).getSeconds();
        		long diffInMinutes1 = java.time.Duration.between(Time3, Time4).toMinutes();
        		long diffInHours1 = java.time.Duration.between(Time3, Time4).toHours();
        		
        		String[] gecen_sure = e_sure.split(":");
        		
        		diffInHours1 = diffInHours1 + Long.valueOf(gecen_sure[0]);
        		diffInMinutes1 = diffInMinutes1 + Long.valueOf(gecen_sure[1]);
        		diffInSeconds1 = diffInSeconds1 + Long.valueOf(gecen_sure[2]);
        		
        		String sure1 =Long.valueOf(diffInHours1).toString()+':'+Long.valueOf(diffInMinutes1).toString()+':'+Long.valueOf(diffInSeconds1).toString();
        		  		
            	
                sql_sorgu = "update kullaniciprogram set IzlemeTarihi=?,IzlemeSure=?,KaldigiBolum=? where Email ='"+giris_mail+"' and ProgramAdi = '"+kalinan_film+"'";
        		sorguIfadesi = baglanti.prepareStatement(sql_sorgu);
        		      		
            	sorguIfadesi.setString(1,formattedDate);
            	sorguIfadesi.setString(2,sure1);
            	sorguIfadesi.setFloat(3,(a+1));
            	sorguIfadesi.executeUpdate();  
            	
        	}	
            
    		else
            	{
            		sql_sorgu1 = "insert into kullaniciprogram(Email,ProgramAdi,IzlemeTarihi,IzlemeSure,KaldigiBolum,Puan) values (?,?,?,?,?,?)";
            		sorguIfadesi = baglanti.prepareStatement(sql_sorgu1);
            		
            		TextInputDialog  dialog = new TextInputDialog("puan giriniz");
                	dialog.setHeaderText("Film Puanlandýrma");
                	dialog.setContentText("Lütfen puan giriniz.");        	
                	Optional<String> sonuc = dialog.showAndWait();       		

            		sorguIfadesi.setString(1,giris_mail.trim());	
                    sorguIfadesi.setString(2,kalinan_film.trim());       	
                	sorguIfadesi.setString(3,formattedDate);
                	sorguIfadesi.setString(4,sure);
                	sorguIfadesi.setInt(5,kalinan_bolum);
                	sorguIfadesi.setString(6,sonuc.get());
                	sorguIfadesi.executeUpdate();  
            	}
    	}
    	catch(Exception e){
    		System.out.println(e.getMessage().toString());    		
    	}	
    }
    
    
    void yaz() {
			
		String sql_sorgu2 = "select kp.KaldigiBolum from kullaniciprogram as kp where kp.Email = '"+girilen_email+"' and kp.ProgramAdi = '"+kalinan_film+"'"; 

			try {

			
			sorguIfadesi = baglanti.prepareStatement(sql_sorgu2);
			ResultSet getirilen3 = sorguIfadesi.executeQuery();

			while(getirilen3.next()) 
			{
				bolum  = getirilen3.getString("KaldigiBolum");
		        kaldigi_bolum.setText(bolum);

			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			String sql_sorgu1 = "select kp.IzlemeSure from kullaniciprogram as kp where kp.Email = '"+girilen_email+"' and kp.ProgramAdi = '"+kalinan_film+"'"; 
			

			try {

		    sorguIfadesi = baglanti.prepareStatement(sql_sorgu1);
			ResultSet getirilen2 = sorguIfadesi.executeQuery();
			
	
			while(getirilen2.next())
			{
				izleme_suresi = getirilen2.getString("IzlemeSure");
				izleme_sure.setText(izleme_suresi);
			}

			} catch (SQLException e) {
				e.printStackTrace();
			}
					
    }
    


    @FXML
    void initialize() {
    	
        assert FilmSayfa != null : "fx:id=\"FilmSayfa\" was not injected: check your FXML file 'IzleForm.fxml'.";
        assert Izlenen_film != null : "fx:id=\"Izlenen_film\" was not injected: check your FXML file 'IzleForm.fxml'.";
        assert bitir_btn != null : "fx:id=\"bitir_btn\" was not injected: check your FXML file 'IzleForm.fxml'.";
        assert devamBtn != null : "fx:id=\"devamBtn\" was not injected: check your FXML file 'IzleForm.fxml'.";
        assert kaldigi_bolum != null : "fx:id=\"kaldigi_bolum\" was not injected: check your FXML file 'IzleForm.fxml'.";
        assert izleme_sure != null : "fx:id=\"izleme_sure\" was not injected: check your FXML file 'IzleForm.fxml'.";
        
        if(gir == 2 && kontrol != 2) {
        	
        	kontrol = 2;
        	giris_mail = email;
        	Izlenen_film.setText(izlenen_film);
        	kalinan_film = izlenen_film;
        	}
        else if(gir == 3 && kontrol != 2) {
        	        	
        	giris_mail = girilen_email;
        	Izlenen_film.setText(izlenen_film);
        	kalinan_film = izlenen_film;
        }
        else if(gir == 3 && kontrol == 2)
        {
        	giris_mail = email;
        	Izlenen_film.setText(izlenen_film);
        	kalinan_film = izlenen_film;
        }
        else {
        	giris_mail = girilen_email;
            Izlenen_film .setText(Programm_Adi);
            kalinan_film = Programm_Adi;
            yaz();
        }
    }	
}
