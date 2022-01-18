package controller;

import model.dto.CafePlace;
import model.dto.CafeReservation;
import model.dto.CafeUser;
import service.CafeReservationModel;
import service.CafeUserModel;
import view.FailEndView;
import view.SuccessEndView;

public class CafeController {
	
	private static CafeController instance = new CafeController();
	private static CafeUserModel userService = CafeUserModel.getInstance();
	private static CafeReservationModel reservationService = CafeReservationModel.getInstance();
	
	private CafeController() {}
	
	public static CafeController getInstance() {
		return instance;
	}

	public void join(CafeUser user){
		try {
				userService.join(user);
		}catch (Exception e) {
			//e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void updateUserInfo(String name, String id, String pnum){
		try {
			userService.updateUserInfo(name, id, pnum);
		}catch (Exception e) {
			//e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void deleteUserInfo(String id){	
		try {
			SuccessEndView.showMsg(userService.deleteUserInfo(id) + "원이 환불되었습니다.");
		}catch (Exception e) {
			//e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void searchUserInfo(String id){
		try {
			SuccessEndView.showInfo(userService.searchUserInfo(id));
		}catch (Exception e) {
			//e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void getAllUserInfo(){
		try {
			SuccessEndView.showAllInfo(userService.getAllUserInfo());
		}catch (Exception e) {
			//e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void chargePoint(String id, int money){
		try {
			SuccessEndView.showMsg("현재 잔액은" + userService.chargePoint(id, money) + "원입니다.");
		}catch (Exception e) {
			//e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void reserve(CafeUser user, CafePlace place, int time, int people){
		try {			
			reservationService.reserve(user, userService.getAllUserInfo(), place, time, people);
		}catch (Exception e) {
			//e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void cancleReservation(CafeReservation reserve){
		try {
			reservationService.cancleReservation(reserve);
		}catch (Exception e) {
			//e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void updateReservation(CafeReservation reserve, CafePlace place, int time, int people){
		try {
			reservationService.updateReservation(reserve, place, time, people);
		}catch (Exception e) {
			//e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}	
	}
	
	public void searchReservationInfo(String reservationNum) {
		try {
			SuccessEndView.showReservationInfo(reservationService.searchReservationInfo(reservationNum));
		}catch (Exception e) {
			//e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void searchAllReservationInfo() {
		try {
			SuccessEndView.showAllReservationInfo(reservationService.searchAllReservationInfo());
		}catch (Exception e) {
			//e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
}
