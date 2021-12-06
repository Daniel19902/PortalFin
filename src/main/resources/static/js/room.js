

var room = (function (){

    let restApi = apiPortal;

    class Player{

        constructor(idSala, id, x, y, lugar) {
            this.idSala = idSala;
            this.id = id;
            this.x = x;
            this.y = y;
            this.lugar = lugar;
        }
    }

    let infoUser = JSON.parse(localStorage.getItem("id"));
    let listPlayers = [];
    let identificador = 0;
    let stompClient = null;
    let numeroUsers = 0;
    let player = null;
    let partida = false;
    let conectado = false;

    let update = function (users){
        console.log(users);
        numeroUsers = users;

        $('#numeroUsers').html("Numero de usuarios: "+numeroUsers);
        $('#id').html("id del usuario:" + identificador);
    }

    let paintPlayers = function (players){
        console.log(players);
        let canvas = document.getElementById("canvas");
        let ctx = canvas.getContext("2d");
        ctx.clearRect(0,0,canvas.width,canvas.height);
        for(let i = 0; i < players.length; i++){
            ctx.beginPath();
            ctx.arc(players[i].x, players[i].y, 10, 0, 2*Math.PI);
            ctx.stroke();
        }
    }

    let mover = function (){
    }

    let makePlayer = function (idSala, id, x, y, lugar){
        player = new Player(idSala, id, x , y , lugar);
        console.log(player);
    }

    let finPartida = function (){
        stompClient.send("/app/infoPlayer."+identificador+"/"+player.id,{})
        //location.href = 'score.html';
        //console.log("entre");
        //$("#historial").click(function (){
          //  alert("funca");
        //});
    }

    let addHistorial = function (historial){
        restApi.setHistorial(historial).then(function (){
            location.href = "score.html";

        });
    }

    let moverPerspnaje = function (x){
        console.log(x);
    }

    let listaJugadores = function (jugadores){
        let html = "";

        jugadores.map( function (jugador){

            html += "<tr>";
            html += "<td>" +jugador.name+ "</td>";
            html += "<td>" +infoUser.nivel+ "</td>";
            html += "</tr>"

        })
        $('#info-jugadores').html(html);
        $('#num-players').html(jugadores.length);

    }

    let publicarMensaje = function (mensaje){
        console.log(mensaje);
        let contenido = $('#chatbox').val();
        alert(contenido);
        $('#chatbox').html(contenido+"\n"+mensaje);
    }

    let iniciarPartida = function (){
        console.log("iniciarPartida");
        partida = true;
        if (stompClient !== null) {
            stompClient.disconnect();
        }
        console.log("Disconnected");
        location.href = "portal.html";
    };

    let paintPlayer = function (player){
        let canvas = document.getElementById("canvas");
        let ctx = canvas.getContext("2d");
        ctx.clearRect(0,0,canvas.width,canvas.height);
        ctx.beginPath();
        ctx.arc(player.x, player.y, 10, 0, 2*Math.PI);
        ctx.stroke();
    }

    let connectAndSubscribe = function (){
        console.log('Connecting to ws...');
        let socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame){
           console.log('Connected' + frame);
           room.conectarseSala();
           stompClient.subscribe('/topic/newUser.'+identificador, function (eventBody){
               let num = JSON.parse(eventBody.body);
               console.log(num);
               listaJugadores(num);
           });

           stompClient.subscribe('/topic/mensaje.'+identificador, function (eventBody){
               let mensaje = (eventBody.body);
               console.log(mensaje);
               publicarMensaje(mensaje);
           });


           stompClient.subscribe('/topic/paintPlayers.'+identificador, function (eventBody){
               let players = JSON.parse(eventBody.body);
               console.log(players);
               //iniciarPartida();
               paintPlayers(players);
           });

           stompClient.subscribe('/topic/iniciarPartida.'+identificador, function (eventBody){
               iniciarPartida();
           });

           stompClient.subscribe('/topic/finPartida.'+identificador, function (eventBody){
               let ms = JSON.parse(eventBody.body);
               console.log(ms.id);
               console.log(player.id);
               if(ms.id == player.id){
                   finPartida();
               }
           });

           stompClient.subscribe('/topic/infoPlayer.'+identificador, function (eventBody){
               let msg = JSON.parse(eventBody.body);
               console.log(msg);
               addHistorial(msg);
           });

            stompClient.subscribe('/topic/paintPlayer.'+identificador, function (eventBody){
                let msg = JSON.parse(eventBody.body);
                console.log(msg);
                paintPlayers(msg);
            });

        });
    }

    return{

        init: function (){
            identificador = JSON.parse(localStorage.getItem("id")).idSala;
            console.log(identificador);
            connectAndSubscribe();
        },

        conectarseSala : function (){
            console.log(conectado);
            if(conectado !== true){
                conectado = true;
                console.log(conectado);
                stompClient.send('/app/newUser.'+identificador, {}, infoUser.nombre);
            }
        },

        iniciarPartida: function (){
            stompClient.send('/app/iniciarPartida.'+identificador, {} );
        },

        mensaje : function (mensaje){
            stompClient.send('/app/mensaje.'+identificador, {}, infoUser.nombre+": "+mensaje);
            $('#usermsg').val("");
        },

    }
})();
