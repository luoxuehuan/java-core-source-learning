package com.java.util.hdutil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/*import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;*/

/**
 * 通过程序访问Http内容(主要利用IE的方式去获取内容)
 * 
 * @author lexonhnrd
 * @version 1.0
 */
public class HttpUtil {

	/**
	 * 根据URL地址获得对应的HTML内容
	 * 
	 * @param url
	 *            url地址
	 * @return html内容
	 */
	public static String getHtmlContent(String url) {
		return "";
	}
		/*DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);

		httpget.addHeader("User-Agent",
				"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");

		try {
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			InputStream is = entity.getContent();

			BufferedReader in = new BufferedReader(new InputStreamReader(is,
					"utf-8"));

			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}

			String html = buffer.toString();

			return html;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}*/
}