package com.tao.test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import com.tao.constant.Constants;
import com.tao.utils.Query;
import com.tao.base.controller.BaseController;
import com.tao.test.model.LoginDefEntity;
import com.tao.test.service.LoginDefService;
 
/**
 * 
 * 
 * @author 444683298
 * @email 444683298@qq.com
 * @date 2018-07-15 21:01:42
 */
@Controller
@RequestMapping("logindef")
public class LoginDefController extends BaseController{
	@Autowired
	private LoginDefService loginDefService;
	
	/**
	 * 页面
	 */
	@RequestMapping("/page")
	public String page(@RequestParam Map<String, Object> params){
		return "logindef";
	}
	/**
	 * 列表
	 */
	@RequestMapping(value="/list", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam Map<String, Object> params){
	    Query query = new Query(params);
		List<LoginDefEntity> loginDefList = loginDefService.queryList(query);
		int total = loginDefService.queryTotal(query);
		return putMsgToJsonString(Constants.WebSite.SUCCESS,"",total,loginDefList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping(value="/info/{id}", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Object info(@PathVariable("id") String id){
		LoginDefEntity loginDef = loginDefService.queryObject(id);
		return putMsgToJsonString(Constants.WebSite.SUCCESS,"",0,loginDef);
	}
	
	/**
	 *保存
	 */
	@RequestMapping(value="/save", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Object save(@ModelAttribute LoginDefEntity loginDef){
		loginDefService.save(loginDef);
		return putMsgToJsonString(Constants.WebSite.SUCCESS,"",0,loginDef);
	}
	
	/**
	 *  修改
	 */
	@RequestMapping(value="/update", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@ModelAttribute LoginDefEntity loginDef){
		int result = loginDefService.update(loginDef);
		return putMsgToJsonString(result,"",0,"");
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(@RequestParam String[] ids){
		int result = loginDefService.deleteBatch(ids);
		return putMsgToJsonString(result,"",0,"");
	}
	
}
