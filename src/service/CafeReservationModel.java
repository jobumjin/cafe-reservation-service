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

	//�����ϱ�
	public void reserve(CafeUser user, ArrayList<CafeUser> userList, CafePlace place, int startTime, int endTime, int people) throws Exception{
		//�Ϸ羿�� ���� ����. ���� 10�� ~ ���� 2�ø� ���� 10~12��, 0��~2�� ������ ���� �ؾ���
		if(user != null) {
			boolean flag = false;
			for(CafeUser u : userList) {
				if(u.getId().equals(user.getId())) {
					flag = true;
					break;
				}
			}
			if(!flag)
				throw new IdNotFoundException("ȸ���� ������ ������ �� �ֽ��ϴ�.");
		}else {
			throw new IdNotFoundException("����ڸ� ã�� �� �����ϴ�.");
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
		}else {
			throw new ReservationImpossibleException("�̹� ������ ������ �ֽ��ϴ�.");
		}
	}
	
	//���� ����ϱ�
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
		throw new IdNotFoundException("���� ������ ã�� �� �����ϴ�.");
	}
	
	//���� �����ϱ�
	public void updateReservation(String reservationNum, CafePlace place, int startTime, int endTime, int people) throws Exception{
		//���� ����, �ð��밡 ��ĥ ��� ������Ʈ �Ұ���
		for(int i=0; i<cafeReservationList.size(); i++) {
			CafeReservation r = cafeReservationList.get(i);
			if(r.getReservationNum().equals(reservationNum)){
				if(startTime >= 0 && endTime <= 24 && startTime < endTime) {
					if(place.isPlaceUsable(startTime, endTime)) {
						if(place.getPeopleNum() >= people){
							if((r.getUser().getPoint() + ((r.getEndTime() - r.getStarTime()) * r.getPlace().getPrice())) >= place.getPrice() * (endTime - startTime)) {
								place.setIsReservePossible(startTime, endTime, false);
								r.getPlace().setIsReservePossible(r.getStarTime(), r.getEndTime(), true);
								//ȯ��
								r.getUser().setPoint((r.getEndTime() - r.getStarTime()) * r.getPlace().getPrice() + r.getUser().getPoint());
								//�ٽ� �� ����
								r.getUser().setPoint(r.getUser().getPoint() - place.getPrice() * (endTime - startTime));
								
								CafeReservation reservation = new CafeReservation(r.getUser(), place, startTime, endTime);
								r.getUser().setReservation(reservation.getReservationNum());
								cafeReservationList.add(reservation);
								cafeReservationList.remove(r);
								return;
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
		}
		throw new IdNotFoundException("���� ������ ã�� �� �����ϴ�.");
	}	
	
	//���� ��ü ��ȯ
	public CafeReservation searchReservationInfo(String reservationNum) throws IdNotFoundException{
		for(CafeReservation r : cafeReservationList) {
			if(r.getReservationNum().equals(reservationNum))
				return r;
		}
		throw new IdNotFoundException("���� ������ �����ϴ�.");
	}
	
	//��ü ���� ��ü ��ȯ
	public ArrayList<CafeReservation> searchAllReservationInfo() {
		return cafeReservationList;
	}
}

