package com.zxhy.xjl.people;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
* @author  yangzaixiong
* 创建时间             2016年4月6日下午3:06:25
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ApplicationContext-people.xml"})
public class PeopleServiceTest {
   private static Log log = LogFactory.getLog(PeopleServiceTest.class);
   @Autowired
   PeopleService peopleService;
   @Test
   public void checkID(){
	      Assert.assertTrue(this.peopleService.checkID("640102196708120329", "王芳宁"));
	      Assert.assertFalse(this.peopleService.checkID("640321198603071742", "李艳玲"));
	      Assert.assertFalse(this.peopleService.checkID("640102196708120329", "李艳玲"));
   }
   @Test
   public void getIDInfo(){
	  
	   Assert.assertTrue(this.peopleService.getIDInfo("640102196708120329", "王芳宁")!=null);
	   Assert.assertTrue(this.peopleService.getIDInfo("640321198603071742", "李艳玲")==null);
	   Assert.assertFalse(this.peopleService.getIDInfo("640321198603071742", "王芳宁")!=null);
	   
	
	   IDInfo gaCard=this.peopleService.getIDInfo("640102196708120329", "王芳宁");
	   log.debug("---------用户信息--------");
	   log.debug("身份证号码:"+gaCard.getIdCode()+" 姓名:"+gaCard.getIdName()+
			              " 居住地址: "+gaCard.getAddress()+" 名族:"+gaCard.getNation()+
			              " 出生日期: "+gaCard.getBirthday()+" 性别: "+gaCard.getSex()+
			              " 国籍： "+gaCard.getIssuingOrganizations()+" 有效日期起： "+gaCard.getExpireDate()+
			              " 有效日期至： "+gaCard.getIssuingDate());
	  
   }
  @Test
   public void getIDPhoto() throws IOException{
	   Assert.assertTrue(this.peopleService.getIDPhoto("640102196708120329", "王芳宁")!=null);
	   Assert.assertTrue(this.peopleService.getIDPhoto("640321198603071742", "李艳玲")==null);
	   Assert.assertFalse(this.peopleService.getIDPhoto("640102196708120329", "李艳玲")!=null);
	   
	   
	    /**
		 * 将获取身份证照片存到D盘
		 */
	   byte[] buffer=this.peopleService.getIDPhoto("640102196708120329", "王芳宁");
	   FileOutputStream out = new FileOutputStream("d:\\1.jpg");
	   out.write(buffer);
	   out.close();
	
   }
}
