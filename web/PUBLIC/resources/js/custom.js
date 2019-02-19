$(function () {
    LoadModal();
});

function LoadModal() {
    $("#add").on('click', function () {
        $("#add").colorbox({iframe: true, innerWidth: 600, innerHeight: 450 ,fadeOut:100,
        onClosed:function(){ 
            location.reload();}
        });
        
    });
}
;