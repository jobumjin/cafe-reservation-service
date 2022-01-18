package view;

import controller.CafeController;
import model.dto.CafePlace;
import model.dto.CafeUser;

public class StartVie {

	public static void main(String[] args) {
		CafeUser u1 = new CafeUser("������", "user1", "2022-01-18", "010-0000-0000", 0, null);
		CafeUser u2 = new CafeUser("������", "user2", "2022-01-03", "010-1111-1111", 0, null);
		CafeUser u3 = new CafeUser("�̽���", "user3", "2022-01-13", "010-2222-2222", 5000, null);
		CafeUser u4 = new CafeUser("���缮", "user4", "2022-01-04", "010-1234-1234", 10000, null);
		
		CafePlace p1 = new CafePlace(101, 5, 20000);
		CafePlace p2 = new CafePlace(201, 7, 30000);
		CafePlace p3 = new CafePlace(301, 12, 50000);	

		CafeController controller = CafeController.getInstance();
		
		System.out.println("***1.ȸ�� ���� ���***");
		controller.join(u1);
		controller.join(u2);
		controller.join(u3);
		controller.join(u4);
		controller.getAllUserInfo();
		
		System.out.println("***2.ȸ�� ���� ����***");
		controller.updateUserInfo("���缮","user4","010-4567-4567");
		controller.updateUserInfo("�̿���","user5","010-4567-4567");
		controller.getAllUserInfo();
		
		System.out.println("***3.ȸ�� ���� ����***");
		controller.deleteUserInfo("user4");
		controller.getAllUserInfo();
		
		System.out.println("***4.ȸ�� ����Ʈ ����***");
		controller.chargePoint("user3", 50000);
		controller.chargePoint("user2", 30000);
		controller.searchUserInfo("user3");
		
		System.out.println("***5.���̳��� ����***");
		controller.reserve(u3, p1, 13, 15, 5);
		controller.reserve(u1, p1, 13, 15, 5);
		controller.reserve(u2, p2, 13, 15, 5);
		controller.reserve(u2, p2, 13, 14, 9);
		controller.reserve(u2, p2, 13, 14, 5);
		controller.searchAllReservationInfo();
		
		System.out.println("***6.���̳��� ����***");
		controller.updateReservation(u3.getReservation(), p3, 0, 1, 2);
		controller.updateReservation(u3.getReservation(), p2, 13, 15, 2);
		controller.searchReservationInfo(u3.getReservation());
		
		System.out.println("***7.���̳��� ���***");
		controller.cancleReservation(u3.getReservation());
		controller.searchReservationInfo(u3.getReservation());
		controller.searchAllReservationInfo();
		controller.searchUserInfo("user3");
	}
}
