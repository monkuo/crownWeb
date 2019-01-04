/*
 * Copyright (c) 2018 O.K.Bone.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.crown.controller;

import java.util.List;

import org.crown.common.annotations.Resources;
import org.crown.enums.StatusEnum;
import org.crown.framework.controller.SuperController;
import org.crown.framework.emuns.ErrorCodeEnum;
import org.crown.framework.responses.ApiResponses;
import org.crown.framework.utils.ApiAssert;
import org.crown.model.entity.Process;
import org.crown.model.parm.ProcessPARM;
import org.crown.service.IProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * Process 前端控制器
 * </p>
 *
 * @author O.K.Bone
 */
@Api(tags = { "Process" }, description = "Process 相關介面")
@RestController
@RequestMapping(value = "/process", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class ProcessRestController extends SuperController {
    @Autowired
    private IProcessService service;

    @Resources
    @ApiOperation(value = "查詢所有製程")
    @GetMapping
    public ApiResponses<List<Process>> list() {
        return success(service.list());
    }

    @Resources
    @ApiOperation("建立製程")
    @PostMapping
    public ApiResponses<Void> create(@RequestBody @Validated(ProcessPARM.Create.class) ProcessPARM parm) {
        int count = service.count(Wrappers.<Process>lambdaQuery().eq(Process::getName, parm.getLoginName()));
        ApiAssert.isTrue(ErrorCodeEnum.USERNAME_ALREADY_EXISTS, count == 0);
        Process pojo = parm.convert(Process.class);

        //預設啟用
        pojo.setStatus(StatusEnum.NORMAL);
        service.save(pojo);
        return success(HttpStatus.CREATED);
    }
}
