package service;

import java.util.ArrayList;

import exception.IdDuplicationException;
import exception.IdNotFoundException;
import exception.NotEnoughInfoException;
import exception.NotEnoughMoneyException;
import exception.ReservationDuplicationException;
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
		throw new IdNotFoundException("���̵� ã�� �� �����ϴ�.");
	}
	
	public int deleteUserInfo(String id) throws IdNotFoundException {	
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				return user.getPoint();
			}
		}
		throw new IdNotFoundException("���̵� ã�� �� �����ϴ�.");
	}
	
	public CafeUser searchUserInfo(String id) {
		return null;
	}
	
	public ArrayList<CafeUser> getAllUserInfo(){
		return cafeUserList;
	}
	
	public void reserve(String id, int point, int time) throws IdNotFoundException, ReservationDuplicationException, NotEnoughMoneyException{
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				if(user.getReserveTime() != 0) {
					throw new ReservationDuplicationException("�̹� ����� ���Դϴ�.");
				}
				if(user.getPoint() < time * 2000) {
					throw new NotEnoughMoneyException("���� �ݾ��� �����մϴ�.");
				}
				
				user.setReserveTime(time);
				user.setPoint(user.getPoint() - time * 2000);
			}
		}
		throw new IdNotFoundException("���̵� ã�� �� �����ϴ�.");
	}
	
	public void cancleReservation(String id) throws IdNotFoundException{
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				user.setPoint(user.getReserveTime() * 2000);
				user.setReserveTime(0);
			}
		}
		throw new IdNotFoundException("���̵� ã�� �� �����ϴ�.");
	}
	
	public void updateReservation(String id, int time) throws IdNotFoundException, NotEnoughMoneyException{
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				if(user.getReserveTime() < time) {
					if(user.getPoint() < (time - user.getReserveTime()) * 2000) {
						throw new NotEnoughMoneyException("�߰� ���� �ݾ��� �����մϴ�.");
					}else {
						user.setPoint(user.getPoint() - ((time - user.getReserveTime()) * 2000));
						user.setReserveTime(time);
					}
				}else {
					user.setPoint(user.getPoint() + ((user.getReserveTime() - time) * 2000));
					user.setReserveTime(time);
				}
			}
		}
		throw new IdNotFoundException("���̵� ã�� �� �����ϴ�.");
		
	}
	
	public void chargePoint(String id, int money) throws IdNotFoundException {
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				user.setPoint(user.getPoint() + money);
			}
		}
		throw new IdNotFoundException("���̵� ã�� �� �����ϴ�.");
	}
}
