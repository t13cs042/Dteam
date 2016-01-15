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
	@PrimaryKey
	private Long id;
	
	//気温
	@Persistent
	private double temp;

	@Persistent
	private Date date;

	public Tempdata(Long id, Date date, double d) {
		this.id = id;
		this.date = date;
		this.temp = d;
	}

	// ゲッタとセッタ
	public Date getDate() {
		return date;
	}
	public double gettemp() {
		return temp;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setContent(double temp) {
		this.temp = temp;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
