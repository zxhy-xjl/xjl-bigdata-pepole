package com.zxhy.xjl.people;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
* @author  yangzaixiong
* 创建时间             2016年4月6日下午3:06:34
*/
@Service
public class PeopleServiceImpl implements PeopleService {
	private static Log log = LogFactory.getLog(PeopleServiceImpl.class);
	private String peopleURL;
	private String ip;
	private int port;
	private String gaidCardSystemId;
	private String gaidCardKey;
	private String gaidCardEntryNamem;
	private String gaidCardAliasm;
	private String gaidCardAliasp;
    private String gaidCardEntryNamep;
	public String getPeopleURL() {
		return peopleURL;
	}

	public void setPeopleURL(String peopleURL) {
		this.peopleURL = peopleURL;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	

	public String getGaidCardSystemId() {
		return gaidCardSystemId;
	}

	public void setGaidCardSystemId(String gaidCardSystemId) {
		this.gaidCardSystemId = gaidCardSystemId;
	}

	public String getGaidCardKey() {
		return gaidCardKey;
	}

	public void setGaidCardKey(String gaidCardKey) {
		this.gaidCardKey = gaidCardKey;
	}

	public String getGaidCardEntryNamem() {
		return gaidCardEntryNamem;
	}

	public void setGaidCardEntryNamem(String gaidCardEntryNamem) {
		this.gaidCardEntryNamem = gaidCardEntryNamem;
	}

	public String getGaidCardAliasm() {
		return gaidCardAliasm;
	}

	public void setGaidCardAliasm(String gaidCardAliasm) {
		this.gaidCardAliasm = gaidCardAliasm;
	}
	
	public String getGaidCardAliasp() {
		return gaidCardAliasp;
	}

	public void setGaidCardAliasp(String gaidCardAliasp) {
		this.gaidCardAliasp = gaidCardAliasp;
	}

	public String getGaidCardEntryNamep() {
		return gaidCardEntryNamep;
	}

	public void setGaidCardEntryNamep(String gaidCardEntryNamep) {
		this.gaidCardEntryNamep = gaidCardEntryNamep;
	}

	public boolean checkID(String idCode, String idName) {
		log.debug("验证身份证信息idCode:" + idCode + " idName:" + idName);
		String isSuccess = null;
		IDInfo gaCard;
		List<IDInfo> gaidcardinfolist=new ArrayList<IDInfo>();
		try {
			//地址改成配置文件，从地址中获取ip和端口，这个不要用在配置一遍
			//todo 杨再兄
				   isSuccess = WebService.Send(gaidcardinfolist,
					this.peopleURL, 
					this.ip, 
					Integer.valueOf(this.port), 
					this.gaidCardSystemId, 
					this.gaidCardKey,
					this.gaidCardEntryNamem,
					this.gaidCardAliasm,
					idCode,
					idName, 
					1, 1);
			
		} catch (Exception e) {
			    log.debug("调用大数据接口异常 ");
				throw new RuntimeException(e);
		}
		if("success".equals(isSuccess)) {
			if (gaidcardinfolist.size() > 0){
				 gaCard=gaidcardinfolist.get(0);
				 if(idName.equals(gaCard.getIdName().trim())){
					log.debug("验证成功");
					return true;
				} else {
					log.debug("返回的名称不匹配" + gaCard.getIdName());
				}
			} else {
				log.debug("返回结果为成功，但是没有数据集合");
			}
		} else {
			log.debug("返回结果为失败");
		}
		log.debug("没有找到相对应的");
		return false;

		
	}

	public IDInfo getIDInfo(String idCode, String idName) {
		log.debug("获取身份证信息idCode:" + idCode + " idName:" + idName);
		String isSuccess = null;
		IDInfo gaCard=null;
		List<IDInfo> gaidcardinfolist=new ArrayList<IDInfo>();
		
		try {
			  
			  isSuccess = WebService.Send(gaidcardinfolist,
					    this.peopleURL, 
						this.ip, 
						Integer.valueOf(this.port), 
						this.gaidCardSystemId, 
						this.gaidCardKey,
						this.gaidCardEntryNamem,
						this.gaidCardAliasm,
						 idCode,
						 idName, 
						 1, 1);
			} catch (Exception e) {
				log.debug("调用大数据接口异常 ");
				throw new RuntimeException(e);
		}
		
		
		if("success".equals(isSuccess))
		{   
			if(gaidcardinfolist.size()>0){
			    gaCard=gaidcardinfolist.get(0);
			    for (IDInfo gaIdCardInfo : gaidcardinfolist) {
				   if(gaIdCardInfo.getIdName()!=null&&idName!=null&&gaIdCardInfo.getIdName().equals(idName))
				    {
					gaCard = gaIdCardInfo;
				    }
			     }
			    log.debug("返回此人的信息 ");
			    return gaCard;
			}else{
				 log.debug("没有此人的信息 ");	
				 return null;
			}
				
			}
		log.debug("没有找到相对应的人员信息");
		return null;
		
		
	}
	/**
	 * 获取身份证照片
	 * @param idCode 身份证号码
	 * @param idName 姓名
	 * @return 身份证照片字节码
	 * @throws 调用大数据接口异常
	 */
	public byte[] getIDPhoto(String idCode, String idName)  {
		String isSuccess = null;
		IDInfo gaCard=null;
		List<IDInfo> gaidcardinfolist=new ArrayList<IDInfo>();
		try {
			isSuccess = WebService.Send(gaidcardinfolist,
					   this.peopleURL, 
						this.ip, 
						Integer.valueOf(this.port), 
						this.gaidCardSystemId, 
						this.gaidCardKey,
						this.gaidCardEntryNamep,
						this.gaidCardAliasp,
					idCode,idName, 1, 1);
		} catch (Exception e) {
		    log.debug("调用大数据接口异常 ");
			throw new RuntimeException(e);
		}
		if("success".equals(isSuccess)){
			if(gaidcardinfolist.size()!=0){
				gaCard=gaidcardinfolist.get(0);
				String photo=gaCard.getPhoto();
				return Base64.decodeBase64(photo);//将String转化成Base64
			} else {
				return null;
			}
		} else{
			return null;
		}
	}

}
