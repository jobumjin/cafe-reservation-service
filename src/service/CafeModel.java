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
					throw new ReservationDuplicationException("이미 예약된 고객입니다.");
				}
				if(user.getPoint() < time * 2000) {
					throw new NotEnoughMoneyException("결제 금액이 부족합니다.");
				}
				
				user.setReserveTime(time);
				user.setPoint(user.getPoint() - time * 2000);
			}
		}
		throw new IdNotFoundException("아이디를 찾을 수 없습니다.");
	}
	
	public void cancleReservation(String id) throws IdNotFoundException{
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				user.setPoint(user.getReserveTime() * 2000);
				user.setReserveTime(0);
			}
		}
		throw new IdNotFoundException("아이디를 찾을 수 없습니다.");
	}
	
	public void updateReservation(String id, int time) throws IdNotFoundException, NotEnoughMoneyException{
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				if(user.getReserveTime() < time) {
					if(user.getPoint() < (time - user.getReserveTime()) * 2000) {
						throw new NotEnoughMoneyException("추가 결제 금액이 부족합니다.");
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
		throw new IdNotFoundException("아이디를 찾을 수 없습니다.");
		
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
