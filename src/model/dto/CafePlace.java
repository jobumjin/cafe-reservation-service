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
	
	public void setIsReservePossible(int time, boolean possible) {
		isReservePossible[time] = possible;
	}
}
