package test;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
public class getImage {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		String imageUrl = "http://ww1.sinaimg.cn/large/005yyi5Jjw1encsi3zwucj30lv0t6452.jpg";
		File file = new File("F:\\Marks\\test");
		if(!file .exists() && !file .isDirectory()){
			 file.mkdir();
		}
		URL url = new URL(imageUrl);
		//打开网络输入流
		DataInputStream dis = new DataInputStream(url.openStream());
		String newImageName=file.getAbsolutePath()+ "/1.jpg";
		//建立一个新的文件
		FileOutputStream fos = new FileOutputStream(new File(newImageName));
				byte[] buffer = new byte[1024];
		int length;
		//开始填充数据
		while((length = dis.read(buffer))>0){
			fos.write(buffer,0,length);
		}
		dis.close();
		fos.close();
	}
}
