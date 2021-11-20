



var user = (function (){

    return{
        init : function (){
            let infoUser = JSON.parse(localStorage.getItem("id"));
            $('#nombre').html(infoUser.nombre);
        }
    }

})();