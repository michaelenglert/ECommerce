<!-- saved from url=(0082)http://stevesouders.com/cuzillion/?c0=bi1hfff2_0_f&c1=bj1iftf0_5_f&t=1376353225249 -->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script>
        var gTop = Number(new Date());
        var gScriptMsg = "";
        function cuz_addHandler(elem, sType, fn, capture) {
            capture = (capture) ? true : false;
            if (elem.addEventListener) {
                elem.addEventListener(sType, fn, capture);
            }
            else if (elem.attachEvent) {
                elem.attachEvent("on" + sType, fn);
            }
            else {
                // Netscape 4
                if (elem["on" + sType]) {
                    // Do nothing - we don't want to overwrite an existing handler.
                }
                else {
                    elem["on" + sType] = fn;
                }
            }
        }
        function doOnload() {
            var end = Number(new Date());
            document.getElementById('loadtime').innerHTML = 'page load time: ' + (end - gTop) + ' ms';
            if (gScriptMsg && document.getElementById('loadedscripts')) {
                document.getElementById('loadedscripts').innerHTML += gScriptMsg;
            }
        }
        cuz_addHandler(window, 'load', doOnload);
        var gbEnabled = false;
        function enableEdit() {
            if (gbEnabled) return;
            gbEnabled = true;
            addStylesheet('cuzillion.1.1.css');
            addScript('cuzillion.1.7.js');
        }
        function addStylesheet(url) {
            var stylesheet = document.createElement('link');
            stylesheet.rel = 'stylesheet';
            stylesheet.type = 'text/css';
            stylesheet.href = url;
            document.getElementsByTagName('head')[0].appendChild(stylesheet);
        }
        function addScript(url) {
            var script = document.createElement('script');
            script.src = url;
            document.getElementsByTagName('head')[0].appendChild(script);
        }
        function scriptSleepOnload(sUrl) {
            var now = Number(new Date());
            var msg = "<nobr>" + (now - gTop) + "ms: \"" + sUrl + "\" done</nobr>\n";
            if (document.getElementById('loadedscripts')) {
                document.getElementById('loadedscripts').innerHTML += msg;
            }
            else {
                gScriptMsg += msg;
            }
        }
        function reloadPage(url) {
            document.body.innerHTML = '';
            document.location = url;
        }
        function cleanText(sText) {
            return sText.replace(/<.*?>/g, '');
        }
    </script>


    <title>Cuzillion</title>
    <link rel="icon" href="http://stevesouders.com/cuzillion/favicon.ico" type="image/x-icon">
    <!--
    Copyright 2008 Google Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    -->
    <script>var se = 1376427459;
    var sleep_now = Number(new Date());

    function externalFunction1(i) {
        return i + 1;
    }

    while (sleep_now + 5000 > Number(new Date())) {
        var tmp = sleep_now;
    }
    if ('function' == typeof(scriptSleepOnload)) scriptSleepOnload('http://stevesouders.com/bin/resource.cgi?type=js&sleep=0&jsdelay=5&n=2&t=1376427457');
    </script>
</head>

<body style="margin: 0px; padding: 0px; font-family: &quot;Trebuchet MS&quot;, &quot;Bitstream Vera Serif&quot;, Utopia, &quot;Times New Roman&quot;, times, serif;">

<div style="background: #333; color: white; padding: 8px;">
    <div style="float:right; margin-top: 2px;">
        <a href="http://stevesouders.com/cuzillion/help.php#examples"
           style="color: white; font-size: 0.9em; text-decoration: none;">Examples</a>&nbsp;|&nbsp;<a
            href="http://stevesouders.com/cuzillion/help.php"
            style="color: white; font-size: 0.9em; text-decoration: none;">Help</a><br><a
            href="http://stevesouders.com/" style="color: white; font-size: 0.9em; text-decoration: none;">stevesouders.com</a>
    </div>
    <font style="font-size: 2em; font-weight: bold; margin-right: 10px;"><a href="http://stevesouders.com/cuzillion/"
                                                                            style="color:white; text-decoration: none;"><img
            border="0" src="./slow3_files/logo-32x32.gif">&nbsp;Cuzillion</a></font><i>'cuz there are a zillion pages to
    check</i>
</div>

