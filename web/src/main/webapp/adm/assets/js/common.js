$(function () {
    $("#a_logout").bind("click", function () {
        $.get("/adm/dologout", function () {
            window.location.href = "/adm/login";
        })
    });
    
});

