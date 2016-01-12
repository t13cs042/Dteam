package Dataclass; //必要に応じて変える

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

// xxクラスでカインド（テーブル）を定義するための宣言
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Candidate {

	//ID
	@PrimaryKey
	// メンバ id の値はシステムが自動的に重複しないようつける
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	//候補年１
	@Persistent 
	private String candi1;

	//候補年２
	@Persistent 
	private String candi2;

	//候補年３
	@Persistent 
	private String candi3;


	public Candidate(Long id, String candi1, String candi2, String candi3) {
		this.id = id;
		this.candi1 = candi1;
		this.candi2 = candi2;
		this.candi3 = candi3; 
	}


	public String getCandi1() {
		return candi1;
	}


	public void setCandi1(String candi1) {
		this.candi1 = candi1;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setCandi2(String candi2) {
		this.candi2 = candi2;
	}


	public void setCandi3(String candi3) {
		this.candi3 = candi3;
	}


	public Long getId() {
		return id;
	}


	public String getCandi2() {
		return candi2;
	}


	public String getCandi3() {
		return candi3;
	}

	
	
}

	