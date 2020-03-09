package com.zzx.sharding1.controller;

import com.zzx.sharding1.api.OrderApi;
import com.zzx.sharding1.common.Page;
import com.zzx.sharding1.common.PageResponse;
import com.zzx.sharding1.entity.Order;
import com.zzx.sharding1.enums.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 技能组
 * @author: zhouzhixiang
 * @date: 2019-12-09
 * @company: 叮当快药科技集团有限公司
 **/
@Controller
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderApi orderApi;

	@PostMapping("list")
	public PageResponse list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PageResponse<Page<Order>> result = new PageResponse<>();
		Page<Order> page = new Page<>();
		//List<Order> technicalGroups = orderApi.;

		result.setCode(ResponseEnum.SUCCESS.getCode());
		result.setMsg(ResponseEnum.SUCCESS.getName());
		//page.setList(technicalGroups);
		//page.setTotalRow(technicalGroups.size());
		page.setPageNumber(1);
		page.setPageSize(1);
		page.setTotalPage(1);
		result.setData(page);
		return result;
	}

}