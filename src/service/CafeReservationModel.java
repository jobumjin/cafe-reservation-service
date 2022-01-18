package service;

import java.util.ArrayList;

import exception.IdNotFoundException;
import exception.ReservationImpossibleException;
import exception.ReservationDuplicationException;
import model.dto.CafePlace;
import model.dto.CafeReservation;
import model.dto.CafeUser;

public class CafeReservationModel {
	private static CafeReservationModel instance = new CafeReservationModel();
	private ArrayList<CafeReservation> cafeReservationList = new ArrayList<>();
	
	private CafeReservationModel() {}
	
	public static CafeReservationModel getInstance() {
		return instance;
	}

	public void reserve(CafeUser user, ArrayList<CafeUser> userList, CafePlace place, int time, int people) throws Exception{
		if(user != null) {
			for(CafeUser u : userList) {
				if(!u.getId().equals(user.getId())) {
					throw new IdNotFoundException("회원만 예약이 가능합니다.");
				}
			}
		}else {
			throw new IdNotFoundException("사용자를 찾을 수 없습니다.");
		}
		if(time >= 24) {	
			if(place.getIsReservePossible()[time]) {
				if(place.getPeopleNum() >= people){
					if(user.getPoint() >= place.getPrice()) {
						place.setIsReservePossible(time, false);
						user.setPoint(user.getPoint() - place.getPrice());
						cafeReservationList.add(new CafeReservation(user.getId()+place.getRoomNum()+time, user, place, time));
					}else {
						throw new ReservationDuplicationException("결제 금액이 부족합니다. 충전한 뒤 다시 시도해주세요.");
					}
				}else {
					throw new ReservationImpossibleException("예약 가능 인원을 초과했습니다.");
				}
			}else {
				throw new ReservationDuplicationException("이미 예약된 공간입니다.");
			}
		}else {
			throw new ReservationDuplicationException("예약 가능 시간이 아닙니다.");
		}
	}
	
	public void cancleReservation(CafeReservation reserve) throws IdNotFoundException{
		for(CafeReservation r : cafeReservationList) {
			if(r.getReservationNum().equals(reserve.getReservationNum())){
				r.getUser().setPoint(r.getUser().getPoint() + r.getPlace().getPrice());
				r.getPlace().setIsReservePossible(r.getTime(), true);
				cafeReservationList.remove(r);
			}
		}
		throw new IdNotFoundException("예약 정보를 찾을 수 없습니다.");
	}
	
	public void updateReservation(CafeReservation reserve, CafePlace place, int time, int people) throws Exception{
		for(CafeReservation r : cafeReservationList) {
			if(r.getReservationNum().equals(reserve.getReservationNum())){
				if(time >= 24) {
					if(place.getIsReservePossible()[time]) {
						if(place.getPeopleNum() >= people){
							//객실 가격 고려
							place.setIsReservePossible(time, false);
							r.getPlace().setIsReservePossible(r.getTime(), true);
							cafeReservationList.add(new CafeReservation(r.getUser().getId()+place.getRoomNum()+time, r.getUser(), place, time));
							cafeReservationList.remove(r);
						}else {
							throw new ReservationImpossibleException("예약 가능 인원을 초과했습니다.");
						}
					}else {
						throw new ReservationDuplicationException("이미 예약된 공간입니다.");
					}
				}else {
					throw new ReservationDuplicationException("예약 가능 시간이 아닙니다.");
				}
			}
		}
		throw new IdNotFoundException("예약 정보를 찾을 수 없습니다.");
	}	
	
	public CafeReservation searchReservationInfo(String reservationNum) throws IdNotFoundException{
		for(CafeReservation r : cafeReservationList) {
			if(r.getReservationNum().equals(reservationNum))
				return r;
		}
		throw new IdNotFoundException("예약 정보가 없습니다.");
	}
	
	public ArrayList<CafeReservation> searchAllReservationInfo() {
		return cafeReservationList;
	}
}

