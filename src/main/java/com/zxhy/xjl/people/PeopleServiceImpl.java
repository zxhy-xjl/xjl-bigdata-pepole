package com.zxhy.xjl.people;
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
	public boolean checkID(String idCode, String idName) {
		log.debug("验证身份证信息idCode:" + idCode + " idName:" + idName);
		String isSuccess = null;
		IDInfo gaCard;
		List<IDInfo> gaidcardinfolist=new ArrayList<IDInfo>();
		try {
				  isSuccess = WebService.Send(gaidcardinfolist,
					"http://172.25.1.26:8089/rs/retrieve", 
					"172.25.1.26", 
					8089, 
					"YCZWXT", 
					"MIIBSwIBADCCASwGByqGSM44BAEwggEfAoGBAP1_U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq_xfW6MPbLm1Vs14E7gB00b_JmYLdrmVClpJ-f6AR7ECLCT7up1_63xhv4O1fnxqimFQ8E-4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC_BYHPUCgYEA9-GghdabPd7LvKtcNrhXuXmUr7v6OuqC-VdMCz0HgmdRWVeOutRZT-ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN_C_ohNWLx-2J6ASQ7zKTxvqhRkImog9_hWuWfBpKLZl6Ae1UlZAFMO_7PSSoEFgIUZb_PF1KCz5sIdT-VANuLWmMfqes",
					"SFZXX",
					"\u8EAB\u4EFD\u8BC1\u4FE1\u606F",
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
						"http://172.25.1.26:8089/rs/retrieve", 
						"172.25.1.26", 
						8089, 
						"YCZWXT", 
						"MIIBSwIBADCCASwGByqGSM44BAEwggEfAoGBAP1_U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq_xfW6MPbLm1Vs14E7gB00b_JmYLdrmVClpJ-f6AR7ECLCT7up1_63xhv4O1fnxqimFQ8E-4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC_BYHPUCgYEA9-GghdabPd7LvKtcNrhXuXmUr7v6OuqC-VdMCz0HgmdRWVeOutRZT-ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN_C_ohNWLx-2J6ASQ7zKTxvqhRkImog9_hWuWfBpKLZl6Ae1UlZAFMO_7PSSoEFgIUZb_PF1KCz5sIdT-VANuLWmMfqes",
						"SFZXX",
						"\u8EAB\u4EFD\u8BC1\u4FE1\u606F",
						 idCode,idName, 1, 1);	
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
					"http://172.25.1.26:8089/rs/retrieve", 
					"172.25.1.26", 
					8089, 
					"YCZWXT", 
					"MIIBSwIBADCCASwGByqGSM44BAEwggEfAoGBAP1_U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq_xfW6MPbLm1Vs14E7gB00b_JmYLdrmVClpJ-f6AR7ECLCT7up1_63xhv4O1fnxqimFQ8E-4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC_BYHPUCgYEA9-GghdabPd7LvKtcNrhXuXmUr7v6OuqC-VdMCz0HgmdRWVeOutRZT-ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN_C_ohNWLx-2J6ASQ7zKTxvqhRkImog9_hWuWfBpKLZl6Ae1UlZAFMO_7PSSoEFgIUZb_PF1KCz5sIdT-VANuLWmMfqes",
					"ZPXX",
					"\u6237\u7C4D\u4EBA\u53E3\u7167\u7247\u4FE1\u606F",
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
