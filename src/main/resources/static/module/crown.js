layui.define(['config', 'layer', 'element', 'form'], function (exports) {
    var config = layui.config;
    var layer = layui.layer;
    var element = layui.element;
    var form = layui.form;
    var popupRightIndex, popupCenterIndex, popupCenterParam;

    var crown = {
        // 路由載入元件
        loadView: function (path) {
            crown.showLoading('.layui-layout-admin .layui-body');
            $('.layui-layout-admin .layui-body').load(path, function () {
                element.render('breadcrumb');
                form.render('select');
                crown.removeLoading('.layui-layout-admin .layui-body');
            });
            crown.activeNav(Q.lash);
            // 移動裝置切換頁面隱藏側導航
            if (document.body.clientWidth <= 750) {
                crown.flexible(true);
            }
        },
        // 設定側欄摺疊
        flexible: function (expand) {
            var isExapnd = $('.layui-layout-admin').hasClass('crown-nav-mini');
            if (isExapnd == !expand) {
                return;
            }
            if (expand) {
                $('.layui-layout-admin').removeClass('crown-nav-mini');
            } else {
                $('.layui-layout-admin').addClass('crown-nav-mini');
            }
            crown.onResize();
        },
        // 設定導航欄選中
        activeNav: function (url) {
            $('.layui-layout-admin .layui-side .layui-nav .layui-nav-item .layui-nav-child dd').removeClass('layui-this');
            if (url && url != '') {
                $('.layui-layout-admin .layui-side .layui-nav .layui-nav-item').removeClass('layui-nav-itemed');
                var $a = $('.layui-layout-admin .layui-side .layui-nav>.layui-nav-item>.layui-nav-child>dd>a[href="#!' + url + '"]');
                $a.parent('dd').addClass('layui-this');
                $a.parent('dd').parent('.layui-nav-child').parent('.layui-nav-item').addClass('layui-nav-itemed');
            }
        },
        // 右側彈出
        popupRight: function (path) {
            popupRightIndex = layer.open({
                type: 1,
                id: 'crownPopupR',
                anim: 2,
                isOutAnim: false,
                title: false,
                closeBtn: false,
                offset: 'r',
                shade: .2,
                shadeClose: true,
                resize: false,
                area: '336px',
                skin: 'layui-layer-crownRight',
                success: function () {
                    //crown.showLoading('#crownPopupR');
                    $('#crownPopupR').load(path, function () {
                        //crown.removeLoading('#crownPopupR');
                    });
                },
                end: function () {
                    layer.closeAll('tips');
                }
            });
        },
        // 關閉右側彈出
        closePopupRight: function () {
            layer.close(popupRightIndex);
        },
        // 中間彈出
        popupCenter: function (param) {
            popupCenterParam = param;
            popupCenterIndex = layer.open({
                type: 1,
                id: 'crownPopupC',
                title: param.title ? param.title : false,
                shade: .2,
                offset: '120px',
                area: param.area ? param.area : '450px',
                resize: false,
                skin: 'layui-layer-crownCenter',
                success: function () {
                    $('#crownPopupC').load(param.path, function () {
                        $('#crownPopupC .close').click(function () {
                            layer.close(popupCenterIndex);
                        });
                        param.success ? param.success() : '';
                    });
                },
                end: function () {
                    layer.closeAll('tips');
                    param.end ? param.end() : '';
                }
            });
        },
        // 關閉中間彈出並且觸發finish回撥
        finishPopupCenter: function () {
            layer.close(popupCenterIndex);
            popupCenterParam.finish ? popupCenterParam.finish() : '';
        },
        fromVal: function (filter, object) {
            var formElem = $('.layui-form[lay-filter="' + filter + '"]');
            formElem.each(function () {
                var itemFrom = $(this);
                layui.each(object, function (key, value) {
                    /* if (typeof (value) === 'object') {
                         fromVal(filter, value);//遞迴
                     }*/
                    var itemElem = itemFrom.find('[name="' + key + '"]');
                    //如果對應的表單不存在，則不執行
                    if (!itemElem[0]) {
                        return;
                    }
                    var type = itemElem[0].type;
                    //如果為覈取方塊
                    if (type === 'checkbox') {
                        if (typeof (value) !== 'object') {
                            itemElem[0].checked = value;
                        } else {
                            layui.each(value, function (index, item) {
                                itemElem.each(function () {
                                    if (this.value === item.function function toString() { [native code] }() { [native code] }()) {
                                        this.checked = true;
                                    }
                                });
                            });
                        }
                    } else if (type === 'radio') { //如果為單選框
                        itemElem.each(function () {
                            if (this.value === value.function function toString() { [native code] }() { [native code] }()) {
                                this.checked = true;
                            }
                        });
                    } else { //其它類型的表單
                        itemElem.val(value);
                    }
                });
            });
            form.render(null, filter);
        },
        // 關閉中間彈出
        closePopupCenter: function () {
            layer.close(popupCenterIndex);
        },
        get: function (url, params, success) {
            params.method = "GET";
            return this.request(url, params, success);
        },
        post: function (url, params, success) {
            params.method = "POST";
            return this.request(url, params, success);
        },
        put: function (url, params, success) {
            params.method = "PUT";
            return this.request(url, params, success);
        },
        delete: function (url, params, success) {
            params.method = "DELETE";
            return this.request(url, params, success);
        },
        request: function (url, params, success) {
            params.method = params.method ? params.method : "GET";
            if (!params.contentType) {
                switch (params.method) {
                    case "GET":
                        params.contentType = '';
                        break;
                    case "POST":
                        params.data = JSON.stringify(params.data);
                        params.contentType = 'application/json; charset=UTF-8';
                        break;
                    case "PUT":
                        params.data = JSON.stringify(params.data);
                        params.contentType = 'application/json; charset=UTF-8';
                        break;
                    case "DELETE":
                        params.contentType = '';
                        break;
                    default:
                        params.contentType = '';
                }
            }
            $.ajax({
                url: config.serverUrl + url,
                data: params.data ? params.data : {},
                type: params.method,
                contentType: params.contentType,
                xhrFields: {
                    withCredentials: false
                },
                async: params.async !== false,
                crossDomain: true,
                success: function (data) {
                    success(data);
                },
                error: function (xhr) {
                    if (xhr.responseJSON.error === 'UNAUTHORIZED') {
                        config.removeAll();
                        layer.msg('登入過期', {icon: 2}, function () {
                            location.href = '/login.html';
                        });
                        return false;
                    }
                    layer.msg(JSON.parse(xhr.responseText).msg, {icon: 5});
                    layer.closeAll('loading');
                },
                beforeSend: function (xhr) {
                    var token = config.getToken();
                    if (token) {
                        xhr.setRequestHeader('Authorization', token);
                    }
                }
            });
        },
        hasPerm: function (buttonAlias) {
            var permButtons = config.getPermButtons();
            if (permButtons) {
                for (var i = 0; i < permButtons.length; i++) {
                    if (buttonAlias == permButtons[i]) {
                        return true;
                    }
                }
            }
            return false;
        },
        // 視窗大小改變監聽
        onResize: function () {
            if (config.autoRender) {
                if ($('.layui-table-view').length > 0) {
                    setTimeout(function () {
                        crown.events.refresh();
                    }, 800);
                }
            }
        },
        // 顯示載入動畫
        showLoading: function (element) {
            $(element).append('<i class="layui-icon layui-icon-loading layui-anim layui-anim-rotate layui-anim-loop crown-loading"></i>');
        },
        // 移除載入動畫
        removeLoading: function (element) {
            $(element + '>.crown-loading').remove();
        },
        // 快取臨時資料
        putTempData: function (key, value) {
            if (value) {
                layui.sessionData('tempData', {key: key, value: value});
            }
        },
        // 獲取快取臨時資料 獲取完刪除
        getTempData: function (key) {
            var tempData = layui.sessionData('tempData')[key];
            layui.sessionData('tempData', {key: key, remove: true});
            return tempData;
        },
        getSearchForm: function () {
            var val = {};
            var inputVals = $('#searchForm').find(':input').filter(function () {
                return $.trim(this.value).length > 0;
            }).serializeArray();
            $(inputVals).each(function () {
                if (val[this.name] !== undefined) {
                    if (!Array.isArray(val[this.name])) {
                        val[this.name] = [val[this.name]];
                    }
                    val[this.name].push(this.value);
                } else {
                    val[this.name] = this.value;
                }
            });
            return val;
        }
    };

    // ewcrown提供的事件
    crown.events = {
        flexible: function (e) {  // 摺疊側導航
            var expand = $('.layui-layout-admin').hasClass('crown-nav-mini');
            crown.flexible(expand);
        },
        refresh: function () {  // 重新整理主體部分
            Q.refresh();
        },
        back: function () {  //後退
            history.back();
        },
        theme: function () {  // 設定主題
            crown.popupRight('components/tpl/about.html');
        },
        fullScreen: function (e) {  // 全屏
            var ac = 'layui-icon-screen-full', ic = 'layui-icon-screen-restore';
            var ti = $(this).find('i');

            var isFullscreen = document.fullscreenElement || document.msFullscreenElement || document.mozFullScreenElement || document.webkitFullscreenElement || false;
            if (isFullscreen) {
                var efs = document.exitFullscreen || document.webkitExitFullscreen || document.mozCancelFullScreen || document.msExitFullscreen;
                if (efs) {
                    efs.call(document);
                } else if (window.ActiveXObject) {
                    var ws = new ActiveXObject('WScript.Shell');
                    ws && ws.SendKeys('{F11}');
                }
                ti.addClass(ac).removeClass(ic);
            } else {
                var el = document.documentElement;
                var rfs = el.requestFullscreen || el.webkitRequestFullscreen || el.mozRequestFullScreen || el.msRequestFullscreen;
                if (rfs) {
                    rfs.call(el);
                } else if (window.ActiveXObject) {
                    var ws = new ActiveXObject('WScript.Shell');
                    ws && ws.SendKeys('{F11}');
                }
                ti.addClass(ic).removeClass(ac);
            }
        }
    };

    // 所有ew-event
    $('body').on('click', '*[ew-event]', function () {
        var event = $(this).attr('ew-event');
        var te = crown.events[event];
        te && te.call(this, $(this));
    });

    // 移動裝置遮罩層點選事件
    $('.site-mobile-shade').click(function () {
        crown.flexible(true);
    });

    // 側導航摺疊狀態下滑鼠經過顯示提示
    $('body').on('mouseenter', '.layui-layout-admin.crown-nav-mini .layui-side .layui-nav .layui-nav-item>a', function () {
        var tipText = $(this).find('cite').text();
        if (document.body.clientWidth > 750) {
            layer.tips(tipText, this);
        }
    }).on('mouseleave', '.layui-layout-admin.crown-nav-mini .layui-side .layui-nav .layui-nav-item>a', function () {
        layer.closeAll('tips');
    });

    // 側導航摺疊狀態下點選展開
    $('body').on('click', '.layui-layout-admin.crown-nav-mini .layui-side .layui-nav .layui-nav-item>a', function () {
        if (document.body.clientWidth > 750) {
            layer.closeAll('tips');
            crown.flexible(true);
        }
    });

    // 所有lay-tips處理
    $('body').on('mouseenter', '*[lay-tips]', function () {
        var tipText = $(this).attr('lay-tips');
        var dt = $(this).attr('lay-direction');
        layer.tips(tipText, this, {tips: dt || 1, time: -1});
    }).on('mouseleave', '*[lay-tips]', function () {
        layer.closeAll('tips');
    });

    exports('crown', crown);
});
