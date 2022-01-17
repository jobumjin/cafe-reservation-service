package view;

import java.util.ArrayList;

import model.dto.CafeUser;

public class SuccessEndView {
	
	public static void showInfo(CafeUser cafeUser) {
		System.out.println(cafeUser);
	}

	public static void showMsg(String message) {
		System.out.println(message);
	}

	public static void showAllInfo(ArrayList<CafeUser> cafeUserList) {
		for(CafeUser user : cafeUserList) {
			System.out.println(user);
		}
	}
}
