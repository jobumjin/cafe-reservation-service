package view;

import java.util.ArrayList;

import model.dto.CafeReservation;
import model.dto.CafeUser;

public class SuccessEndView {	
	public static void showMsg(String message) {
		System.out.println(message);
	}
	
	public static void showInfo(CafeUser cafeUser) {
		System.out.println(cafeUser);
	}

	public static void showAllInfo(ArrayList<CafeUser> cafeUserList) {
		for(CafeUser user : cafeUserList) {
			System.out.println(user);
		}
	}
	
	public static void showReservationInfo(CafeReservation reservation) {
		System.out.println(reservation);
	}
	
	public static void showAllReservationInfo(ArrayList<CafeReservation> reservationList) {
		if(reservationList == null) {
			System.out.println("예약 정보가 없습니다.");
		}
		
		for(CafeReservation r : reservationList) {
			System.out.println(r);
		}
	}
}
