package home;

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

// Memoクラスでカインド（テーブル）を定義するための宣言
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Memo {

	// メンバ id はエンティティ（タプル）のキーである
	@PrimaryKey
	// メンバ id の値はシステムが自動的に重複しないようつける
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

	// メンバ content をデータストアに書き込む
    @Persistent 
    private String content;
    
    // メンバ date をデータストアに書き込む
    @Persistent 
    private Date date;
    
 // メンバ name をデータストアに書き込む
    @Persistent 
    private String name;  //名前
    

    public Memo(String content, Date date,String name) {
        this.content = content;
        this.date = date;
        this.name = name;
    }

    // ゲッタとセッタ
    public Long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public Date getDate() {
        return date;
    }
    public String getName() {
        return name;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
