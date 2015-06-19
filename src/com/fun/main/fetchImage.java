package com.fun.main;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fun.bean.Mark;
import com.fun.util.HttpUnitUtil;

public class fetchImage {

	//http://www.henhenpa.com/html/tupian/toupaizipai/162280.html
	private final static String ADDRESS = "http://www.henhenpa.com";


	private final static String BASE = "F:\\Marks";

	public static void main(String[] args) {
		//http://www.henhenpa.com/html/tupian/toupaizipai/index_2.html
		String url = "http://www.henhenpa.com/html/tupian/toupaizipai/index_4.html";
		List<Mark> marks = putUrlInBean(url);
		System.out.println("�ɹ�������"+marks.size()+"�ļ�");
	}




	/**
	 * ����ÿ������Bean����
	 * @param url
	 * @return
	 */
	public static List<Mark> putUrlInBean(String url){
		List<Mark> marks = new ArrayList<Mark>();

		Document doc = getContent(url);
		String rex = "div.zxlist ul";
		Elements eles = doc.select(rex);
		for(Element ele : eles){
			Mark mark = new Mark();
			Elements ul = ele.select("li");
			mark.setTitle(ul.get(0).select("a").text());
			mark.setUrl(ADDRESS+ul.get(0).select("a").attr("href"));

			//��ȡÿ�������µ�����ͼƬ����
			mark.setSonUrls(putUrlInBeanList(ADDRESS+ul.get(0).select("a").attr("href")));
			try {
				downloadImag(mark);
			} catch (IOException e) {
				e.printStackTrace();
			}

			marks.add(mark);
		}
		return marks;
	}

	/**
	 * ��ȡÿ��ͼƬ���Ӽ���
	 * @param url
	 * @return
	 */
	private static List<String> putUrlInBeanList(String url) {
		List<String> urls = new ArrayList<String>();
		Document doc = getContent(url);

		String rex = "div.n_bd img";
		Elements eles = doc.select(rex);

		for(Element ele:eles){
			String imgUrl = ele.attr("src");
			if(imgUrl.equals("http://www.photcoco.com/i/01/2.jpg")){
				continue;
			}
			//��ӹ���
			urls.add(imgUrl);
		}
		return urls;
	}

	/**
	 * �ܾ����������ļ�
	 * @param mark
	 * @throws IOException 
	 */
	private static void downloadImag(Mark mark) throws IOException {
		System.out.println("------------------"+mark.getTitle()+"----------------------------");
		
		List<String> urls = mark.getSonUrls();
		
		File file = new File(BASE + "\\" + mark.getTitle());
		if(!file .exists() && !file .isDirectory()){
			 file.mkdir();
		}
		for(int i =0 ; i<urls.size();i++){
			System.out.println(urls.get(i));
			URL url = new URL(urls.get(i));
			//������������
			DataInputStream dis = new DataInputStream(url.openStream());
			String fileName = file.getAbsolutePath()+ "/" + i +".jpg";
			//����һ���µ��ļ�
			FileOutputStream fos = new FileOutputStream(new File(fileName));
			byte[] buffer = new byte[1024];
			int length;
			//��ʼ�������
			while((length = dis.read(buffer))>0){
				fos.write(buffer,0,length);
			}
			dis.close();
			fos.close();
		}
		
		System.out.println(mark.getTitle() +"�ļ��������");
		
		
		
	}

	/**
	 * ��ȡhtml����
	 * @param url
	 * @return
	 */
	private static Document getContent(String url) {
		String content = HttpUnitUtil.getHtml(url);
		Document doc = null;
		doc = Jsoup.parse(content);
		return doc;
	}



}
