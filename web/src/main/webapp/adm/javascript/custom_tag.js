/* 标签动作 */

$(function () {
   init_TagsInput();
});

/* INPUTS */

function onAddTag(tag) {
	alert("Added a tag: " + tag);
}

function onRemoveTag(tag) {
	alert("Removed a tag: " + tag);
}

function onChangeTag(input, tag) {
	alert("Changed a tag: " + tag);
}

//tags input
function init_TagsInput() {
	if (typeof $.fn.tagsInput !== 'undefined') {

		$('.tagsInput').tagsInput({
			width: 'auto'
		});

	}

};