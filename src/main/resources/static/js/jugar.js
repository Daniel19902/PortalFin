

var jugar = (function (){

    let api = apiPortal;

    return{

        init : function (){

            api.getSalas(function (error, data){
                let html = "";
                data.map( function (sala){
                    html += "<tr>";
                    html += "<td>" +sala.nombre+ "</td>";
                    html += "<td>" +sala.numeroUsers+ "</td>";
                    html += "<td><button type='button' class='btn btn-success' onclick= 'jugar.loadRoom(\""+sala.id+"\")' >Unirse</button></td>";
                    html += "</tr>"
                });
                $('#info-salas').html(html);
            });
        },

        loadRoom : function (idSala){
            let infoUser = JSON.parse(localStorage.getItem("id"));
            alert(infoUser.id);
            api.asignarSala(idSala, infoUser.id).then( function (){
                jugar.updateUser(infoUser);
            });
        },

        crearSala : function (name, numeroUser){
            let infoUser = JSON.parse(localStorage.getItem("id"));
            console.log(infoUser.id);
            let sala = {
                nombre: name,
                numeroUsers: parseInt(numeroUser),
                idcreador: infoUser.id
            };
            api.crearSala(sala).then(function (){
                jugar.updateUser(infoUser);
            });
        },


        updateUser : function (infoUser){
            api.getPersonaje(infoUser.id, function (error, data){
                localStorage.removeItem("id");
                localStorage.setItem("id", JSON.stringify(data));
                location.href = 'room.html';
            });
        }

    }

})();

