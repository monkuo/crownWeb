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
package cc.okbone.controller;

import java.util.List;

import org.crown.common.annotations.Resources;
import org.crown.framework.responses.ApiResponses;
import org.crown.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;

import cc.okbone.model.entity.Process;
import cc.okbone.service.IProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RestController;
import org.crown.framework.controller.SuperController;

/**
 * <p>
 * Process 製程 前端控制器
 * </p>
 *
 * @author O.K.Bone
 */
@Api(tags = {"Process"}, description = "Process 製程相關介面")
@RestController
@RequestMapping(value = "/process", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class ProcessRestController extends SuperController {
        @Autowired
        private IProcessService service;

        @Resources
        @ApiOperation(value = "查询所有製程")
        public ApiResponses<List<Process>> list() {
                return success(service.list());
        }
}
