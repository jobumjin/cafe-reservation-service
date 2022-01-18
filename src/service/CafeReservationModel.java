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
					throw new IdNotFoundException("ȸ���� ������ �����մϴ�.");
				}
			}
		}else {
			throw new IdNotFoundException("����ڸ� ã�� �� �����ϴ�.");
		}
		if(time >= 24) {	
			if(place.getIsReservePossible()[time]) {
				if(place.getPeopleNum() >= people){
					if(user.getPoint() >= place.getPrice()) {
						place.setIsReservePossible(time, false);
						user.setPoint(user.getPoint() - place.getPrice());
						cafeReservationList.add(new CafeReservation(user.getId()+place.getRoomNum()+time, user, place, time));
					}else {
						throw new ReservationDuplicationException("���� �ݾ��� �����մϴ�. ������ �� �ٽ� �õ����ּ���.");
					}
				}else {
					throw new ReservationImpossibleException("���� ���� �ο��� �ʰ��߽��ϴ�.");
				}
			}else {
				throw new ReservationDuplicationException("�̹� ����� �����Դϴ�.");
			}
		}else {
			throw new ReservationDuplicationException("���� ���� �ð��� �ƴմϴ�.");
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
		throw new IdNotFoundException("���� ������ ã�� �� �����ϴ�.");
	}
	
	public void updateReservation(CafeReservation reserve, CafePlace place, int time, int people) throws Exception{
		for(CafeReservation r : cafeReservationList) {
			if(r.getReservationNum().equals(reserve.getReservationNum())){
				if(time >= 24) {
					if(place.getIsReservePossible()[time]) {
						if(place.getPeopleNum() >= people){
							//���� ���� ���
							place.setIsReservePossible(time, false);
							r.getPlace().setIsReservePossible(r.getTime(), true);
							cafeReservationList.add(new CafeReservation(r.getUser().getId()+place.getRoomNum()+time, r.getUser(), place, time));
							cafeReservationList.remove(r);
						}else {
							throw new ReservationImpossibleException("���� ���� �ο��� �ʰ��߽��ϴ�.");
						}
					}else {
						throw new ReservationDuplicationException("�̹� ����� �����Դϴ�.");
					}
				}else {
					throw new ReservationDuplicationException("���� ���� �ð��� �ƴմϴ�.");
				}
			}
		}
		throw new IdNotFoundException("���� ������ ã�� �� �����ϴ�.");
	}	
	
	public CafeReservation searchReservationInfo(String reservationNum) throws IdNotFoundException{
		for(CafeReservation r : cafeReservationList) {
			if(r.getReservationNum().equals(reservationNum))
				return r;
		}
		throw new IdNotFoundException("���� ������ �����ϴ�.");
	}
	
	public ArrayList<CafeReservation> searchAllReservationInfo() {
		return cafeReservationList;
	}
}

