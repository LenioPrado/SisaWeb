function closeOpennedExpander(){
	var i = $('.ui-row-toggler.ui-icon-circle-triangle-s').length;
	if(i == 1){return;}
    $('.ui-row-toggler.ui-icon-circle-triangle-s').trigger('click');
}