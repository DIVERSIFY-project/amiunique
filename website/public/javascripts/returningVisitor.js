$(document).ready(function() {
    if(readCookie('returningvis') == null && readCookie('amiunique') != null && readCookie("tempReturningVis")==null){
        createCookie('returningvis', 1, 2);
        $('#returningvis').trigger('click');
    }
});