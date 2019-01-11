<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>传智播客搜索</title>
    <link rel="dns-prefetch" href="//s0.qhimg.com">
    <link rel="dns-prefetch" href="//s1.qhimg.com">
    <link rel="dns-prefetch" href="//p0.qhimg.com">
    <link rel="dns-prefetch" href="//p1.qhimg.com">
    <!--[if IE 6]>
    <script>try {
        document.execCommand("BackgroundImageCache", !1, !0)
    } catch (e) {
    }</script><![endif]-->
    <script>var FELOG = {};
    FELOG['BEHEAD'] = new Date().getTime();
    var ENV_MONITOR = {}, ENV_FLAG = {};
    var so_login = 0, so_qid = "";
    var PageLine = {
        queues: {release: []}, fill: function (e) {
            var t = this;
            t.save(e)
        }, save: function (e) {
            var t = null, n = this;
            typeof e != "function" ? t = function () {
                Display.moheLog(e)
            } : t = e, n.queues.release.push(t), n.isReleased && n.release("release")
        }, on: function (e, t) {
            var n = this;
            n.queues[e] || (n.queues[e] = []), typeof t == "function" && n.queues[e].push(t)
        }, trigger: function (e, t) {
            var n = this, r = 0, i = [];
            if (!n.queues[e]) return !1;
            i = n.queues[e];
            while (r < i.length) i[r].call(this, t), r++
        }, release: function (e) {
            function r() {
                var e = n.shift();
                e && typeof e == "function" && e();
                if (n.length <= 0) return t.isReleased = !0, !1;
                r()
            }

            var t = this, n = t.queues[e];
            if (!n) return !1;
            r()
        }
    }</script>
    <style>body {
        background: #fff;
        color: #000
    }

    body, th, td {
        font-family: arial
    }

    html, body, ul, ol, li, dl, dd, h1, h2, h3, h4, h5, h6, pre, form, input, button, textarea, p, th, td {
        margin: 0;
        padding: 0
    }

    p, form, ol, ul, li, h3, menu {
        list-style: none
    }

    table, img {
        border: 0
    }

    img, object, select, input, textarea, button {
        vertical-align: middle
    }

    th {
        text-align: left
    }

    h1, h2, h3, h4, h5, h6, input, textarea, select, cite, em, i, b, strong, th {
        font-size: 100%;
        font-style: normal
    }

    ins, s, u, del {
        text-decoration: none
    }

    em, cite {
        font-style: normal
    }

    em {
        color: #c00
    }

    a em {
        text-decoration: underline
    }

    .f14 {
        font-size: 14px
    }

    .form {
        clear: both;
        zoom: 1
    }

    .round {
        background: url('http://p0.qhimg.com/d/bg.png') no-repeat -304px 0;
        border: 1px solid #b6b6b6;
        border-color: #7b7b7b #b6b6b6 #b6b6b6 #7b7b7b;
        display: inline-block;
        height: 30px;
        margin-right: 3px;
        vertical-align: top;
        width: 533px
    }

    .input_key {
        -webkit-appearance: none;
        background: #fff;
        border: 0;
        font: 16px/22px arial;
        height: 22px;
        margin: 5px 0 0 7px;
        outline: 0;
        padding: 0;
        width: 520px
    }

    .ie6 .round, .ie7 .round {
        margin-right: 5px
    }

    #warper {
        min-width: 940px;
        _zoom: 1
    }

    #head, #container {
        padding-left: 20px
    }

    #head {
        font-size: 12px;
        margin-top: 23px;
        _margin-top: 20px;
        margin-bottom: 1.5em;
        z-index: 888
    }

    #head, .form {
        position: relative
    }

    #head .placeholder, -webkit-input-placeholder, -moz-placeholder {
        color: #999
    }

    .button {
        background: url('http://p0.qhimg.com/d/bg.png') no-repeat -202px 0;
        display: inline-block;
        height: 34px;
        vertical-align: top;
        width: 97px;
        z-index: 0
    }

    .s_btn {
        background: #ddd url('http://p0.qhimg.com/d/bg.png');
        border: 0;
        cursor: pointer;
        font-size: 14px;
        height: 32px;
        padding-top: 2px \9;
        padding: 0;
        width: 95px
    }

    .s_btn_h {
        background-position: -100px 0
    }

    .slogan {
        display: none
    }

    .notice {
        font-family: "宋体";
        left: 20px;
        position: relative;
        top: 8px
    }

    .f14 {
        font-size: 14px
    }

    .autospell {
        margin: 0 15px 10px 0
    }

    .jc a {
        color: #c00
    }

    .skin-c .slogan {
        color: #ccc;
        display: inline-block;
        font-size: 12px;
        line-height: 34px;
        margin-left: 7px
    }

    .result .res-list, .result-d .res-d-list {
        font-size: 13px;
        line-height: 18px;
        margin-bottom: 1.5em
    }

    .result .res-list {
        word-break: break-all
    }

    .result-d .res-d-list {
        margin-bottom: 0
    }

    .result .res-list h3, .result-d .res-d-list h3 {
        font-weight: 400;
        font-size: 16px;
        padding-bottom: 1px
    }

    .ie6 .skin-c .slogan {
        font-family: "宋体";
        margin-top: 10px;
        vertical-align: top
    }

    .skin-c .slogan .slogo {
        background: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat 0 -60px;
        display: inline-block;
        height: 15px;
        margin-right: 9px;
        overflow: hidden;
        vertical-align: text-bottom;
        width: 91px
    }

    .result .res-list .res-title, .result-d .res-d-list .res-d-title {
        white-space: nowrap
    }

    .result .res-list .res-title {
        white-space: normal;
        word-break: break-all
    }

    .result .res-list .mark-nowrap {
        white-space: nowrap
    }

    .res-list .res-desc .timer {
        color: #666
    }

    .skin-c .slogan .slogo em {
        visibility: hidden
    }

    .result .res-list p.res-desc, .result-d .res-d-list p.res-d-desc {
        font-size: 1em;
        line-height: 1.5em;
        text-align: left;
        word-break: break-all;
        word-wrap: break-word;
        z-index: 1
    }

    .result-d .res-d-list .res-d-desc cite {
        color: green
    }

    #main {
        float: left;
        width: 540px
    }

    #container {
        position: relative;
        _zoom: 1
    }

    .ie6 #container {
        width: 980px
    }

    .ie6 .skin-c #head {
        width: 910px
    }

    .result .res-list p.erro, .res-d-list p.erro {
        background: #ffdede url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat -200px -647px;
        border: 1px solid #f6c5c5;
        color: #c00;
        display: inline-block;
        *display: inline;
        font-size: 12px;
        height: 18px;
        line-height: 18px;
        *line-height: 20px;
        margin: 5px 0 3px;
        _overflow: hidden;
        padding-left: 21px;
        zoom: 1
    }

    .result .res-list p.erro a, .res-d-list p.erro a {
        text-decoration: none
    }

    .result .res-list p.erro a, .result .res-list p.erro a:hover, .res-d-list p.erro a, .res-d-list p.erro a:hover {
        color: #c00
    }

    .icon-official {
        background-color: #6fb963;
        border-radius: 2px;
        color: #FFF;
        display: inline-block;
        font-size: 12px;
        height: 17px;
        margin-left: 3px;
        text-align: center;
        vertical-align: top;
        width: 56px
    }

    .m-icp {
        left: 0;
        margin-left: 10px;
        position: absolute;
        top: 0;
        z-index: 5
    }

    .m-rz-arr {
        background: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat -200px -130px;
        display: block;
        height: 7px;
        left: -4px;
        position: absolute;
        top: 8px;
        width: 5px
    }

    .ie6 .icon-official, .ie7 .icon-official, .ie8 .icon-official, .ie9 .icon-official {
        line-height: 18px;
        width: 52px
    }

    .m-icp .m-icp-content {
        background: #fff;
        background-image: -webkit-gradient(linear, 0% 0, 0% 90%, from(#f7f7f7), to(#fff));
        background-image: -moz-linear-gradient(0% 40% 270deg, #f7f7f7, #fff);
        border: 1px #ccc solid;
        border-radius: 2px;
        box-shadow: 1px 1px 1px #ccc;
        padding: 0 0 10px
    }

    .m-icp .icon-close {
        cursor: pointer;
        font-size: 18px;
        height: 25px;
        position: absolute;
        right: 0;
        text-align: center;
        top: 0;
        width: 25px;
        z-index: 5
    }

    .ie6 .m-icp {
        width: 340px
    }

    .m-icp-rz div.m-icp-content {
        border: 1px solid #c8e9c3
    }

    .result .res-list .tip-info {
        background: #f5f5f5;
        background-image: -webkit-gradient(linear, 0% 0, 0% 90%, from(#f7f7f7), to(#fff));
        background-image: -moz-linear-gradient(0% 40% 270deg, #f7f7f7, #fff);
        border: 1px #ccc solid;
        border-radius: 3px;
        cursor: pointer;
        display: inline-block;
        height: 18px;
        margin-left: 8px;
        position: relative;
        vertical-align: middle
    }

    .result .res-list .tip-icp-null {
        color: #adaead;
        cursor: default
    }

    .result .res-list .tip-hospital {
        background: #f2ffef url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat -196px -114px;
        border: 1px solid #acdca3
    }

    .ie6 .m-icp .icon-close {
        font-size: 12px;
        top: 5px
    }

    .result .res-list .tip-info i {
        color: #acacae;
        display: inline-block;
        padding: 0 2px
    }

    .result .res-list .tip-info b {
        color: #77c;
        font-weight: lighter;
        margin: 0 3px 0 2px
    }

    .result .res-list .tip-info cite {
        display: inline-block;
        height: 18px;
        left: 2px;
        overflow: hidden;
        position: relative;
        vertical-align: top;
        width: 10px
    }

    .result .res-list .tip-hospital b {
        color: #4c9141;
        padding-left: 20px
    }

    .result .res-list .tip-info cite b {
        font-size: 10px;
        font-size: 11px \9;
        left: -7px;
        position: relative;
        top: -2px;
        top: 0 \9
    }

    .ie6 .result .res-list .tip-info {
        height: 18px;
        line-height: 18px
    }

    .ie7 .result .res-list .tip-icp cite b {
        font-size: 12px
    }

    .ie7 .result .res-list .tip-icp cite b, .ie6 .result .res-list .tip-icp cite b {
        top: -2px
    }

    .ie6 .result .res-list .tip-info .icp-type {
        vertical-align: bottom
    }

    .ie7 .result .res-list .tip-info .icp-type {
        vertical-align: top
    }

    #side {
        float: left;
        left: 145px;
        position: relative;
        top: 0;
        width: 240px
    }

    #side .atom-adv, #side .atom-r-onebox {
        margin-bottom: 10px
    }

    .result .res-list cite, a.g {
        color: green
    }

    .favurl {
        background-repeat: no-repeat;
        background-position: 0 1px;
        padding-left: 20px
    }

    .bbs {
        color: #707070;
        display: block;
        padding-top: 3px
    }

    a.m:visited {
        color: #606
    }

    .m, a.m {
        color: #666;
        zoom: 1
    }

    .nums {
        color: #999;
        font-size: 12px
    }

    #page {
        clear: both;
        padding-top: 1.45em;
        white-space: nowrap
    }

    #page, #page a, #more, #page strong {
        font-size: 14px
    }

    #rs {
        background: #eff2fa;
        display: none;
        margin: 20px 0 0;
        padding: 8px 0;
        width: 100%
    }

    #search {
        padding: 35px 0 16px 20px
    }

    #footer {
        border-top: 1px solid #e8e8e8;
        color: #999;
        font-size: 12px;
        margin-top: 1.3em
    }

    #no-result {
        color: #333;
        font-size: 14px;
        margin-top: 30px
    }

    #page a, #page strong {
        background: white;
        border: 1px solid #e7ecf0;
        display: inline-block;
        height: 22px;
        line-height: 22px;
        margin-right: 5px;
        text-align: center;
        text-decoration: none;
        vertical-align: middle;
        width: 23px
    }

    #page strong {
        border: 0
    }

    #rs th {
        font-size: 14px;
        font-weight: 400;
        line-height: 19px;
        text-align: left;
        vertical-align: top;
        white-space: nowrap
    }

    #rs td {
        width: 5%
    }

    #footer p {
        line-height: 22px;
        padding: 15px 0 5px;
        text-align: center
    }

    #no-result p {
        margin-bottom: 1em
    }

    #footer p a {
        color: #999;
        font-size: 12px;
        margin: 0 8px;
        text-decoration: none
    }

    #page .n {
        font-family: "宋体";
        line-height: 24px;
        overflow: hidden;
        width: 60px
    }

    #rs .tt {
        font-weight: 700;
        padding: 0 10px 0 20px
    }

    #page a:hover {
        background: #ebebeb
    }

    #page a#spre, #page a#snext {
        font-family: "宋体";
        _height: 20px;
        _overflow: hidden;
        _padding-top: 2px;
        width: 64px
    }

    #no-result p span, #no-result li {
        font-family: 宋体
    }

    .toask {
        line-height: 24px;
        margin: 0 0 20px
    }

    .ac_wrap {
        background-color: #FFF;
        font-size: 14px;
        line-height: 20px;
        overflow-y: hidden;
        position: absolute;
        text-align: left;
        z-index: 100
    }

    .ac_wrap_inner {
        border: 1px solid #999;
        z-index: 10
    }

    .ac_toolbar {
        bottom: 0;
        color: #e5e5e5;
        font-size: 12px;
        height: 24px;
        line-height: 24px;
        position: absolute;
        right: 10px;
        text-align: right
    }

    .ac_bgIframe {
        height: 100%;
        left: 0;
        position: absolute;
        top: 0;
        width: 100%;
        z-index: -1
    }

    .ac_wrap ul {
        background: url(http://p1.qhimg.com/d/_hao360/sou360/bg_sug.jpg) no-repeat bottom right white;
        margin: 0;
        padding: 0
    }

    .ac_wrap li {
        cursor: pointer;
        line-height: 26px;
        list-style: none;
        padding-left: 8px
    }

    .ac_toolbar a {
        color: #66F;
        margin-left: 15px
    }

    .toask .ico-ask {
        background: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat -192px -563px;
        display: inline-block;
        height: 24px;
        margin-right: 10px;
        vertical-align: middle;
        width: 24px
    }

    .ac_wrap li.hover {
        background-color: #e6e6e6
    }

    .ac_wrap li.selected {
        background-color: #eee
    }

    #remove_mark {
        color: #000;
        font-size: 14px;
        margin: 10px 0
    }

    #suggests {
        background: #fff;
        border: 1px solid #817f82;
        left: 20px;
        position: absolute;
        top: 31px;
        visibility: hidden;
        width: 533px;
        z-index: 999
    }

    #warning_info {
        font-size: 14px;
        font-weight: 800;
        margin: 0 15px 10px 0
    }

    #warning_info2 {
        background: #f6f6f6;
        border: 1px #e6e6e6 solid;
        color: #333;
        font-family: arial;
        margin: 0 15px 20px 0;
        padding: 10px;
        width: 615px
    }

    #tabS {
        background: #fff;
        border-bottom: 1px #e0e0e0 solid;
        height: 34px;
        min-width: 820px;
        padding-left: 20px;
        _padding-left: 0;
        position: fixed;
        _position: relative;
        top: 0;
        width: 100%;
        z-index: 1000
    }

    #no-result strong {
        display: block;
        font-weight: lighter;
        margin: 10px 0
    }

    #no-result ul {
        margin-left: 40px
    }

    #no-result li {
        color: #26a038;
        font-size: 12px;
        line-height: 22px;
        list-style: inside disc
    }

    #suggests a {
        color: #343434;
        cursor: pointer;
        display: block;
        font-size: 14px;
        height: 25px;
        line-height: 25px;
        overflow: hidden;
        padding-left: 8px;
        text-decoration: none
    }

    #warning_info2 dt {
        background: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat -202px -649px;
        font-size: 14px;
        font-weight: 700;
        line-height: 14px;
        margin-bottom: 8px;
        padding-left: 20px
    }

    #warning_info2 dd {
        font-size: 12px;
        line-height: 20px
    }

    #tabS span {
        position: relative;
        top: 0;
        vertical-align: top
    }

    #mod-channel a {
        height: 34px;
        line-height: 34px;
        margin-right: 10px;
        padding: 0 2px;
        position: relative;
        top: 0;
        vertical-align: middle;
        _vertical-align: top
    }

    #no-result li span {
        color: #333
    }

    #no-result li a {
        color: #26a038
    }

    #warning_info2 p a, #tabS .tabS-title {
        display: inline-block
    }

    #tabS .tabS-title {
        color: #999;
        font-size: 12px;
        font-weight: 400;
        line-height: 32px;
        padding: 0;
        top: 2px \9;
        _top: 4px
    }

    #tabS .tabS-channel {
        _margin-left: 20px
    }

    #suggests a.hover {
        background-color: #a8d5fc
    }

    #tabS .tabS-channel a {
        background-color: none;
        color: #333;
        display: inline-block;
        font-size: 14px;
        outline: 0;
        text-decoration: none
    }

    #mod-channel a.cur, #mod-channel a:hover {
        border-bottom: 2px solid #3EAF0E;
        color: #3EAF0E;
        height: 32px
    }

    #mod-engs a.cur {
        border: 1px #e0e0e0 solid;
        border-top: 0;
        border-bottom: 1px #FFF solid;
        color: #5c5c5c;
        padding: 0
    }

    .ie6 #warning_info2 p b, .ie7 #warning_info2 p b {
        top: -16px
    }

    #tabS #tabS-right {
        display: inline-block;
        float: right;
        position: relative;
        _position: absolute;
        _right: 0;
        width: 420px
    }

    #tabS #mod-engs {
        _left: 10px;
        _margin-left: 0;
        _position: absolute;
        _top: 2px
    }

    #tabS #mod-engs a {
        background-image: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png);
        display: inline-block;
        height: 34px;
        line-height: 35px;
        overflow: hidden;
        text-indent: 28px;
        width: 85px;
        font-size: 12px
    }

    #tabS #tabS-right.short {
        width: 130px
    }

    .ie9 #tabS #tabS-right.short {
        width: 100px
    }

    #mod-engs a, #tabS .setting, #tabS .close {
        background-repeat: no-repeat;
        background-position: 0 0
    }

    .ws-total {
        color: #000;
        font-size: 13px;
        font-weight: 700;
        margin: -10px 0 5px
    }

    .ws-null {
        font-size: 12px;
        margin: 0 0 20px 20px
    }

    .atom-webscan {
        border: 1px #eee solid;
        height: 150px;
        margin: 0 0 25px;
        overflow: hidden;
        width: 534px
    }

    table.res-site {
        font-size: 13px;
        line-height: 20px;
        margin-left: 1em
    }

    .ws-null a {
        color: #00C
    }

    table.res-site i {
        font-size: 20px;
        _font-size: 14px;
        left: 0;
        position: relative;
        _position: static;
        top: 2px;
        _top: 0
    }

    table.res-site a {
        margin-right: 24px
    }

    .ie6 .ws-total {
        position: relative
    }

    .atom-webscan .ele-score {
        border-right: 1px #eee solid;
        color: #000;
        float: left;
        font-size: 12px;
        height: 135px;
        padding-top: 20px;
        width: 160px
    }

    .atom-webscan .ele-score span {
        display: block;
        line-height: 22px
    }

    #mod-engs .srh {
        background-position: 8px -416px;
        _background-position: 8px -417px
    }

    #mod-engs .baidu {
        background-position: 8px -449px;
        _background-position: 8px -450px;
        border-bottom: 1px #e0e0e0 solid
    }

    #mod-engs .google {
        background-position: 8px -482px;
        _background-position: 8px -483px
    }

    #tabS .close {
        background: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat -97px -566px
    }

    .ie6 #warper {
        min-width: 100%
    }

    .ie6 #head, .ie7 #head {
        width: 85%
    }

    .ie6 #mod-engs .srh {
        height: 30px
    }

    #tabS .close:hover {
        background-position: -97px -588px
    }

    .ie6 #mod-engs .srh i {
        font-size: 12px;
        position: relative;
        top: -2px
    }

    #tabS #tabS-right .oth {
        float: right;
        margin-right: 25px;
        _margin-right: 0;
        position: relative;
        top: 6px
    }

    #tabS #mod-engs a.baidu {
        vertical-align: top \9
    }

    #tabS #tabS-right .oth a {
        display: inline-block;
        height: 22px;
        margin-left: 10px;
        _margin-right: 5px;
        opacity: .7;
        width: 24px;
        filter: Alpha(opacity=70)
    }

    #tabS #tabS-right .oth a:hover {
        opacity: 1;
        filter: Alpha(opacity=100)
    }

    .atom-webscan .ele-score a, .atom-webscan .wstip-b, .atom-webscan .wstip-s {
        background-image: url(http://p0.qhimg.com/t0176aef79974582088.png);
        background-repeat: no-repeat
    }

    .res-ads {
        margin: 0 0 8px;
        width: 540px
    }

    .favicon_img {
        height: 16px;
        margin-right: 5px;
        vertical-align: text-bottom;
        width: 16px
    }

    .m-search {
        margin: 3px 0
    }

    .med_item {
        margin: 5px 0
    }

    .s_site {
        padding-top: 3px
    }

    .icon-normal-bd, .icon-normal-qvod {
        color: #666
    }

    .icon-normal-qvod {
        font-family: "arial";
        font-size: 12px
    }

    .res-video, .res-video-ready {
        color: #666;
        font-size: 12px;
        height: 22px;
        line-height: 22px
    }

    .res-video-close {
        color: #77c;
        display: none;
        float: left;
        margin-left: 8px;
        text-decoration: none
    }

    .res-video-ready {
        margin: 5px 0
    }

    .s_site input {
        margin: 0;
        padding: 0
    }

    .s_site label {
        margin: 0 18px 0 3px
    }

    .res-linkinfo cite {
        color: green
    }

    .res-video span, .res-video-ready span {
        float: left;
        margin: 0 5px
    }

    span.qvod-text {
        margin-left: 0
    }

    .res-video em, .res-video-ready em {
        color: #000;
        display: inline;
        float: left;
        height: 22px;
        max-width: 50px;
        overflow: hidden;
        text-decoration: none
    }

    .res-video em {
        color: #666
    }

    .med_item h3 span {
        color: green;
        font-size: 13px
    }

    .atom-webscan .wstip-b {
        display: inline-block;
        float: left;
        height: 66px;
        margin: 20px;
        width: 66px
    }

    .atom-webscan .wstip-s {
        display: inline-block;
        height: 16px;
        margin: 4px 5px 0;
        vertical-align: top;
        width: 16px
    }

    .atom-webscan .wstip-null {
        background-position: 0 0
    }

    .atom-webscan .wstip-low {
        background-position: -81px 0
    }

    .atom-webscan .wstip-mid {
        background-position: -165px 0
    }

    .atom-webscan .wstip-high {
        background-position: -250px 0
    }

    .atom-webscan .wstip-safe {
        background-position: -334px 0
    }

    .atom-webscan .wstip-s-unknown {
        background-position: 0 -80px
    }

    .atom-webscan .wstip-s-safe {
        background-position: -25px -80px
    }

    .atom-webscan .wstip-s-danger {
        background-position: -52px -80px
    }

    .atom-webscan .ele-list {
        color: #666;
        font-size: 13px;
        line-height: 25px;
        margin: -2px 0 0 280px;
        width: 234px
    }

    .g-mohe .seemore {
        color: #77C
    }

    .m-search .m-s-word {
        border-width: 1px;
        border-color: #a9a9a9 #d0d0d0 #d0d0d0 #a9a9a9;
        border-style: solid;
        height: 22px;
        width: 184px
    }

    .m-search .m-s-btn {
        height: 26px;
        margin-left: 7px;
        width: 71px
    }

    .res-video .mt-tv, .res-video .icon-more {
        display: none
    }

    .res-video .icon-last {
        display: inline
    }

    .atom-webscan .ele-score a {
        background-position: -88px -80px;
        color: #000;
        display: inline-block;
        height: 25px;
        line-height: 25px;
        margin-top: 8px;
        text-align: center;
        text-decoration: none;
        width: 135px
    }

    .atom-webscan .ele-score em {
        color: #000;
        font-size: 36px
    }

    .atom-webscan .ele-score i {
        font-size: 12px;
        margin-left: 5px
    }

    .atom-webscan .ele-score cite {
        width: 110px
    }

    .atom-webscan .ele-score b {
        font-weight: lighter
    }

    .atom-webscan .ele-list li {
        border-bottom: 1px #eee solid
    }

    .res-list .res-video cite, .res-list .res-video-ready cite {
        color: #666;
        float: left
    }

    .atom-webscan .ele-list li label {
        display: inline-block;
        width: 86px
    }

    #m-google {
        height: 0;
        overflow: hidden
    }

    .res-video-ready .icons, .res-video-ready .icon-btn .btn-left, .res-video-ready .icon-btn .btn-right {
        background-image: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png);
        background-repeat: no-repeat;
        background-position: 0 0;
        cursor: pointer;
        display: inline-block
    }

    .res-video-player {
        clear: both;
        display: none;
        height: 420px;
        width: 535px
    }

    .so-clean {
        margin-bottom: 20px
    }

    .ui-tag {
        background-color: #6fb963;
        border-radius: 2px;
        color: #FFF;
        font-family: tahoma \9;
        font-size: 12px;
        margin: 0 5px 0 0;
        padding: 2px 3px;
        text-align: center
    }

    .so-short-video {
        clear: both;
        margin: 5px 0
    }

    .so-doc {
        background: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat -196px -413px;
        color: #666;
        display: block;
        font-size: 12px;
        line-height: 24px;
        padding-left: 20px
    }

    .so-doc-pdf {
        background-position: -196px -525px
    }

    .so-doc-txt {
        background-position: -196px -498px
    }

    .so-doc-ppt {
        background-position: -196px -470px
    }

    .so-doc-xls {
        background-position: -196px -442px
    }

    .so-doc-doc {
        background-position: -196px -414px
    }

    .tabS-hd-user {
        font-size: 12px;
        position: absolute;
        right: 5px;
        top: 10px
    }

    .icon-tomato-red, .icon-tomato-yellow, .icon-tomato-green {
        background: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat 0 -358px;
        padding-left: 18px
    }

    .icon-tomato-yellow {
        background-position: 0 -378px
    }

    .icon-tomato-red {
        background-position: 0 -398px
    }

    .res-dianping {
        color: #666;
        margin-bottom: 2px;
        *margin-bottom: 1px
    }

    .ui-open {
        background-image: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png);
        background-position: 0 -561px;
        display: inline-block;
        float: left;
        height: 15px;
        width: 15px
    }

    .so-tomato {
        font-size: 12px;
        margin-bottom: 20px;
        position: relative;
        z-index: 1
    }

    .so-clean h4 {
        font-size: 14px;
        line-height: 30px
    }

    .so-clean p {
        color: #333;
        font-size: 12px;
        line-height: 24px
    }

    .so-ask b {
        color: #c00;
        font-weight: lighter
    }

    .tabS-hd-user a {
        color: #888;
        margin: 0 5px;
        text-decoration: none
    }

    .so-clean h4 a {
        background-color: #f2fdec;
        border: 1px #ddf6ce solid;
        border-radius: 2px;
        color: #41930b;
        font-size: 12px;
        font-weight: lighter;
        margin-left: 5px;
        padding: 2px 3px;
        text-decoration: none
    }

    .so-clean p a {
        color: #00c;
        font-family: serif;
        text-decoration: none
    }

    .res-video-ready .icon-qvod {
        background: none;
        cursor: auto;
        height: 22px;
        line-height: 22px
    }

    .res-video-ready .icon-btn {
        background-position: 0 -641px;
        background-repeat: repeat-x;
        color: #000;
        display: inline-block;
        font-size: 12px;
        font-family: arial;
        height: 24px;
        line-height: 24px;
        margin: 0 2px;
        text-decoration: none
    }

    .res-video-ready .mt-tv, .res-video-ready .icon-more {
        display: inline-block
    }

    .res-video-ready .icon-more {
        float: left
    }

    .res-video-ready .icon-last {
        display: none
    }

    .res-video-player .bg-loading {
        background: #000;
        color: #fff;
        height: 100%;
        width: 100%
    }

    .folding .result {
        overflow: hidden
    }

    .folding .res-list, .folding .mod-relation {
        display: none
    }

    .so-short-video .video-pic {
        color: #fff;
        float: left;
        height: 58px;
        margin-right: 10px;
        position: relative;
        text-decoration: none;
        width: 76px;
        z-index: 0
    }

    .so-short-video .video-ext {
        color: #666;
        display: block;
        font-size: 13px
    }

    .so-short-video .video-quality {
        background: #008EE2;
        cursor: pointer;
        font-size: 12px;
        height: 15px;
        line-height: 15px;
        position: absolute;
        right: 2px;
        top: 2px
    }

    .so-short-video .video-time {
        background: rgba(0, 0, 0, .5);
        bottom: 2px;
        display: block;
        height: 15px;
        left: 2px;
        line-height: 15px;
        position: absolute;
        text-align: right;
        width: 72px;
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#7f000000, endColorstr=#7f000000) \9
    }

    .so-ask .so-ask-tips {
        color: #666
    }

    .so-ask .so-ask-tips, .so-ask .so-ask-q {
        display: block
    }

    .so-ask .agree-num {
        background: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat -122px -320px;
        color: #666;
        padding-left: 15px;
        white-space: nowrap;
        word-break: keep-all
    }

    .res-dianping .comment_score {
        background: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat -37px -333px;
        display: inline-block;
        height: 13px;
        margin-right: 5px;
        overflow: hidden;
        position: relative;
        top: 1px;
        width: 74px
    }

    .so-short-video .video-pic img {
        border: 1px #dadadb solid;
        height: 54px;
        padding: 1px;
        width: 72px
    }

    .so-short-video .video-time span {
        background: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat -93px -539px;
        cursor: pointer;
        display: block;
        font-size: 12px;
        height: 100%;
        padding-right: 3px
    }

    .so-ask .so-ask-q em, .so-ask .so-ask-a em, .so-ask .so-ask-best em {
        color: green
    }

    .tabS-hd-user a:hover {
        color: #3eaf0e
    }

    .res-dianping .comment_score b {
        background: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat -37px -320px;
        float: left;
        height: 13px
    }

    .so-clean p a:hover {
        text-decoration: underline
    }

    .res-video-ready .icon-btn .btn-left {
        background-position: 0 -612px;
        float: left;
        height: 24px;
        margin-right: 3px;
        width: 5px
    }

    .res-video-ready .icon-btn .btn-right {
        background-position: -10px -612px;
        float: left;
        height: 24px;
        margin-left: 5px;
        width: 16px
    }

    .so-short-video .video-pic:hover img {
        border: 1px #7979ca solid
    }

    #suggest-align {
        position: relative
    }

    #suggest-tp {
        border: 1px #9fe084 solid;
        border-radius: 2px;
        color: #237500;
        display: none;
        font-size: 12px;
        position: absolute;
        right: 5px;
        top: 4px
    }

    #tips-res-fold {
        bottom: 0;
        color: #666;
        font-size: 14px;
        left: 0;
        line-height: 24px;
        position: absolute;
        width: 635px
    }

    #egg {
        display: none
    }

    #egg-5d-end {
        visibility: visible
    }

    #hd-tools {
        color: #eaeaea
    }

    #suggest-tp a {
        background-image: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png);
        background-repeat: repeat-x;
        background-position: 0 -677px;
        color: #237500;
        display: inline-block;
        line-height: 21px;
        padding: 0 3px;
        text-decoration: none
    }

    #tips-res-fold p {
        line-height: 30px
    }

    #hd-tools a {
        margin: 0 5px
    }

    .folding #main {
        position: relative
    }

    .folding #first {
        display: block;
        padding-bottom: 60px
    }

    .folding #page {
        visibility: hidden
    }

    #tips-res-fold .icon-unfold {
        color: #00c;
        display: inline;
        font-size: 16px;
        height: 5px;
        overflow: hidden;
        position: relative;
        top: 10px;
        width: 15px
    }

    #suggest-tp a:hover {
        background-position: 0 -698px
    }

    #tips-res-fold .icon-unfold i {
        left: 0;
        position: relative;
        top: -14px
    }

    #hd-tools a:hover {
        color: #3eaf0e
    }

    #tips-res-fold #ac-unfold, #tips-res-fold .icon-unfold {
        float: left
    }

    #egg-5d #egg {
        display: block;
        left: 0;
        position: absolute;
        text-align: center;
        top: 0
    }

    .so-tomato dt span, .so-tomato dt strong, .so-tomato dd {
        background: url(http://p1.qhimg.com/d/inn/173f6c69/tomato.png) no-repeat 10px 10px
    }

    .res-yx {
        color: #4c9141;
        margin: 5px 0 3px
    }

    .g-mohe {
        width: 535px
    }

    .so-tomato dt {
        background: #f4f4f4;
        border: 1px solid #f4f4f4;
        color: #333;
        cursor: pointer;
        height: 20px;
        padding: 4px 0 0 10px;
        padding-top: 6px \9
    }

    .so-tomato dd {
        background-color: #fafafa;
        border: 1px solid #e9e9e9;
        color: #666;
        display: none;
        line-height: 21px;
        padding: 5px 10px 8px 30px
    }

    .g-mohe em {
        color: #c00
    }

    .g-mohe table {
        border-collapse: collapse;
        border-spacing: 0
    }

    .g-mohe td {
        line-height: 1.5;
        vertical-align: top
    }

    .so-tomato dt span {
        background-position: 0 -67px;
        float: right;
        height: 4px;
        margin: 7px 10px 0 0;
        overflow: hidden;
        width: 9px
    }

    .so-tomato dt strong {
        background-position: 0 0;
        display: inline-block;
        height: 13px;
        margin: 0 5px;
        vertical-align: middle;
        width: 13px
    }

    .g-mohe thead th {
        background: url(http://p1.qhimg.com/t01b0f4bacf6866c588.png) repeat-x;
        border-bottom: 1px solid #e6e6e6;
        font-weight: 400;
        height: 24px
    }

    .res-yx .yxlink {
        background: #f5f5f5;
        border: 1px solid #EFEFEF;
        border-radius: 2px;
        color: #919191;
        display: inline-block;
        line-height: 18px;
        margin-right: 10px;
        padding: 0 3px;
        text-decoration: none;
        white-space: nowrap
    }

    .res-yx .yxmore {
        color: #77c
    }

    .g-mohe .cont {
        margin-top: 5px
    }

    .g-mohe .url {
        clear: both;
        color: green
    }

    .g-mohe .seemore {
        color: #77C
    }

    .g-mohe .gclearfix {
        zoom: 1
    }

    .g-mohe .unify-btn-2 {
        background: url(http://p9.qhimg.com/d/_onebox/unify/btn-2.gif) no-repeat 0 0;
        border: none;
        color: #000;
        display: inline-block;
        height: 24px;
        line-height: 24px;
        overflow: hidden;
        text-align: center;
        text-decoration: none;
        width: 71px
    }

    .g-mohe .unify-btn-4, .g-mohe .unify-btn-6, .g-mohe .unify-btn-mobi-dl, .g-mohe .unify-btn-pc-dl {
        border: none;
        color: #000;
        display: inline-block;
        height: 28px;
        line-height: 28px;
        overflow: hidden;
        text-align: center;
        text-decoration: none
    }

    .g-mohe .unify-btn-4 {
        background: url(http://p9.qhimg.com/d/_onebox/unify/btn-4.gif) no-repeat 0 0;
        width: 95px
    }

    .g-mohe .unify-btn-6 {
        background: url(http://p9.qhimg.com/d/_onebox/unify/btn-6.gif) no-repeat 0 0;
        width: 119px
    }

    .g-mohe .unify-btn-mobi-dl {
        background: url(http://p9.qhimg.com/d/_onebox/unify/btn-mobi-dl.gif) no-repeat 0 0;
        padding: 0 0 0 41px;
        text-align: left;
        width: 81px
    }

    .g-mohe .unify-btn-pc-dl {
        background: url(http://p9.qhimg.com/d/_onebox/unify/btn-pc-dl.gif) no-repeat 0 0;
        padding: 0 0 0 41px;
        text-align: left;
        width: 81px
    }

    .so-tomato dt.opened {
        border: 1px solid #e9e9e9;
        border-bottom: 0
    }

    .so-tomato dd .tomato-red {
        color: #ef7761
    }

    .so-tomato dd .tomato-yellow {
        color: #f5940b
    }

    .so-tomato dd .tomato-green {
        color: #3fb025
    }

    .g-mohe .title em {
        text-decoration: underline
    }

    .g-mohe table.list {
        margin-bottom: 5px;
        table-layout: fixed
    }

    .so-tomato dt.opened span {
        background-position: 0 -81px
    }

    .g-mohe table.list td, .g-mohe table.list th {
        text-indent: 10px
    }

    .g-mohe .gclearfix:after {
        clear: both;
        content: '';
        display: block;
        height: 0;
        visibility: hidden
    }

    .g-mohe .unify-btn-2:active {
        background-position: 0 -24px
    }

    .g-mohe .unify-btn-4:active {
        background-position: 0 -28px
    }

    .g-mohe .unify-btn-6:active {
        background-position: 0 -28px
    }

    .g-mohe .unify-btn-mobi-dl:active {
        background-position: 0 -28px
    }

    .g-mohe .unify-btn-pc-dl:active {
        background-position: 0 -28px
    }

    #side {
        border-left: 1px solid #eee;
        padding: 0 0 0 15px
    }

    .so-w1330 #side {
        margin-left: 50px;
        width: 410px
    }

    #side .atom-adv, #side .atom-r-onebox {
        margin: 0 0 27px
    }

    #side .g-mohe {
        width: auto
    }

    #side .title {
        color: #333;
        font-size: 14px;
        font-weight: 700;
        margin: 0 0 11px
    }

    .ie6 #container.so-w1330 {
        width: 1180px
    }

    #hd {
        border-bottom: 1px solid #e0e0e0;
        font-size: 14px;
        font-family: arial, sans-serif;
        height: 35px;
        min-width: 940px;
        text-align: left
    }

    #hd-inner {
        _margin-left: -940px;
        position: relative;
        z-index: 999
    }

    #hd a {
        text-decoration: none
    }

    #hd .m920 {
        _border-left: 940px solid #fff
    }

    #hd .m920, #hd-inner {
        _height: 1px
    }

    #hd-tools {
        height: 35px;
        line-height: 35px;
        position: absolute;
        right: 20px;
        text-align: right
    }

    #hd-nav {
        display: inline-block;
        line-height: 35px;
        padding-left: 20px
    }

    #hd-nav, #hd-nav-tabs, #hd-nav li {
        float: left
    }

    #hd-tools a {
        color: #888;
        font-size: 12px
    }

    #hd-nav li {
        margin-right: 22px;
        _margin-right: 24px
    }

    #hd-nav li a {
        color: #333;
        display: inline-block;
        height: 33px
    }

    #hd-tools .hd-tools-home {
        background: url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat 0 -416px;
        display: inline-block;
        height: 34px;
        text-indent: 16px
    }

    #hd-nav .hd-logo {
        float: left;
        height: 35px;
        overflow: hidden;
        width: 153px
    }

    #hd-nav .hd-logo a {
        display: inline-block;
        height: 35px
    }

    #hd-nav .hd-logo img {
        border: none;
        margin-top: 7px;
        vertical-align: top
    }

    #hd-nav li a:hover, #hd-nav a.hd-cur {
        border-bottom: 2px solid #3eaf0e;
        color: #3eaf0e
    }

    #hd-nav .hd-title-more {
        float: left;
        position: relative
    }

    #hd-nav .hd-more-div {
        background: #fff;
        border: 1px solid #ccc;
        left: -11px;
        overflow: hidden;
        position: absolute;
        top: 35px;
        width: 78px;
        z-index: 999
    }

    #hd-nav .morel {
        background: #fff url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat 31px -545px;
        margin-right: 0;
        padding-right: 22px
    }

    #hd-nav .liangyi {
        background: #fff url(http://p0.qhimg.com/d/inn/129456c7/sprite.png) no-repeat 39px -575px
    }

    #hd-nav a.hd-cur {
        font-weight: 700
    }

    #hd-nav .hd-more-div li {
        float: none;
        margin: 0
    }

    #hd-nav .hd-more-div a {
        display: block;
        text-indent: 10px;
        width: 100%
    }

    #hd-nav .hd-title-more a:hover, #hd-nav .hd-more-div a:hover {
        border-bottom: 0
    }

    #hd-nav .hd-more-div a:hover {
        background-color: #f1f1f1;
        color: #3eaf0e
    }

    #hd-nav a.morel:hover {
        background-color: #fff;
        color: #3eaf0e
    }</style>
    <script>(function (e) {
        function t(e, t) {
            var n = document, r = n.getElementsByTagName("head")[0] || n.documentElement, i = n.createElement("script"),
                s = !1;
            i.src = e, i.onerror = i.onload = i.onreadystatechange = function () {
                !s && (!this.readyState || this.readyState == "loaded" || this.readyState == "complete") && (s = !0, t && t(), i.onerror = i.onload = i.onreadystatechange = null, r.removeChild(i))
            }, r.insertBefore(i, r.firstChild)
        }

        function n(e) {
            var t, n, r, i;
            for (t = 0; t < l.length; t++) {
                r = l[t], i = r.requires;
                for (n = 0; n < i.length; n++) if (i[n] == e) break;
                i.splice(n, 1), i.length === 0 && (r.fun(), l.splice(t, 1))
            }
        }

        function r() {
            var e = f.splice(0, 1)[0], i = a[e], s = function () {
                n(e), i.loaded = !0, f.length ? r() : c = !1
            };
            if (!i) return;
            c = !0, i.loaded || i.checker && i.checker() ? s(e) : t(i.url, function () {
                s(e)
            })
        }

        var i = {};
        e.OB = i, i.Browser = function () {
            var t = e.navigator, n = t.userAgent.toLowerCase(),
                r = /(msie|webkit|gecko|presto|opera|safari|firefox|chrome|maxthon|android|ipad|iphone|webos|hpwos)[ \/os]*([\d_.]+)/ig,
                i = {platform: t.platform};
            n.replace(r, function (e, t, n) {
                var r = t.toLowerCase();
                i[r] || (i[r] = n)
            }), i.opera && n.replace(/opera.*version\/([\d.]+)/, function (e, t) {
                i.opera = t
            });
            if (i.msie) {
                i.ie = i.msie;
                var s = parseInt(i.msie, 10);
                i["ie" + s] = !0
            }
            return i
        }();
        if (i.Browser.ie) try {
            document.execCommand("BackgroundImageCache", !1, !0)
        } catch (s) {
        }
        var o = i.Browser, u = {
            ready: function (e, t) {
                function n() {
                    clearTimeout(t.__QWDomReadyTimer);
                    if (r.length) {
                        var e = r.shift();
                        r.length && (t.__QWDomReadyTimer = setTimeout(n, 0)), e()
                    }
                }

                t = t || document;
                var r = t.__QWDomReadyCbs = t.__QWDomReadyCbs || [];
                r.push(e), setTimeout(function () {
                    if (/complete/.test(t.readyState)) n(); else if (t.addEventListener) "interactive" == t.readyState ? n() : t.addEventListener("DOMContentLoaded", n, !1); else {
                        var e = function () {
                            e = new Function, n()
                        };
                        (function () {
                            try {
                                t.body.doScroll("left")
                            } catch (n) {
                                return setTimeout(arguments.callee, 1)
                            }
                            e()
                        })(), t.attachEvent("onreadystatechange", function () {
                            "complete" == t.readyState && e()
                        })
                    }
                }, 0)
            }
        };
        i.DomU = u;
        var a = {
            jquery: {
                url: "http://s0.qhimg.com/lib/jquery/183.js", checker: function () {
                    return !!e.jQuery
                }
            }
        }, f = [], l = [], c = !1;
        e._loader = {
            add: function (e, t, n) {
                a[e] || (a[e] = {url: t, checker: n})
            }, use: function (e, t) {
                i.DomU.ready(function () {
                    e = e.split(/\s*,\s*/g), f = f.concat(e), l.push({requires: e, fun: t}), c || r()
                })
            }
        }
    })(window);
    FELOG['UNHEAD'] = new Date().getTime();</script>
