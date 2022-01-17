package service;

import java.util.ArrayList;

import model.dto.CafeUser;

public class CafeModel {
	private static CafeModel instance = new CafeModel();
	private ArrayList<CafeUser> cafeUserList = new ArrayList<>();
	
	public static CafeModel getInstance() {
		return instance;
	}
	
	public void join(String name, String id, String joinDate, String pnum) {
		
	}
	
	public void updateUserInfo(String name, String id, String pnum) {
		
	}
	
	public int deleteUserInfo(String id) {
		return 0;
	}
	
	public CafeUser searchUserInfo(String id) {
		return null;
	}
	
	public ArrayList<CafeUser> getAllUserInfo(){
		return null;
	}
	
	public void reserve(String id, int point, int time) {
		
	}
	
	public void cancleReservation(String id) {
		
	}
	
	public void updateReservation(String id, int time) {
		
	}
	
	public void chargePoint(String id, int money) {
		
	}
}
