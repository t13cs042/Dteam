package home; //必要に応じて変える

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

// xxクラスでカインド（テーブル）を定義するための宣言
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Comment {

	//日付
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Date date;

	//アドレス
	@PrimaryKey
	private String address;

	//件名
	@Persistent 
	private String sub;

	//要望
	@Persistent 
	private String comment;


	public Comment(Date date, String address, String sub, String comment) {
		this.date = date;
		this.address = address;
		this.sub = sub;
		this.comment = comment; 
	}

	// ゲッタとセッタ
	public Date getDate() {
		return date;
	}
	public String gettemp() {
		return address;
	}
	public String getlaytime() {
		return sub;
	}
	public String getprec() {
		return comment;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setContent(String address) {
		this.address = address;
	}
	public void setlaytime(String sub) {
		this.sub = sub;
	}
	public void setprec(String comment) {
		this.comment = comment;
	}

}
