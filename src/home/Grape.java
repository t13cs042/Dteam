package home; //必要に応じて変える

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

// xxクラスでカインド（テーブル）を定義するための宣言
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Grape {

	//日付
	@PrimaryKey
	private String date;

	//気温
	@Persistent 
	private double num;

	public Grape(String date, double num) {
		this.date = date;
		this.num = num;
	}

	// ゲッタとセッタ
	public String getDate() {
		return date;
	}
	public double getNum() {
		return num;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setNum(double num) {
		this.num = num;
	}

}
