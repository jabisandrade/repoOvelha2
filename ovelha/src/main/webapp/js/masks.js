applyMasks();

function applyMasks() {

    jQuery(function($) {
	$('.telefoneAluno').mask('(00) 0000-00009', {
	    reverse : true
	});

	
    });
    $(document).ready(function() {
        $('#checkAll').on('click', function() {
             //selects all records on the displayed page if pagination is supported.
             //list.selectAllRowsOnPage();
        	 alert ('javascript funcionando');
             //or you can select all the rows across all pages.
             //list.selectAllRows();
        });
    });
}