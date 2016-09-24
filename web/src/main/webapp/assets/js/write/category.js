var e_tr = null;
var cl = "";

$(function () {
    $("#btnAdd").click(save);
    $("#btnCancel").click(cancelEdit);
    $("#txtCat").keydown(function (ev) {
        ev = ev || event;
        if (tools.isEnter(ev)) save();
    });
    rowHighlight();
});

function doExec(e, id, t) {
    var isDo = false;
    switch (t) {
        case "edit": edit(e); break;
        case "del":
            var num = $(e).parent().prev().children().eq(0).html();
            if (parseInt(num) > 0) {
                isDo = confirm("该类别下有" + num + "篇文章，删除将会把文章移出此类，是否继续？");
            } else {
                isDo = confirm("确定要删除该类别吗？");
            }
            break;
        case "up": isDo = true; break;
        case "down": isDo = true; break;
        case "show": isDo = true; break;
        case "hide": isDo = true; break;
    }
    if (isDo) {
        $.get("?t=" + t, { id: id, r: tools.random() }, function (ret) {
            fillItems(ret);
        });
    }
}

function edit(e) {
    cancelEdit();
    var tr = e.parentNode.parentNode;
    cl = tr.className;
    tr.className = "over";
    e_tr = tr;
    var id = e.href.split('#')[1];
    var td = tr.childNodes[0];
    var text = td.childNodes[0].innerHTML;
    var span = document.createElement("span");
    span.innerHTML = "<input type='text' onkeydown='enterSave(event," + id + ")' maxlength=30 />"
        + " <a href='#' onclick='javascript:saveEdit(" + id + ");return false;'>保存</a>"
        + " <a href='#' onclick='javascript:cancelEdit();return false;'>取消</a>";
    td.childNodes[0].style.display = "none";
    td.appendChild(span);
    $("input", $(span)).focus().val(text.replace(/&lt;/gi, '<').replace(/&gt;/gi, '>'));
}
function enterSave(ev, id) {
    ev = ev || event;
    if (tools.isEnter(ev)) saveEdit(id);
}
function saveEdit(id) {
    var cat = $("input", $(e_tr)).val();
    var data = { id: id, name: cat };
    $.get("?t=edit", data);
    e_tr.firstChild.firstChild.innerHTML = cat;
    cancelEdit();
}
function cancelEdit() {
    if (e_tr) {
        e_tr.className = cl;
        var td = e_tr.childNodes[0];
        td.removeChild(td.childNodes[1]);
        td.childNodes[0].style.display = "inline";
        e_tr = null;
    }
}
function save() {
    if (tools.doing) return;
    var cat = tools.val("txtCat");
    if (!cat) {
        alert("请输入类别名称。");
        $("#txtCat").focus();
        return false;
    }
    tools.doing = true;
    var data = { name: cat };
    $.get("?t=add", data, function (ret) {
        tools.doing = false;
        $("#txtCat").val("");
        fillItems(ret);
    });
}
function fillItems(items) {
    $("#lstBody").html(items);
    rowHighlight();
}
function rowHighlight() {
    $("#lstBody tr").mouseover(function () {
        $(this).addClass("over");
    }).mouseout(function () {
        $(this).removeClass("over");
    });
}