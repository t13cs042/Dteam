package home;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import java.text.MessageFormat;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

import Dataclass.Memo;
import Dataclass.PMF;

@SuppressWarnings("serial")
public class CommentPage extends HttpServlet {

	// ページの先頭部分
    private static final String head =     
          "<html>\n" 
        + "<body>\n"
        + " <span style=\"font-size: 200%\">ご要望</span>\n"
        + "  <form action=\"/newcomment\" method=\"post\">\n"
        + "    <div>件名\n"
        + "      <textarea name=\"sub\" rows=\"1\" cols=\"40\" ></textarea>\n"
        + "    </div>ご意見\n"
        //+ "        <td><a href=\"SubPage\">kensaku</a></td> "
        + "      <textarea name=\"content\" rows=\"5\" cols=\"40\" ></textarea>"
        + "    </div>\n"
        + "    <input type=\"submit\" value=\"Submit\" />\n"
        + "  </form>\n";     
    
    // メモのテンプレート
    private static final String memoTmpl = 
          "  <div>\n"
        + "    <span class=\"date\"> {0,time} {0,date} </span> \n"
        + "    <pre>name {2}</pre> \n"
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
                   .getContent(), memo.getName()));
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
