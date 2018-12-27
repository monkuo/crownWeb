layui.define(['config', 'crown', 'layer'], function (exports) {
    var $ = layui.$;
    var config = layui.config;
    var crown = layui.crown;
    var layer = layui.layer;

    var index = {
        // 渲染左側導航欄
        initLeftNav: function () {
            var menus = config.getMenus();
            $('.layui-layout-admin .layui-side').vm({menus: menus});
            crown.activeNav(Q.lash);
        },
        // 路由註冊
        initRouter: function () {
            index.regRouter(config.getMenus());
            Q.init({
                index: 'user'
            });
        },
        // 使用遞迴迴圈註冊
        regRouter: function (menus) {
            $.each(menus, function (i, data) {
                if (data.router) {
                    Q.reg(data.router, function () {
                        crown.loadView('components/' + data.path);
                    });
                }
                if (data.childrens) {
                    index.regRouter(data.childrens);
                }
            });
        },
        // 從伺服器獲取登入使用者的資訊
        getUser: function (success) {
            crown.get('/account/info', {}, function (data) {
                config.putUser(data.result);
                success(data.result);
            });
        },

        // 頁面元素繫結事件監聽
        bindEvent: function () {
            // 退出登入
            $('#logout').click(function () {
                layer.confirm('確定退出登入？', function (i) {
                    layer.close(i);
                    config.removeAll();
                    location.replace('login.html');
                });
            });
            // 主題設定
            $('#setTheme').click(function () {
                crown.popupRight('components/tpl/theme.html');
            });
            // 修改密碼
            $('#setPassword').click(function () {
                crown.popupRight('components/tpl/password.html');
            });
            // 個人資訊
            $('#setInfo').click(function () {
                crown.popupRight('components/tpl/userinfo.html');
            });
            // 訊息
            $('#btnMessage').click(function () {
                crown.popupRight('components/tpl/message.html');
            });
        }
    };

    exports('index', index);
});
