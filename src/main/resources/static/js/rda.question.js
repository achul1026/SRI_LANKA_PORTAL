// questSectionNewAdd
function questSecition(_this,invstNm,qstnList) {
	let container = $('#infoTableWrap');
	let dataContainer = container.children('.quest-container').length+1;
	let questTitleNumber = `Quset ${dataContainer}`;
	let questBox = questListBox(1,qstnList);
	let qstnJsonList = JSON.stringify(qstnList);
	qstnJsonList = qstnJsonList.replace(/"/g, '&quot;');
	let questContainer = '<div class="quest-container" data-container="'+dataContainer+'">'
		questContainer +=	'<div class="quest-label">'
		questContainer +=		'<h3 class="quest-title-number">'+questTitleNumber+'</h3>'
		questContainer +=		'<input type="button" class="is-key-button" onclick="sectionRemove(this)" value="삭제"/>'
		questContainer +=	'</div>'
		questContainer += 	'<div class="quest-wrap">'
		questContainer += 		'<div class="quest-box">'
		questContainer += 			'<div>'
		questContainer += 				'<input type="text" class="quest-title" value="'+invstNm+'" readonly/>'
		questContainer += 			'</div>'
		questContainer += 			'<div>'
		questContainer += 				'<input type="text" class="quest-sub-title" name="srvySubTitle" placeholder="(선택사항) 조사 부제를 입력하세요."/>'
		questContainer += 			'</div>'
		questContainer += 		'</div>'
		questContainer += 		'<div class="sortable">'
		questContainer +=			questBox
	 	questContainer += 		'</div>'
		questContainer += 	'</div>'
		questContainer += 	'<div class="quest-box-add">'
		questContainer += 		'<input type="button" class="is-key-button" onclick="questBoxAdd(this,'+qstnJsonList+')" value="설문항목 추가하기"/>'
		questContainer += 	'</div>'
		questContainer += '</div>'
		
	container.append(questContainer);
}

// questList
function questBoxAdd(_this,qstnList) {
	let questBoxPar = $(_this).parent().siblings('.quest-wrap').find('.sortable');
	let questBoxLength = questBoxPar.children('.sortable-box').length+1;
	let questBox = questListBox(questBoxLength,qstnList);
 	questBoxPar.append(questBox);
}

// selected layout show/hide
function optionSelected(_this) {
	let selectedItem = $(_this).find('option:selected').val();
	let value = ['radio', 'checkbox', 'select'];
	let itemBox = '<div class="list-answer">'
		itemBox +=	'<ul class="all-answer">'					
		itemBox +=		'<li class="option-list">'
		itemBox +=			'<input type="text" name="qstnAnswContent" placeholder="새 옵션" data-sort="1">'
		itemBox +=			'<input type="button" onclick="optionRemove(this)" value="삭제"/>'
		itemBox +=		'</li>'
		itemBox +=	'</ul>'
		itemBox +=	'<div class="list-append">'
		itemBox +=		'<input type="button" onclick="optionAdd(this)" value="+ 옵션 추가"/>'
		itemBox +=	'</div>'
		itemBox +='</div>'		
		
	$(_this).closest('.quest-box').find('.list-answer').remove();
	if(value.includes(selectedItem)) $(_this).closest('.quest-box').append(itemBox);
}

// optionAdd
let optionAdd = function(_this) {
	let eleParent = $(_this).parent().siblings('.all-answer');
	let listLength = eleParent.children('li').length+1;
	
	let option = '<li class="option-list">';
		option +=	'<input type="text" name="qstnAnswContent" placeholder="새 옵션" data-sort="'+listLength+'">'
		option +=	'<input type="button" onclick="optionRemove(this)" value="삭제"/>'
		option +='</li>';
		
	eleParent.append(option);
}

// optionRemove
function optionRemove(_this) {
	$(_this).parent().remove();
  	let liElements = $('.option-list');
  
	liElements.each(function(index) {
		$(this).find('input[type="text"]').attr('data-sort', index+1);
	});
}

// sectionRemove
function sectionRemove(_this) {
	let questContainer = $('#infoTableWrap').children('.quest-container').length;
	if(questContainer !== 1) $(_this).closest('.quest-container').remove();
	
	$('.quest-title-number').each(function(idx, item){
		let qusetTitleIndex = `Quest ${idx+1}`
		$(item).text(qusetTitleIndex)
	})
	$('.quest-container').each(function(idx, item){
		$(item).attr('data-container', idx+1)
	})
}

// mouseover sortable
function overSortable(_this) {
	$(_this).find('.sortable-handle').removeClass('none');
	$(".sortable").sortable({
		handle: '.sortable-handle',
		stop: function(e, ui){
			$(_this).closest('.quest-container').find(".sortable-box").each(function(idx,item){
				$(item).attr('data-index', idx+1);
			});
			$(_this).closest('.quest-container').find('.quest-number').each(function(idx,item){
				$(item).text('Q'+(idx+1)+'.');
			});
		}
	});
	$(".sortable").disableSelection();
}

// mouseleave sortable
function leaveSortable(_this) {
	$(_this).find('.sortable-handle').addClass('none');
}

// questboxRemove
function questBoxClose(_this) {
	let _thisMemory = $(_this).closest('.quest-container');
	
	$(_this).closest('.quest-box').remove();
	_thisMemory.find(".sortable-box").each(function(idx,item){
		$(item).attr('data-index', idx+1);
	});
	_thisMemory.find('.quest-number').each(function(idx,item){
		$(item).text('Q'+(idx+1)+'.');
	});
}

// questListNewAdd
function questListBox(length = 1,qstnList){
	
	let questBox = 	'<div class="quest-box sortable-box" onmouseover="overSortable(this)" onmouseleave="leaveSortable(this)" data-index="'+length+'">'
	questBox +=		'<div class="sortable-handle none">'
	questBox += 		'<span></span>'
	questBox += 	'</div>'
	questBox += 	'<div class="quest-title-box">'	
	questBox +=			'<div class="quest-title-input">'
	questBox += 			'<span class="quest-number">Q'+length+'.</span>'
	questBox += 			'<input type="text" name="qstnTitle" placeholder="질문을 입력하세요."/>'
	questBox +=			'</div>'
	questBox +=			'<div class="quest-title-select">'
	questBox += 			'<select class="select-box" name="qstnType" onchange="optionSelected(this)">'
								for(qstnItem of qstnList){
	questBox += 				'<option value="'+qstnItem.cd+'">'+qstnItem.cdNm+'</option>'
								}
	questBox += 			'</select>'
	questBox += 			'<span class="quest-close" onclick="questBoxClose(this)"></span>'
	questBox +=			'</div>'
	questBox += 	'</div>'
	questBox += 	'<div>'
	questBox += 		'<input type="text" class="quest-sub-title" name="qstnSubTitle" placeholder="(선택사항) 질문 부제를 입력하세요."/>'	
	questBox += 	'</div>'
	questBox +=	 '</div>';
	
	return questBox;
}
