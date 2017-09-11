package cn.lsh.admin.web;


import lombok.extern.slf4j.Slf4j;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lsh
 * @since 2017-09-05
 */
@Slf4j
@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index(Model model){
		log.info("this is frame");
		return "common/frame";
	}
	
	@RequestMapping("/index")
	public String list(Model model){
		log.info("this is index");
		return "index";
	}
}
