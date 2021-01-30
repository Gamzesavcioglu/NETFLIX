package application;

import java.sql.Time;

import javafx.scene.control.Button;

public class Programlar {
	
	private String ProgramAdi;
	private String ProgramTipi;
	private String ProgramTuru;
	private int BolumSayisi;
	private Time ProgramUzunluk;
	private Button Izle;
	
	
	public String getProgramAdi() {
		return ProgramAdi;
	}
	public void setProgramAdi(String programAdi) {
		ProgramAdi = programAdi;
	}
	public String getProgramTipi() {
		return ProgramTipi;
	}
	public void setProgramTipi(String programTipi) {
		ProgramTipi = programTipi;
	}
	public String getProgramTuru() {
		return ProgramTuru;
	}
	public void setProgramTuru(String programTuru) {
		ProgramTuru = programTuru;
	}
	public int getBolumSayisi() {
		return BolumSayisi;
	}
	public void setBolumSayisi(int bolumSayisi) {
		BolumSayisi = bolumSayisi;
	}
	public Time getProgramUzunluk() {
		return ProgramUzunluk;
	}
	public void setProgramUzunluk(Time programUzunluk) {
		ProgramUzunluk = programUzunluk;
	}
	
	public Button getIzle() {
		return Izle;
	}
	public void setIzle(Button izle) {
		Izle = izle;
	}
	
	Programlar(){
		
	}
	
	Programlar(String ProgramAdi,String ProgramTipi,String ProgramTuru, int BolumSayisi, Time ProgramUzunluk, Button Izle){
		
		this.ProgramAdi =  ProgramAdi;
		this.ProgramTipi =  ProgramTipi;
		this.ProgramTuru =  ProgramTuru;
		this.BolumSayisi =  BolumSayisi;
		this.ProgramUzunluk =  ProgramUzunluk;
		this.Izle =Izle;		
	}

}
