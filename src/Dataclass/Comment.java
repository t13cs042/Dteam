package Dataclass; //必要に応じて変える

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
	private Long id;
	
	@Persistent 
	private String address;

	//件名
	@Persistent 
	private String sub;

	//要望
	@Persistent 
	private String comment;
	
	@Persistent 
	private String time;

	public Comment(String address, String sub, String comment, String date) {
		this.address = address;
		this.sub = sub;
		this.comment = comment;
		this.time = date;
	}

	// ゲッタとセッタ

	public String getTime() {
		return time;
	}
	public void setTime(String date) {
		this.time = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getId() {
		return id;
	}


}
