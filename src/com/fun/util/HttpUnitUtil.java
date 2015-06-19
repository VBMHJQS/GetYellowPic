package com.fun.util;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HttpUnitUtil {

	public static  String getHtml(String url){
		String str;
		//����һ��webclient
		WebClient webClient = new WebClient();
		//htmlunit ��css��javascript��֧�ֲ��ã�������ر�֮
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		//��ȡҳ��
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
		//��ȡָ��Ԫ��
		str = page.getWebResponse().getContentAsString();
		//�ر�webclient
		return str;

	}
}
