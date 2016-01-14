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
	/*
	// メンバ id の値はシステムが自動的に重複しないようつける
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	*/
	@Persistent
	private String year;
	
	//気温
	@Persistent
	private String yield;



	public Yielddata(String date, String d) {
		this.year = date;
		this.yield = d;
	}

	// ゲッタとセッタ
	public String getDate() {
		return year;
	}
	public String gettemp() {
		return yield;
	}
	public void setDate(String date) {
		this.year = date;
	}
	public void setContent(String temp) {
		this.yield = temp;
	}

}
