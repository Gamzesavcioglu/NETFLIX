package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.IsteMysql.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class ProgramFormController extends KullaniciFormController{

	String[] adlar = new String[3];
	static String izlenen_film;
	static String mail;
	static Time uzunluk;
	static int kontrol = 1;
	static String formattedTimee;
		
	String sql ="";
	Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen = null;
	
	public ProgramFormController() {	
		
		baglanti = VeritabaniUtil.Baglan();			
	}
	
    @FXML
    private AnchorPane ProgramSayfa;    

	@FXML
    private URL location;    

    @FXML
    private Button AraBtn;    

    @FXML
    private Button Izle1_btn1;
    
    @FXML
    private Button Izle1_btn2;
    
    @FXML
    private Button Izle3_btn1;

    @FXML
    private Button Izle2_btn1;

    @FXML
    private Button Izle2_btn2;

    @FXML
    private Button Izle3_btn2;

    @FXML
    private TextField Secilen1;
    
    @FXML
    private TextField ArananText;    

    @FXML
    private TableColumn<Programlar, Button> IzleSutun;

    @FXML
    private TableView<Programlar> TabloGoster;

    @FXML
    private TableColumn<Programlar, String> ProgramlarSutun;

    @FXML
    private TableColumn<Programlar, String> ProgramTipiSutun;

    @FXML
    private TableColumn<Programlar, String> ProgramTuruSutun;

    @FXML
    private TableColumn<Programlar, Integer> BolumSayisiSutun;

    @FXML
    private TableColumn<Programlar, Time> ProgramUzunluguSutun;

    @FXML
    private TextField Secilen2;

    @FXML
    private TextField Secilen3;

    @FXML
    private TextField Secilen1_Program1;

    @FXML
    private TextField Secilen1_Program2;

    @FXML
    private TextField Secilen2_Program1;

    @FXML
    private TextField Secilen3_Program1;

    @FXML
    private TextField Secilen2_Program2;

    @FXML
    private TextField Secilen3_Program2;
  
    public void SecilenYazdir()
    {
    	adlar = KullaniciFormController.SecilenTurler();
    
    	 Secilen1.setText(adlar[0]);
         Secilen2.setText(adlar[1]);
    	 Secilen3.setText(adlar[2]);
    		
    		String tur_arama =  Secilen1.getText();
        	String sql_sorgu2 = "select distinct(ProgramAdi) from programtur where ProgramTur='"+tur_arama+"'";
        	        	
        	String tur_arama2 =  Secilen2.getText();
        	String sql_sorgu3 = "select distinct(ProgramAdi) from programtur where ProgramTur='"+tur_arama2+"'";        	
        	
        	String tur_arama3 =  Secilen3.getText();
        	String sql_sorgu4 = "select distinct(ProgramAdi) from programtur where ProgramTur='"+tur_arama3+"'";
                   	      	
        	try {
        		
				sorguIfadesi = baglanti.prepareStatement(sql_sorgu2);
				ResultSet getirilen = sorguIfadesi.executeQuery();
				
				while(getirilen.next())
				{
					Secilen1_Program1.setText(getirilen.getString("ProgramAdi"));
					
					while(getirilen.next())
					{
						Secilen1_Program2.setText(getirilen.getString("ProgramAdi"));
					}					
				}			
        	 } 
        	
        	catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	
        	try {
        		
        		sorguIfadesi = baglanti.prepareStatement(sql_sorgu3);
				ResultSet getirilen2 = sorguIfadesi.executeQuery();
               
				while(getirilen2.next())
				{
					Secilen2_Program1.setText(getirilen2.getString("ProgramAdi"));
					
					while(getirilen2.next()) 
					{				
						Secilen2_Program2.setText(getirilen2.getString("ProgramAdi"));
					}		
				}
     	       } 
        	catch(Exception e) {
        		System.out.println(e.getMessage().toString());
            }
            	
        	
        	try {
        		
        	sorguIfadesi = baglanti.prepareStatement(sql_sorgu4);
			ResultSet getirilen3 = sorguIfadesi.executeQuery();
             
			while(getirilen3.next())
			{
				Secilen3_Program1.setText(getirilen3.getString("ProgramAdi"));
				
				while(getirilen3.next())
				{
					Secilen3_Program2.setText(getirilen3.getString("ProgramAdi"));
		        }			
			}    		
    	  
        }
        	catch(Exception e) {
        		System.out.println(e.getMessage().toString());
        }  		
    }
    
    public void SecilenYazdir2() {
    	
    	String sorgu = "select Turler from kullanici where Email = '"+girilen_email+"'";
    	String tur = null;
    	
    	try {
    		
			sorguIfadesi = baglanti.prepareStatement(sorgu);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			
			while(getirilen.next())
			{
				tur = getirilen.getNString("Turler");

			}
			
			String[] tur_dizi = tur.split(",");
			
				Secilen1.setText(tur_dizi[0].substring(1, tur_dizi[0].length()).trim());
				Secilen2.setText(tur_dizi[1].trim());
				Secilen3.setText(tur_dizi[2].substring(0, (tur_dizi[2].length())-1).trim());	
	    		
	    		String tur_arama =  Secilen1.getText();
	        	String sql_sorgu2 = "select distinct(ProgramAdi) from programtur where ProgramTur='"+tur_arama+"'";
	        	        	
	        	String tur_arama2 =  Secilen2.getText();
	        	String sql_sorgu3 = "select distinct(ProgramAdi) from programtur where ProgramTur='"+tur_arama2+"'";        	
	        	
	        	String tur_arama3 =  Secilen3.getText();
	        	String sql_sorgu4 = "select distinct(ProgramAdi) from programtur where ProgramTur='"+tur_arama3+"'";
	                   	      	
	        	try {
	        		
					sorguIfadesi = baglanti.prepareStatement(sql_sorgu2);
					ResultSet getirilen1 = sorguIfadesi.executeQuery();
					
					while(getirilen1.next())
					{
						Secilen1_Program1.setText(getirilen1.getString("ProgramAdi"));
						
						while(getirilen1.next())
						{
							Secilen1_Program2.setText(getirilen1.getString("ProgramAdi"));
						}					
					}			
	        	 } 
	        	
	        	catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	       	
	        	try {
	        		
	        		sorguIfadesi = baglanti.prepareStatement(sql_sorgu3);
					ResultSet getirilen2 = sorguIfadesi.executeQuery();
	               
					while(getirilen2.next())
					{
						Secilen2_Program1.setText(getirilen2.getString("ProgramAdi"));
						
						while(getirilen2.next()) 
						{				
							Secilen2_Program2.setText(getirilen2.getString("ProgramAdi"));
						}
						
					}
	     	       } 
	        	catch(Exception e) {
	        		System.out.println(e.getMessage().toString());
	            }
	            	
	        	
	        	try {
	        		
	        	sorguIfadesi = baglanti.prepareStatement(sql_sorgu4);
				ResultSet getirilen3 = sorguIfadesi.executeQuery();
	             
				while(getirilen3.next())
				{
					Secilen3_Program1.setText(getirilen3.getString("ProgramAdi"));
					
					while(getirilen3.next())
					{
						Secilen3_Program2.setText(getirilen3.getString("ProgramAdi"));
			        }			
				}    		
	    	  
	        }
	        	catch(Exception e) {
	        		System.out.println(e.getMessage().toString());
	        }
				
    	 } 
    	
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	
   
    }
    
    @FXML
    void Izle1_btn1Click(ActionEvent event) {
     
      String sorgula = Secilen1_Program1.getText();
      izlenen_film = sorgula;	
    	
     DateTimeFormatter formatter1 = DateTimeFormatter.ISO_TIME;
	formattedTimee = formatter1.format(LocalTime.now());
		
		try {	
			AnchorPane pane3 = (AnchorPane) FXMLLoader.load(getClass().getResource("IzleForm.fxml"));
			ProgramSayfa.getChildren().setAll(pane3);
			
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	} 	
}

    @FXML
    void Izle1_btn2Click(ActionEvent event) {
        
        String sorgula = Secilen1_Program2.getText();
		izlenen_film = sorgula;									
		
		DateTimeFormatter formatter1 = DateTimeFormatter.ISO_TIME;
		formattedTimee = formatter1.format(LocalTime.now());
		
		try {
		
			AnchorPane 	pane3 = (AnchorPane) FXMLLoader.load(getClass().getResource("IzleForm.fxml"));
			ProgramSayfa.getChildren().setAll(pane3);
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	

}

    @FXML
    void Izle2_btn1Click(ActionEvent event) {
        
        String sorgula = Secilen2_Program1.getText();
        izlenen_film = sorgula;	
	

     	DateTimeFormatter formatter1 = DateTimeFormatter.ISO_TIME;
		formattedTimee = formatter1.format(LocalTime.now());
	
	try {
		
		AnchorPane pane3 = (AnchorPane) FXMLLoader.load(getClass().getResource("IzleForm.fxml"));
		ProgramSayfa.getChildren().setAll(pane3);
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
	}
}

    @FXML
    void Izle2_btn2Click(ActionEvent event) {
        
        String sorgula = Secilen2_Program2.getText();
		izlenen_film = sorgula;							
				
	DateTimeFormatter formatter1 = DateTimeFormatter.ISO_TIME;
	formattedTimee = formatter1.format(LocalTime.now());

	try {
		
		AnchorPane pane3 = (AnchorPane) FXMLLoader.load(getClass().getResource("IzleForm.fxml"));
		ProgramSayfa.getChildren().setAll(pane3);
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
	}
}

    @FXML
    void Izle3_btn1Click(ActionEvent event) {
        
        String sorgula = Secilen3_Program1.getText();
		izlenen_film = sorgula;							
	
	DateTimeFormatter formatter1 = DateTimeFormatter.ISO_TIME;
	formattedTimee = formatter1.format(LocalTime.now());
	
	try {			

		AnchorPane pane3 = (AnchorPane) FXMLLoader.load(getClass().getResource("IzleForm.fxml"));
		ProgramSayfa.getChildren().setAll(pane3);
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
	}
}

    @FXML
    void Izle3_btn2Click(ActionEvent event) {
        
        String sorgula = Secilen3_Program2.getText();
		izlenen_film = sorgula;									
		
	DateTimeFormatter formatter1 = DateTimeFormatter.ISO_TIME;
	formattedTimee = formatter1.format(LocalTime.now());

      try {
		
		AnchorPane pane3 = (AnchorPane) FXMLLoader.load(getClass().getResource("IzleForm.fxml"));
		ProgramSayfa.getChildren().setAll(pane3);
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
	}
  }
 
    @FXML
    void AraBtnClick(ActionEvent event) throws SQLException {
   	
    	String aranan = ArananText.getText();
    	String sql_sorgu = "select *from programtur,program where programtur.ProgramAdi='"+aranan+"' and program.ProgramAdi = programtur.ProgramAdi or programtur.ProgramTur='"+aranan+"' and program.ProgramAdi = programtur.ProgramAdi" ;
    		    	
    	ObservableList <Programlar> KayitlarListe =  FXCollections.observableArrayList();
    	
    	try {
    		
		sorguIfadesi = baglanti.prepareStatement(sql_sorgu);		
		ResultSet getirilen = sorguIfadesi.executeQuery();
		
		while(getirilen.next()) {
			
			Button btn = new Button();
			btn.setText("IZLE");
			
			btn.setOnAction(new EventHandler<ActionEvent>(){
	             @Override
	             public void handle(ActionEvent event) {
	            	 
	            	 int index =TabloGoster.getSelectionModel().getSelectedIndex();
	            	 
	            	try {
	            		 izlenen_film = ProgramlarSutun.getCellData(index);
		     			AnchorPane pane3 = (AnchorPane) FXMLLoader.load(getClass().getResource("IzleForm.fxml"));
		    			ProgramSayfa.getChildren().setAll(pane3);
	            	}
	            	catch(Exception e) {
	            		
	            	System.out.println(e.getMessage().toString());
	            	}	            	 
	             }
	         });
			
			KayitlarListe.add(new Programlar (getirilen.getString("ProgramAdi"),getirilen.getString("ProgramTipi"),getirilen.getString("ProgramTuru"),getirilen.getInt("BolumSayisi"),getirilen.getTime("ProgramUzunluk"),btn));   			
		}
		
		ProgramlarSutun.setCellValueFactory(new PropertyValueFactory <>("ProgramAdi"));
		ProgramTipiSutun.setCellValueFactory(new PropertyValueFactory <>("ProgramTipi"));
		ProgramTuruSutun.setCellValueFactory(new PropertyValueFactory <>("ProgramTuru"));
		BolumSayisiSutun.setCellValueFactory(new PropertyValueFactory <>("BolumSayisi"));
		ProgramUzunluguSutun.setCellValueFactory(new PropertyValueFactory <>("ProgramUzunluk"));
		IzleSutun.setCellValueFactory(new PropertyValueFactory <>("Izle"));
		TabloGoster.setItems(KayitlarListe);   			
		
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage().toString());
    	}  	
    }    
    
    
   public void DegerleriGetir(TableView TabloGoster){
	   	   
    	sql = "Select *from program";    	
    	ObservableList <Programlar> KayitlarListe =  FXCollections.observableArrayList();
    		
    	try {    		
    		sorguIfadesi = baglanti.prepareStatement(sql);
    		ResultSet getirilen = sorguIfadesi.executeQuery();
    		
    		while(getirilen.next()) {
    			
    			Button btn = new Button();
    			btn.setText("IZLE");
    			
    			btn.setOnAction(new EventHandler<ActionEvent>(){
    	             @Override
    	             public void handle(ActionEvent event) {
    	            	 
    	            	 int index =TabloGoster.getSelectionModel().getSelectedIndex();
    	            	 
    	            	 try {    	            		    
    	            		    izlenen_film = ProgramlarSutun.getCellData(index);
    	            		     	            			
    	            		    DateTimeFormatter formatter1 = DateTimeFormatter.ISO_TIME;
    	            			formattedTimee = formatter1.format(LocalTime.now());
    	            			
    			     			AnchorPane pane3 = (AnchorPane) FXMLLoader.load(getClass().getResource("IzleForm.fxml"));
    			    			ProgramSayfa.getChildren().setAll(pane3);
    		            	}
    		            	catch(Exception e) {
    		            		
    		            	System.out.println(e.getMessage().toString());
    		            	}   	            	 	                 
    	             }
    	         });
    			
    			KayitlarListe.add(new Programlar (getirilen.getString("ProgramAdi"),getirilen.getString("ProgramTipi"),getirilen.getString("ProgramTuru"),getirilen.getInt("BolumSayisi"),getirilen.getTime("ProgramUzunluk"),btn));   			
    		}

    		ProgramlarSutun.setCellValueFactory(new PropertyValueFactory <>("ProgramAdi"));
    		ProgramTipiSutun.setCellValueFactory(new PropertyValueFactory <>("ProgramTipi"));
    		ProgramTuruSutun.setCellValueFactory(new PropertyValueFactory <>("ProgramTuru"));
    		BolumSayisiSutun.setCellValueFactory(new PropertyValueFactory <>("BolumSayisi"));
    		ProgramUzunluguSutun.setCellValueFactory(new PropertyValueFactory <>("ProgramUzunluk"));
    		IzleSutun.setCellValueFactory(new PropertyValueFactory <>("Izle"));
    		TabloGoster.setItems(KayitlarListe);  
		
        	}
    	catch(Exception e) {
    		System.out.println(e.getMessage().toString());
    	}   	
   }        
    @FXML
    void initialize() {	
    	
    	assert ArananText != null : "fx:id=\"ArananText\" was not injected: check your FXML file 'ProgramForm.fxml'.";
    	assert AraBtn != null : "fx:id=\"AraBtn\" was not injected: check your FXML file 'ProgramForm.fxml'."; 
    	assert Secilen1 != null : "fx:id=\"Secilen1\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert TabloGoster != null : "fx:id=\"TabloGoster\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert ProgramlarSutun != null : "fx:id=\"ProgramlarSutun\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert ProgramTipiSutun != null : "fx:id=\"ProgramTipiSutun\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert ProgramTuruSutun != null : "fx:id=\"ProgramTuruSutun\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert BolumSayisiSutun != null : "fx:id=\"BolumSayisiSutun\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert ProgramUzunluguSutun != null : "fx:id=\"ProgramUzunluguSutun\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert IzleSutun != null : "fx:id=\"IzleSutun\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert Secilen2 != null : "fx:id=\"Secilen2\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert Secilen3 != null : "fx:id=\"Secilen3\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert Secilen1_Program1 != null : "fx:id=\"Secilen1_Program1\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert Secilen1_Program2 != null : "fx:id=\"Secilen1_Program2\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert Secilen2_Program1 != null : "fx:id=\"Secilen2_Program1\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert Secilen3_Program1 != null : "fx:id=\"Secilen3_Program1\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert Secilen2_Program2 != null : "fx:id=\"Secilen2_Program2\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert Secilen3_Program2 != null : "fx:id=\"Secilen3_Program2\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert Izle1_btn1 != null : "fx:id=\"Izle1_btn1\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert Izle1_btn2 != null : "fx:id=\"Izle1_btn2\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert Izle3_btn1 != null : "fx:id=\"Izle3_btn1\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert Izle2_btn1 != null : "fx:id=\"Izle2_btn1\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert Izle2_btn2 != null : "fx:id=\"Izle2_btn2\" was not injected: check your FXML file 'ProgramForm.fxml'.";
        assert Izle3_btn2 != null : "fx:id=\"Izle3_btn2\" was not injected: check your FXML file 'ProgramForm.fxml'.";

        if(gir == 2 || kontrol == 2)
        {
          DegerleriGetir(TabloGoster);
          mail = email; 
          SecilenYazdir();
        }
        else{
        	gir=3;
        	 DegerleriGetir(TabloGoster);
        	 SecilenYazdir2();
        }
   }
}