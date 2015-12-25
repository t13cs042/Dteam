package signup_;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

// データストアを利用するためのヘルパクラス
// 永続化マネージャを管理
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");
    private PMF() {}//コンストラクタがprivate　インSタンスは作れない
    public static PersistenceManagerFactory get() {
        return pmfInstance;//データストアを扱うサーブレットから呼ばれるメソッド
    }
}

