package com.sabril.PAapp;

public class Puskesmas {
	int _id;
	String _namapuskesmas;
	String _latitude;
	String _longnitude;
	
	public Puskesmas(){
		
	}
	
	public Puskesmas(int id, String namapuskesmas, String _latitude, String _longnitude){
		this._id = id;
		this._namapuskesmas = namapuskesmas;
		this._latitude = _latitude;
		this._longnitude = _longnitude;
	}
	
	public Puskesmas(String namapuskesmas, String _latitude, String _longnitude){
		this._namapuskesmas = namapuskesmas;
		this._latitude = _latitude;
		this._longnitude = _longnitude;
	}
	
	public int getID(){
		return this._id;
	}
	
	public void setID(int id){
		this._id = id;
	}
	
	public String getNamaPuskesmas(){
		return this._namapuskesmas;
	}
	
	public void setNamaPuskesmas(String namaPuskesmas) {
		this._namapuskesmas = namaPuskesmas;
	}
	
	public String getLatitude(){
		return this._latitude;
	}
	
	public void setLatitude(String latitude) {
		this._latitude = latitude;
	}
	
	public String getLongnitude(){
		return this._longnitude;
	}
	
	public void setLongnitude(String longnitude) {
		this._longnitude = longnitude;
	}
}
