package controller;

import service.CafeUserModel;
import view.FailEndView;
import view.SuccessEndView;

public class CafeUserController {
	
	private static CafeUserController instance = new CafeUserController();
	private static CafeUserModel service = CafeUserModel.getInstance();
	
	private CafeUserController() {}
	
	public static CafeUserController getInstance() {
		return instance;
	}

	public void join(String name, String id, String joinDate, String pnum){
		try {
				service.join(name, id, joinDate, pnum);
		}catch (Exception e) {
			e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void updateUserInfo(String name, String id, String pnum){
		try {
			service.updateUserInfo(name, id, pnum);
		}catch (Exception e) {
			e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void deleteUserInfo(String id){	
		try {
			SuccessEndView.showMsg(service.deleteUserInfo(id) + "원이 환불되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void searchUserInfo(String id){
		try {
			SuccessEndView.showInfo(service.searchUserInfo(id));
		}catch (Exception e) {
			e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void getAllUserInfo(){
		try {
			SuccessEndView.showAllInfo(service.getAllUserInfo());
		}catch (Exception e) {
			e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void reserve(String id, int point, int time){
		try {
			service.reserve(id, point, time);
		}catch (Exception e) {
			e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void cancleReservation(String id){
		try {
			service.cancleReservation(id);
		}catch (Exception e) {
			e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
	
	public void updateReservation(String id, int time){
		try {
			service.updateReservation(id, time);
		}catch (Exception e) {
			e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}	
	}
	
	public void chargePoint(String id, int money){
		try {
			service.chargePoint(id, money);
		}catch (Exception e) {
			e.printStackTrace();
			FailEndView.showError(e.getMessage());
		}
	}
}
