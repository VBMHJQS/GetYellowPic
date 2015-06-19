package com.fun.util;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HttpUnitUtil {

	public static  String getHtml(String url){
		String str;
		//创建一个webclient
		WebClient webClient = new WebClient();
		//htmlunit 对css和javascript的支持不好，所以请关闭之
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		//获取页面
		HtmlPage page = null;
		try {
			page = webClient.getPage(url);
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			if(webClient!=null){
				webClient.closeAllWindows();
			}
		}
		//获取指定元素
		str = page.getWebResponse().getContentAsString();
		//关闭webclient
		return str;

	}
}
