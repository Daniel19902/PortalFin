



var user = (function (){

    let expRequerida = 100;

    let experiencia = function (infoUser){
        let exp = infoUser.nivel * expRequerida;
        $('#exp-id').html(infoUser.experiencia +" / "+exp);
        let expUser = Math.trunc(expRequerida*infoUser.experiencia / exp);
        console.log(String(exp+"%"));
        document.getElementById("exp-id").style.setProperty('--progreso-div', String(expUser+"%"));

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