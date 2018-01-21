$(function () {
	/* blockUI 全局设置 */
	// override these in your code to change the default behavior and style
	$.blockUI.defaults.css = {
	        border: 'none',
	        padding: '15px',
	        backgroundColor: '#000',
	        '-webkit-border-radius': '10px',
	        '-moz-border-radius': '10px',
	        opacity: .5,
	        color: '#fff'
	};
	$.blockUI.defaults.message = 'Hold on!';
       
	/** 标签动作 */
   init_TagsInput();
   
   init_InputMask();
   
   init_validator();
});


// Switchery
// 多按钮的统一初始化
$(function () {
	if ($(".js-switch")[0]) {
		var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
		elems.forEach(function(html) {
			var switchery = new Switchery(html, {
				color: '#26B99A'
			});
		});
	}
});
// /Switchery


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


/* INPUT MASK */
function init_InputMask() {

	if (typeof($.fn.inputmask) === 'undefined') {
		return;
	}
	console.log('init_InputMask');

	$(":input").inputmask();

};


/* VALIDATOR */

function init_validator() {

	if (typeof(validator) === 'undefined') {
		return;
	}
	console.log('init_validator');

	// initialize the validator function
	validator.message.date = 'not a real date';

	// validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
	$('form')
		.on('blur', 'input[required], input.optional, select.required', validator.checkField)
		.on('change', 'select.required', validator.checkField)
		.on('keypress', 'input[required][pattern]', validator.keypress);

	$('.multi.required').on('keyup blur', 'input', function() {
		validator.checkField.apply($(this).siblings().last()[0]);
	});

	$('form').submit(function(e) {
		e.preventDefault();
		var submit = true;

		// evaluate the form using generic validaing
		if (!validator.checkAll($(this))) {
			submit = false;
		}

		if (submit)
			this.submit();

		return false;
	});

};


