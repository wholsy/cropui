<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="utf-8" />
        <title>Simple example - Editor.md examples</title>
        <link rel="stylesheet" href="${ctx}/adm/assets/js/editor.md/css/editormd.css"/>
    </head>
    <body>
        <div id="layout">
            <header>
                <h1>Simple example</h1>
            </header>
            <div id="test-editormd">
                <textarea style="display:none;">
				- TeX (Based on KaTeX);
				- Emoji;
				</textarea>
            </div>
        </div>
        
        <script src="http://static.yueny.website/plugins/jquery/v2.1.4/dist/jquery.js"></script>
        <script src="${ctx}/adm/assets/js/editor.md/editormd.min.js"></script>
        
        <script type="text/javascript">
			var testEditor;
			
			$(function() {
                testEditor = editormd("test-editormd", {
                    width   : "90%",
                    height  : 640,
                    syncScrolling : "single",
                    path: "/adm/assets/js/editor.md/lib/",
                });
            });
            //$(function() {
            //    testEditor = new_md_editor("test-editormd");
            //});
        </script>
    </body>
</html>