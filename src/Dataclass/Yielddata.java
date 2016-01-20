package Dataclass; //必要に応じて変える

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

// xxクラスでカインド（テーブル）を定義するための宣言
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Yielddata {

	// メンバ id はエンティティ（タプル）のキーである
	@PrimaryKey
	private String yearmail;
	
	/*
	// メンバ id の値はシステムが自動的に重複しないようつける
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	*/
	@Persistent
	private int year;
	
	@Persistent
	private String mail;

	@Persistent
	private String date;
	
	//気温
	@Persistent
	private String yield;
	



	public Yielddata(String yearmail, int year, String mail, String date,  String yield) {
		this.yearmail = yearmail;
		this.year     = year;
		this.mail     = mail;
		this.date     = date;
		this.yield    = yield;
	}




	public String getYearmail() {
		return yearmail;
	}




	public void setYearmail(String yearmail) {
		this.yearmail = yearmail;
	}






	public int getYear() {
		return year;
	}




	public void setYear(int year) {
		this.year = year;
	}




	public String getMail() {
		return mail;
	}




	public void setMail(String mail) {
		this.mail = mail;
	}




	public String getDate() {
		return date;
	}




	public void setDate(String date) {
		this.date = date;
	}




	public String getYield() {
		return yield;
	}




	public void setYield(String yield) {
		this.yield = yield;
	}

	
	
	
}