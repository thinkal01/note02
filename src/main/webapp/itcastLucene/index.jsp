<%@ page pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html class="w3c">
<head>
    <meta charset="utf-8">
    <title>传智播客搜索 - 干净、安全、可信任的搜索引擎</title>
    <link rel="dns-prefetch" href="//s0.qhimg.com">
    <link rel="dns-prefetch" href="//s1.qhimg.com">
    <link rel="dns-prefetch" href="//p0.qhimg.com">
    <link rel="dns-prefetch" href="//p1.qhimg.com">
    <meta name="keywords" content="传智播客搜索,搜索,搜索引擎,网页搜索,视频搜索,影视搜索,新闻搜索">
    <meta name="description" content="传智播客旗下搜索引擎服务，包含网页、新闻、影视等搜索产品，为您带来更安全、干净的搜索体验。传智专业安全保证，杜绝欺诈、木马网站。">
    <style>html, body {
        height: 100%
    }

    html, body, form, input, span, p, img, ul, ol, li, dl, dt, dd {
        margin: 0;
        padding: 0;
        border: 0
    }

    ul, ol {
        list-style: none
    }

    body {
        background: #fff;
        font: 12px/1.5 arial, sans-serif;
        text-align: center
    }

    a {
        text-decoration: none
    }

    a:hover {
        text-decoration: underline
    }

    .page-wrap {
        position: relative;
        min-height: 100%;
        _height: 100%
    }

    #hd {
        min-width: 920px;
        height: 36px;
        line-height: 36px;
        padding: 0 20px 0 0;
        text-align: right
    }

    #hd a {
        color: #888;
        text-decoration: none
    }

    #hd a:hover {
        color: #3eaf0e
    }

    #hd .hd-sep {
        margin: 0 7px;
        color: #eaeaea
    }

    #main {
        width: 842px;
        margin: 60px auto 0;
        padding-bottom: 100px
    }

    #logo {
        margin: 0 auto 55px;
        position: relative;
        height: 43px;
        left: 20px
    }

    #logo-xp {
        height: 49px;
        background: url(http://p0.qhimg.com/t018f70c2c1d54f7e5a.png) no-repeat center top;
        margin-bottom: 50px;
        position: relative;
        display: none
    }

    #logo-xp a {
        width: 31px;
        height: 27px;
        display: block;
        position: absolute;
        left: 527px;
        top: 2px
    }

    #search-box {
        padding-left: 140px;
        text-align: left
    }

    #input-container {
        width: 500px;
        height: 34px;
        display: inline-block;
        border: 2px solid #3eaf0e;
        box-shadow: 0 2px 1px #f0f0f0;
        position: relative;
        z-index: 1
    }

    #suggest-align {
        height: 32px;
        position: relative
    }

    #input {
        width: 485px;
        height: 22px;
        margin: 5px 0 5px 8px;
        outline: 0;
        background: #fff;
        font-size: 16px;
        line-height: 22px;
        vertical-align: top
    }

    #search-button {
        width: 100px;
        height: 38px;
        _height: 40px;
        display: inline-block;
        margin-left: 5px;
        outline: 0;
        border: 1px solid #3eaf0e;
        *border: 0;
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
        -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
        -moz-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
        background: url(http://p1.qhimg.com/d/_onebox/btn-98-114.png) no-repeat #3eaf0e;
        color: #fff;
        font: bold 16px arial, sans-serif;
        vertical-align: top;
        cursor: pointer
    }

    #search-button.hover {
        border: 1px solid #4bbe11;
        *border: 0;
        background-position: 0 -38px
    }

    #search-button.mousedown {
        border: 1px solid #4bbe11;
        *border: 0;
        background-position: 0 -76px
    }

    #slogan {
        padding-top: 80px
    }

    #slogan a {
        color: #3eaf0e;
        text-decoration: none
    }

    #slogan a:hover {
        color: #ef730f
    }

    #footer {
        width: 100%;
        height: 36px;
        line-height: 36px;
        text-align: left;
        color: #959595;
        position: absolute;
        left: 0;
        bottom: 0
    }

    #footer p {
        margin: 0 20px
    }

    #footer a {
        color: #959595;
        margin: 0 5px 0 3px
    }

    #footer span {
        float: right;
        font-style: normal;
        color: #959595
    }

    .ac_wrap {
        position: absolute;
        z-index: 100;
        overflow-y: hidden;
        font-size: 14px;
        background: #fff;
        text-align: left;
        line-height: 20px
    }

    .ac_wrap_inner {
        z-index: 10;
        border: 1px solid #999
    }

    .ac_wrap ul {
        background: #fff url(http://p1.qhimg.com/d/_hao360/sou360/bg_sug.jpg) no-repeat bottom right
    }

    .ac_wrap li {
        padding-left: 8px;
        line-height: 26px;
        cursor: pointer
    }

    .ac_wrap li.hover {
        background: #e6e6e6
    }

    .ac_wrap li.selected {
        background: #eee
    }

    .ac_toolbar {
        font-size: 12px;
        position: absolute;
        bottom: 0;
        right: 10px;
        height: 24px;
        line-height: 24px;
        text-align: right;
        color: #e5e5e5
    }

    .ac_toolbar a {
        margin-left: 15px;
        color: #66F
    }

    .ac_bgIframe {
        position: absolute;
        top: 0;
        left: 0;
        z-index: -1;
        width: expression(this.parentNode.offsetWidth);
        height: 2000px
    }

    #so-nav-tabs {
        font-size: 14px;
        text-align: left;
        padding-left: 150px;
        position: relative;
        z-index: 999
    }

    #so-nav-tabs a, #so-nav-tabs strong {
        height: 31px;
        line-height: 31px;
        display: inline-block;
        margin-right: 18px;
        *margin-right: 15px;
        color: #333
    }

    #so-nav-tabs a:hover {
        color: #3eaf0e;
        text-decoration: none
    }

    #so-nav-tabs strong {
        color: #3eaf0e
    }

    #so-nav-more {
        width: 50px;
        position: absolute;
        z-index: 0;
        top: 30px;
        right: 195px;
        margin-left: 370px;
        *margin-left: -70px;
        padding: 0;
        line-height: 30px;
        text-align: left;
        border: 1px #dedede solid;
        background: #fff;
        list-style: none;
        display: none
    }

    #so-nav-more a {
        width: 100%;
        display: block;
        margin: 0;
        text-indent: 10px
    }

    #so-nav-more a:hover {
        background: #f1f1f1
    }

    #so-nav-tabs-more {
        width: 45px;
        background: url(http://p0.qhimg.com/d/inn/56e29abd/arr.gif) 32px 15px no-repeat
    }

    .desktop {
        width: 148px;
        height: 30px;
        display: block;
        background: url(http://p1.qhimg.com/t01e739d9d685bc2d8f.png) no-repeat -5px -6px;
        margin: 0 auto
    }

    .desktop:hover {
        background-position: -5px -60px
    }

    .tools {
        width: 560px;
        margin: 100px auto 0 165px;
        display: none
    }

    .tools dt {
        height: 30px;
        border-top: 1px solid #ededed;
        position: relative;
        z-index: 0
    }

    .tools dt div {
        width: 80px;
        color: #666;
        background: #fff url(http://p0.qhimg.com/d/inn/888902b7/arr.png) no-repeat 75px -19px;
        position: relative;
        margin: -10px auto 0;
        padding-right: 10px;
        cursor: pointer
    }

    .tools dt div.close {
        background-position: 75px 8px
    }

    .tools dd {
        width: 100%;
        height: 90px;
        overflow: hidden
    }

    .tools dd a {
        width: 70px;
        height: 70px;
        color: #8c8c8c;
        text-decoration: none;
        border-radius: 5px;
        display: block;
        padding: 18px 0 0;
        float: left;
        outline: 0
    }

    .tools dd a:hover {
        color: #4eb51b;
        background: #f9f9f9;
        text-decoration: none
    }

    .tools dd span {
        width: 30px;
        height: 30px;
        display: inline-block;
        background: url(http://p0.qhimg.com/d/inn/af75f7f9/icons.png);
        margin: 0 auto 5px
    }

    .tools-tq span {
        background-position: 0 0
    }

    a.tools-fy span {
        background-position: 0 -32px
    }

    a.tools-rl span {
        background-position: 0 -64px
    }

    a.tools-jsq span {
        background-position: 0 -256px
    }

    a.tools-kd span {
        background-position: 0 -128px
    }

    a.tools-kb span {
        background-position: 0 -160px
    }

    a.tools-hcp span {
        background-position: 0 -192px
    }

    a.tools-mnxc span {
        background-position: 0 -224px
    }

    a:hover.tools-tq span {
        background-position: -32px 0
    }

    a:hover.tools-fy span {
        background-position: -32px -32px
    }

    a:hover.tools-rl span {
        background-position: -32px -64px
    }

    a:hover.tools-jsq span {
        background-position: -32px -256px
    }

    a:hover.tools-kd span {
        background-position: -32px -128px
    }

    a:hover.tools-kb span {
        background-position: -32px -160px
    }

    a:hover.tools-hcp span {
        background-position: -32px -192px
    }

    a:hover.tools-mnxc span {
        background-position: -32px -224px
    }</style>
    <link rel="stylesheet" href="http://s1.qhimg.com/i360/;css;index_pop/v3202.css">
    <script type="text/javascript">var Tool = {
        isCN: function (e) {
            var t = new RegExp("[\u4e00-\u9fa5]");
            return t.test(e)
        }, cookie: {
            get: function (e) {
                var t = [], n = {}, r = [], i = document.cookie;
                t = i.split(";");
                for (var s = 0, o = t.length; s < o; s++) {
                    r = t[s].replace(/^\s+/, "").replace(/\s+$/, ""), r = r.split("="), n[r[0]] = r[1];
                    if (r[0] == e) return r[1]
                }
                return e ? null : n
            }, set: function (e, t, n, r, i, s) {
                var o = encodeURIComponent(e) + "=" + encodeURIComponent(t);
                n instanceof Date && (o += "; expires=" + n.toGMTString()), r && (o += "; path=" + r), i && (o += "; domain=" + i), s && (o += "; secure"), document.cookie = o
            }
        }, getURI: function (e) {
            var t = Tool.getJsonHref();
            if (typeof e == "string" && e.indexOf(",") < 0) return t[e];
            var n = [];
            typeof e == "string" && e.indexOf(",") > 0 ? n = e.split(",") : _keys instanceof Array && (n = e);
            var r = [];
            for (var i = 0; i < n.length; i++) t[n[i]] && r.push(t[n[i]]);
            return r.join("&")
        }, getJsonHref: function () {
            var e = window.location.href, t = window.location.search.replace("?", ""),
                n = arguments[0] == "undefined" ? "" : arguments[0], r = t.split("&"), i = {};
            for (var s = 0; s < r.length; s++) {
                var o = r[s].split("=");
                Tool.isCN(o[1]) ? i[o[0]] = o[0] + "=" + encodeURIComponent(o[1]) : i[o[0]] = r[s]
            }
            return i
        }
    }</script>
    <script type="text/javascript">function getAbversion() {
        var e = Tool.cookie.get("__guid"), t = "";
        if (/[&?]_abversion=([abcd])/gi.test(location.search)) t = RegExp.$1; else if (e) {
            var n = parseInt(e.substr(e.length - 2, 2));
            n > 0 && n <= 15 ? t = "a" : n > 15 && n <= 30 ? t = "b" : n > 30 && n <= 45 && (t = "c")
        }
        return t
    }

    var so = {};
    so.web = {comm: {src: Tool.getURI("src") ? Tool.getURI("src").replace("src=", "") : "", abv: ""}}, function () {
        if (so.web.comm.src == "wd_xp" || window.location.search.indexOf("src=haotips2") > -1 || window.location.search.indexOf("src=hao_tips2") > -1) document.createStyleSheet ? (css = document.createStyleSheet(), css.addRule("#logo", "display:none;"), css.addRule("#slogan", "display:none;"), css.addRule("#logo-xp", "display:block;")) : (css = document.createElement("STYLE"), css.type = "text/css", css.innerHTML = "#logo{display:none;}#slogan{display:none;}#logo-xp{display:block;}", document.getElementsByTagName("head")[0].appendChild(css))
    }()</script>
    <base target="_blank">
</head>

<body>
<div class="page-wrap">

    <div id="hd">
        <a id="setHomepage" href="http://www.so.com/help/help_1_3.html" target="_blank"
           onclick="var a=false,e=function(c,d){var b=c.match(RegExp(d+'\\b[ \\/]?([\\w\\.]*)','i'));return b?b.slice(1):['','']}(navigator.userAgent,'(maxthon|360se|360ee|theworld|se|greenbrowser|qqbrowser)');try{a=external.twGetRunPath.toLowerCase().indexOf('360se')>-1}catch(f){}if(!(a||e[0]))try{this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.so.com/');return false}catch(g){}"
           data-linkid="5">设传智搜索为主页</a>
        <span class="hd-sep">|</span>
        <a href="http://hao.360.cn/?src=so_home" target="_blank" data-linkid="7">导航</a>
        <span id="hd-user" class="hd-user">
            <span class="hd-sep">|</span>
            <a href="http://i.360.cn/login?src=pcw_so&destUrl=http%3A%2F%2Fwww.so.com%2F" id="user-login"
               target="_self">登录</a>&nbsp;&nbsp;
            <a href="http://i.360.cn/reg?src=pcw_so&destUrl=http%3A%2F%2Fwww.so.com%2F" id="user-reg"
               target="_blank">注册</a></span>
    </div>

    <div id="main" style="margin-top:80px;">
        <%--<p id="logo" style="height:auto;margin-bottom:0;">
            <a style="outline:none;" href="javascript:void(0)">
                <img style="padding-right: 80px;" src="image/logo.gif"/>
            </a>
        </p>--%>
        <br/>
        <%--<p id="logo" style="display:none;"><img src="http://p1.qhimg.com/d/_onebox/search.png" width="260" height="43">--%>
        <p id="logo"><img src="http://p1.qhimg.com/d/_onebox/search.png" width="260" height="43">
        </p>
        <p id="logo-xp"><a href="javascript:void(0)"></a></p>
        <div id="search-box" style="padding-top: 10px;">
            <form action="query?currentPage=1" target="_self" method="post">
				<span id="input-container">
					<div id="suggest-align">
						<input type="text" name="keywords" id="input" suggestWidth="501px" autocomplete="off"
                               x-webkit-speech><cite id="suggest-tp"></cite>
					</div>
				</span>
                <input type="submit" id="search-button" value="搜索一下" onmouseover="this.className='hover'"
                       onmousedown="this.className='mousedown'" onmouseout="this.className=''">
            </form>
        </div>
        <div id="slogan">
            <a href="javascript:void(0)" class="desktop" data-linkid="ctrl"></a>
        </div>
    </div>
</div>
<!--<script src="http://s1.qhimg.com/static/cfc3468c259f23e2.js"></script>-->

<script type="text/javascript" src="http://s4.qhimg.com/static/9cecc5c6d3eea79b.js"></script>
<script>createSuggest({
    inputElement: document.getElementById("input"),
    alignElement: document.getElementById("suggest-align"),
    urlPrefix: "http://sug.so.360.cn/suggest/word?callback=suggest_so&encodein=utf-8&encodeout=utf-8&word=",
    enableHoverState: !1,
    afterSetSelectedIndex: function () {
        Tool.cookie.set("sgtclick", this.acValue + "|" + this.selectedIndex + "|" + this.filteredValue)
    },
    beforeSelectItem: function (e) {
        Tool.cookie.set("sgtclick", e.word + "|" + e.index + "|" + e.inputValue)
    }
}), document.getElementById("input").focus()</script>
<script src="http://s0.qhimg.com/monitor/;monitor/4f1853e5.js"></script>
<script src="http://s0.qhimg.com/lib/jquery/183.js"></script>
<script>(function () {
    try {
        var e = monitor.log;
        monitor.log = function () {
            var t = Tool.cookie.get("_soguid"), n = arguments;
            n[0]._soguid = t || "", e.apply(monitor, n);
            if (!t) {
                var r = {
                    call: function (e) {
                        var t = document.createElement("IFRAME");
                        t.src = e.domain + "/__osoc.html#key=" + e.key, t.id = "osocPage", t.width = "0", t.height = "0", t.style.display = "none", document.body.appendChild(t)
                    }, init: function (e) {
                        var t = this;
                        t.call(e)
                    }
                };
                r.init({domain: "http://so.360.cn", key: "__guid"}), window.cookieReady = function (e) {
                    t = e.substr(1), document.cookie = "_soguid=" + encodeURIComponent(t) + "; expires=Tue, 19 Jan 2038 03:14:07 GMT; path=/; domain=.so.com"
                }
            }
        }
    } catch (t) {
    }
})()</script>
<script>(function () {
    function u() {
        var e = Tool.cookie.get("__sid");
        if (e) return e;
        var t = monitor.util.getGuid(), n = t + "." + +(new Date);
        return Tool.cookie.set("__sid", n), n
    }

    var e = null, t = null, n = $("#so-nav-more"), r = $("#so-nav-tabs-more");
    r.hover(function () {
        clearTimeout(t), e = setTimeout(function () {
            n.show()
        }, 200)
    }, function () {
        clearTimeout(e), t = setTimeout(function () {
            n.hide()
        }, 200)
    }), n.hover(function () {
        clearTimeout(t), e = setTimeout(function () {
            n.show()
        }, 200)
    }, function () {
        clearTimeout(e), t = setTimeout(function () {
            n.hide()
        }, 200)
    });
    if (so.web.comm.src) {
        var i = "home_" + so.web.comm.src;
        switch (so.web.comm.src) {
            case"hao":
                i = "360sou_home_hao";
                break;
            case"wd_xp":
                i = "360sou_home_xp"
        }
        $("#from").val(i)
    }
    if (so.web.comm.src == "hao" || Tool.cookie.get("tools_temp") == "1") {
        $("#tools").show(), $("#slogan").hide(), Tool.cookie.set("tools_temp", "1");
        var s = $("#tools dd"), o = $("#tools_bar");
        Tool.cookie.get("tools_switch") == "0" && (s.hide(), o.addClass("close")), o.bind("click", function () {
            var e = 0;
            s.is(":visible") ? (s.fadeOut(), o.addClass("close"), e = 0) : (s.fadeIn(), o.removeClass("close"), e = 1);
            var t = new Date;
            t.setDate(t.getDate() + 365), Tool.cookie.set("tools_switch", e, t), monitor && monitor.setConf("clickUrl", "http://s.360.cn/sou/home_link.gif").log({
                on: e,
                abv: so.web.comm.abv
            }, "click")
        }), $("#tools dd a").bind("mousedown", function () {
            monitor && monitor.setConf("clickUrl", "http://s.360.cn/sou/home_link.gif").log({
                tools: $(this).text(),
                abv: so.web.comm.abv
            }, "click")
        })
    }
    $("#so-nav-tabs a[data-s]").bind("click", function () {
        var e = $(this), t = $.trim($("#input").val()), n = e.attr("data-s");
        t && n && (n = n.replace(/%q%/g, encodeURIComponent(t)), e.attr("href", n))
    }), $("form").bind("submit", function () {
        var e = (+(new Date)).toString().substr(8);
        $("#ms").val("s" + e)
    });
    var a = $.trim($("#input").val());
    $("a[data-linkid]").bind("mousedown", function () {
        var e = $(this).attr("data-linkid");
        monitor.setConf("clickUrl", "http://s.360.cn/sou/home_link.gif").log({lk: e, abv: so.web.comm.abv}, "click")
    }), $("#search-button").bind("mousedown", function () {
        monitor.setConf("clickUrl", "http://s.360.cn/sou/home_click.gif").log({q: a, eng: 0, sguid: u()}, "click")
    }), $(".ac_wrap").delegate("li", "mousedown", function () {
        monitor.setConf("clickUrl", "http://s.360.cn/sou/home_suggst.gif").log({
            input: a,
            click: $(this).attr("acvalue"),
            sguid: u()
        }, "click"), monitor.setConf("clickUrl", "http://s.360.cn/sou/suggest.gif").log({
            pro: "so_com",
            pid: "home",
            mod: "suggest",
            input: a,
            click: $(this).attr("acvalue"),
            pos: $(this).attr("ac_index"),
            sguid: u()
        }, "click")
    }), monitor.setConf("clickUrl", "http://s.360.cn/sou/home.gif").log({
        src: so.web.comm.src,
        ref: document.referrer,
        abv: so.web.comm.abv,
        guid: Tool.cookie.get("__guid")
    }, "click")
})()</script>
<script src="http://s0.qhimg.com/i360/;js;pass_api_/seed,log/v3202.js"></script>
<script>(function () {
    QHPass.resConfig.src = "pcw_so", QHPass.resConfig.cookie_domains = ["so|360"], QHPass.resConfig.postCharset = "utf-8", QHPass.resConfig.loginOpts.loginType = "quick", $("#user-login").bind("click", function () {
        return QHPass.login(function () {
            window.location.reload()
        }), !1
    }), $("#user-reg").bind("click", function () {
        return QHPass.reg(function () {
            window.location.reload()
        }), !1
    }), $("#user-logout").bind("click", function () {
        return QHPass.logout(), !1
    }), QHPass.getUserInfo(function (e) {
        if (typeof e != "undefined") {
            var t = '<span class="hd-sep">|</span><a href="http://i.360.cn/?src=pcw_so" target="_target">' + e.username + '</a>&nbsp;&nbsp;<a href="http://login.360.cn/?src=pcw_so&op=logout&destUrl=http%3A%2F%2Fwww.so.com%2F" target="_self">\u9000\u51fa</a>';
            $("#hd-user").html(t), monitor.setConf("clickUrl", "http://s.360.cn/sou/login.gif").log({
                src: so.web.comm.src,
                ref: document.referrer,
                qid: e.qid,
                abv: so.web.comm.abv
            }, "click")
        }
    })
})()</script>
<script>
    (function (e, t, n, r, i) {
        function s(i) {
            e._iwtLoading = 1, n = t.createElement("script"), n.src = r.URL, i = t.getElementsByTagName("script"), i = i[i.length - 1], i.parentNode.insertBefore(n, i)
        }

        r = {
            UA: "UA-360-000003",
            NO_FLS: 1,
            WITH_REF: 0,
            URL: "/resource/js/iwt-min.js"
        }, e._iwt ? e._iwt.track(r, i) : (e._iwtTQ = e._iwtTQ || []).push([r, i]), !e._iwtLoading && s()
    })(this, document)
</script>
</body>
</html>