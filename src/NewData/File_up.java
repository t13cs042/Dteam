package NewData;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import Dataclass.Climate;
import Dataclass.PMF;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@SuppressWarnings("serial")
public class File_up extends HttpServlet {

	// インスタンス保持用
	// このためクローズ処理を書いていないのだが
	// いいのだろうか？
	//private PersistenceManager pm;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ServletFileUpload fileUpload = new ServletFileUpload();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		


		try {
			FileItemIterator itemIterator = fileUpload.getItemIterator(req);
			while (itemIterator.hasNext()) {
				FileItemStream itemStream = itemIterator.next();
				InputStream inputStream = itemStream.openStream();
				String contentType = itemStream.getContentType();
				if (contentType == null) {
					contentType = "";
				}
				// 画像ファイルの時はそのまま書き出し処理
				if (contentType.contains("image")) {
					resp.setContentType(contentType);
					BufferedInputStream bi = new BufferedInputStream(
							inputStream);
					int len;
					while ((len = bi.read()) != -1) {
						resp.getOutputStream().write(len);
					}
					// テキストファイルの場合またはファイル名の拡張子が".csv"の場合
				} else if (contentType.contains("text")
						|| itemStream.getName().endsWith(".csv")) {
					resp.setContentType("text/html");
					BufferedReader buffer = new BufferedReader(
							new InputStreamReader(inputStream, "UTF-8"));
					String line = null;
					int i = 0;


					while ((line = buffer.readLine()) != null) {
						String[] split = line.split(",");

						//学生登録
						if (split != null && split.length != 0) {
							// csvから単語の取り出し
							String date = split[0].trim();
							String temp = split[1].trim();
							String laytime = split[2].trim();
							String prec = split[3].trim();
							
							if (split != null && split.length != 0) {

								// 登録するモデル
								Climate per = new Climate(date,Double.parseDouble(temp), Double.parseDouble(laytime), Double.parseDouble(prec));

								pm.makePersistent(per);
								i++;

							}
						}
					}
					resp.setCharacterEncoding("UTF-8");
					resp.getWriter().write(i + "件登録しました。");

				} else {
					resp.setContentType("text/html");
					resp.setCharacterEncoding("UTF-8");
					resp.getWriter().write("ファイルを認識できません。");

				}
			}
		} catch (FileUploadException e) {
			resp.sendError(500);
		}finally{
			pm.close();
		}
	}
}

