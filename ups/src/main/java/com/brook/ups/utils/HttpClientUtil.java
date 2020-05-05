package com.brook.ups.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * httpclient工具类
 * @author		姜宝俊
 * @Group 		技术组
 * @Worker	 	1861 
 * @date 		2018年10月22日 上午9:38:25
 */
public class HttpClientUtil {
	private RequestConfig requestConfig = RequestConfig.custom()  
            .setSocketTimeout(15000)  
            .setConnectTimeout(15000)  
            .setConnectionRequestTimeout(15000)  
            .build();  
      
    private static HttpClientUtil instance = null;  
    private HttpClientUtil(){}
    
    /**
     * 获得httpclient工具对象（单例）
     * @author		姜宝俊
     * @date 		2018年10月22日 上午9:38:49
     */
    public static HttpClientUtil getInstance(){  
        if(instance==null){
			synchronized(HttpClientUtil.class){
				if(instance==null)
					instance = new HttpClientUtil();
			}
		}
		return instance;
    }  
      
    /** 
     * 发送 post请求 
     * @param httpUrl 地址 
     */  
    public String sendHttpPost(String httpUrl) {  
        HttpPost httpPost = new HttpPost(httpUrl);
        return sendHttpPost(httpPost);  
    }  
      
    /** 
     * 发送 post请求 
     * @param httpUrl 地址 
     * @param params 参数(格式:key1=value1&key2=value2) 
     */  
    public String sendHttpPost(String httpUrl, String params) {  
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
        try {  
            //设置参数  
            StringEntity stringEntity = new StringEntity(params, "UTF-8");  
            stringEntity.setContentType("application/x-www-form-urlencoded");  
            httpPost.setEntity(stringEntity);  
        } catch (Exception e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);
        }  
        return sendHttpPost(httpPost);  
    }  
      
    /** 
     * 发送 post请求 
     * @param httpUrl 地址 
     * @param maps 参数 
     */  
    public String sendHttpPost(String httpUrl, Map<String, String> maps) {  
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
        // 创建参数队列    
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
        for (String key : maps.keySet()) {  
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));  
        }  
        try {  
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));  
        } catch (Exception e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);
        }  
        return sendHttpPost(httpPost);  
    }
    
    /** 
     * 发送 post请求 ,下载文件，返回文件流
     * @param httpUrl 地址 
     * @param maps 参数 
     */  
    public InputStream sendHttpPostDownloadStream(String httpUrl, Map<String, String> maps) {
    	CloseableHttpClient httpClient = null;  
    	CloseableHttpResponse response = null;  
    	HttpEntity entity = null;  
    	InputStream inputStream = null;  
    	try {  
    		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
    		// 创建参数队列    
    		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
    		for (String key : maps.keySet()) {
    			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));  
    		}
    		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));  
    		// 创建默认的httpClient实例.  
    		httpClient = HttpClients.createDefault();  
    		httpPost.setConfig(requestConfig);  
    		// 执行请求  
    		response = httpClient.execute(httpPost);  
    		entity = response.getEntity();  
    		inputStream = entity.getContent();
    		//是否返回文件
    		if(isFileByContentType(response)) {
    			return inputStream;
    		}else {
    			return null;
    		}
    	} catch (Exception e) {  
    		e.printStackTrace(); 
    		throw new RuntimeException(e);
    	}
    }
    /** 
     * 发送 post请求 ,下载文件
     * @param httpUrl 地址 
     * @param maps 参数 
     */  
    public String sendHttpPostDownload(String httpUrl, Map<String, String> maps) {
    	String result = "";
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        InputStream inputStream = null;  
        try {  
        	HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
        	// 创建参数队列    
        	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
        	for (String key : maps.keySet()) {
        		nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));  
        	}
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));  
            // 创建默认的httpClient实例.  
            httpClient = HttpClients.createDefault();  
            httpPost.setConfig(requestConfig);  
            // 执行请求  
            response = httpClient.execute(httpPost);  
            entity = response.getEntity();  
            inputStream = entity.getContent();
            //是否返回文件
            if(isFileByContentType(response)) {
            	String localSavePath = maps.get("localSavePath"); //本地存储路径
           	 	String localSaveName = maps.get("localSaveName"); //本地存储文件名
           	 	if(localSavePath==null || "".equals(localSavePath)) {
           	 		localSavePath = File.separator;
           	 	}
           	 	if(localSaveName==null || "".equals(localSaveName)) {
           	 		localSaveName = getFileName(response);
           	 	}
                if(localSaveName==null || "".equals(localSaveName)) {
                	localSaveName = "unknow";
           	 	}
                //本地保存路径，不存在创建
                File path = new File(localSavePath);
                if (!path.exists()) {
                	path.mkdirs();
				}
                FileOutputStream fileout = new FileOutputStream(new File(localSavePath+File.separator+localSaveName));  
                /** 
                 * 根据实际运行效果 设置缓冲区大小 
                 */  
                byte[] buffer=new byte[2048];  
                int ch = 0;  
                while ((ch = inputStream.read(buffer)) != -1) {  
                    fileout.write(buffer,0,ch);  
                }  
                inputStream.close();
                fileout.flush();  
                fileout.close();
                result = "{result:\"success\"}";
            }else {
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                 String line;
                 StringBuffer jsonString = new StringBuffer();
                 while((line = bufferedReader.readLine()) != null) {
                     jsonString.append(line);
                 }
                 String message = jsonString.toString();
                 result = "{result:\"fail\",reason:\""+message+"\"}";
                 
            }
        } catch (Exception e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);
        } finally {  
            try {  
                // 关闭连接,释放资源  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
        return result;
    }
      
      
    /** 
     * 发送 post请求（带File文件） 
     * @param httpUrl 地址 
     * @param maps 参数 
     * @param fileParName 文件参数名称
     * @param fileLists 文件信息
     */  
    public String sendHttpPostWithFile(String httpUrl, Map<String, String> maps, String fileParName, List<File> fileLists) {  
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		ContentType strContent = ContentType.create("text/plain", Charset.forName("UTF-8"));
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		meBuilder.setMode(HttpMultipartMode.RFC6532);
		for (String key : maps.keySet()) {
			meBuilder.addTextBody(key, maps.get(key), strContent);
		}
		for (File file : fileLists) {
			FileBody fileBody = new FileBody(file);
			meBuilder.addPart(fileParName, fileBody);
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		return sendHttpPost(httpPost);
	}
    
    /**
     * 发送 post请求（带Stream文件） 
     * @param httpUrl 地址 
     * @param maps 参数 
     * @param fileParName 文件参数名称
     * @param fileLists 文件信息[{name:文件名,stream:文件流}]
     * @author		姜宝俊
     * @date 		2018年10月16日 下午4:15:33
     */
    public String sendHttpPostWithStream(String httpUrl, Map<String, String> maps, String fileParName, List<Map<String,Object>> fileLists) {  
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        ContentType strContent=ContentType.create("text/plain",Charset.forName("UTF-8"));
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();  
        meBuilder.setMode(HttpMultipartMode.RFC6532);
        for (String key : maps.keySet()) {  
            meBuilder.addTextBody(key, maps.get(key), strContent);
        } 
        for(Map<String,Object> map : fileLists) {
        	String name = (String) map.get("name");
        	InputStream inputStream = (InputStream) map.get("stream");
        	if(name!=null && !"".equals(name)) {
        		meBuilder.addBinaryBody(fileParName, inputStream, ContentType.create("multipart/form-data"), name);
        	}
        }
        HttpEntity reqEntity = meBuilder.build();  
        httpPost.setEntity(reqEntity);  
        return sendHttpPost(httpPost);  
    }
    
      
    /** 
     * 发送Post请求，返回文本结果
     * @return 
     */  
    private String sendHttpPost(HttpPost httpPost) {  
    	CloseableHttpClient httpClient = null;  
    	CloseableHttpResponse response = null;  
    	HttpEntity entity = null;  
    	String responseContent = null;  
    	try {  
    		// 创建默认的httpClient实例.  
    		httpClient = HttpClients.createDefault();  
    		httpPost.setConfig(requestConfig);  
    		// 执行请求  
    		response = httpClient.execute(httpPost);  
    		entity = response.getEntity();  
    		responseContent = EntityUtils.toString(entity, "UTF-8");  
    	} catch (Exception e) {  
    		e.printStackTrace(); 
    		throw new RuntimeException(e);
    	} finally {  
    		try {  
    			// 关闭连接,释放资源  
    			if (response != null) {  
    				response.close();  
    			}  
    			if (httpClient != null) {  
    				httpClient.close();  
    			}  
    		} catch (IOException e) {  
    			e.printStackTrace();  
    			throw new RuntimeException(e);
    		}  
    	}  
    	return responseContent;  
    }
    
    /** 
     * 发送 get请求 
     * @param httpUrl 
     */  
    public String sendHttpGet(String httpUrl) {  
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求  
        return sendHttpGet(httpGet);  
    }  
      
    /** 
     * 发送 get请求Https 
     * @param httpUrl 
     */  
    public String sendHttpsGet(String httpUrl) {  
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求  
        return sendHttpsGet(httpGet);  
    }  
      
    /** 
     * 发送Get请求 
     * @param httpPost 
     * @return 
     */  
    private String sendHttpGet(HttpGet httpGet) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        String responseContent = null;  
        try {  
            // 创建默认的httpClient实例.  
            httpClient = HttpClients.createDefault();  
            httpGet.setConfig(requestConfig);  
            // 执行请求  
            response = httpClient.execute(httpGet);  
            entity = response.getEntity();  
            responseContent = EntityUtils.toString(entity, "UTF-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);
        } finally {  
            try {  
                // 关闭连接,释放资源  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);
            }  
        }  
        return responseContent;  
    }  
      
    /** 
     * 发送Get请求Https 
     * @param httpPost 
     * @return 
     */  
    private String sendHttpsGet(HttpGet httpGet) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        String responseContent = null;  
        try {  
            // 创建默认的httpClient实例.  
            PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL(httpGet.getURI().toString()));  
            DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);  
            httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();  
            httpGet.setConfig(requestConfig);  
            // 执行请求  
            response = httpClient.execute(httpGet);  
            entity = response.getEntity();  
            responseContent = EntityUtils.toString(entity, "UTF-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);
        } finally {  
            try {  
                // 关闭连接,释放资源  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace(); 
                throw new RuntimeException(e);
            }  
        }  
        return responseContent;  
    }
    
    /** 
     * 获取response header中Content-Disposition中的filename值 
     */  
    private static String getFileName(HttpResponse response) {  
    	Header contentHeader = response.getFirstHeader("Content-Disposition");  
    	String filename = null;  
    	if (contentHeader != null) {  
    		HeaderElement[] values = contentHeader.getElements();  
    		if (values.length == 1) {  
    			NameValuePair param = values[0].getParameterByName("filename");  
    			if (param != null) {  
    				try {  
//    					filename = param.getValue();  
//    					filename=URLDecoder.decode(param.getValue(),"utf-8");  
    					filename = new String(param.getValue().toString().getBytes("ISO-8859-1"), "utf-8");  
    				} catch (Exception e) {  
    					e.printStackTrace();  
    				}  
    			}  
    		}  
    	}  
    	return filename;  
    }
    
    /** 
     * 根据content-type判断，是否为文件流（octet-stream）
     */  
    private static Boolean isFileByContentType(HttpResponse response) {  
        Header contentHeader = response.getFirstHeader("content-type");  
        if (contentHeader != null) {  
           HeaderElement[] values = contentHeader.getElements();
           if(values!=null) {
        	   for(HeaderElement element:values) {
        		   if(element.getName().indexOf("octet-stream")>0) {
        			   return true;
        		   }
        	   }
           }
        }  
        return false;  
    } 
}
