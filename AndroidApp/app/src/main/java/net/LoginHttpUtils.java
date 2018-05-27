package net;

import android.os.Handler;
import android.os.Message;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import utils.StreamUtils;


public class LoginHttpUtils {

	
	public static void requestNetForGetLogin(final Handler handler ,final String username, final String password) {


		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				
				//使用HttpClient请求服务器将用户密码发送服务器验证
				try{
				String path = "http://192.168.13.83:8080/itheima74/servlet/LoginServlet?username="+URLEncoder.encode(username,"utf-8")+"&pwd="+URLEncoder.encode(password,"utf-8");
				//1.创建一个httpClient对象
				HttpClient httpclient = new DefaultHttpClient();

				//2.设置请求的方式
				HttpGet httpget = new HttpGet(path);
				//3.执行一个http请求
				HttpResponse response = httpclient.execute(httpget);
				//4.获取请求的状态码，
				StatusLine statusLine = response.getStatusLine();
				int code = statusLine.getStatusCode();
				
				//5.判断状态码后获取内容
				if(code == 200){
					HttpEntity entity = response.getEntity();//获取实体内容，中封装的有http请求返回的流信息
					InputStream inputStream = entity.getContent();
					//将流信息转换成字符串
					String result = StreamUtils.streamToString(inputStream);
					
					Message msg = Message.obtain();
					msg.what = 1;
					msg.obj = result;
					handler.sendMessage(msg);
				}
				
				}catch (Exception e) {
					e.printStackTrace();
				}

				
			}
		}).start();

		


	}
	
	public static void requestNetForPostLogin(final Handler handler ,final String username, final String password) {


		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				//使用UrlConncetion请求服务器将用户密码发送服务器验证
				try{
						String path = "http://bus.2500.tv/api_line_status.php";
						//1.创建一个httpclient对象
						HttpClient httpclient = new DefaultHttpClient();
						//2.创建一个请求方式
						HttpPost httppost = new HttpPost(path);
						//创建集合封装数据
						ArrayList<BasicNameValuePair> arrayList = new ArrayList<BasicNameValuePair>();
						BasicNameValuePair nameValuePair = new BasicNameValuePair("lineID","10000520");
						arrayList.add(nameValuePair);
//						BasicNameValuePair nameValuePair1 = new BasicNameValuePair("pwd",password);
//						arrayList.add(nameValuePair1);
						
						//创建一个Entity
						UrlEncodedFormEntity entity = new UrlEncodedFormEntity(arrayList, "utf-8");
						//设置请求时的内容
						httppost.setEntity(entity);
						
						//3.执行一个请求,返回一个response对象
						HttpResponse response = httpclient.execute(httppost);
						//4.获取状态码
						int code = response.getStatusLine().getStatusCode();
						//5.判断并获取内容
						if(code == 200){
							HttpEntity entity1 = response.getEntity();//获取实体内容，中封装的有http请求返回的流信息
							InputStream inputStream = entity1.getContent();
							//将流信息转换成字符串
							String result = StreamUtils.streamToString(inputStream);
							Message msg = Message.obtain();
							msg.what = 2;
							msg.obj = result;
							handler.sendMessage(msg);
						}

				}catch (Exception e) {
					e.printStackTrace();
				}

				
				
			}
		}).start();

	}
	
}
