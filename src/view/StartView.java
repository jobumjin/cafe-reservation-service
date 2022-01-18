package view;

import org.junit.Test;

import model.dto.CafeUser;
import model.dto.CafePlace;
import model.dto.CafeReservation;
import controller.CafeController;

public class StartView {
	@Test
	public static void start() {
		CafeUser u1 = new CafeUser("������", "user1", "2022-01-18", "010-0000-0000", 0);
		CafeUser u2 = new CafeUser("������", "user2", "2022-01-03", "010-1111-1111", 0);
		CafeUser u3 = new CafeUser("�̽���", "user3", "2022-01-13", "010-2222-2222", 5000);
		
		CafePlace p1 = new CafePlace(101, 5, 20000);
		CafePlace p2 = new CafePlace(201, 7, 30000);
		CafePlace p3 = new CafePlace(301, 12, 50000);
		
		CafeController controller = CafeController.getInstance();
		
		System.out.println("***1.ȸ�� ���� ���***");
		
		System.out.println("***2.ȸ�� ���� ����***");
		System.out.println("***3.ȸ�� ���� ����***");
		System.out.println("***4.ȸ�� ����Ʈ ����***");
		System.out.println("***5.���̳��� ����***");
	}
}
