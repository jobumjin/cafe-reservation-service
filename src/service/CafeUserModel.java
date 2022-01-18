package service;

import java.util.ArrayList;

import exception.IdDuplicationException;
import exception.IdNotFoundException;
import exception.NotEnoughInfoException;
import model.dto.CafeUser;

public class CafeUserModel {
	private static CafeUserModel instance = new CafeUserModel();
	private ArrayList<CafeUser> cafeUserList = new ArrayList<>();
	
	private CafeUserModel() {}
	
	public static CafeUserModel getInstance() {
		return instance;
	}
	
	public void join(CafeUser user) throws IdDuplicationException, NotEnoughInfoException{
		if(user == null)
			throw new NotEnoughInfoException("필수 입력 정보가 입력되지 않았습니다.");
		
		for(CafeUser u : cafeUserList) {
			if(u.getId().equals(user.getId())){
				throw new IdDuplicationException("아이디가 중복되었습니다.");
			}
		}
		cafeUserList.add(user);
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
		throw new IdNotFoundException("아이디를 찾을 수 없습니다.");
	}
	
	public int deleteUserInfo(String id) throws IdNotFoundException {	
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				return user.getPoint();
			}
		}
		throw new IdNotFoundException("아이디를 찾을 수 없습니다.");
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
		return cafeUserList;
	}
	
	public void chargePoint(String id, int money) throws IdNotFoundException {
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				user.setPoint(user.getPoint() + money);
			}
		}
		throw new IdNotFoundException("아이디를 찾을 수 없습니다.");
	}
}
