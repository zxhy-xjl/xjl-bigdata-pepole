package com.zxhy.xjl.people;

import java.util.List;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
* @author  yangzaixiong
* 创建时间             2016年4月6日下午3:07:26
*/
public  class WebService {
	
public static String Send(List<IDInfo> idcardinfolist,String url,String ip,int port,String systemid,String key,String entryname,String alias,String idnumber,String name,int pageindex,int pagesize) throws Exception
{
	//String url = " http://10.45.6.134:8080/mdp/rs/retrieve";
	HttpClient client = new HttpClient();
		client.getState().setCredentials(
		             new AuthScope(ip, port),
		             new UsernamePasswordCredentials(systemid,SignatureHelper
		                                        .createSignature(systemid,key)));
	        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
	client.getParams().setAuthenticationPreemptive(true);
	GetMethod method = new GetMethod(url);
	NameValuePair pairs = new NameValuePair("xmlstring", "<root><params><entityName>"+entryname+"</entityName><alias>"+alias+"</alias>"
		+"<filter><![CDATA[IDENTITY_NO='"+idnumber+"' and POP_NAME='"+name+"']]></filter>"
		//+"<filter><![CDATA[IDENTITY_NO='"+idnumber+"']]></filter>"
		//+"<filter><![CDATA[POP_NAME='"+name+"']]></filter>"
		+"<pageIndex><![CDATA["+pageindex+"]]></pageIndex>"
		+"<pageSize><![CDATA["+pagesize+"]]></pageSize>"
		+"</params>"
		+"</root>");
	   method.setQueryString(new NameValuePair[]{pairs});
	   method.setDoAuthentication(true);
		client.executeMethod(method);
		String response = method.getResponseBodyAsString();
		if(response!=null&&!response.equals("")){
			 return XmlParse.Gaxml(response,idcardinfolist);
		}
		return "";
	
	

	}


	
}