<div id="content" style="margin: 8px;">


    <!-- begin resources for inbody -->
    <!-- image<p style='margin: 0 4px 4px 12px; font-size: 0.8em;'> on domain1 with a 2 second delay using HTML tags -->
    <img src="./slow3_files/resource.cgi">

    <!-- external script<p style='margin: 0 4px 4px 12px; font-size: 0.8em;'> on domain1 with a 5 second execute time using XHR injection after onload -->
    <script>
        function create_elem2() {
            xhrObj_elem2 = getXHRObject();
            xhrObj_elem2.onreadystatechange = function () {
                if (xhrObj_elem2.readyState != 4 || 200 != xhrObj_elem2.status) return;
                var se = document.createElement('script');
                document.getElementsByTagName('head')[0].appendChild(se);
                se.text = xhrObj_elem2.responseText;
            };
            try {
                xhrObj_elem2.open('GET', 'http://stevesouders.com/bin/resource.cgi?type=js&sleep=0&jsdelay=5&n=2&t=1376427457', true);
                xhrObj_elem2.send('');
            }
            catch (e) {
            }
        }
        function getXHRObject() {
            var xhrObj = false;
            try {
                xhrObj = new XMLHttpRequest();
            }
            catch (e) {
                var progid = ['MSXML2.XMLHTTP.5.0', 'MSXML2.XMLHTTP.4.0', 'MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP'];
                for (var i = 0; i < progid.length; ++i) {
                    try {
                        xhrObj = new ActiveXObject(progid[i]);
                    }
                    catch (e) {
                        continue;
                    }
                    break;
                }
            }
            finally {
                return xhrObj;
            }
        }
        cuz_addHandler(window, 'load', create_elem2);
    </script>

    <!-- end resources for inbody -->

    <div id="floattray" style="float: left; width: 170px; margin-right: 30px;">
        <div id="step1text" style="text-align: left; margin: 0 0 4px 4px; height: 50px; padding-top: 12px;"></div>
        <div id="comptray">
            &nbsp;
        </div>
    </div>

    <div id="pageavatar" style="float: left; width: 310px; margin-right: 30px;">
        <div id="step2text" style="text-align: left; margin: 0 0 4px 4px; height: 50px; padding-top: 12px;"></div>
        <div style="background: #CCC; border: 1px solid black; ">
            <code style="font-size: 1.2em; font-weight: bold; color: #666666; display: block;">&lt;HTML&gt;</code>
            <code style="font-size: 1.2em; font-weight: bold; color: #666666; display: block;">&lt;HEAD&gt;</code>

            <div class="drop"
                 style="border: 1px solid #EEE; background: #EEE; padding: 12px 0 12px 0; width: 300px; margin: 0 0 0 4px;">
                <ul style="margin: 0; padding: 0;" id="inhead"></ul>
                <div id="inheadTarget"></div>
            </div>
            <code style="font-size: 1.2em; font-weight: bold; color: #666666; display: block;">&lt;/HEAD&gt;</code>
            <code style="font-size: 1.2em; font-weight: bold; color: #666666; display: block;">&lt;BODY&gt;</code>

            <div class="drop"
                 style="border: 1px solid #EEE; background: #EEE; padding: 12px 0 12px 0; width: 300px; margin: 0 0 0 4px;">
                <ul style="margin: 0; padding: 0;" id="inbody">
                    <li onclick="enableEdit()" id="acomp1" class="sortitem image"
                        style="cursor: move; list-style: none; border-width: 2px; border-style: solid; border-color: #555; margin: 4px;">
                        <div id="acomp1Div" class="component image"
                             style="padding: 2px; font-family: Arial; text-align: center; display: block; text-decoration: none; color: white; background: #000080; text-align: left;">
                            <span>image<p style="margin: 0 4px 4px 12px; font-size: 0.8em;"> on domain1 with a 2 second
                                delay using HTML tags</p></span></div>
                    </li>
                    <li onclick="enableEdit()" id="acomp2" class="sortitem extjs"
                        style="cursor: move; list-style: none; border-width: 2px; border-style: solid; border-color: #555; margin: 4px;">
                        <div id="acomp2Div" class="component extjs"
                             style="padding: 2px; font-family: Arial; text-align: center; display: block; text-decoration: none; color: white; background: #040; text-align: left;">
                            <span>external script<p style="margin: 0 4px 4px 12px; font-size: 0.8em;"> on domain1 with a
                                5 second execute time using XHR injection after onload</p></span></div>
                    </li>
                </ul>
                <div id="inbodyTarget"></div>
            </div>
            <code style="font-size: 1.2em; font-weight: bold; color: #666666; display: block;">&lt;/BODY&gt;</code>
            <code style="font-size: 1.2em; font-weight: bold; color: #666666; display: block;">&lt;/HTML&gt;</code>
        </div>
        <div id="loadtime" style="text-align: left; margin-top: 10px;">page load time: 2066 ms</div>
        <div id="loadedscripts" style="text-align: left; margin-top: 10px; width: 300px; font-size: 0.9em;">
            <nobr>7133ms: "http://stevesouders.com/bin/resource.cgi?type=js&amp;sleep=0&amp;jsdelay=5&amp;n=2&amp;t=1376427457"
                done
            </nobr>
        </div>
    </div>
    <!-- end pageavatar -->

    <div style="position: absolute; left: 560px;">
        <div id="step3text" style="text-align: left; margin: 0 0 4px 4px; height: 50px; padding-top: 12px;"></div>
        <div id="pagesubmit" style="text-align: left;">
            <nobr>
                <input type="button" value="Edit" onclick="enableEdit()">&nbsp;&nbsp;
                <input type="button" value="Reload"
                       onclick="reloadPage(&#39;/cuzillion/?c0=bi1hfff2_0_f&amp;c1=bj1iftf0_5_f&amp;t=1376427457&#39;)">&nbsp;&nbsp;
                <input type="button" value="Clear" onclick="document.location=&#39;.&#39;">&nbsp;&nbsp;
            </nobr>
        </div>
    </div>

    <div style="clear: both;">
    </div>

</div>
<!-- content -->


</body>
</html>