package Dataclass; //必要に応じて変える

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

// xxクラスでカインド（テーブル）を定義するための宣言
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Comment {

	//アドレス
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String address;

	//件名
	@Persistent 
	private String sub;

	//要望
	@Persistent 
	private String comment;


	public Comment(String address, String sub, String comment) {
		this.address = address;
		this.sub = sub;
		this.comment = comment; 
	}

	// ゲッタとセッタ

	public String gettemp() {
		return address;
	}
	public String getlaytime() {
		return sub;
	}
	public String getprec() {
		return comment;
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
