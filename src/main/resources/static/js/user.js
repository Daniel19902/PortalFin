



var user = (function (){

    let expRequerida = 100;

    let experiencia = function (infoUser){
        let exp = infoUser.nivel * expRequerida;
        $('#exp-id').html(infoUser.experiencia +" / "+exp);
    }


    return{

        init : function (){
            let infoUser = JSON.parse(localStorage.getItem("id"));
            $('#nombre-id').html(infoUser.nombre);
            $('#oro-id').html("Oro: "+infoUser.oro);
            experiencia(infoUser);
            $('#nivel-id').html(infoUser.nivel);
        }
    }

})();