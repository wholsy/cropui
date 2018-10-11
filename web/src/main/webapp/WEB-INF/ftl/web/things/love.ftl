<!DOCTYPE html>
<html lang="en">
<head>
    <#-- 这3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <#--  Enable responsive viewport, 自适应，宽度等于当前设备的宽度（width=device-width）缩放比例是当前不缩放（initial-scale=1）。 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="${title}">
    <meta name="author" content="云少">

    <title>${title}</title>
    <link rel="shortcut icon" href="http://static.codealy.com/favicon.ico">

    <link href="${ctx}/web/things/love/css/default.css" rel="stylesheet">

    <script src="http://static.codealy.com/plugins/jquery/v2.1.4/dist/jquery.js"></script>
</head>

<body>
<div id="mainDiv">
    <div id="content">
        <div id="code">
            <span class="comments">/**</span><br />
            <span class="space"/><span class="comments">*${fullYear}-${month}-${day}.</span><br />
            <span class="space"/><span class="comments">*/</span><br />
            Boy name = <span class="keyword">Mr</span> ${Mr}<br />
            Girl name = <span class="keyword">Mrs</span> ${Mrs}<br />

            ${love.context}
            <#--<span class="comments">// Fall in love river. </span><br />-->
            <#--The boy love the girl;<br />-->
            <#--<span class="comments">// They love each other.</span><br />-->
            <#--The girl loved the boy;<br />-->
            <#--<span class="comments">// AS time goes on.</span><br />-->
            <#--The boy can not be separated the girl;<br />-->
            <#--<span class="comments">// At the same time.</span><br />-->
            <#--The girl can not be separated the boy;<br />-->
            <#--<span class="comments">// Both wind and snow all over the sky.</span><br />-->
            <#--<span class="comments">// Whether on foot or 5 kilometers.</span><br />-->
            <#--<span class="keyword">The boy</span> very <span class="keyword">happy</span>;<br />-->
            <#--<span class="keyword">The girl</span> is also very <span class="keyword">happy</span>;<br />-->
            <#--<span class="placeholder"/><span class="comments">// Whether it is right now</span><br />-->
            <#--<span class="placeholder"/><span class="comments">// Still in the distant future.</span><br />-->
            <#--<span class="placeholder"/>The boy has but one dream;<br />-->
            <#--<span class="comments">// The boy wants the girl could well have been happy.</span><br />-->
            <#--<br>-->
            <#--<br>-->
            <#--I want to say:<br />-->
            <#--Baby, I love you forever;<br />-->
        </div>

        <div id="loveHeart">
            <canvas id="garden"></canvas>
            <div id="words">
                <div id="messages">
                <#--${love.messages}-->
                    亲爱的，这是我们相爱在一起的时光。
                    <div id="elapseClock"></div>
                </div>
                <div id="loveu">
                    ${love.loveu}<br/>
                    爱你直到永永远远。<br/>
                    <div class="signature">- Your darling ${Mr}</div>
                </div>
            </div>
        </div>
    </div>
</div>

    <#include "include/comp-body-footer-js.ftl">
    <script type="text/javascript" src="${ctx}/web/things/love/js/garden.js"></script>
    <script type="text/javascript" src="${ctx}/web/things/love/js/functions.js"></script>

    <script type="text/javascript">
        var offsetX = $("#loveHeart").width() / 2;
        var offsetY = $("#loveHeart").height() / 2 - 55;
        var together = new Date();
        together.setFullYear(${fullYear}, ${month}, ${day});
        together.setHours(${hours});
        together.setMinutes(${minutes});
        together.setSeconds(${seconds});
        together.setMilliseconds(${milliseconds});

        if (!document.createElement('canvas').getContext) {
            var msg = document.createElement("div");
            msg.id = "errorMsg";
            msg.innerHTML = "Your browser doesn't support HTML5!<br/>Recommend use Chrome 14+/IE 9+/Firefox 7+/Safari 4+";
            document.body.appendChild(msg);
            $("#code").css("display", "none")
            $("#copyright").css("position", "absolute");
            $("#copyright").css("bottom", "10px");
            document.execCommand("stop");
        } else {
            setTimeout(function () {
                startHeartAnimation();
            }, 5000);

            timeElapse(together);
            setInterval(function () {
                timeElapse(together);
            }, 500);

            adjustCodePosition();
            $("#code").typewriter();
        }
    </script>

</body>
</html>