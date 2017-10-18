package main;

import method.GetMethod;

public class MainTest {
	public static void main(String[] args){
		GetMethod getHtml = new GetMethod();
		String url = "https://www.baidu.com/";
		String encoding = "utf-8";
		String html = getHtml.sendGet(url, encoding);
		System.out.println(html);
	}
}
