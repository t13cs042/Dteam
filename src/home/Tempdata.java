package home; //必要に応じて変える

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

// xxクラスでカインド（テーブル）を定義するための宣言
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Tempdata {

	//日付
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Date date;

	//気温
	@PrimaryKey
	private Long temp;


	public Tempdata(Date date, Long temp) {
		this.date = date;
		this.temp = temp;
	}

	// ゲッタとセッタ
	public Date getDate() {
		return date;
	}
	public Long gettemp() {
		return temp;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setContent(Long temp) {
		this.temp = temp;
	}

}
