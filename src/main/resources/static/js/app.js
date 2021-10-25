
var app = (function (){

    let apiRegistro = registroUser;
    let apiLogin = loginUser;
    let apiRoom = room;
    let salaUser = null;
    let dataUser = null;


    return {
        registroUserP: function (name, mail, password, edad){
            let user = {
                name: name,
                mail: mail,
                password: password,
                age: parseInt(edad)
            };
            apiRegistro.registroUserPost(user).then( function () {
                alert("El usuario a sido registrado");
                location.href = '../loginUser.html';
            });
        },

        loginUser : function (mail, password){
            apiLogin.verificarUser(mail, password,function (error, data){
                registroUser.cacheUser(data).then( function (){
                    location.href = 'user.html';
                });
            });
        },

        loadInfoUser: function (){
            registroUser.getCacheUser( function (error, data){
                //let dataUser = JSON.parse(data.body);
                console.log(data);
                $('#nameUser').html("Welcome "+data.name);
            })
        },

        crearSala : function (name, numeroUser){
            let sala = {
                nombre: name,
                numeroUsers: parseInt(numeroUser)
            };
            registroUser.crearSala(sala).then( function (){
                alert("Sala creada");
                location.href = '../loginUser.html';
            });
        },

        infoSalas : function (){
            registroUser.getCacheUser(function (error, data){
                dataUser = data;
                $('#name').html(data.name);
                $('#id').html(data.id);
            });
            registroUser.getSalas(function (error, data){
                let html = "";
                data.map( function (sala){
                    html += "<tr>";
                    html += "<td>" +sala.nombre+ "</td>";
                    html += "<td>" +sala.numeroUsers+ "</td>";
                    html += "<td>" +sala.codigoSala+ "</td>";
                    html += "<td><button type='button' class='btn btn-success' onclick='app.conectarSala(\""+sala.codigoSala+"\")'>Unirse</button></td>";
                    html += "</tr>"
                });
                $('#info-salas').html(html);
            });
        },

        conectarSala: function (sala){
            salaUser = sala;
            registroUser.setSala(sala).then(function (){
                console.log(salaUser);
                apiRegistro.updateSalaUser(dataUser.id, salaUser).then( function (){
                    location.href = 'room.html';
                });
            });
        },

        loadInfoSala: function (){
            registroUser.getSala(function (error, data){
                salaUser = data;
                $('#funca').html("redireccionamiento");
                registroUser.getCacheUser(function (error, data){
                    dataUser = data;
                    apiRoom.init(salaUser.id, dataUser.id);
                })
            })
        },

        empezarPartida: function (){
            apiRoom.iniciarPartida();
        },

        historialUser : function (){
            apiRegistro.getCacheUser(function (error, data){
                dataUser = data;
                $('#name').html(data.name);
                apiRegistro.getHistorial(dataUser.id, function (error, data){
                    console.log(data);
                    let html = "";
                    data.map( function (historial){
                        html += "<tr>";
                        html += "<td>" +historial.id+ "</td>";
                        html += "<td>" +historial.podio+ "</td>";
                        html += "<td>" +historial.oro+ "</td>";
                        html += "<td>" +historial.experiencia+ "</td>";
                        html += "</tr>"
                    });
                    $('#table-historial').html(html);
                });
            });
        }

    };
})();