</head>
<body link="#0000cc" class="www360sou" id="">
<div id="hd">
    <div class="m920">
        <div id="hd-inner">
            <div id="hd-nav">
                <div class="hd-logo">
                </div>
            </div>
            <div id="hd-tools">
                <a class="hd-tools-home" href="http://www.so.com/?src=tab_web">返回首页</a>
                <span class="hd-user">|
				<a id="user-login"
                   href="http://i.360.cn/login?src=pcw_so&destUrl=http%3A%2F%2Fwww.so.com%2Fs%3Fie%3Dutf-8%26src%3D360sou_home%26ms%3Ds20025%26q%3D%25E4%25B8%25AD%25E5%259B%25BD">登录</a>
                <a id="user-reg"
                   href="http://i.360.cn/reg?src=pcw_so&destUrl=http%3A%2F%2Fwww.so.com%2Fs%3Fie%3Dutf-8%26src%3D360sou_home%26ms%3Ds20025%26q%3D%25E4%25B8%25AD%25E5%259B%25BD"
                   target="_blank">注册</a>
                </span>
            </div>
        </div>
    </div>
</div>
<script>FELOG['BEBODY'] = new Date().getTime();</script>
<div id="warper" class="">
    <div id="head" style="position:relative;z-index:888;">
        <form name="f2" class="form" action="query?currentPage=1" method="post">
      <span id="suggest-align" class="round">
      <input id="keywords" name="keywords" tabindex="1" class="input_key" value="" maxlength="100" autocomplete="off">
      <cite id="suggest-tp"></cite>
      </span>
            <span class="button">
                <input type="submit" tabindex="3" id="su" value="搜索一下" class="s_btn"
                       onmousedown="this.className='s_btn s_btn_d'"
                       onmouseout="this.className='s_btn'" onmouseover="this.className='s_btn s_btn_h'">
             </span>
            <span class="notice">
            </span>
        </form>
    </div>

    <div id="container">
        <script>
            (function () {
                function i() {
                    n.clientWidth > 1200 ? t.className.indexOf("so-w1330") < 0 && (t.className += "so-w1330") : t.className = t.className.replace("so-w1330", "")
                }

                function s() {
                    e && clearTimeout(e), e = setTimeout(function () {
                        i(), PageLine.trigger("resize", {name: "xinz"})
                    }, 100)
                }

                var e = null, t = document.getElementById("container"), n = document.documentElement,
                    r = document.addEventListener ? "addEventListener" : "attachEvent";
                document.addEventListener ? window.addEventListener("resize", s) : window.attachEvent("onresize", s), i()
            })();
        </script>
        <script>ENV_MONITOR['version'] = "1.3.2";
        ENV_MONITOR['abv'] = "a";
        ENV_MONITOR['ls'] = "0";
        ENV_MONITOR['src'] = "360sou_home";
        ENV_FLAG['w'] = "0";
        </script>
        <div id="main">
            <c:forEach var="resultlist" items="${resultbean.result}">
                <div>
                    <a href="${resultlist.url }" target="_blank">${resultlist.title }</a>
                </div>
                <p class="res-desc " style="font-size: 14px;">${resultlist.conent }</p>
                <p class="res-linkinfo ">- <a href="${resultlist.url }" target="_blank" class="m">快照</a></p>
            </c:forEach>
        </div>

        <div id="page">
            ${pagetotal}
            <span class="nums" style="margin-left:120px">找到相关结果约${resultbean.total }个</span></div>
    </div>
    <div class="mod-relation">
        <div id="rs" data-rpt="0"></div>
    </div>
    <div id="search">
        <div id="m-google">
            <div id="m-google-top" style="zoom:1;">
            </div>
            <div id="m-google-right" style="zoom:1;">

            </div>
        </div>
    </div>
    <div id="footer">
        <p>
            <a href="" target="_blank">意见反馈</a>|<a href="" target="_blank">网站收录</a>|<a href="" target="_blank">使用帮助</a>|
            <a href="" target="_blank">推广合作</a><br>
            Copyright &copy; 传智播客.CN All Rights Reserved. &nbsp;&nbsp;
        </p>
    </div>
    <div id="outer"></div>
