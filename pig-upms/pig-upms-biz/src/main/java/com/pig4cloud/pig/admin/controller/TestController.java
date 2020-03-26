/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.pig4cloud.pig.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.admin.admin.entity.Test;
import com.pig4cloud.pig.admin.service.TestService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 测试
 *
 * @author cy
 * @date 2020-03-23 14:24:44
 */
@RestController
@AllArgsConstructor
@RequestMapping("/test")
@Api(value = "test", tags = "测试管理")
public class TestController
{

	private final TestService testService;

	/**
	 * 分页查询
	 *
	 * @param page 分页对象
	 * @param test 测试
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/page")
	public R getTestPage(Page page, Test test)
	{
		//返回
		return R.ok(testService.page(page, Wrappers.query(test)));
	}


	/**
	 * 通过id查询测试
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询", notes = "通过id查询")
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") Integer id)
	{
		return R.ok(testService.getById(id));
	}

	/**
	 * 新增测试
	 *
	 * @param test 测试
	 * @return R
	 */
	@ApiOperation(value = "新增测试", notes = "新增测试")
	@SysLog("新增测试")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('admin_test_add')")
	public R save(@RequestBody Test test)
	{
		return R.ok(testService.save(test));
	}

	/**
	 * 修改测试
	 *
	 * @param test 测试
	 * @return R
	 */
	@ApiOperation(value = "修改测试", notes = "修改测试")
	@SysLog("修改测试")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('admin_test_edit')")
	public R updateById(@RequestBody Test test)
	{
		return R.ok(testService.updateById(test));
	}

	/**
	 * 通过id删除测试
	 *
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id删除测试", notes = "通过id删除测试")
	@SysLog("通过id删除测试")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('admin_test_del')")
	public R removeById(@PathVariable Integer id)
	{
		return R.ok(testService.removeById(id));
	}

}
