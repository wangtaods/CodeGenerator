package com.tao.test.model;

import java.util.Date;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.tao.base.model.BaseModel;


/**
 * 
 * 
 * @author 444683298
 * @email 444683298@qq.com
 * @date 2018-07-15 21:01:42
 */
public class LoginAndroidEntity extends BaseModel{
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String serviceids;
	//
	private String platform;
	//
	private String server;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * 设置：
	 */
	public void setServiceids(String serviceids) {
		this.serviceids = serviceids;
	}
	/**
	 * 获取：
	 */
	public String getServiceids() {
		return this.serviceids;
	}
	/**
	 * 设置：
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	/**
	 * 获取：
	 */
	public String getPlatform() {
		return this.platform;
	}
	/**
	 * 设置：
	 */
	public void setServer(String server) {
		this.server = server;
	}
	/**
	 * 获取：
	 */
	public String getServer() {
		return this.server;
	}

	 
	@Override
	public String toString() {
		return  ReflectionToStringBuilder.toString(this);
	}
}