</div>
<script>ENV_MONITOR['sid'] = "b35bc5a37ce382173c85df8961f58126";
ENV_MONITOR['pid'] = "360sou";
ENV_MONITOR['rf'] = "";
FELOG['ABV'] = "a";</script>
<script>FELOG['UNBODY'] = new Date().getTime();</script>
<script>FELOG['JQ_B'] = +new Date;
FELOG['JQ_HEAD'] = 0;</script>
<script src="http://s0.qhimg.com/lib/jquery/183.js"></script>
<script>FELOG['JQ_E'] = +new Date;</script>
<script src="http://s0.qhimg.com/monitor/;monitor/fd7e782a.js"></script>
<script src="http://s1.qhimg.com/static/8f9ad0848a7b37e1/result.js"></script>
<script src="http://s0.qhimg.com/static/2a11f646e79cfdf1/socom.js"></script>
<script src="http://s0.qhimg.com/i360/;js;pass_api_/seed,log/v3202.js"></script>
<script src="http://s0.qhimg.com/static/db883e6fcc7d5e78/foot.js"></script>
<script>(function (e, t, n, r, i) {
    function s(i) {
        e._iwtLoading = 1, n = t.createElement("script"), n.src = r.URL, i = t.getElementsByTagName("script"), i = i[i.length - 1], i.parentNode.insertBefore(n, i)
    }

    r = {
        UA: ENV_MONITOR['pid'] == 'so360cn' ? 'UA-360-000002' : 'UA-360-000003',
        NO_FLS: 1,
        WITH_REF: 0,
        URL: "/resource/js/iwt-min.js"
    }, e._iwt ? e._iwt.track(r, i) : (e._iwtTQ = e._iwtTQ || []).push([r, i]), !e._iwtLoading && s()
})(this, document);
FELOG['ENBODY'] = +new Date;</script>
</body>
</html>
