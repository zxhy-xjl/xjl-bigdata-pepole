package com.zxhy.xjl.people;


/**
 * 人口库服务
 * @author leasonlive
 *
 */
public interface PeopleService { 
	/**
	 * 验证身份证信息
	 * @param idCode 身份证号码
	 * @param idName 姓名
	 * @return 验证结果，如果身份证号码和姓名在大数据人口库中存在并且有效，则返回true，否则返回false
	 */
    public boolean checkID(String idCode, String idName);
    /**
     * 获取身份证信息
     * @param idCode 身份证号码
     * @param idName 姓名
     * @return 身份证信息
     */
    public IDInfo getIDInfo(String idCode, String idName);
    /**
     * 获取身份证照片
     * @param idCode 身份证号码
     * @param idName 姓名
     * @return 身份证照片字节码
     */
    public byte[] getIDPhoto(String idCode, String idName) ;
    
}
