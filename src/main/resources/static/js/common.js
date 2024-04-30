$(function(){
	// sidebar detph active
 	$('.side-depth1').on('click', function(){
		$(this).parent().siblings().removeClass('active');
		$(this).parent().toggleClass('active');
	    $(this).parent().siblings().find(".side-depth2-box").stop().slideUp(300);
	    $(this).next().stop().slideToggle(300);
	})	
		
	// selectTime
	let hourOption = "";
	let minuteOption = "";
	for(i = 0; i < 24; i++){
		if(i < 10){
			hourOption += "<option value=0"+i+">0"+i+"시</option>";
		}else{
			hourOption += "<option value="+i+">"+i+"시</option>";
		}
	}
	for(i = 0; i <= 5; i++){
		minuteOption += "<option value="+i+"0>"+i+"0분</option>";
	}
	$('.select-hour').append(hourOption);
	$('.select-minute').append(minuteOption);
})


isNull = function(value) {
    return (value === undefined || value === null || value === '') ? true : false;
}

// paging
function pageMove(pageNo){
	$("input[name='pageNo']").val(pageNo);
	document.searchForm.submit();
}
function modalPageMove(pageNo){
	$("#modalPageNo").val(pageNo);
	modalSearchList();
}

// toggle active
function toggleActive(idName){
	$(idName).toggleClass('active');
}
