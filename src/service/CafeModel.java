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
			throw new NotEnoughInfoException("�ʼ� �Է� ������ �Էµ��� �ʾҽ��ϴ�.");
		
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				throw new IdDuplicationException("���̵� �ߺ��Ǿ����ϴ�.");
			}
		}
		cafeUserList.add(new CafeUser(name, id, joinDate, pnum));
	}
	
	public void updateUserInfo(String name, String id, String pnum) throws IdNotFoundException, NotEnoughInfoException{
		if(id == null || name == null || pnum == null)
			throw new NotEnoughInfoException("�ʼ� �Է� ������ �Էµ��� �ʾҽ��ϴ�.");
		
		
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				user.setName(name);
				user.setPnum(pnum);
			}
		}
		throw new IdNotFoundException("���̵� �����ϴ�.");
	}
	
	public int deleteUserInfo(String id) throws IdNotFoundException {	
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				return user.getPoint();
			}
		}
		throw new IdNotFoundException("���̵� �����ϴ�.");
	}
	
	public CafeUser searchUserInfo(String id) throws IdNotFoundException{
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				return user;
			}
		}
		throw new IdNotFoundException("���̵� �����ϴ�.");
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
