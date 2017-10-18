package method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetMethod {
	/*
	 * 以GET方式获取网页代码
	 */
	public String sendGet(String url, String encoding){
		String result = "";
		String noResult = "";
		BufferedReader in = null;
		try {
			System.out.println("...开始获取目标网页HTML...");
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.connect();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),encoding));
			String line;
			while((line = in.readLine()) != null){
				result += line;
			}
//			if(result != ""){
//				System.out.println("...获取完毕...");
//			}else{
//				GetFromUrl get = new GetFromUrl();
//				noResult = get.sendGet(url, encoding);
//				if(noResult != null){
//					
//				}
//				System.out.println("...未获取到...");
//			}
			if(result != ""){
					System.out.println("...获取完毕...");
			}else{
				GetMethod get = new GetMethod();
				noResult = get.sendGet(url, encoding);
				//若请求不到网址则循环多次请求
				if(noResult == ""){
					for(int i = 1; i <= 10; i++){	
						noResult = get.sendGet(url, encoding);
						while(noResult != ""){
							result = noResult;
							break;
						}
					}
					System.out.println("...真的！未获取到！...");
				}else{
					result = noResult;
					
				}
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("网址格式?");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("网址连接未成功...");
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("释放内存失败...");
				}
			}
		}
		return result;
	}
}
