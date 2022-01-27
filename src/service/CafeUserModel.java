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
	
	//회원 가입
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
	
	//회원 정보 수정
	public void updateUserInfo(String name, String id, String pnum) throws IdNotFoundException, NotEnoughInfoException{
		if(id == null || name == null || pnum == null)
			throw new NotEnoughInfoException("필수 입력 정보가 입력되지 않았습니다.");
		
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				user.setName(name);
				user.setPnum(pnum);
				return;
			}
		}
		throw new IdNotFoundException("아이디를 찾을 수 없습니다.");
	}
	
	//회원 탈퇴
	public int deleteUserInfo(String id) throws IdNotFoundException {	
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				int point = user.getPoint();
				cafeUserList.remove(user);
				return point;
			}
		}
		throw new IdNotFoundException("아이디를 찾을 수 없습니다.");
	}
	
	//포인트 충전
	public int chargePoint(String id, int money) throws IdNotFoundException {
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				user.setPoint(user.getPoint() + money);
				return user.getPoint();
			}
		}
		throw new IdNotFoundException("아이디를 찾을 수 없습니다.");
	}
	
	//유저 정보 반환
	public CafeUser searchUserInfo(String id) throws IdNotFoundException{
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				return user;
			}
		}
		throw new IdNotFoundException("아이디가 없습니다.");
	}
	
	//유저 전체 반환
	public ArrayList<CafeUser> getAllUserInfo(){
		return cafeUserList;
	}
}
