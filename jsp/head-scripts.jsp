<link rel="stylesheet" href=".\css\new\tear_ball.css">

<link rel="stylesheet" href=".\animsition-master\dist\css\animsition.min.css">
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- animsition.js -->
<script src=".\animsition-master\dist\js\animsition.min.js"></script>
<script src="./js/jajaxloader.js"></script>

<script type="text/javascript">
 window.onload=function(){

if (!document.getElementById) return false;
var f = document.getElementById('auto_off');



}

$(document).ready(function() {
    $('.animsition').animsition();

  
  
  var jTarget = $('#target');


function setHandler(id, cssClass, content) {
    if ('undefined' === typeof content) {
        content = '';
    }
    $('#' + id).on('click', function () {
        
        jTarget.ajaxloader({
            cssClass: cssClass,
            content: content,
        });
    });
}

setHandler('loader1', 'vulchivijay_rosace', '<div class="spinnerBlock"><span></span><span></span><span></span><span></span><span></span><span></span><span></span></div>');
setHandler('loader2', 'lukehaas_vertical_bars');
setHandler('loader3', 'lukehaas_circle_on_path');
setHandler('loader4', 'lukehaas_tear_ball');
setHandler('loader5', 'cssload_thecube', '<div class="cssload-cube cssload-c1"></div><div class="cssload-cube cssload-c2"></div><div class="cssload-cube cssload-c4"></div><div class="cssload-cube cssload-c3"></div>');
setHandler('loader6', 'cssload_colordots', '<div class="cssload-dots" style="filter: url(#goo);"><div class="cssload-dot"></div><div class="cssload-dot"></div><div class="cssload-dot"></div><div class="cssload-dot"></div><div class="cssload-dot"></div></div><svg version="1.1" xmlns="http://www.w3.org/2000/svg"><defs><filter id="goo"><feGaussianBlur in="SourceGraphic" result="blur" stdDeviation="12" ></feGaussianBlur><feColorMatrix in="blur" mode="matrix" values="1 0 0 0 0	0 1 0 0 0	0 0 1 0 0	0 0 0 18 -7" result="goo" ></feColorMatrix><!--<feBlend in2="goo" in="SourceGraphic" result="mix" ></feBlend>--></filter></defs></svg>');
setHandler('loader7', 'cssload_flipping_square', '<div class="cssload-flipper"><div class="cssload-front"></div><div class="cssload-back"></div></div>');
setHandler('loader8', 'cssload_spinning_square');
setHandler('loader9', 'cssload_zenith', '<div class="cssload-zenith"></div>');
setHandler('loader10', 'cssload_ventilator', '<div class="cssload-ventilator"></div>');



});

</script>
