package memo;////////////パッケージする名前に変更する

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

// LoginDBクラスでカインド（テーブル）を定義するための宣言
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class LoginDB {

	// メンバ id はエンティティ（タプル）のキーである
	@PrimaryKey
	// メンバ id の値はシステムが自動的に重複しないようつける
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	// メンバ mail address をデータストアに書き込む
	@Persistent 
	private String mail;

	// メンバ password をデータストアに書き込む
	@Persistent 
	private String password;

	// メンバ area(作付面積)をデータストアに書き込む
	@Persistent 
	private int area;

	// メンバ start_month(収穫開始月) をデータストアに書き込む
	@Persistent 
	private int start_month;

	// メンバ finish_month(収穫終了月) をデータストアに書き込む
	@Persistent 
	private int finish_month;

	// メンバ question1(秘密の質問1) をデータストアに書き込む
	@Persistent 
	private String question1;

	// メンバ answer1(秘密の質問1の答え) をデータストアに書き込む
	@Persistent 
	private String answer1;

	// メンバ question2(秘密の質問2) をデータストアに書き込む
	@Persistent 
	private String question2;

	// メンバ answer2(秘密の質問2の答え) をデータストアに書き込む
	@Persistent 
	private String answer2;

	public LoginDB(String mail, String password, int area, int start_month,
			int finish_month, String question1, String answer1, String question2, String answer2) {
		this.mail = mail;
		this.password = password;
		this.area = area;
		this.start_month = start_month;
		this.finish_month = finish_month;
		this.question1 = question1;
		this.answer1 = answer1;
		this.question2 = question2;
		this.answer2 = answer2;
	}

	// ゲッタとセッタ
	public Long getId() {
		return id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getStart_month() {
		return start_month;
	}
	public void setStart_month(int start_month) {
		this.start_month = start_month;
	}
	public int getFinish_month() {
		return finish_month;
	}
	public void setFinish_month(int finish_month) {
		this.finish_month = finish_month;
	}
	public String getQuestion1() {
		return question1;
	}
	public void setQuestion1(String question1) {
		this.question1 = question1;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getQuestion2() {
		return question2;
	}
	public void setQuestion2(String question2) {
		this.question2 = question2;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
}
