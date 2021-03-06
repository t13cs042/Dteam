package Dataclass; //必要に応じて変える

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

// xxクラスでカインド（テーブル）を定義するための宣言
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Candidate {

	//ID

	// メンバ id の値はシステムが自動的に重複しないようつける
	@PrimaryKey
	private Long id;
	//@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	//private Long id;

	//登録日
	
	@Persistent
	private String date;
	
	
	//候補年１
	@Persistent
	private String candi1;

	//候補年２
	@Persistent 
	private String candi2;

	//候補年３
	@Persistent 
	private String candi3;


	public Candidate(Long id,String date, String candi1, String candi2, String candi3) {
		this.id     = id;
		this.date   = date;
		this.candi1 = candi1;
		this.candi2 = candi2;
		this.candi3 = candi3; 
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getCandi1() {
		return candi1;
	}


	public void setCandi1(String candi1) {
		this.candi1 = candi1;
	}


	public void setCandi2(String candi2) {
		this.candi2 = candi2;
	}


	public void setCandi3(String candi3) {
		this.candi3 = candi3;
	}

	
	public String getCandi2() {
		return candi2;
	}


	public String getCandi3() {
		return candi3;
	}

	
	
}

	