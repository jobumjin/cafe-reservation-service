package model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CafePlace {
	private int roomNum;
	private int peopleNum;
	private int price;
	private boolean[] isReservePossible;
	
	public CafePlace(int roomNum, int peopleNum, int price) {
		this.roomNum = roomNum;
		this.peopleNum = peopleNum;
		this.price = price;
		this.isReservePossible = new boolean[] {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
	}
	
	public void setIsReservePossible(int startTime, int endTime, boolean possible) {
		for(int i=startTime; i<endTime; i++)
			isReservePossible[i] = possible;
	}
	
	public boolean isPlaceUsable(int startTime, int endTime) {
		for(int i=startTime; i<endTime; i++) {
			if(!isReservePossible[i])
				return false; 
		}
		return true;
	}
}
