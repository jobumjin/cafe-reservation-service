package service;

import java.util.ArrayList;

import exception.IdDuplicationException;
import exception.IdNotFoundException;
import exception.NotEnoughInfoException;
import model.dto.CafeUser;

public class CafeUserModel {
	private static CafeUserModel instance = new CafeUserModel();
	private ArrayList<CafeUser> cafeUserList = new ArrayList<>();
	
	private CafeUserModel() {}
	
	public static CafeUserModel getInstance() {
		return instance;
	}
	
	//ȸ�� ����
	public void join(CafeUser user) throws IdDuplicationException, NotEnoughInfoException{
		if(user == null)
			throw new NotEnoughInfoException("�ʼ� �Է� ������ �Էµ��� �ʾҽ��ϴ�.");
		
		for(CafeUser u : cafeUserList) {
			if(u.getId().equals(user.getId())){
				throw new IdDuplicationException("���̵� �ߺ��Ǿ����ϴ�.");
			}
		}
		cafeUserList.add(user);
	}
	
	//ȸ�� ���� ����
	public void updateUserInfo(String name, String id, String pnum) throws IdNotFoundException, NotEnoughInfoException{
		if(id == null || name == null || pnum == null)
			throw new NotEnoughInfoException("�ʼ� �Է� ������ �Էµ��� �ʾҽ��ϴ�.");
		
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				user.setName(name);
				user.setPnum(pnum);
				return;
			}
		}
		throw new IdNotFoundException("���̵� ã�� �� �����ϴ�.");
	}
	
	//ȸ�� Ż��
	public int deleteUserInfo(String id) throws IdNotFoundException {	
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				int point = user.getPoint();
				cafeUserList.remove(user);
				return point;
			}
		}
		throw new IdNotFoundException("���̵� ã�� �� �����ϴ�.");
	}
	
	//����Ʈ ����
	public int chargePoint(String id, int money) throws IdNotFoundException {
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				user.setPoint(user.getPoint() + money);
				return user.getPoint();
			}
		}
		throw new IdNotFoundException("���̵� ã�� �� �����ϴ�.");
	}
	
	//���� ���� ��ȯ
	public CafeUser searchUserInfo(String id) throws IdNotFoundException{
		for(CafeUser user : cafeUserList) {
			if(user.getId().equals(id)){
				return user;
			}
		}
		throw new IdNotFoundException("���̵� �����ϴ�.");
	}
	
	//���� ��ü ��ȯ
	public ArrayList<CafeUser> getAllUserInfo(){
		return cafeUserList;
	}
}
