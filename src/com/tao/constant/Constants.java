/**
 ***************************************************************************************
 *  @Author     1044053532@qq.com   
 *  @License    http://www.apache.org/licenses/LICENSE-2.0
 ***************************************************************************************
 */
package com.tao.constant;


public class Constants {

	public static interface WebSite{
		public static final int SUCCESS = 0;
		public static final int ERROR = -1;
	}
	
	public static interface ViewTemplateConfig{
		public static String template = "pctemplate/";
		public static String mobiletemplate = "mobiletemplate/";
	}
	
	public static interface NotifyConfig{
		public static final int NOTIFY_SUCCESS = 1;
	    public static final int NOTIFY_FAILURE = 0;
	    public static final int NOTIFY_NO_SESSION = 2;
	}
   
    
  
}
