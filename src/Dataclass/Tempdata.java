package Dataclass; //必要に応じて変える

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

// xxクラスでカインド（テーブル）を定義するための宣言
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Tempdata {

	// メンバ id はエンティティ（タプル）のキーである
	//@PrimaryKey
	//@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	//private Long id;
	
	@PrimaryKey
	private String datestring;
	
	@Persistent
	private String mail;

	@Persistent
	private String date;
	//気温
	@Persistent
	private double temp;

	@Persistent
	private int year;
	
	@Persistent
	private int month;
	
	@Persistent
	private int day;

	public Tempdata( String dateString, String mail, String date, double temp, String year, String month, String day) {
		this.datestring = dateString;
		this.mail = mail;
		this.date = date;
		this.temp = temp;
		this.year = Integer.parseInt(year);
		this.month = Integer.parseInt(month);
		this.day = Integer.parseInt(day);
	}

	// ゲッタとセッタ

	public double gettemp() {
		return temp;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setContent(double temp) {
		this.temp = temp;
	}
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
}
