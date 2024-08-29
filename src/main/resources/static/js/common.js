function mobileMenuToggle(_this) {
	const mobileBtns = document.querySelectorAll('.parentMenu');
	let clickedIndex = _this.getAttribute('data-idx');

	mobileBtns.forEach((btn, index) => {
		const dropItem = btn.querySelector('.mobile-drop-menu');
		const cmArrow = btn.querySelector('.mobile-arrow');
		const cmTitle = btn.querySelector('.mobile-title');
		const cmTitleFirst = btn.querySelector('.mobile-title h4');


		if (clickedIndex == index) {
			if (dropItem.classList.contains("on")) {
				dropItem.classList.remove("on");
				cmArrow.classList.remove("on");
				cmTitle.classList.remove("on");
				cmTitleFirst.classList.remove("on");
			} else {
				dropItem.classList.add("on");
				cmArrow.classList.add("on");
				cmTitle.classList.add("on");
				cmTitleFirst.classList.add("on");
			}
		} else {
			dropItem.classList.remove("on");
			cmArrow.classList.remove("on");
			cmTitle.classList.remove("on");
			cmTitleFirst.classList.remove("on");
		}
	});
}
function lagChange() {
	document.addEventListener("DOMContentLoaded", function() {
		//언어 쿠키 세팅
		let lang = getCookie("lang");
		if (isNull(lang)) lang = 'eng';
		document.getElementById('langSave').value = lang;
		if (isNull(getCookie('lang'))) setCookie("lang", lang, 1);
	});

	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if (start != -1) {
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if (end == -1) end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}

	let setLangCookie = () => {
		let selectLang = document.getElementById('selectLang');
		const langValue = document.getElementsByName('lang');
		let selectedValue;
		for (const lang of langValue) {
			if (lang.selected) {
				selectedValue = lang.value;
				break;
			}
		}
		setCookie("lang", selectedValue, 1);
		window.location.reload();
	}
	function setCookie(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toGMTString()) + "; path=/;";
		document.cookie = cookieName + "=" + cookieValue;
	}
	
}

//paing
function pageMove(pageNo){
   $("input[name='pageNo']").val(pageNo);
   document.searchForm.submit();
}

//비동기 paing
function pageMoveForAsynchronous(pageNo){
   $("#asynchronousPageNo").val(pageNo);
   asynchronousSearchList();
}