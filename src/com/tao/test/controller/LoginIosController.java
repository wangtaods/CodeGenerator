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
import com.tao.test.model.LoginIosEntity;
import com.tao.test.service.LoginIosService;
 
/**
 * 
 * 
 * @author 444683298
 * @email 444683298@qq.com
 * @date 2018-07-15 21:01:42
 */
@Controller
@RequestMapping("loginios")
public class LoginIosController extends BaseController{
	@Autowired
	private LoginIosService loginIosService;
	
	/**
	 * 页面
	 */
	@RequestMapping("/page")
	public String page(@RequestParam Map<String, Object> params){
		return "loginios";
	}
	/**
	 * 列表
	 */
	@RequestMapping(value="/list", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam Map<String, Object> params){
	    Query query = new Query(params);
		List<LoginIosEntity> loginIosList = loginIosService.queryList(query);
		int total = loginIosService.queryTotal(query);
		return putMsgToJsonString(Constants.WebSite.SUCCESS,"",total,loginIosList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping(value="/info/{id}", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Object info(@PathVariable("id") String id){
		LoginIosEntity loginIos = loginIosService.queryObject(id);
		return putMsgToJsonString(Constants.WebSite.SUCCESS,"",0,loginIos);
	}
	
	/**
	 *保存
	 */
	@RequestMapping(value="/save", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Object save(@ModelAttribute LoginIosEntity loginIos){
		loginIosService.save(loginIos);
		return putMsgToJsonString(Constants.WebSite.SUCCESS,"",0,loginIos);
	}
	
	/**
	 *  修改
	 */
	@RequestMapping(value="/update", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@ModelAttribute LoginIosEntity loginIos){
		int result = loginIosService.update(loginIos);
		return putMsgToJsonString(result,"",0,"");
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete", produces="text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(@RequestParam String[] ids){
		int result = loginIosService.deleteBatch(ids);
		return putMsgToJsonString(result,"",0,"");
	}
	
}
