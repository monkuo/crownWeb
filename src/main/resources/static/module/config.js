layui.define(function (exports) {

    var config = {
        serverUrl: 'http://localhost:8088', // 伺服器地址
        scope: 'crown',  // 作用域
        autoRender: false,  // 視窗大小改變後是否自動重新渲染表格，解決layui資料表格非響應式的問題
        request: {
            //頁碼的參數名稱，預設：page
            pageName: 'cursor',
            //每頁資料量的參數名，預設：limit
            limitName: 'limit'
        },
        parseData: function (res) { //res 即為原始返回的資料
            return {
                "code": res.status, //解析介面狀態
                "msg": res.msg, //解析提示文字
                "count": res.result.total, //解析資料長度
                "data": res.result.records //解析資料列表
            };
        },
        response: {
            //規定成功的狀態碼，預設：0
            statusCode: 200
        },
        // 清空本地快取
        removeAll: function () {
            layui.data(config.scope, null);
        },
        // 獲取快取的token
        getToken: function () {
            var token = layui.data(config.scope).token;
            if (token) {
                return JSON.parse(token);
            }
        },
        // 快取token
        putToken: function (token) {
            layui.data(config.scope, {
                key: 'token',
                value: JSON.stringify('Bearer ' + token)
            });
        },
        // 獲取快取的選單
        getMenus: function () {
            var menus = layui.data(config.scope).menus;
            if (menus) {
                return JSON.parse(menus);
            }
        },
        // 快取選單
        putMenus: function (menus) {
            layui.data(config.scope, {
                key: 'menus',
                value: JSON.stringify(menus)
            });
        },
        // 獲取快取的許可權按鈕
        getPermButtons: function () {
            var permButtons = layui.data(config.scope).permButtons;
            if (permButtons) {
                return JSON.parse(permButtons);
            }
        },
        // 快取許可權按鈕
        putPermButtons: function (permButtons) {
            layui.data(config.scope, {
                key: 'permButtons',
                value: JSON.stringify(permButtons)
            });
        },
        // 獲取快取的token
        getUid: function () {
            var uid = layui.data(config.scope).uid;
            if (uid) {
                return JSON.parse(uid);
            }
        },
        // 快取Uid
        putUid: function (uid) {
            layui.data(config.scope, {
                key: 'uid',
                value: JSON.stringify(uid)
            });
        },
        // 當前登入的使用者
        getUser: function () {
            var user = layui.data(config.scope).user;
            if (user) {
                return JSON.parse(user);
            }
        },
        // 快取user
        putUser: function (user) {
            layui.data(config.scope, {
                key: 'user',
                value: JSON.stringify(user)
            });
        }
    };
    exports('config', config);
});
