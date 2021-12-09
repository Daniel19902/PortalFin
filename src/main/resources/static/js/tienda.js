

var tienda = (function (){

    let api = apiPortal;
    let infoUser = JSON.parse(localStorage.getItem("id"));
    return{


        comprar : function ( imagen, precio ){
            api.comprarSkin(precio, infoUser.nombre).then(function (){
                localStorage.setItem("skin", JSON.stringify(imagen));
            });
        }

    }

})();