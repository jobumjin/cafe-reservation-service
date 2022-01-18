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
public class CafeReservation {
	private String reservationNum;
	private CafeUser user;
	private CafePlace place;
	private int starTime;
	private int endTime;
	
	public CafeReservation(CafeUser user, CafePlace place, int startTime, int endTime) {
		this.reservationNum = user.getId() + "reserved" + place.getRoomNum() + starTime;
		this.user = user;
		this.place = place;
		this.starTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "CafeReservation [reservationNum=" + reservationNum + ", name=" + user.getName() + ", place=" + place.getRoomNum()
				+ ", starTime=" + starTime + ", endTime=" + endTime + "]";
	}
}
