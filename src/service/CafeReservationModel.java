package service;

import java.util.ArrayList;

import exception.IdNotFoundException;
import exception.NotEnoughMoneyException;
import exception.ReservationDuplicationException;
import model.dto.CafePlace;
import model.dto.CafeReservation;
import model.dto.CafeUser;

public class CafeReservationModel {
	private ArrayList<CafeReservation> cafeReservationList = new ArrayList<>();

	public void reserve(CafeUser user, CafePlace place, int time) throws IdNotFoundException, ReservationDuplicationException, NotEnoughMoneyException{
		if(user != null) {
			if(place.getIsReservePossible()[time]) {
				cafeReservationList.add(new CafeReservation(user.getId()+place.getRoomNum()+time, user, place, time));
			}
			throw new ReservationDuplicationException("이미 예약된 공간입니다.");
		}
		throw new IdNotFoundException("아이디를 찾을 수 없습니다.");
	}
	
	public void cancleReservation(CafeReservation reserve) throws IdNotFoundException{
		for(CafeReservation r : cafeReservationList) {
			if(r.getReservationNum().equals(reserve.getReservationNum())){
				r.getUser().setPoint((r.getTime() * 2000));
				cafeReservationList.remove(r);
			}
		}
		throw new IdNotFoundException("예약 정보를 찾을 수 없습니다.");
	}
	
	public void updateReservation(CafeReservation reserve, CafePlace place, int time) throws IdNotFoundException, ReservationDuplicationException{
		for(CafeReservation r : cafeReservationList) {
			if(r.getReservationNum().equals(reserve.getReservationNum())){
				if(place.getIsReservePossible()[time]) {
					r.getPlace().setIsReservePossible(time, true);
					cafeReservationList.add(new CafeReservation(r.getUser().getId()+place.getRoomNum()+time, r.getUser(), place, time));
					cafeReservationList.remove(r);
				}else {
					throw new ReservationDuplicationException("이미 예약된 공간입니다. 해당 예약사항으로 변경하실 수 없습니다.");
				}
			}
		}
		throw new IdNotFoundException("예약 정보를 찾을 수 없습니다.");
		
	}
	
}
