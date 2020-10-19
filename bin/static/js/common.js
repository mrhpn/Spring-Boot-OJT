$(function() {
	if (document.getElementsByTagName('form').length !== 0) {
		$('form').each(function(idx, e) {				
			$(e).parsley({
				// http://parsleyjs.org/doc/annotated-source/defaults.html
				successClass: 'has-success',
				errorClass: 'has-error',
				classHandler: function (ParsleyField) {
					return $(ParsleyField.$element).parents('.form-group');
				},
				errorsWrapper: '<span></span>',
				errorTemplate: '<small class="help-block"></small>',
			})
			.on('field:validated', function() {
				field = this.$element;
				msgContainer = field.data('parsley-errors-container');
				errorsWrappers = $(msgContainer).children('.filled');
				if (errorsWrappers.first().not(':visible')) {
					errorsWrappers.first().show();
				}
				errorsWrappers.not(':first').hide();
			});
		});
	}
	
	// Usage:data-parsley-datetimepicker="#datetimepickerコンテナ"
	Parsley.addValidator('datetimepicker', {
		requirementType: 'string',
		validateString: function(value, requirement, parsleyField) {
			datetimepickerContainer = $(requirement);
			// 要素が存在すること
			if (datetimepickerContainer.size() === 0) {
				console.log('parsley validate error : target not found. (datetimepicker = ' + requirement + ')');
				return false;
			}
			// DateTimePickerが初期化されていること
			var dtp = $(datetimepickerContainer).data('DateTimePicker');
			if (dtp === undefined) {
				console.log('parsley validate error : datetimepicker was not initialized. (datetimepicker = ' + requirement + ')');
				return false;
			}
			var parsleyForm = $(datetimepickerContainer).parents('form').parsley();
			var fieldErrorElement = parsleyForm.options.classHandler(parsleyField);
			var errorClass = parsleyForm.options.errorClass;

			// 前回のエラーを削除
			parsleyField.removeError('custom-error-message');

			// DateTimePickerへ入力内容を渡し、DateTimePickerの入力チェック処理を起動する。
			// 入力エラーの場合、DateTimePicker.dp.errorイベントがトリガーされる。
			$(datetimepickerContainer).datetimepicker().on('dp.error', function(e) {
				var inputText = parsleyField.$element;
				inputText.parsley().addError('custom-error-message', {message: inputText.data('parsley-error-message')});
			});
			dtp.date(value);
			$(datetimepickerContainer).datetimepicker().off('dp.error');

			// エラーCSSが設定されていた場合、エラーとする
			return fieldErrorElement.hasClass(errorClass) !== true;
		}
	});

	// Usage: data-parsley-passwordpolicy="true"
	Parsley.addValidator('passwordpolicy', {
		requirementType: 'boolean',
		validateString: function(value, requirement) {
			var cnt = 0;
			if(8 > value.trim().length || value.trim().length > 32){
				return false;
			}

			if(value.match(/[0-9]/)){
				cnt++;
			}
			if(value.match(/[a-z]/)){
				cnt++;
			}
			if(value.match(/[A-Z]/)){
				cnt++;
			}
			if(value.match(/[!-/:-@≠\[-`{-~]/)){
				cnt++;
			}
			if (3 > cnt) {
				return false;
			} else {
				return true;
			}
		},
		messages: {
		}
	});
});