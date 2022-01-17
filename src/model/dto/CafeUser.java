package model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CafeUser {
	private String name;
	private String id;
	private int point;
	private String joinDate;
	private String pnum;
	private int reserveTime;
	
	public CafeUser(String name, String id, String joinDate, String pnum) {
		this.name = name;
		this.id = id;
		this.joinDate = joinDate;
		this.pnum = pnum;
	}
}
