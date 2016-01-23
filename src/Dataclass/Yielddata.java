package Dataclass; //必要に応じて変える

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
	//年
	@Persistent
	private int year;
	
	//アドレス
	@Persistent
	private String mail;

	//日付
	@Persistent
	private String date;
	
	
	//収穫量
	@Persistent
	private double yield;

	public Yielddata(String yearmail, int year, String mail, String date,  double yield) {
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
	public double getYield() {
		return yield;
	}
	public void setYield(double yield) {
		this.yield = yield;
	}
}