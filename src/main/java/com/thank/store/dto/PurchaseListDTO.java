package com.thank.store.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//작성자 : 방지훈
//작성일자 : 05/24 16:26
@Getter
@Setter
@ToString
public class PurchaseListDTO {
	private long no;
	private long customerno;
	private String productcode;
	private String name;
	private long purchaseprice;
	private long cvstoreproductno;
	
}
