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

	//예약하기
	public void reserve(CafeUser user, ArrayList<CafeUser> userList, CafePlace place, int startTime, int endTime, int people) throws Exception{
		//하루씩만 예약 가능. 오후 10시 ~ 새벽 2시면 오후 10~12시, 0시~2시 예약을 따로 해야함
		if(user != null) {
			boolean flag = false;
			for(CafeUser u : userList) {
				if(u.getId().equals(user.getId())) {
					flag = true;
					break;
				}
			}
			if(!flag)
				throw new IdNotFoundException("회원만 예약을 진행할 수 있습니다.");
		}else {
			throw new IdNotFoundException("사용자를 찾을 수 없습니다.");
		}
		if(user.getReservation() == null) {
			if(startTime >= 0 && endTime <= 24 && startTime < endTime) {	
				if(place.isPlaceUsable(startTime, endTime)) {
					if(place.getPeopleNum() >= people){
						if(user.getPoint() >= place.getPrice() * (endTime - startTime)) {
							place.setIsReservePossible(startTime, endTime, false);
							user.setPoint(user.getPoint() - (place.getPrice() * (endTime - startTime)));
							CafeReservation reserve = new CafeReservation(user, place, startTime, endTime);
							user.setReservation(reserve.getReservationNum());
							cafeReservationList.add(reserve);				
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
		}else {
			throw new ReservationImpossibleException("이미 진행한 예약이 있습니다.");
		}
	}
	
	//예약 취소하기
	public void cancleReservation(String reservationNum) throws IdNotFoundException{
		for(int i=0; i<cafeReservationList.size(); i++) {
			CafeReservation r = cafeReservationList.get(i);
			if(r.getReservationNum().equals(reservationNum)){
				r.getUser().setPoint(r.getUser().getPoint() + r.getPlace().getPrice() * (r.getEndTime() - r.getStarTime()));
				r.getPlace().setIsReservePossible(r.getStarTime(), r.getEndTime(), true);
				r.getUser().setReservation(null);
				cafeReservationList.remove(r);
				return;
			}
		}
		throw new IdNotFoundException("예약 정보를 찾을 수 없습니다.");
	}
	
	//예약 변경하기
	public void updateReservation(String reservationNum, CafePlace place, int startTime, int endTime, int people) throws Exception{
		//같은 객실, 시간대가 겹칠 경우 업데이트 불가능
		for(int i=0; i<cafeReservationList.size(); i++) {
			CafeReservation r = cafeReservationList.get(i);
			if(r.getReservationNum().equals(reservationNum)){
				if(startTime >= 0 && endTime <= 24 && startTime < endTime) {
					if(place.isPlaceUsable(startTime, endTime)) {
						if(place.getPeopleNum() >= people){
							if((r.getUser().getPoint() + ((r.getEndTime() - r.getStarTime()) * r.getPlace().getPrice())) >= place.getPrice() * (endTime - startTime)) {
								place.setIsReservePossible(startTime, endTime, false);
								r.getPlace().setIsReservePossible(r.getStarTime(), r.getEndTime(), true);
								//환불
								r.getUser().setPoint((r.getEndTime() - r.getStarTime()) * r.getPlace().getPrice() + r.getUser().getPoint());
								//다시 돈 빼기
								r.getUser().setPoint(r.getUser().getPoint() - place.getPrice() * (endTime - startTime));
								
								CafeReservation reservation = new CafeReservation(r.getUser(), place, startTime, endTime);
								r.getUser().setReservation(reservation.getReservationNum());
								cafeReservationList.add(reservation);
								cafeReservationList.remove(r);
								return;
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
		}
		throw new IdNotFoundException("예약 정보를 찾을 수 없습니다.");
	}	
	
	//예약 객체 반환
	public CafeReservation searchReservationInfo(String reservationNum) throws IdNotFoundException{
		for(CafeReservation r : cafeReservationList) {
			if(r.getReservationNum().equals(reservationNum))
				return r;
		}
		throw new IdNotFoundException("예약 정보가 없습니다.");
	}
	
	//전체 예약 객체 반환
	public ArrayList<CafeReservation> searchAllReservationInfo() {
		return cafeReservationList;
	}
}

