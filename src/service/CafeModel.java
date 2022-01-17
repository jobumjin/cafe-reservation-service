package service;

import java.util.ArrayList;

import exception.IdDuplicationException;
import exception.IdNotFoundException;
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
	
	public void updateUserInfo(String name, String id, String pnum) throws IdNotFoundException, NotEnoughInfoException{
		if(id == null || name == null || pnum == null)
			throw new NotEnoughInfoException("필수 입력 정보가 입력되지 않았습니다.");
		
		
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				user.setName(name);
				user.setPnum(pnum);
			}
		}
		throw new IdNotFoundException("아이디가 없습니다.");
	}
	
	public int deleteUserInfo(String id) throws IdNotFoundException {	
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				return user.getPoint();
			}
		}
		throw new IdNotFoundException("아이디가 없습니다.");
	}
	
	public CafeUser searchUserInfo(String id) throws IdNotFoundException{
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				return user;
			}
		}
		throw new IdNotFoundException("아이디가 없습니다.");
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
