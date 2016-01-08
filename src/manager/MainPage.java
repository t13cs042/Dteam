package manager;

import Dataclass.Memo;
import Dataclass.PMF;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import java.text.MessageFormat;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class MainPage extends HttpServlet {

	// ページの先頭部分
    private static final String head = 
    		//説明文
          "<html>\n" 
          +  "<head>\n"
          +  "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n"
          +  " <title>管理者画面</title>\n"
          +  " </head>"
        + "<body>\n"

      +  "<div Align=\"center\"/>\n"
      +  "<br>\n"
      + "<a href=\"http://c-.appspot.com/\">未認証アカウント</a><br><br>\n"
      + "<a href=\"http://c-.appspot.com/\">アカウント管理</a><br><br>\n"
      + "<a href=\"http://home-1157.appspot.com/Home/File_up.jsp\">気象情報のアップロード</a><br><br>\n"
      + "<a href=\"http://c-.appspot.com/\">予測の実行</a><br><br>\n"
      + "<a href=\"http://c-.appspot.com/\">ログアウト</a><br><br>\n"
      + " <br> <span style=\"font-size: 100%\">予測の最終実行日時：X月X日　XX：XX</span>\n"
      +	"</div>\n";
      
    // メモのテンプレート
    private static final String memoTmpl = 
          "  <div>\n"
        + "    <span class=\"date\"> {0,time} {0,date} </span> \n"
        + "    <pre>{1}</pre> \n"
        + "  </div> \n";
    
    // ページの最後
    private static final String tail =      
          "  </body> \n" + "</html>\n";

    // タイムゾーンを JST に設定
    static {
        TimeZone.setDefault(TimeZone.getTimeZone("JST"));
    }
    
    private String render(List<Memo> memos) {
        StringBuffer sb = new StringBuffer();
        sb.append(head);
        for (Memo memo : memos)
            sb.append(MessageFormat.format(memoTmpl, memo.getDate(), memo
                    .getContent()));
        sb.append(tail);
        return sb.toString();
    }

    // doGetメソッド
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        PersistenceManager pm = null;
        try {
            pm = PMF.get().getPersistenceManager();
            Query query = pm.newQuery(Memo.class);
            query.setOrdering("date desc");

            List<Memo> memos = (List<Memo>) query.execute();

            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            resp.getWriter().print(render(memos));
        } finally {
            if (pm != null && !pm.isClosed())
                pm.close();
        }
    }
}
