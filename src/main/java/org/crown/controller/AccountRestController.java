/*
 * Copyright (c) 2018-2022 Caratacus, (caratacus@qq.com).
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
import java.util.Set;

import org.crown.common.annotations.Resources;
import org.crown.common.utils.IpUtils;
import org.crown.enums.AuthTypeEnum;
import org.crown.framework.controller.SuperController;
import org.crown.framework.responses.ApiResponses;
import org.crown.model.dto.MenuTreeDTO;
import org.crown.model.dto.TokenDTO;
import org.crown.model.dto.UserDetailsDTO;
import org.crown.model.entity.User;
import org.crown.model.parm.AccountInfoPARM;
import org.crown.model.parm.LoginPARM;
import org.crown.model.parm.PasswordPARM;
import org.crown.service.IMenuService;
import org.crown.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 帳戶 前端控制器
 * </p>
 *
 * @author Caratacus
 */
@Api(tags = {"Account"}, description = "帳號操作相關介面")
@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class AccountRestController extends SuperController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    @Resources(auth = AuthTypeEnum.OPEN)
    @ApiOperation("獲取Token")
    @PostMapping("/token")
    public ApiResponses<TokenDTO> getToken(@RequestBody @Validated LoginPARM loginPARM) {
        User user = userService.login(loginPARM.getLoginName(), loginPARM.getPassword(), IpUtils.getIpAddr(request));
        TokenDTO tokenDTO = userService.getToken(user);
        return success(tokenDTO);
    }

    @Resources(auth = AuthTypeEnum.LOGIN)
    @ApiOperation("清除Token")
    @DeleteMapping("/token")
    public ApiResponses<Void> removeToken() {
        return success(HttpStatus.NO_CONTENT);
    }

    @Resources(auth = AuthTypeEnum.LOGIN)
    @ApiOperation("修改密碼")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "oldPassword", value = "原密碼", required = true, dataType = "String"),
        @ApiImplicitParam(name = "newPassword", value = "新密碼", required = true, dataType = "String"),
    })
    @PutMapping("/password")
    public ApiResponses<Void> updatePassword(@RequestBody @Validated PasswordPARM passwordPARM) {
        userService.updatePassword(currentUid(), passwordPARM.getOldPassword(), passwordPARM.getNewPassword());
        return success();
    }

    @Resources(auth = AuthTypeEnum.LOGIN)
    @ApiOperation("獲取帳戶詳情")
    @GetMapping("/info")
    public ApiResponses<UserDetailsDTO> accountInfo() {
        Integer uid = currentUid();
        UserDetailsDTO userDetails = userService.getUserDetails(uid);
        return success(userDetails);
    }

    @Resources(auth = AuthTypeEnum.LOGIN)
    @ApiOperation("修改帳戶資訊")
    @PutMapping("/info")
    public ApiResponses<Void> accountInfo(@RequestBody @Validated AccountInfoPARM accountInfoPARM) {
        Integer uid = currentUid();
        User user = accountInfoPARM.convert(User.class);
        user.setId(uid);
        userService.updateById(user);
        return success();
    }

    @Resources(auth = AuthTypeEnum.LOGIN)
    @ApiOperation("獲取帳戶選單")
    @GetMapping("/menus")
    public ApiResponses<List<MenuTreeDTO>> menus() {
        List<MenuTreeDTO> menuTrees = menuService.getUserPermMenus(currentUid());
        return success(menuTrees);
    }

    @Resources(auth = AuthTypeEnum.LOGIN)
    @ApiOperation("獲取帳戶按鈕")
    @GetMapping("/buttons/aliases")
    public ApiResponses<Set<String>> buttonsAliases() {
        return success(menuService.getUserPermButtonAliases(currentUid()));
    }
}

