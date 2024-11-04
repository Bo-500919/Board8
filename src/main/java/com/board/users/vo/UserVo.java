package com.board.users.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok 적용
//  @Data : @Getter, @Setter, @ToString
//        : @EqualsAndHashCode, @RequiredArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
	private  String   userid;
	private  String   passwd;
	private  String   username;
	private  String   email;
	private  int      upoint;
	private  String   regdate;
	
}


