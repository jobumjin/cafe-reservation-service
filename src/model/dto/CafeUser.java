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
public class CafeUser {
	private String name;
	private String id;
	private String joinDate;
	private String pnum;
	private int point;
	private String reservation;
	
	@Override
	public String toString() {
		if(reservation == null) {
			return "CafeUser [name=" + name + ", id=" + id + ", joinDate=" + joinDate + ", pnum=" + pnum + ", point="
					+ point + ", reservation=예약 정보 없음]";
		}else {
			return "CafeUser [name=" + name + ", id=" + id + ", joinDate=" + joinDate + ", pnum=" + pnum + ", point="
					+ point + ", reservation=" + reservation + "]";
		}		
	}
}
