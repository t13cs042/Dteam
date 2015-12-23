package home; //必要に応じて変える

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

// xxクラスでカインド（テーブル）を定義するための宣言
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Climate {

	//日付
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Date date;

	//気温
	@PrimaryKey
	private double temp;

	//日照量
	@Persistent 
	private double laytime;

	//降水量
	@Persistent 
	private double prec;


	public Climate(Date date, double temp, double laytime, double prec) {
		this.date = date;
		this.temp = temp;
		this.laytime = laytime;
		this.prec = prec; 
	}

	// ゲッタとセッタ
	public Date getDate() {
		return date;
	}
	public double gettemp() {
		return temp;
	}
	public double getlaytime() {
		return laytime;
	}
	public double getprec() {
		return prec;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setContent(double temp) {
		this.temp = temp;
	}
	public void setlaytime(double laytime) {
		this.laytime = laytime;
	}
	public void setprec(double prec) {
		this.prec = prec;
	}

}
