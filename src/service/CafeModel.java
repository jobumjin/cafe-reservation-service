package service;

import java.util.ArrayList;

import exception.IdDuplicationException;
import exception.NotEnoughInfoException;
import model.dto.CafeUser;

public class CafeModel {
	private static CafeModel instance = new CafeModel();
	private ArrayList<CafeUser> cafeUserList = new ArrayList<>();
	
	public static CafeModel getInstance() {
		return instance;
	}
	
	public void join(String name, String id, String joinDate, String pnum) throws IdDuplicationException, NotEnoughInfoException{
		if(id == null || name == null || joinDate == null || pnum == null)
			throw new NotEnoughInfoException("필수 입력 정보가 입력되지 않았습니다.");
		
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				throw new IdDuplicationException("아이디가 중복되었습니다.");
			}
		}
		cafeUserList.add(new CafeUser(name, id, joinDate, pnum));
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
