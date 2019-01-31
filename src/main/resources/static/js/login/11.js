!function () {
    function set(str, value) {
        for (var p, cur = VARS.source, ps = str.split("."), ks = [], fns = "", i = 0, len = ps.length; len > i; i++) p = ps[i], cur = cur[p], ks.push('["' + ps[i] + '"]');
        fns = "VARS.source" + ks.join("") + '="' + value + '"', eval("(" + fns + ")")
    }

    function get(e) {
        for (var t = VARS.source, a = e.split("."), o = 0, n = a.length; n > o; o++) if (t = t[a[o]], void 0 === t) return;
        return t
    }

    for (var VARS = {
        prefix: "$",
        keys: ["SYS", "ROOM", "PAGE"],
        source: {},
        scope: window
    }, i = 0; i < VARS.keys.length; i++) {
        var key = VARS.keys[i], fkey = VARS.prefix + key, obj = VARS.scope[fkey];
        obj && (VARS.source[key.toLowerCase()] = obj)
    }
    define("dypassport/context", ["shark/util/cookie/1.0"], function (e) {
        location.host;
        e.config("keypre", get("sys.cookie_pre") || "");
        var t = {set: set, get: get};
        return t
    })
}(), shark.config({
    resolve: function (e) {
        if (shark.helper.file.isAbsolute(e)) return !1;
        var t = e.split("/"), a = t[0], o = shark.helper.file.isCss(e) ? "css/" : "js/";
        switch (a) {
            case"dypassport":
                e = "app/douyu-passport/" + o + t.slice(1).join("/");
                break;
            case"dypassport-activity":
                e = "app/douyu-passport/activity/" + o + t.slice(1).join("/")
        }
        return e
    }
}), shark.on("createNode", function (e, t) {
    var a, o = shark.helper.file, n = "2.0.37";
    a = t.url, o.isCss(a) ? a.indexOf("?") > 0 ? e.href = a + "&" + n : e.href = a + "?" + n : a.indexOf("?") > 0 ? e.src = a + "&" + n : e.src = a + "?" + n
}), shark.on("saved", function (e) {
    var t = new RegExp("^dypassport//*");
    t.test(e.id) && shark.helper.domReady(function () {
        define([e.id], function (e) {
            e && "function" == typeof e.application && e.application.call(e)
        })
    })
}), define("dypassport/com/verify/verify", ["jquery", "shark/class", "dypassport/com/verify/geetestV3", "dypassport/com/verify/geetestV4", "dypassport/com/verify/imgCode", "dypassport/com/verify/vcode9"], function (e, t, a, o, n, i) {
    var r = t({
        init: function (t) {
            this.options = e.extend(!0, {}, t), this.verifyData = {}, this.check()
        }, check: function () {
            if (!this.loading) {
                var t = this, a = t.options.xhrParams || {};
                this.loading = !0, e(".refresh-code").addClass("disabled"), e.ajax(a.url, {
                    type: "post",
                    data: a.data || {},
                    dataType: "json",
                    timeout: 5e3,
                    success: function (o) {
                        if (t.loading = !1, e(".refresh-code").removeClass("disabled"), o && 0 === +o.error && o.data) {
                            var n = o.data || {};
                            if (t.instance) {
                                if (t.verifyData.code_type === n.code_type) return t.instance.renderVerity(n.code_data), void(t.verifyData.code_token = n.code_token);
                                t.instance.destroy(), t.instance = null
                            }
                            t.verifyData.code_type = n.code_type, t.verifyData.code_token = n.code_token, t.build(n)
                        } else e.dialog.tips_black(o.msg || "网络错误"), e.isFunction(a.errorHandle) && a.errorHandle()
                    },
                    error: function () {
                        t.loading = !1, e(".refresh-code").removeClass("disabled"), e.dialog.tips_black("网络错误，验证码加载失败"), e.isFunction(a.errorHandle) && a.errorHandle()
                    }
                })
            }
        }, build: function (t) {
            var r = this, c = r.options.xhrParams, s = t.code_data || t || {}, d = r.options.options,
                p = r.options.otherFunctions || {}, l = {
                    success: r.options.success || e.noop,
                    error: r.options.error || e.noop,
                    close: r.options.close || e.noop,
                    init: p.init || e.noop,
                    "catch": p["catch"] || e.noop,
                    refresh: p.refresh || e.noop,
                    destroy: p.destroy || e.noop,
                    onNextWillShow: p.onNextWillShow || e.noop
                };
            switch (+t.code_type) {
                case 1:
                    c.data && "v4" === c.data.gt_version ? this.instance = o.create(d, s, l) : this.instance = a.create(d, s, l);
                    break;
                case 2:
                    this.instance = n.create(d, s, l, function () {
                        r.check(), l.refresh()
                    });
                    break;
                case 3:
                    this.instance = i.create(d, s, l, function () {
                        r.check(), l.refresh()
                    })
            }
        }
    });
    return r
}), define("dypassport/com/verify/geetestV3", ["jquery", "shark/class"], function (e, t) {
    var a = t({
        init: function (e, t, a) {
            this.options = e || {}, this.functions = a || {}, this.buildAsync(t)
        }, render: function () {
            this.geeId = Math.random().toString(36).substring(2), this.options.target.addClass("render").append('<div id="' + this.geeId + '"></div>')
        }, buildAsync: function (e) {
            var t = this, a = location.protocol;
            require.use([a + "//static.geetest.com/static/tools/gt.js"], function () {
                t.render(), t.build(e)
            })
        }, build: function (t) {
            var a = this, o = a.functions.success || e.noop, n = a.functions.error || e.noop,
                i = (a.functions.close || e.noop, a.functions.init || e.noop), r = a.functions["catch"] || e.noop,
                c = a.functions.refresh || e.noop, s = a.functions.destroy || e.noop;
            delete a.options.target, delete a.options.v9Area, delete a.options.shadow;
            var d = e.extend(!0, {
                product: "custom",
                width: "100%",
                area: "body",
                next_width: "260px",
                bg_color: "transparent"
            }, a.options, t || {});
            try {
                window.initGeetest(d, function (e) {
                    i();
                    var t = null;
                    a.getResult = function () {
                        return t
                    }, a.refresh = function () {
                        t = null, c(), e.reset()
                    }, a.destroy = function () {
                        t = null, s(), e.destroy()
                    }, e.appendTo("#" + a.geeId), e.onSuccess(function () {
                        t = e.getValidate(), o(t)
                    }), e.onError(function () {
                        t = null, n()
                    })
                })
            } catch (p) {
                r()
            }
        }
    }), o = {
        create: function (e, t, o) {
            return new a(e, t, o)
        }
    };
    return o
}), !function (e, t) {
    "use strict";
    "object" == typeof module && "object" == typeof module.exports ? module.exports = e.document ? t(e, !0) : function (e) {
        if (!e.document) throw new Error("Geetest requires a window with a document");
        return t(e)
    } : t(e)
}("undefined" != typeof window ? window : this, function (e, t) {
    "use strict";

    function a(e) {
        this._obj = e
    }

    function o(e) {
        var t = this;
        new a(e)._each(function (e, a) {
            t[e] = a
        })
    }

    if (void 0 === e) throw new Error("Geetest requires browser environment");
    var n = e.document, i = e.Math, r = n.getElementsByTagName("head")[0];
    a.prototype = {
        _each: function (e) {
            var t = this._obj;
            for (var a in t) t.hasOwnProperty(a) && e(a, t[a]);
            return this
        }
    }, o.prototype = {
        api_server: "api.geetest.com",
        type_path: "/gettype_deepknow.php",
        protocol: "http://",
        static_servers: ["static.geetest.com", "dn-staticdown.qbox.me"],
        path: "/static/js/sense.js",
        type: "sense",
        _extend: function (e) {
            var t = this;
            new a(e)._each(function (e, a) {
                t[e] = a
            })
        }
    };
    var c = function (e) {
        return "number" == typeof e
    }, s = function (e) {
        return "string" == typeof e
    }, d = function (e) {
        return "boolean" == typeof e
    }, p = function (e) {
        return "function" == typeof e
    }, l = function () {
        return parseInt(1e4 * i.random()) + (new Date).valueOf()
    }, u = function (e, t) {
        var a = n.createElement("script");
        a.charset = "UTF-8", a.async = !0, a.onerror = function () {
            t(!0)
        };
        var o = !1;
        a.onload = a.onreadystatechange = function () {
            o || a.readyState && "loaded" !== a.readyState && "complete" !== a.readyState || (o = !0, setTimeout(function () {
                t(!1)
            }, 0))
        }, a.src = e, r.appendChild(a)
    }, f = function (e) {
        return e.replace(/^https?:\/\/|\/$/g, "")
    }, h = function (e) {
        return e = e.replace(/\/+/g, "/"), 0 !== e.indexOf("/") && (e = "/" + e), e
    }, g = function (e) {
        if (!e) return "";
        var t = "?";
        return new a(e)._each(function (e, a) {
            (s(a) || c(a) || d(a)) && (t = t + encodeURIComponent(e) + "=" + encodeURIComponent(a) + "&")
        }), "?" === t && (t = ""), t.replace(/&$/, "")
    }, v = function (e, t, a, o) {
        t = f(t);
        var n = h(a) + g(o);
        return t && (n = e + t + n), n
    }, y = function (e, t, a, o, n) {
        var i = function (r) {
            var c = v(e, t[r], a, o);
            u(c, function (e) {
                e ? r >= t.length - 1 ? n(!0) : i(r + 1) : n(!1)
            })
        };
        i(0)
    }, w = function (t, a, o, n) {
        var i = "geetest_" + l();
        e[i] = function (t) {
            n("success" === t.status ? t.data : t), e[i] = void 0;
            try {
                delete e[i]
            } catch (a) {
            }
        }, y(o.protocol, t, a, {gt: o.id, callback: i}, function (e) {
            e && m("networkError", o)
        })
    }, m = function (e, t) {
        var a = {
            networkError: "网络错误",
            idNotExist: "参数id必填",
            cbIlegal: "callback必须是function",
            geetestNotExist: "Geetest不存在"
        };
        if ("function" != typeof t.onError) throw new Error(a[e]);
        t.onError(a[e])
    }, _ = function () {
        return !!e.Geetest
    }, k = function (t, a) {
        var n = new o(t);
        return t.https ? n.protocol = "https://" : "http:" !== e.location.protocol && "https:" !== e.location.protocol ? n.protocol = "https://" : n.protocol = e.location.protocol + "//", t && t.id ? a && !p(a) ? void m("cbIlegal", n) : _() ? void(a && a(new e.Geetest(n))) : void w([n.api_server], n.type_path, n, function (t) {
            y(n.protocol, t.static_servers, t.path, null, function (o) {
                if (o) m("networkError", n); else {
                    if ("undefined" == typeof e.Geetest) return void m("geetestNotExist", n);
                    n._extend(t), a && a(new e.Geetest(n))
                }
            })
        }) : void m("idNotExist", n)
    };
    return e.initSense = k, k
}), define("dypassport/com/verify/geetestV4", ["jquery", "shark/class"], function (e, t) {
    var a = t({
        init: function (e, t, a) {
            this.options = e || {}, this.functions = a || {}, this.build(t)
        }, build: function (t) {
            var a = this, o = a.options.infos || {}, n = (a.functions.show || e.noop, a.functions.success || e.noop),
                i = a.functions.error || e.noop, r = a.functions.close || e.noop, c = a.functions.init || e.noop,
                s = a.functions["catch"] || e.noop, d = a.functions.refresh || e.noop,
                p = a.functions.onNextWillShow || e.noop;
            delete a.options.target, delete a.options.v9Area, delete a.options.shadow;
            var l = e.extend(!0, {
                https: !0,
                width: "260px",
                area: "body",
                bg_color: "transparent"
            }, a.options, t || {});
            try {
                console.time("start"), window.initSense(l, function (e) {
                    c(), console.timeEnd("start");
                    var t = null;
                    a.getResult = function () {
                        return t
                    }, a.show = function () {
                        e.sense()
                    }, a.refresh = function () {
                        t = null, d(), e.reset()
                    }, e.setInfos(function () {
                        return o
                    }).onNextWillShow(function () {
                        p()
                    }).onSuccess(function (e) {
                        t = e, t.id = l.id, n(t), a.getResult = function () {
                            return t
                        }
                    }).onClose(function () {
                        r()
                    }).onError(function (e) {
                        i(e)
                    })
                })
            } catch (u) {
                s()
            }
        }
    }), o = {
        create: function (e, t, o) {
            return new a(e, t, o)
        }
    };
    return o
}), define("dypassport/com/verify/vcode9", ["jquery", "shark/class"], function (e, t) {
    var a = [[0, 0], [-55, 0], [-110, 0], [0, -40], [-55, -40], [-110, -40], [0, -80], [-55, -80], [-110, -80]],
        o = ['<div class="vcode9">', '<div class="vcode9-tit clearfix">', "<h3>验证码</h3>", '<a href="javascript:;" class="vcode9-close">关闭</a>', "</div>", '<div class="vcode9-con">', '<div class="vcode9-preview clearfix">', "<span>验证码</span>", "<b></b><b></b><b></b><b></b>", '<b class="p-delete" title="清除"></b>', "</div>", '<div class="vcode9-guide">', "<span></span>", '<a class="refresh-code" href="javascript:;">看不清？</a>', "</div>", '<div class="vcode9-action">点击框内文字输入验证码</div>', '<div class="vcode9-input clearfix">', '<a class="i-0" href="javascript:;"><b data-num="0"></b></a>', '<a class="i-1" href="javascript:;"><b data-num="1"></b></a>', '<a class="i-2" href="javascript:;"><b data-num="2"></b></a>', '<a class="i-3" href="javascript:;"><b data-num="3"></b></a>', '<a class="i-4" href="javascript:;"><b data-num="4"></b></a>', '<a class="i-5" href="javascript:;"><b data-num="5"></b></a>', '<a class="i-6" href="javascript:;"><b data-num="6"></b></a>', '<a class="i-7" href="javascript:;"><b data-num="7"></b></a>', '<a class="i-8" href="javascript:;"><b data-num="8"></b></a>', "</div>", "</div>", "</div>"].join(""),
        n = t({
            init: function (t, a, o, n) {
                var i = {title: "验证码", auto: !1, lock: !0, shadow: {opacity: .7}};
                this.options = e.extend(!0, i, t || {}), this.functions = o || {}, this.refresh = n || e.noop;
                try {
                    this.render(), this.bindEvt(), this.options.auto && this.show(), this.renderVerity(a)
                } catch (r) {
                    var c = o["catch"] || e.noop;
                    c()
                }
            }, render: function () {
                var t = this.make(), a = this.options.v9Area, o = this.options.area, n = e(a || o || "body");
                this.options.$pop = e(t.pop), this.options.$shadow = e(t.shadow), this.options.$pop.hide().removeClass("hide"), this.options.$shadow.hide().removeClass("hide"), o && (this.options.$pop.css("position", "absolute"), this.options.$shadow.css("position", "absolute")), this.options.$pop.find(".vcode9-tit h3").text(this.options.title), this.options.$shadow.css({
                    opacity: this.options.shadow.opacity,
                    filter: "Alpha(Opacity=" + 100 * this.options.shadow.opacity + ")"
                }), n.append(this.options.$pop), n.append(this.options.$shadow), this.options._is_show = !1, this.options.selected = []
            }, make: function () {
                return {pop: o, shadow: '<div class="vcode9-shadow hide"></div>'}
            }, show: function () {
                var t = this;
                this.options.lock && this.options.$shadow.fadeIn(), this.options.$pop.fadeIn(function () {
                    var a = t.functions.init || e.noop;
                    t.options._is_show = !0, a.call(t)
                })
            }, hide: function () {
                var t = this;
                this.options.lock && this.options.$shadow.fadeOut(), this.options.$pop.fadeOut(function () {
                    var a = t.functions.close || e.noop;
                    t.options._is_show = !1, a.call(t)
                })
            }, getResult: function () {
                for (var e = this.options.selected, t = "", a = 0, o = e.length; o > a; a++) t += e[a].v;
                return {code: t}
            }, pushCode: function (t) {
                var a = this.options.selected.length,
                    o = this.options.$pop.find(".vcode9-preview > b").not(".p-delete");
                if (!(a >= 4) && (this.options.selected.push(t), o.eq(a).css({
                        "background-position-x": t.x + "px ",
                        "background-position-y": t.y + "px ",
                        "background-position": t.x + "px " + t.y + "px"
                    }), a = this.options.selected.length, 4 === a)) {
                    var n = this.functions.success || e.noop;
                    this.hide(), n.call(this, {code: this.getResult()})
                }
            }, popCode: function () {
                var e = this.options.selected.length, t = this.options.$pop.find(".vcode9-preview>b").not(".p-delete");
                e && (this.options.selected.pop(), t.eq(e - 1).css({"background-position": "-500px -500px"}))
            }, destroy: function () {
                this.options.$pop.remove(), this.options.$shadow.remove();
                var t = this.functions.destroy || e.noop;
                t()
            }, renderVerity: function (e) {
                var t = "";
                e && e.base64_data && (t = "data:image/jpeg;base64," + e.base64_data);
                var a = this.options.$pop, o = a.find(".vcode9-preview > b").not(".p-delete"), n = "url(" + t + ")";
                this.options.selected = [], a.find(".vcode9-preview>b").not(".p-delete").css("background-image", n), a.find(".vcode9-guide>span").css("background-image", n), a.find(".vcode9-input>a>b").css("background-image", n), o.css("background-position", "-500px -500px")
            }, bindEvt: function () {
                function t(e) {
                    e.stopPropagation(), e.preventDefault()
                }

                var o = this;
                this.options.$pop.find(".p-delete").click(function () {
                    o.popCode()
                }), this.options.$pop.find(".vcode9-input > a").click(function (n) {
                    t(n);
                    var i, r, c, s = e(this), d = s.find("b"), p = parseInt(d.data("num")) || 0;
                    i = a[p], r = i[0] - 3, c = i[1] - 3, o.pushCode({v: p, x: r, y: c})
                }), this.options.$pop.find(".vcode9-close").click(function () {
                    return o.hide(), !1
                }), this.options.$pop.find(".vcode9-guide > a").click(function () {
                    return o.refresh(), !1
                })
            }
        }), i = {
            create: function (e, t, a, o) {
                return new n(e, t, a, o)
            }
        };
    return i
}), define("dypassport/com/verify/imgCode", ["jquery", "shark/class"], function (e, t) {
    var a = t({
        init: function (t, a, o, n) {
            this.options = e.extend(!0, {}, {target: null}, t), this.refresh = n || e.noop, this.functions = o || {};
            try {
                this.renderVerity(a), this.bindEvt()
            } catch (i) {
                var r = o["catch"] || e.noop;
                r()
            }
        }, renderVerity: function (t) {
            var a = "";
            t && t.base64_data && (a = "data:image/jpeg;base64," + t.base64_data), this.options.$tar = e(this.options.target), this.options.$tar.addClass("render").empty().append(['<span class="verify-imgCode-span"></span>', '<input class="verify-imgCode-input" type="text" name="captcha_word" maxlength="4" placeholder="获取验证码">', '<img class="verify-imgCode-img" src=' + a + " >"].join("")), this.options.$ipt = this.options.$tar.find("input"), this.options.$img = this.options.$tar.find("img")
        }, onShow: function () {
            this.options.$tar.show();
            var t = this.functions.init || e.noop;
            t()
        }, onHide: function () {
            this.options.$tar.hide();
            var t = this.functions.close || e.noop;
            t()
        }, getResult: function () {
            return {code: this.options.$ipt.val()}
        }, bindEvt: function () {
            var e = this;
            e.options.$tar.on("click", "img", function (t) {
                t.stopPropagation(), t.preventDefault(), e.refresh()
            })
        }
    }), o = {
        create: function (e, t, o, n) {
            return new a(e, t, o, n)
        }
    };
    return o
}), define("dypassport/com/user-control", ["jquery", "shark/util/lang/1.0", "shark/util/cookie/1.0", "shark/util/flash/bridge/1.0", "shark/ext/swfobject"], function (e, t, a, o, n) {
    var i = ({client_id: window.client_id || 1, hmac_flash_ready: !1, salt: null}, {});
    return i.control = {}, i.control.API = {
        auth: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, e.ajax({
                url: passport_host + "iframe/auth",
                type: "GET",
                data: t,
                dataType: "jsonp",
                jsonp: "callback",
                callback: "json_callback",
                success: a,
                error: o
            })
        }, iframeLogin: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, $SYS.lang && (t.lang = $SYS.lang), e.ajax({
                url: "/iframe/loginNew",
                type: "post",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, cpsReward: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, e.ajax({
                url: "/api/cpsReward",
                type: "GET",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, login: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, e.ajax({
                url: "/api/passport/login",
                type: "GET",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, reg: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, e.ajax("/member/register/ajax_new", {
                type: "post",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, iframeReg: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, $SYS.lang && (t.lang = $SYS.lang), e.ajax({
                url: "/iframe/registerNew",
                type: "post",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, subSiteLogin: function (t, a, o, n) {
            t && (o = o || e.noop, n = n || e.noop, e.ajax({
                url: t,
                type: "GET",
                data: a,
                dataType: "jsonp",
                jsonp: "callback",
                callback: "json_callback",
                success: o,
                error: n
            }))
        }, qrGenerateCode: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, e.ajax("/scan/generateCode", {
                type: "post",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, qrCheckScan: function (t, a, o) {
            var n = (new Date).getTime();
            a = a || e.noop, o = o || e.noop, e.ajax({
                url: "/lapi/passport/qrcode/check?time=" + n,
                type: "GET",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, qrDeleteCode: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, e.ajax("/scan/deleteCode ", {
                type: "post",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, getCPS: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, e.ajax({
                url: "/member/register/getCpsId?v=" + (new Date).getTime(),
                type: "GET",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, checkUserStatus: function (t, a, o) {
            t = t || {}, a = a || e.noop, o = o || e.noop, e.ajax({
                url: "/member/cp/phone_anchor_status",
                type: "GET",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, checkNickName: function (t, a, o) {
            t = t || {}, a = a || e.noop, o = o || e.noop, $SYS.lang && (t.lang = $SYS.lang), e.ajax({
                url: "/api/nicknameCheck",
                type: "post",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, verifyPhone: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, $SYS.lang && (t.lang = $SYS.lang), e.ajax("/user/bindphone/sendPhoneVoice", {
                type: "post",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, bindPhone: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, $SYS.lang && (t.lang = $SYS.lang), e.ajax("/user/bindphone/bindPhone", {
                type: "post",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, loginVerifyPhone: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, e.ajax("/iframe/loginCaptcha", {
                type: "post",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, regVerifyPhone: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, e.ajax("/iframe/registerCaptcha", {
                type: "post",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, regByPhone: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, e.ajax({
                url: "/iframe/register",
                type: "post",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, submitNickname: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, e.ajax({
                url: "/iframe/updateNickname",
                type: "post",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }, getUserInfo: function (t, a, o, n) {
            o = o || e.noop, n = n || e.noop, e.ajax({
                url: t,
                type: "get",
                data: a,
                dataType: "jsonp",
                jsonp: "callback",
                timeout: 2e3,
                callback: "json_callback",
                success: o,
                error: n
            })
        }, getPcCode: function (t, a, o, n) {
            o = o || e.noop, n = n || e.noop, e.ajax({
                url: t,
                type: "get",
                data: a,
                dataType: "jsonp",
                jsonp: "callback",
                timeout: 2e3,
                async: !1,
                crossDomain: !0,
                callback: "json_callback",
                success: o,
                error: n
            })
        }, useClentCodeToLogin: function (t, a, o) {
            a = a || e.noop, o = o || e.noop, e.ajax({
                url: "/lapi/passport/pcAuth/check",
                type: "get",
                data: t,
                dataType: "json",
                success: a,
                error: o
            })
        }
    }, i.control
}), define("dypassport/com/user-view-common", ["jquery", "shark/util/cookie/1.0"], function (e, t) {
    var a = {
        init: function () {
            if (e.fn.placeholder && e("input[placeholder], textarea[placeholder]").each(function () {
                    e(this).placeholder()
                }), e.dialog.tips_black && e.dialog.through) try {
                var a = e.dialog.tips_black;
                e.dialog.tips_black = function (t, a) {
                    var o = {}, n = e(".loginbox-con");
                    n.size() > 0 && (o.left = n.offset().left + 90, o.top = n.offset().top + 140);
                    var i = {
                        id: "Tips",
                        zIndex: e.dialog.defaults.zIndex,
                        title: !1,
                        cancel: !1,
                        fixed: !0,
                        lock: !1,
                        padding: 0,
                        margin: 0
                    }, a = 3;
                    return i = e.extend(!0, i, o), e.dialog.through(i).content('<div class="infodrmation">' + t + "</div>").time(a)
                }
            } catch (o) {
                e.dialog.tips_black = a
            }
            try {
                window.SMSdk && SMSdk.getDeviceId && t.set("sm_did", encodeURIComponent(SMSdk.getDeviceId()), 864e5)
            } catch (o) {
            }
            window.return_loadhmOk = function () {
                hmac_flash_ready = !0
            }
        }
    };
    return a
}), define("dypassport/com/dp-base", ["jquery", "shark/observer"], function (e, t) {
    function a(t, a) {
        n.ext && (n.ext = {});
        var o = e.extend(!0, {}, n, {action_code: a, ext: t});
        return o
    }

    function o(e, t, o) {
        var n = a(e, t);
        o ? DYS.sub({kernelFlag: !0, realTime: !0}, n) : DYS.sub(n)
    }

    var n = {};
    return t.on("dys.com.dpdata", function (e) {
        n = e || {};
        try {
            DYS.sub.setPageCode(n.page_code)
        } catch (t) {
        }
    }), window.DYS ? o : e.noop
}), define("dypassport/com/dp-reg", ["jquery", "shark/observer", "dypassport/com/dp-base"], function (e, t, a) {
    var o = {
        "dys.reg.show": function (e) {
            a(e, "show_sign", !0)
        }, "dys.reg.input.change": function (e) {
            var t = {
                phoneNum: "click_sign_phones_number",
                password: "click_sign_phones_pw",
                protocol: "click_sign_agree",
                phoneCaptcha: "click_sign_phones_codein",
                reName: "click_sign_phones_succ_modname"
            }, o = t[e.name];
            e && delete e.name, a(e, o)
        }, "dys.reg.phones.limitcode.succ": function (e) {
            a(e, "show_sign_phones_limitcode_succ")
        }, "dys.reg.phones.limitcode.fail": function (e) {
            a(e, "show_sign_phones_limitcode_fail")
        }, "dys.reg.phones.get.code": function (e) {
            a(e, "click_sign_phones_get_code")
        }, "dys.reg.phones.tosign": function (e) {
            a(e, "click_sign_phones_tosign", !0)
        }, "dys.reg.phone.succ": function (e) {
            a(e, "show_sign_phones_succ", !0)
        }, "dys.reg.phone.fail": function (e) {
            a(e, "show_sign_phones_fail", !0)
        }, "dys.reg.phone.succ.login": function (e) {
            a(e, "click_sign_phones_succ_login")
        }, "dys.com.thirds.entrance": function (e) {
            a(e, "click_login_other")
        }, "dys.reg.poccupy.con": function (e) {
            a(e, "click_sign_poccupy_con")
        }, "dys.reg.poccupy.cancel": function (e) {
            a(e, "click_newphone_cancel")
        }, "dys.reg.plessthirty.fail": function (e) {
            a(e, "show_sign_poccupy_plessthirty_fail")
        }, "dys.reg.phone.country": function (e) {
            a(e, "click_login_phone_country")
        }, "dys.reg.geetest.click": function (e) {
            a(e, "click_sign_code")
        }
    };
    for (var n in o) !function (e) {
        var a = o[e];
        t.on(e, function (e) {
            a.call(this, e)
        })
    }(n)
}), define("dypassport/com/user-pw-strongth", ["shark/util/lang/1.0"], function (e) {
    var t = {
        reg: {
            regB: /[^\x00-\xff]/,
            regNum: /[0-9]/,
            regCode: /[A-Za-z]/,
            regS: /\S[^A-Za-z0-9]/,
            regs: /\s/,
            regHan: /[\u4e00-\u9fa5]/
        }, ck: function (a) {
            var o = e.string.bytelen(a) || 0, n = 0, i = 0, r = {}, c = t.reg;
            if (c.regs.test(a)) return r = {icon: -1, type: -1};
            if (c.regHan.test(a)) return r = {icon: -2, type: -2};
            if (c.regB.test(a)) return r = {icon: -3, type: -3};
            switch (6 > o ? n = 1 : 9 > o ? n = 2 : 11 > o ? n = 3 : 16 > o ? n = 4 : 27 > o && (n = 5), c.regNum.test(a) && i++, c.regCode.test(a) && i++, c.regS.test(a) && i++, i) {
                case 0:
                    r.icon = 1;
                    break;
                case 1:
                    n > 1 && (r.icon = 2);
                    break;
                case 2:
                    3 > n ? r.icon = 2 : r.icon = 3;
                    break;
                case 3:
                    1 === n ? r.icon = 2 : 5 > n ? r.icon = 3 : r.icon = 4
            }
            switch (n) {
                case 1:
                    r.type = 1;
                    break;
                case 2:
                    2 >= i ? r.type = 2 : r.type = 5;
                    break;
                case 3:
                case 4:
                    1 === i ? r.type = 2 : 2 === i ? r.type = 4 : r.type = 5;
                    break;
                case 5:
                    1 === i ? r.type = 2 : 2 === i ? r.type = 4 : r.type = 6
            }
            return r
        }, PWStrength: function (e) {
            var a = e.length, o = t.ck(e), n = o.type, i = "", r = {};
            switch (o.icon) {
                case-1:
                    i = "密码不能包含空格", o.icon = 0;
                    break;
                case-2:
                    i = "密码不能包含汉字", o.icon = 0;
                    break;
                case-3:
                    i = "密码不能包含全角字符", o.icon = 0
            }
            if (26 > a && a > 5) switch (n) {
                case 2:
                    i = "强度低，有被盗风险，至少使用两种字符组合";
                    break;
                case 3:
                    i = "强度低，请使用三种字符组合可降低被盗号风险";
                    break;
                case 4:
                    i = "强度适中，使用三种字符组合可提高安全性";
                    break;
                case 5:
                    i = "强度适中，加大密码长度可以提升安全性";
                    break;
                case 6:
                    i = "您的密码安全性极高"
            } else a > 25 ? (i = "请输入6-25个字符", o.icon = 0) : (i = "组合6-25位字母、数字或符号", o.icon = 1);
            return r.icon = o.icon, r.cont = i, r
        }
    };
    return {ckPWStrength: t}
}), define("dypassport/com/user-view-util", ["jquery"], function (e) {
    function t(t) {
        var a = "";
        try {
            a = e.i18n.prop(t)
        } catch (o) {
        }
        return a
    }

    var a = {
        countTimer: null, isTimer: !1, serialize: function (e) {
            var t = "", a = [];
            try {
                for (var o in e) a.push(o + "=" + decodeURIComponent(e[o]));
                t = a.join("&")
            } catch (n) {
            }
            return t
        }, formatDate: function (e, t) {
            try {
                var a = {
                    "M+": e.getMonth() + 1,
                    "d+": e.getDate(),
                    "h+": e.getHours(),
                    "m+": e.getMinutes(),
                    "s+": e.getSeconds(),
                    "q+": Math.floor((e.getMonth() + 3) / 3),
                    S: e.getMilliseconds()
                };
                /(y+)/.test(t) && (t = t.replace(RegExp.$1, (e.getFullYear() + "").substr(4 - RegExp.$1.length)));
                for (var o in a) new RegExp("(" + o + ")").test(t) && (t = t.replace(RegExp.$1, 1 == RegExp.$1.length ? a[o] : ("00" + a[o]).substr(("" + a[o]).length)))
            } catch (n) {
            }
            return t
        }, getLocationParam: function (e) {
            var t = new RegExp("(^|&)" + e + "=([^&]*)(&|$)"), a = window.location.search.substr(1).match(t);
            return null != a ? decodeURIComponent(a[2]) : null
        }, addParam: function (t, a) {
            var o = e(".loginbox-con"), n = o.find('.third-icon-qq[data-point-2="qq"]'),
                i = o.find('.third-icon-wx[data-point-2="wx"]'), r = o.find('.third-icon-wb[data-point-2="wb"]'),
                c = o.find('.third-icon-fb[data-point-2="fb"]'), s = function (e) {
                    e.length && (e.attr("href").indexOf("?") > -1 ? e.attr("href", e.attr("href") + "&fac=" + a.fac + "&type=" + t) : e.attr("href", e.attr("href") + "?fac=" + a.fac + "&type=" + t))
                };
            s(n, t), s(i, t), s(r, t), c.length && s(c, t)
        }, countDown: function (e, o, n) {
            this.second || (this.second = n), this.second >= 0 ? (e.attr("disabled", !0), e.val(o + "(" + this.second + ")"), e.addClass("long"), this.second--, a.countTimer = setTimeout(function () {
                a.countDown(e, o)
            }, 1e3)) : (a.countTimer = null, e.removeClass("long"), e.attr("disabled", !1), e.val(t("编码.重新获取") || "重新获取"))
        }, resetCountDown: function (e) {
            a.countTimer && (a.countTimer = null, this.second = 0)
        }
    };
    return a
}), define("dypassport/com/user-view-iframe", ["jquery", "dypassport/com/user-view-util"], function (e, t) {
    var a = {
        init: function () {
            a.resetDomain()
        }, resetDomain: function () {
            var e = document.domain;
            try {
                e = e.split(".").slice(-2).join(".")
            } catch (t) {
                console.log && console.log(t)
            }
            document.domain = e
        }, checkLoginnedUserAuthData: function () {
            var e = {};
            return window.passport_code && "0" != window.passport_uid ? (e.passport_callback = window.passport_callback, e.code = window.passport_code, e.uid = window.passport_uid, e) : !1
        }, apiLoginCallback: function (a) {
            var o = t.getLocationParam(Vars.CALLBACK.login);
            if (o && window.parent && window.parent[o]) {
                var n = window.parent[o];
                n && e.isFunction(n) && n.call(window.parent, a)
            }
        }, dpCallback: function (a) {
            var o = t.getLocationParam(Vars.CALLBACK.dp);
            if (o && window.parent && window.parent[o]) {
                var n = window.parent[o];
                n && e.isFunction(n) && n.call(window.parent, a)
            }
        }
    };
    return a
}), define("dypassport/com/static-config", [], function () {
    var e = {
        areaConfig: [{area: "中国大陆", code: "86"}, {area: "中国澳门", code: "853"}, {
            area: "中国香港",
            code: "852"
        }, {area: "中国台湾", code: "886"}, {area: "阿尔巴尼亚", code: "355"}, {area: "阿尔及利亚", code: "213"}, {
            area: "阿富汗",
            code: "93"
        }, {area: "阿根廷", code: "54"}, {area: "阿拉伯联合酋长国", code: "971"}, {area: "阿鲁巴岛", code: "297"}, {
            area: "阿曼",
            code: "968"
        }, {area: "阿塞拜疆", code: "994"}, {area: "阿森松岛", code: "247"}, {area: "埃及", code: "20"}, {
            area: "埃塞俄比亚",
            code: "251"
        }, {area: "爱尔兰", code: "353"}, {area: "爱沙尼亚", code: "372"}, {area: "安道尔", code: "376"}, {
            area: "安哥拉",
            code: "244"
        }, {area: "安圭拉岛", code: "1264"}, {area: "安提瓜和巴布达", code: "1268"}, {area: "奥地利", code: "43"}, {
            area: "澳大利亚",
            code: "61"
        }, {area: "巴巴多斯", code: "1246"}, {area: "巴布亚新几内亚", code: "675"}, {area: "巴哈马", code: "1242"}, {
            area: "巴基斯坦",
            code: "92"
        }, {area: "巴拉圭", code: "595"}, {area: "巴勒斯坦", code: "970"}, {area: "巴林", code: "973"}, {
            area: "巴拿马",
            code: "507"
        }, {area: "巴西", code: "55"}, {area: "白俄罗斯", code: "375"}, {area: "百慕大", code: "1441"}, {
            area: "保加利亚",
            code: "359"
        }, {area: "北马里亚纳群岛", code: "1"}, {area: "贝宁", code: "229"}, {area: "比利时", code: "32"}, {
            area: "冰岛",
            code: "354"
        }, {area: "波多黎各", code: "1787"}, {area: "波黑", code: "387"}, {area: "波兰", code: "48"}, {
            area: "玻利维亚",
            code: "591"
        }, {area: "伯利兹", code: "501"}, {area: "博茨瓦纳", code: "267"}, {area: "博奈尔岛、圣尤斯特歇斯和萨巴岛", code: "599"}, {
            area: "不丹",
            code: "975"
        }, {area: "布基纳法索", code: "226"}, {area: "布隆迪", code: "257"}, {area: "朝鲜", code: "850"}, {
            area: "赤道几内亚",
            code: "240"
        }, {area: "丹麦", code: "45"}, {area: "德国", code: "49"}, {area: "迪戈加西亚岛", code: "246"}, {
            area: "东帝汶",
            code: "670"
        }, {area: "多哥", code: "228"}, {area: "多米尼加", code: "1767"}, {area: "多米尼加共和国", code: "1809"}, {
            area: "俄罗斯",
            code: "7"
        }, {area: "厄瓜多尔", code: "593"}, {area: "厄立特里亚", code: "291"}, {area: "法国", code: "33"}, {
            area: "法罗群岛",
            code: "298"
        }, {area: "法属波利尼西亚", code: "689"}, {area: "法属圭亚那", code: "594"}, {area: "梵蒂冈", code: "379"}, {
            area: "菲律宾",
            code: "63"
        }, {area: "斐济", code: "679"}, {area: "芬兰", code: "358"}, {area: "佛得角", code: "238"}, {
            area: "福克兰群岛",
            code: "500"
        }, {area: "冈比亚", code: "220"}, {area: "刚果", code: "242"}, {area: "刚果民主共和国", code: "243"}, {
            area: "哥伦比亚",
            code: "57"
        }, {area: "哥斯达黎加", code: "506"}, {area: "格林纳达", code: "1473"}, {area: "格陵兰", code: "299"}, {
            area: "格鲁吉亚",
            code: "995"
        }, {area: "古巴", code: "53"}, {area: "瓜德罗普", code: "590"}, {area: "关岛", code: "1671"}, {
            area: "圭亚那",
            code: "592"
        }, {area: "哈萨克斯坦", code: "7"}, {area: "海地", code: "509"}, {area: "韩国", code: "82"}, {
            area: "荷兰",
            code: "31"
        }, {area: "荷属圣马丁", code: "1721"}, {area: "黑山", code: "382"}, {area: "洪都拉斯", code: "504"}, {
            area: "基里巴斯",
            code: "686"
        }, {area: "吉布提", code: "253"}, {area: "吉尔吉斯斯坦", code: "996"}, {area: "几内亚", code: "224"}, {
            area: "几内亚比绍",
            code: "245"
        }, {area: "加拿大", code: "1"}, {area: "加纳", code: "233"}, {area: "加蓬", code: "241"}, {
            area: "柬埔寨",
            code: "855"
        }, {area: "捷克共和国", code: "420"}, {area: "津巴布韦", code: "263"}, {area: "喀麦隆", code: "237"}, {
            area: "卡塔尔",
            code: "974"
        }, {area: "开曼群岛", code: "1345"}, {area: "科摩罗和马约特岛", code: "269"}, {area: "科特迪瓦", code: "225"}, {
            area: "科威特",
            code: "965"
        }, {area: "克罗地亚", code: "385"}, {area: "肯尼亚", code: "254"}, {area: "库克群岛", code: "682"}, {
            area: "库拉索",
            code: "599"
        }, {area: "拉脱维亚", code: "371"}, {area: "莱索托", code: "266"}, {area: "老挝", code: "856"}, {
            area: "黎巴嫩",
            code: "961"
        }, {area: "立陶宛", code: "370"}, {area: "利比里亚", code: "231"}, {area: "利比亚", code: "218"}, {
            area: "列支敦士登",
            code: "423"
        }, {area: "留尼旺岛", code: "262"}, {area: "卢森堡", code: "352"}, {area: "卢旺达", code: "250"}, {
            area: "罗马尼亚",
            code: "40"
        }, {area: "马达加斯加", code: "261"}, {area: "马尔代夫", code: "960"}, {area: "马耳他", code: "356"}, {
            area: "马拉维",
            code: "265"
        }, {area: "马来西亚", code: "60"}, {area: "马里", code: "223"}, {area: "马其顿", code: "389"}, {
            area: "马绍尔群岛",
            code: "692"
        }, {area: "马提尼克岛", code: "596"}, {area: "毛里求斯", code: "230"}, {area: "毛里塔尼亚", code: "222"}, {
            area: "美国",
            code: "1"
        }, {area: "美属萨摩亚", code: "1684"}, {area: "美属维尔京群岛", code: "1340"}, {area: "蒙古", code: "976"}, {
            area: "蒙特塞拉特岛",
            code: "1664"
        }, {area: "孟加拉国", code: "880"}, {area: "秘鲁", code: "51"}, {area: "密克罗尼西亚", code: "691"}, {
            area: "缅甸",
            code: "95"
        }, {area: "摩尔多瓦", code: "373"}, {area: "摩洛哥", code: "212"}, {area: "摩纳哥", code: "377"}, {
            area: "莫桑比克",
            code: "258"
        }, {area: "墨西哥", code: "52"}, {area: "纳米比亚", code: "264"}, {area: "南非", code: "27"}, {
            area: "南苏丹",
            code: "211"
        }, {area: "瑙鲁", code: "674"}, {area: "尼加拉瓜", code: "505"}, {area: "尼泊尔", code: "977"}, {
            area: "尼日尔",
            code: "227"
        }, {area: "尼日利亚", code: "234"}, {area: "纽埃岛", code: "683"}, {area: "挪威", code: "47"}, {
            area: "诺福克岛",
            code: "6723"
        }, {area: "帕劳", code: "680"}, {area: "葡萄牙", code: "351"}, {area: "日本", code: "81"}, {
            area: "瑞典",
            code: "46"
        }, {area: "瑞士", code: "41"}, {area: "萨尔瓦多", code: "503"}, {area: "萨摩亚", code: "685"}, {
            area: "塞尔维亚",
            code: "381"
        }, {area: "塞拉利昂", code: "232"}, {area: "塞内加尔", code: "221"}, {area: "塞浦路斯", code: "357"}, {
            area: "塞舌尔",
            code: "248"
        }, {area: "沙特阿拉伯", code: "966"}, {area: "圣巴泰勒米岛", code: "590"}, {area: "圣多美和普林西比", code: "239"}, {
            area: "圣赫勒拿岛",
            code: "290"
        }, {area: "圣基茨和尼维斯", code: "1869"}, {area: "圣卢西亚", code: "1758"}, {area: "圣马丁", code: "590"}, {
            area: "圣马力诺",
            code: "378"
        }, {area: "圣皮埃尔和密克隆群岛", code: "508"}, {area: "圣文森特和格林纳丁斯", code: "1784"}, {area: "斯里兰卡", code: "94"}, {
            area: "斯洛伐克",
            code: "421"
        }, {area: "斯洛文尼亚", code: "386"}, {area: "斯威士兰", code: "268"}, {area: "苏丹", code: "249"}, {
            area: "苏里南",
            code: "597"
        }, {area: "所罗门群岛", code: "677"}, {area: "索马里", code: "252"}, {area: "塔吉克斯坦", code: "992"}, {
            area: "泰国",
            code: "66"
        }, {area: "坦桑尼亚", code: "255"}, {area: "汤加", code: "676"}, {area: "特克斯和凯科斯群岛", code: "1649"}, {
            area: "特立尼达和多巴哥",
            code: "1868"
        }, {area: "突尼斯", code: "216"}, {area: "图瓦卢", code: "688"}, {area: "土耳其", code: "90"}, {
            area: "土库曼斯坦",
            code: "993"
        }, {area: "托克劳", code: "690"}, {area: "瓦利斯和富图纳群岛", code: "681"}, {area: "瓦努阿图", code: "678"}, {
            area: "危地马拉",
            code: "502"
        }, {area: "委内瑞拉", code: "58"}, {area: "文莱", code: "673"}, {area: "乌干达", code: "256"}, {
            area: "乌克兰语",
            code: "380"
        }, {area: "乌拉圭", code: "598"}, {area: "乌兹别克斯坦", code: "998"}, {area: "希腊", code: "30"}, {
            area: "西班牙",
            code: "34"
        }, {area: "新加坡", code: "65"}, {area: "新喀里多尼亚", code: "687"}, {area: "新西兰", code: "64"}, {
            area: "匈牙利",
            code: "36"
        }, {area: "叙利亚", code: "963"}, {area: "牙买加", code: "1876"}, {area: "亚美尼亚", code: "374"}, {
            area: "也门",
            code: "967"
        }, {area: "伊拉克", code: "964"}, {area: "伊朗", code: "98"}, {area: "以色列", code: "972"}, {
            area: "意大利",
            code: "39"
        }, {area: "印度", code: "91"}, {area: "印度尼西亚", code: "62"}, {area: "英国", code: "44"}, {
            area: "英属维尔京群岛",
            code: "1284"
        }, {area: "约旦", code: "962"}, {area: "越南", code: "84"}, {area: "赞比亚", code: "260"}, {
            area: "乍得",
            code: "235"
        }, {area: "直布罗陀", code: "350"}, {area: "智利", code: "56"}, {area: "中非共和国", code: "236"}]
    };
    return e
}), define("dypassport/com/select-area", ["jquery", "shark/class", "shark/util/lang/1.0", "shark/util/template/2.0", "dypassport/com/static-config", "dypassport/com/i18n"], function (e, t, a, o, n, i) {
    var r = n.areaConfig, c = t({
        init: function (t) {
            this.config = e.extend(!0, {
                container: null,
                target: null,
                isEnglish: 0,
                selectClass: "selectCountry-avtive",
                trigonUp: "selectCountry-san1",
                extFnObj: {clickParent: e.noop, selectLi: e.noop}
            }, t || {}), this.config.target && (this.render(), this.getDoms(), this.bindEvent(), this.config.isEnglish && i.interprete())
        }, render: function () {
            var e = this,
                t = a.string.join('<div class="select-area-wrap">', '<input class="selectCountry-box-code js-selectCountry-box-code" type="text" name="areaCode" value="+86"/>', '<div class="selectCountry-san-box">', '<span class="selectCountry-san js-selectCountry-san"></span>', "</div>", '<ul class="select-country-list js-select-country-list clearfix">', "{{ each list as item index }}", '<li value="00{{ item.code }}">', '<span class="fl selectCountry-country" ', '{{ if isEnglish }} data-i18n="{{ item.area }}" {{ /if }}>{{ item.area }}', "</span>", '<span class="fr selectCountry-value">+{{ item.code }}</span>', "</li>", "{{ /each }} ", "</ul>", "</div>"),
                n = o.compile(t), i = n({list: r, isEnglish: e.config.isEnglish});
            this.config.target.append(i)
        }, getDoms: function () {
            var e = this.config.target;
            this.doms = {
                target: e,
                selectUl: e.find(".js-select-country-list"),
                codeInput: e.find(".js-selectCountry-box-code"),
                triangle: e.find(".js-selectCountry-san")
            }
        }, bindEvent: function () {
            var t = this.config, a = this.doms, o = function (e) {
                e.stopPropagation(), e.preventDefault()
            };
            t.container && t.container.length && t.container.on("click", function () {
                e(this).closest(".js-select-country-list").length || (a.selectUl.hide(),
                    a.triangle.removeClass(t.trigonUp))
            }), a.target.on("click", function (e) {
                o(e), a.selectUl.toggle(), a.triangle.toggleClass(t.trigonUp), t.extFnObj.clickParent && t.extFnObj.clickParent()
            }), a.selectUl.on("click", "li", function (n) {
                o(n), a.selectUl.find("li").removeClass(t.selectClass), e(this).addClass(t.selectClass), a.selectUl.hide(), a.triangle.toggleClass(t.trigonUp);
                var i = a.selectUl.find("." + t.selectClass + " .selectCountry-value").text() || "+86";
                a.codeInput.val(i), t.extFnObj.selectLi && t.extFnObj.selectLi(i)
            })
        }
    });
    return {
        init: function (e) {
            return new c(e)
        }
    }
}), define("dypassport/com/user-view-reg", ["jquery", "shark/observer", "shark/util/cookie/1.0", "dypassport/com/user-control", "dypassport/com/user-view-common", "dypassport/com/dp-reg", "dypassport/com/user-pw-strongth", "dypassport/com/user-view-util", "dypassport/com/user-view-iframe", "dypassport/com/select-area", "dypassport/com/verify/verify"], function (e, t, a, o, n, i, r, c, s, d, p) {
    function l(t) {
        var a = "";
        try {
            a = e.i18n.prop(t)
        } catch (o) {
        }
        return a
    }

    n.init();
    var u = {
            view: {
                el: {pop: "pop", shadow: "shadow", regform: "regform"},
                type: {login: "login", reg: "reg"},
                currentView: null
            },
            client_id: window.client_id || 1,
            lang: window.$SYS ? $SYS.lang : "cn",
            hmac_flash_ready: !1,
            step: 1,
            fac: "",
            salt: null,
            pwdStrength: "",
            CALLBACK: {
                reg: "passport_reg_callback",
                login: "passport_login_callback",
                close: "passport_close_callback",
                dp: "passport_dp_callback"
            },
            areaCode: "0086",
            closeStatus: 0,
            lock: {sms: !1, editName: !1, hasSendSMS: !1}
        }, f = {}, h = {}, g = {},
        v = '<span class="capsLock-tip js-capsLock"><i class="capsLockTip-triangle"></i>大写锁定已打开</span>',
        y = function () {
            var t = e(".loginbox-reg").find(".loginbox-p:eq(1)"), a = e(".js-capsLock");
            a.length <= 0 && t.append(v), a.removeClass("hide")
        }, w = function () {
            var t = e(".js-capsLock");
            t && t.length > 0 ? t.addClass("hide") : null
        }, m = navigator.userAgent, _ = m.indexOf("compatible") > -1 && m.indexOf("MSIE") > -1,
        k = m.indexOf("Trident") > -1 && m.indexOf("rv:11.0") > -1;
    f.control = o, f.PWStrength = r.ckPWStrength, f.Reg = r.ckPWStrength.reg, f.init = function () {
        s.init(), h.init(), f.view.init()
    }, f.view = {
        init: function () {
            f.view.$pop = e(".loginbox-con"), u.view.currentView = f.view.$pop.find('.loginbox-bd[data-type="reg"]'), f.view.$regform = f.view.$pop.find(".loginbox-reg form"), f.view.$voiceBtn = f.view.$pop.find(".js-sendvoice"), f.view.$thirds = f.view.$pop.find(".third-list a"), f.view._is_show = !1, setTimeout(function () {
                f.view.checkStkToLogin(), f.view.evt()
            }, 50), d.init({
                container: e("#loginbox"),
                target: e(".js-selectCountry-box"),
                isEnglish: "en" === u.lang,
                extFnObj: {
                    clickParent: function () {
                        t.trigger("dys.reg.phone.country", {step: u.step++, fac: u.fac})
                    }, selectLi: function (e) {
                        var t = f.view.$voiceBtn, a = t.val();
                        if (u.areaCode = String(e).replace("+", "00"), t.length && !t.hasClass("long")) {
                            var o = window.$SYS ? window.$SYS.foreign_captcha_type : 2, n = 1 === +o ? "语音" : "短信";
                            "0086" === u.areaCode ? t.val(a.replace(n, "短信")) : t.val(a.replace("短信", n))
                        }
                    }
                }
            }), g.init()
        }, el: function (e) {
            return e === u.view.el.pop ? f.view.$pop : e === u.view.el.regform ? f.view.$regform : void 0
        }, loginTip: function (t) {
            var a, o, n, i = t.type ? ".login-cheak-" + t.type : "", r = t.iconType || "", c = t.cont || "",
                s = 1e3 * t.delay || 1500;
            return a = u.view.currentView.find(i), o = '<span class="myuser-tip-icon myuser-tip-icon' + r + '"></span><span class="myuser-tip-text' + r + '">' + c + "</span>", n = e(o), f.view.hideTips(), a.html(n), setTimeout(function () {
                n.fadeOut("fast", function () {
                    n.remove()
                })
            }, s), n
        }, hideTips: function () {
            u.view.currentView.find("p.myuser-tip").empty()
        }, make: function () {
            return {pop: e.trim(e("#dytemp-loginbox").html()), shadow: e.trim(e("#dytemp-loginbox-shadow").html())}
        }, checkStkToLogin: function () {
            a.get("ltkid") && a.get("nickname") && a.get("uid") && !a.get("stk") && this.show()
        }, show: function (a, o) {
            var n = u.view.showParam;
            n && n.source && (u.fac = n.source), t.trigger("dys.reg.show", {
                step: u.step++,
                fac: u.fac
            }), n = e.isPlainObject(n) ? n : {}, e.extend(!0, u, n), u.callback || (u.callback = n.callback), c.addParam(u.view.type.reg, {fac: u.fac})
        }, hide: function () {
            f.view.$pop.fadeOut(function () {
                f.view._is_show = !1
            })
        }, toggle: function () {
            f.view._is_show ? f.view.hide() : f.view.show()
        }, redirect: function (e) {
            var t = f.view._redirect;
            return t ? (f.view._redirect = void 0, setTimeout(function () {
                location.href = t
            }, e || 50), !0) : !1
        }, callback: function (t) {
            if (e.isFunction(u.callback)) try {
                u.callback(t)
            } catch (a) {
            }
        }, evt: function () {
            var a = f.view.$pop, o = f.view.$thirds, n = f.view.$regform, i = {};
            a.find(".loginbox-close, .js-loginbox-bd-loginnow").click(function (a) {
                t.trigger("dys.com.user.reg.close.click", {
                    step: u.step++,
                    fac: u.fac
                }), f.view._evt_stop(a), f.view.$bd = e(".loginbox-bd:not(.hide)");
                var o = c.getLocationParam(u.CALLBACK.close);
                if (o && window.parent && window.parent[o]) {
                    var n = window.parent[o];
                    n && e.isFunction(n) && n.call(window.parent, u.closeStatus)
                }
            }), n.on("click", ".captcha-gt", function () {
                t.trigger("dys.reg.geetest.click", {step: u.step++, fac: u.fac, type: 1})
            }), a.on("change", "input,select", function (a) {
                var o = e(this), n = o.data("modified");
                if (!n) return !1;
                try {
                    switch (!0) {
                        case o.is('[data-type="reg"] [name="phoneNum"]'):
                            t.trigger("dys.reg.input.change", {name: "phoneNum", step: u.step++, fac: u.fac});
                            break;
                        case o.is('[data-type="reg"] [name="password"]'):
                            t.trigger("dys.reg.input.change", {name: "password", step: u.step++, fac: u.fac});
                            break;
                        case o.is('[data-type="reg"] [name="protocol"]'):
                            t.trigger("dys.reg.input.change", {
                                name: "protocol",
                                step: u.step++,
                                stat: o.is(":checked") ? 1 : 2,
                                fac: u.fac
                            });
                            break;
                        case o.is('[data-type="reg"] [name="phoneCaptcha"]'):
                            t.trigger("dys.reg.input.change", {
                                v_type: "0086" === u.areaCode ? "mes" : "voice",
                                name: "phoneCaptcha",
                                step: u.step++,
                                fac: u.fac
                            });
                            break;
                        case o.is('[name="reName"]'):
                            t.trigger("dys.reg.input.change", {
                                name: "reName",
                                step: u.step++,
                                fac: u.fac,
                                type: "sign"
                            })
                    }
                    n = !1, o.data("modified", !1)
                } catch (i) {
                }
            }).on("keyup mouseup", "input ,select", function () {
                e(this).data("modified", !0)
            }), o.on("mousedown", function () {
                var a = e(this), o = u.view.currentView.data("type"), n = a.attr("data-point-2"), i = "";
                switch (n) {
                    case"qq":
                        i = "qq";
                        break;
                    case"wx":
                        i = "weixin";
                        break;
                    case"wb":
                        i = "sinawb";
                        break;
                    case"fb":
                        i = "facebook"
                }
                "reg" === o && t.trigger("dys.com.thirds.entrance", e.extend({}, f.REGPageData, {
                    step: u.step++,
                    type: i,
                    fac: u.fac
                }))
            }), a.on("change", "input", function () {
                f.validate.check(this, "reg")
            }), n.on("keyup", 'input[name="password"]', function () {
                var t = e(this), a = f.PWStrength.PWStrength(t.val()), o = a.icon, n = a.cont, i = null;
                1 > o && (i = t), f.validate._showerr(l("编码." + n) || n, "pw", o + 1, i), u.pwdStrength = 10 * (o - 1)
            }).on("focusout", 'input[name="password"]', function () {
                var t = e(this), a = t.val(), o = a.length;
                0 === o ? f.validate._showerr(l("编码.密码不能为空") || "密码不能为空", "pw", 1, t) : (6 > o || o > 25) && f.validate._showerr(l("编码.密码长度不正确，仅限6~25个字符") || "密码长度不正确，仅限6~25个字符", "pw", 1, t), w()
            }).on("keyup change", 'input[name="password"]', function (t) {
                var a = e(this);
                i.same(a);
                var t = t || window.event, o = t.keyCode ? t.keyCode : t.which, n = t.shiftKey ? t.shiftKey : 16 == o,
                    r = e(t.target).val().length;
                if (r > 0) {
                    var c = e(t.target).val().charCodeAt(r - 1);
                    o >= 65 && 90 >= o && (c >= 65 && 90 >= c && !n || c >= 97 && 122 >= c && n ? _ || k || y() : w())
                }
            }).on("click", "span.pw-span", function () {
                var t = e(this);
                i.show(t)
            }), n.on("click", ".js-sendvoice", function () {
                f.view.registerCaptcha(), t.trigger("dys.reg.phones.get.code", {
                    v_type: "0086" === u.areaCode ? "mes" : "voice",
                    step: u.step++,
                    fac: u.fac
                })
            }), n.submit(function () {
                return f.view.submitReg(), t.trigger("dys.reg.phones.tosign", {step: u.step++, fac: u.fac}), !1
            }), i.same = function (e) {
                var t = e.val();
                "password" === e.attr("type") && e.next('input[type="text"]').val(t), "text" === e.attr("type") && e.prev('input[type="password"]').val(t)
            }, i.show = function (e) {
                var t = e.closest("div");
                e.hasClass("pw-hide") ? (e.removeClass("pw-hide").addClass("pw-show"), t.find('input[type="text"]').val(t.find('input[type="password"]').val()).css({zIndex: 1})) : (e.removeClass("pw-show").addClass("pw-hide"), t.find('input[type="text"]').css({zIndex: -1}))
            }
        }, _evt_stop: function (e) {
            e.stopPropagation(), e.preventDefault()
        }, checkCaptcha: function () {
            var t = g.verify ? g.verify.verifyData : {}, a = t.code_type;
            if (1 === a) return !0;
            if (!g.verify || !g.verify.instance) return e.dialog.tips_black("验证码加载失败，请稍后再试"), !1;
            var o = g.verify.instance.getResult().code;
            return o && 4 === o.length ? !0 : (3 === a ? g.verify.instance.show() : 2 === a && (e('[name="captcha_word"].verify-imgCode-input').addClass("verify-captcha-error"), setTimeout(function () {
                e('[name="captcha_word"].verify-imgCode-input').removeClass("verify-captcha-error")
            }, 2e3)), !1)
        }, captchaRefresh: function () {
            g.verify && g.verify.instance && g.verify.instance.refresh && g.verify.instance.refresh()
        }, getCodeVerifyParams: function () {
            var e = g.verify ? g.verify.verifyData : {},
                t = g.verify && g.verify.instance ? g.verify.instance.getResult() : {}, a = {
                    room_id: f.REGPageData.room_id,
                    code_verify_data: {code_type: e.code_type, code_token: e.code_token, code_data: {}}
                };
            if (1 === parseInt(e.code_type, 10)) {
                var o = {geetest_challenge: t.challenge, id: t.id, gt_version: "v4"};
                a.code_verify_data.code_data = o
            } else {
                var n = g.verify && g.verify.instance ? g.verify.instance.getResult().code : "";
                a.code_verify_data.code_data.code = n
            }
            return a
        }, registerCaptcha: function (a) {
            var o = f.view.$regform, n = f.view.$voiceBtn, i = o.find(".loginbox-p input[name]"),
                r = o.find('input[name="phoneNum"]'), s = {};
            if (g.verify && g.verify.instance && g.verify.instance.getResult && (s = g.verify.instance.getResult()), !u.lock.sms && (e('input[class="ipt showpw1 notsub"]').val() || e('input[class="ipt showpw1 notsub"]').val(e('input[class="ipt"]').val()), i.each(function () {
                    return (flag = f.validate.check(this, "phoneCaptcha")) ? void 0 : !1
                }), flag && f.view.checkCaptcha())) {
                if (g.verify && g.verify.instance && !(fast = g.verify.instance.getResult())) return void g.verify.instance.show();
                var d = e.extend(!0, {
                    phoneNum: r.val(),
                    areaCode: u.areaCode,
                    lang: u.lang
                }, f.view.getCodeVerifyParams());
                a && (d.confirm = a);
                var p = function (a) {
                    if (0 === +a.error) c.countDown(n, n.val(), 60); else if (130018 === +a.error) {
                        var o = '该手机号码已绑定斗鱼帐号<span class="account">' + a.msg + "</span>，请直接登录，若继续操作，将解除与原帐号的绑定";
                        e.dialog({
                            skin: "confirm-change-phone", content: o, okVal: "继续注册", ok: function () {
                                f.view.registerCaptcha(1), t.trigger("dys.reg.poccupy.con", {
                                    step: u.step++,
                                    fac: u.fac
                                })
                            }, cancelVal: "取消", cancel: function () {
                                f.view.captchaRefresh(), t.trigger("dys.reg.poccupy.cancel", {
                                    step: u.step++,
                                    fac: u.fac
                                })
                            }, width: "280px", lock: !0, opacity: .1
                        })
                    } else -1004 === parseInt(a.error, 10) ? (f.view.captchaRefresh(), g.verify.verifyData.code_token = a.data.code_token, e.dialog.tips_black("验证码失效")) : (f.view.captchaRefresh(), a.msg && f.validate._showerr(a.msg, "phone", 1, r), 5 === parseInt(a.error, 10) && t.trigger("dys.reg.plessthirty.fail", {
                        step: u.step++,
                        fac: u.fac
                    }));
                    u.lock.sms = !1
                }, l = function () {
                    e.dialog.tips_black("请求错误"), u.lock.sms = !1
                };
                u.lock.sms = !0, u.lock.hasSendSMS = !0, f.control.API.regVerifyPhone(d, p, l)
            }
        }, submitReg: function () {
            var t = f.view.$regform, o = t.find(".btn-sub"), n = t.find("input[name]"), i = !0, r = null, c = {};
            if (!t.data("submit") && (n.each(function () {
                    return (i = f.validate.check(this, "reg")) ? void("areaCode" === this.name ? c.areaCode = u.areaCode : c[this.name] = this.value) : !1
                }), i)) {
                if (g.verify && g.verify.instance && !(r = g.verify.instance.getResult())) return void g.verify.instance.show();
                if (!u.lock.hasSendSMS) return void e.dialog.tips_black("请先完成短信验证");
                var s = u.view.cpsDirthName || a.get("ditchName"), d = f.view.getCodeVerifyParams();
                d.pwdStrength = u.pwdStrength, c = e.extend(!0, c, d), s && (c.ditchName = s), t.data("submit", !0), o.val(l("编码.注册中…") || "注册中…"), f.view.reg(c, function (e) {
                    f.view.callback(e), t.data("submit", e), o.val(l("编码.注册") || "注册")
                })
            }
        }, reg: function (o, n) {
            o.password && (o.password = CryptoJS.MD5(o.password).toString()), o.password2 = o.password;
            var i = function (a) {
                var i = a.error, r = 0 === i;
                return n(r), 0 === i ? (t.trigger("dys.reg.phone.succ", {
                    step: u.step++,
                    em: o.msg,
                    fac: u.fac
                }), void f.view.subLoginJsonp(a.data)) : (2 === i ? (e.dialog.alert(l("编码.当前ip输入验证码时错误次数过多，请等待一天后在重新尝试") || "当前ip输入验证码时错误次数过多，请等待一天后在重新尝试"), t.trigger("dys.reg.phone.fail", {
                    em: "当前ip输入验证码时错误次数过多，请等待一天后在重新尝试！",
                    step: u.step++,
                    fac: u.fac
                })) : (c.resetCountDown(f.view.$voiceBtn), e.dialog.tips_black(a.msg), t.trigger("dys.reg.phone.fail", {
                    em: a.msg,
                    step: u.step++,
                    fac: u.fac
                })), void f.view.captchaRefresh())
            }, r = function () {
                n(!1), e.dialog.tips_black(l("编码.注册过程中发生错误，请稍候再试") || "注册过程中发生错误，请稍候再试"), t.trigger("dys.reg.phone.fail", {
                    step: u.step++,
                    em: "注册过程中发生错误，请稍候再试",
                    fac: u.fac
                }), f.view.captchaRefresh()
            };
            o.redirect_url = location.href, o.client_id = u.client_id, o.reg_src = "web", window.cpsid && (o.cpsid = window.cpsid), f.REGPageData && (o = e.extend({}, o, f.REGPageData));
            try {
                o.sm_did = SMSdk.getDeviceId() || ""
            } catch (s) {
            }
            o.did = a.get("did") || "", o.lang = u.lang, f.control.API.regByPhone(o, i, r)
        }, subLoginJsonp: function (a) {
            if (a.url) {
                var o = function (t) {
                    if (0 !== t.error) return void e.dialog.tips_black(t.msg);
                    e.dialog.tips_black(l("编码.注册成功") || "注册成功", 1);
                    var a = c.getLocationParam(u.CALLBACK.reg);
                    if (a && window.parent && window.parent[a]) {
                        var o = window.parent[a];
                        if (o && e.isFunction(o)) return void o.call(window.parent, {error: 0})
                    } else {
                        var n = window.passport_callback;
                        setTimeout(function () {
                            n ? location.replace(n) : location.reload()
                        })
                    }
                }, n = function () {
                    e.dialog.tips_black(l("编码.注册过程中发生错误，请稍候再试") || "注册过程中发生错误，请稍候再试"), t.trigger("dys.reg.phone.fail", {
                        em: "注册过程中发生错误，请稍候再试",
                        step: u.step++,
                        fac: u.fac
                    })
                };
                f.control.API.subSiteLogin(a.url, a, o, n)
            }
        }
    }, f.validate = {
        check: function (t, a) {
            var o = e(t), n = o.attr("name"), i = e.trim(o.val());
            if (o.attr("placeholder") || o.val(i), "geetest_challenge" === n || "geetest_validate" === n || "geetest_seccode" === n) return !0;
            if ("captcha_word" === n) {
                if (f.view._type === u.view.type.reg && g.verify && g.verify.instance) return !0;
                if (g.verify && g.verify.instance) {
                    var r = g.verify ? g.verify.verifyData : {};
                    if (2 === r.code_type) {
                        var c = g.verify.instance.getResult().code;
                        if (!c || 4 !== c.length) return e('[name="captcha_word"].verify-imgCode-input').addClass("verify-captcha-error"), setTimeout(function () {
                            e('[name="captcha_word"].verify-imgCode-input').removeClass("verify-captcha-error")
                        }, 2e3), !1
                    }
                }
            }
            if ("" === i) return "phoneCaptcha" === a && "phoneCaptcha" === n ? !0 : (f.validate._fx_err_ipt(t), !1);
            var s = !0;
            return "reg" === a ? "phoneNum" === n ? s = f.validate._ck_phonenum(t) : "password" === n ? s = f.validate._ck_reg_password(t) : "phoneCaptcha" === n ? s = f.validate._ck_yzphonenum(t) : "email" === n ? s = f.validate._ck_reg_email(t) : "protocol" === n && (s = f.validate._ck_reg_protocol(t)) : "phoneCaptcha" === a && ("phoneNum" === n ? s = f.validate._ck_phonenum(t) : "password" === n && (s = f.validate._ck_reg_password(t))), s ? (f.validate._fx_rig_ipt(t), !0) : !1
        }, _ck_phonenum: function (t) {
            var a = e.trim(e(t).val()), o = /^[0-9]*$/.test(a);
            return o ? !0 : (e.dialog.tips_black(l("编码.手机号码不正确") || "手机号码不正确"), !1)
        }, _ck_yzphonenum: function (t) {
            var a = e(t).val();
            return 6 != a.length ? (e.dialog.tips_black(l("编码.校验码格式不正确") || "校验码格式不正确"), !1) : !0
        }, _showerr: function (e, t, a, o) {
            f.view.loginTip({type: t, iconType: a, cont: e}), f.validate._fx_err_ipt(o)
        }, _showinfo: function (e, t) {
            f.view.errorTip({element: t, cont: e, type: "info", delay: 2})
        }, _fx_rig_ipt: function (t) {
            e(t).removeClass("ipt-err")
        }, _fx_success_ipt: function (t) {
            var a = e(t);
            f.view._type !== u.view.type.login && (a.hasClass("ipt-success") || (a.is(":text") || a.is(":password")) && (a.addClass("ipt-success"), setTimeout(function () {
                a.removeClass("ipt-success")
            }, 2e3)))
        }, _fx_err_ipt: function (t) {
            var a = e(t), o = a, n = null;
            a.hasClass("ipt-err") || (a.hasClass("ipt-need-parent") && (a.parent().addClass("ipt-parent-err"), setTimeout(function () {
                a.parent().removeClass("ipt-parent-err")
            }, 1500)), (a.is(":text") || a.is(":password")) && ("password" !== a.attr("name") && "password2" !== a.attr("name") || (a.hasClass("notsub") ? (o = a.prev("input"), n = a) : n = a.next("input")), o.addClass("ipt-err"), n && n.addClass("ipt-err"), a.parent().find("span.pw-span").addClass("ipt-err-span"), setTimeout(function () {
                o.removeClass("ipt-err"), n && n.removeClass("ipt-err"), a.parent().find("span.pw-span").removeClass("ipt-err-span")
            }, 1500)))
        }, _ck_reg_password: function (t) {
            var a = e(t).val(), o = a.length, n = f.PWStrength.PWStrength(a), i = n.icon, r = n.cont;
            return 6 > o || o > 25 ? (f.validate._showerr(l("编码.密码长度不正确") || "密码长度不正确，仅限6~25个字符", "pw", 1, t), !1) : 0 === i ? (f.validate._showerr(l("编码." + r) || r, "pw", 1, t), !1) : !0
        }, _ck_reg_email: function (t) {
            var a = /^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+.[a-zA-Z]{2,4}$/,
                o = e(t).val();
            return a.test(o) ? !0 : (e.dialog.tips_black(l("编码.邮箱地址格式不正确") || "邮箱地址格式不正确"), !1)
        }, _ck_reg_protocol: function (t) {
            var a = e(t);
            return a.prop("checked") ? !0 : (e.dialog.tips_black(l("编码.您还没有同意注册协议") || "您还没有同意注册协议"), !1)
        }
    }, h.init = function () {
        h.getParams(), h.completeUserInfo()
    }, h.getParams = function () {
        var a, o;
        try {
            window.top && window.top.DYS && window.top.DYS.sub && e.isPlainObject(window.top.DYS.sub.getDefaultFied()) && (o = window.top.DYS.sub.getDefaultFied() || {}, t.trigger("dys.com.dpdata", o)), window.top && window.top.UserJsSDK && (a = window.top.UserJsSDK.POSSPORT || {}, a && (u.view.showParam = a.get("param", "show") || {}, u.view.cpsDirthName = a.get("cpsDirthName", "cps") || "", u.source = u.view.showParam.source || ""))
        } catch (n) {
        }
    }, h.completeUserInfo = function () {
        var t;
        try {
            f.REGPageData = {
                room_id: 0,
                cate_id: 0,
                tag_id: 0,
                child_id: 0,
                vid: 0,
                fac: u.source || ""
            }, window.top && window.top.$ROOM && (t = window.top.$ROOM, f.REGPageData.room_id = t.room_id || 0, f.REGPageData.cate_id = t.category_id || 0, f.REGPageData.tag_id = t.cate_id || 0, f.REGPageData.child_id = t.child_id || 0, f.REGPageData.vid = t.vid || 0), e(".loginbox-con").find(".third-list a").attr("href", function () {
                var t = e(this).attr("href"), a = e.extend({}, f.REGPageData);
                return delete a.fac, t += "&", t += e.param(a)
            })
        } catch (a) {
        }
    }, g._is_init = !1, g.init = function (a) {
        if (!g._is_init) {
            var o = u.view.currentView.find(".captcha-gt"), n = !!o.length;
            if (n) {
                g._is_init = !0;
                var i = {op_type: "22", gt_version: "v4", room_id: f.REGPageData.room_id};
                g.verify = new p({
                    xhrParams: {url: "/iframe/checkGeeTest", data: i},
                    options: {
                        target: o,
                        area: "#loginbox-con",
                        v9Area: o,
                        lang: window.$SYS && "en" === $SYS.lang ? "en" : "zh-cn",
                        shadow: {opacity: 0}
                    },
                    success: function () {
                        var a = g.verify.verifyData;
                        3 !== a.code_type && 1 !== a.code_type || e('input[class="phone-send js-sendvoice fl"]').trigger("click"), t.trigger("dys.reg.phones.limitcode.succ", {
                            step: u.step++,
                            fac: u.fac
                        })
                    },
                    error: function () {
                        t.trigger("dys.reg.phones.limitcode.fail", {step: u.step++, fac: u.fac, em: "极限验证出错"})
                    }
                })
            }
        }
    };
    var b = {
        init: function (t) {
            t = t || {}, e.isFunction(t.onAuto) && (f.control._on_auto_login = t.onAuto), f.init()
        },
        show: f.view.show,
        hide: f.view.hide,
        toggle: f.view.toggle,
        exitif: f.view.exitif,
        enter: f.view.enter,
        IFRAME: s,
        util: c
    };
    return window.return_loadhmOk = function () {
        hmac_flash_ready = !0
    }, b
}), define("dypassport/com/i18n", ["jquery"], function (e) {
    var t = [];
    return {
        init: function (t) {
            var a = this;
            a.language = t, e.i18n.properties({
                name: "translations",
                path: "/i18n/",
                mode: "map",
                checkAvailableLanguages: !1,
                language: t,
                cache: !0,
                async: !1,
                callback: function () {
                    a.interprete(), a.addClass(), a.hideNoInterEl()
                }
            })
        }, interprete: function () {
            var t = e("[data-i18n]");
            try {
                t.each(function (t, a) {
                    var o = e(a), n = o.html(), i = o.data("i18n"), r = e.i18n.prop(i);
                    o.attr("title") && o.attr("title", r), o.attr("placeholder") && o.attr("placeholder", r);
                    var c = o.is("h1,h2,h3,h4,h5,h6,div,p,span,a,option");
                    c && e.trim(n) && o.html(r);
                    var s = o.is("input,select,button"), d = o.val();
                    s && d && !o.attr("placeholder") && o.val(r)
                })
            } catch (a) {
            }
        }, addClass: function () {
            e("body").addClass("lang_" + this.language)
        }, hideNoInterEl: function () {
            try {
                e.each(t, function (t, a) {
                    var o = "[data-i18n='" + a + "']";
                    e(o).addClass("hide")
                })
            } catch (a) {
            }
        }
    }
}), define("dypassport/page/reg/app", ["jquery", "dypassport/com/user-view-reg", "dypassport/com/i18n"], function (e, t, a) {
    var o = {language: {en: "en_us"}};
    e(function () {
        var e = t.util.getLocationParam("type"), n = $SYS.lang;
        n && "en" === n && a.init(o.language[n]), t.init(), e ? t.show(e) : t.show("login")
    })
});