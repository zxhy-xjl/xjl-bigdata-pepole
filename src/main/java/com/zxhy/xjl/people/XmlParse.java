package com.zxhy.xjl.people;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
* @author  yangzaixiong
* 创建时间             2016年4月6日下午3:07:37
*/
public class XmlParse {
public static String Gaxml(String str,List<IDInfo> idcardinfolist) throws ParserConfigurationException, SAXException, IOException
{
	if(str!=null)
	{
		str=str.replaceFirst("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
	}
	String result = "";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder db = dbf.newDocumentBuilder(); 
	IDInfo idcardinfo = null;
			ByteArrayInputStream bin = new ByteArrayInputStream(str.getBytes("UTF-8"));
			Document document = db.parse(bin);
			NodeList statusnodelist =document.getElementsByTagName("status");
			{
				if(statusnodelist!=null)
				{
					result = statusnodelist.item(0).getTextContent();
				}
			}
			NodeList nList = document.getElementsByTagName("record");
			if(nList!=null)
			{
				
				Node node = null;
				NodeList fieldsList =null;
				Node fieldnode = null;
				NodeList fieldnodeList = null;
				for(int i =0;i<nList.getLength();i++)
				{
					node = nList.item(i);
					fieldsList = node.getChildNodes();
					if(fieldsList!=null)
					{
						for(int j=0;j<fieldsList.getLength();j++)
						{
							if(fieldsList.item(j).getNodeName()!=null&&fieldsList.item(j).getNodeName().equals("fields"))
							{
								fieldnodeList = fieldsList.item(j).getChildNodes();
								if(fieldnodeList!=null)
								{
									idcardinfo = new IDInfo();
									idcardinfolist.add(idcardinfo);
									for(int k=0;k<fieldnodeList.getLength();k++)
									{
										fieldnode = fieldnodeList.item(k);
										if(fieldnode.getNodeName()!=null)
										{
											if(fieldnode.getNodeName().equals("pop_name"))
											{
												idcardinfo.setIdName(fieldnode.getTextContent());
											}
											else if(fieldnode.getNodeName().equals("identity_no"))
											{
												idcardinfo.setIdCode(fieldnode.getTextContent());
											}
											else if(fieldnode.getNodeName().equals("certificate_type"))
											{
												
											}
											else if(fieldnode.getNodeName().equals("certificate_no"))
											{
												
											}
											else if(fieldnode.getNodeName().equals("sex_content"))
											{
												idcardinfo.setSex(fieldnode.getTextContent());
											}
											else if(fieldnode.getNodeName().equals("nation_content"))
											{
												idcardinfo.setNation(fieldnode.getTextContent());
											}
											else if(fieldnode.getNodeName().equals("birthday"))
											{
												idcardinfo.setBirthday(fieldnode.getTextContent());
											}
											else if(fieldnode.getNodeName().equals("address"))
											{
												idcardinfo.setAddress(fieldnode.getTextContent());
											}
											else if(fieldnode.getNodeName().equals("photo"))
											{
												idcardinfo.setPhoto(fieldnode.getTextContent());
											}
										}
									}
										
								}
								
							}
						}
					}
				}
			}
			return result;
	}
}
