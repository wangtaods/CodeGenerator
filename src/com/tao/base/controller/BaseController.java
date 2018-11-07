/**
 ***************************************************************************************
 *  @Author     1044053532@qq.com   
 *  @License    http://www.apache.org/licenses/LICENSE-2.0
 ***************************************************************************************
 */
package com.tao.base.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;


public class BaseController {
	@Autowired  
	public  HttpServletRequest request;
	
 
	/**
     * 提示消息
     */
    public String message;
	/**
     * Page size
     */
    public int resultSize;
    
    public int skipToPage=1;
    
    public int pageSize =10;
    
    public int totalPageSize;
 
	public int getResultSize() {
		return resultSize;
	}
	 
	 public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

 
	/**
	 * 
	 * @param code  1 成功  0 失败  
	 * @param msg   消息内容
	 * @param count 最大条数
	 * @param data  具体内容
	 * @return
	 */
	public Object putMsgToJsonString(int code,String msg,int count ,Object data){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("msg", msg);
		map.put("count", count);
		map.put("data", data);
		return JSONArray.toJSON(map);
	}
	/**
	 *  返回消息内容
	 * @param type     1 成功消息  2错误消息  3 警告消息 4 普通消息
	 * @param msg
	 */
	public void putMsg(int type,String msg){
		request.setAttribute("msgType", type);
		request.setAttribute("msgContent", msg);
	}
	
	/**
	 *  返回消息内容
	 * @param type    1 成功消息  2错误消息  3 警告消息 4 普通消息
	 * @param msg
	 */
	public void putRedirectMsg(int type,String msg,RedirectAttributes attr){
		attr.addFlashAttribute("msgType", type);
		attr.addFlashAttribute("msgContent", msg);
	}
	
	public int getSkipToPage() {
		try{
			skipToPage  = Integer.parseInt(request.getParameter("skipToPage"));
		}catch(Exception e){
			skipToPage=1;
		}
		return skipToPage;
	}

	public void setSkipToPage(int skipToPage) {
		this.skipToPage = skipToPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPageSize() {
		return totalPageSize;
	}

	public void setTotalPageSize(int totalPageSize) {
		this.totalPageSize = totalPageSize;
	}

 
	public  HttpSession  getSession(){
		return request.getSession();
	}

	public void write(HttpServletResponse response,String msg){
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	protected String getIP() {
   	String ip = request.getHeader("x-forwarded-for");    
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
           ip = request.getHeader("Proxy-Client-IP");    
       }    
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
           ip = request.getHeader("WL-Proxy-Client-IP");    
       }    
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
          ip = request.getRemoteAddr();    
       } 
       return ip;
	}
	
	

	// \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),    
    // 字符串在编译时会被转码一次,所以是 "\\b"    
    // \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)    
    static String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"    
            +"|windows (phone|ce)|blackberry"    
            +"|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"    
            +"|laystation portable)|nokia|fennec|htc[-_]"    
            +"|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";    
    static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser"    
            +"|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";    
      
    //移动设备正则匹配：手机端、平板  
    static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);    
    static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);    
        
     
     /**
      * 移除用户
      * */
     public void removeUser(HttpServletResponse response){
    	 request.getSession().setAttribute("user",null);
    	 request.getSession().removeAttribute("user");
     }  
 
	
}
