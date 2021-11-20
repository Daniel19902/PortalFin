

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
    let listPlayers = [];
    let identificador = 0;
    let stompClient = null;
    let numeroUsers = 0;
    let player = null;
    let Partida = null;

    let update = function (users){
        console.log(users);
        numeroUsers = users;

        $('#numeroUsers').html("Numero de usuarios: "+numeroUsers);
        $('#id').html("id del usuario:" + identificador);
    }

    let paintPlayers = function (players){
        listPlayers = players;
        let canvas = document.getElementById("canvas");
        let ctx = canvas.getContext("2d");
        ctx.clearRect(0,0,canvas.width,canvas.height);
        for(let i = 0; i < players.length; i++){
            ctx.beginPath();
            ctx.arc(players[i].x+20, i*30 + 20,10,0,2*Math.PI);
            ctx.stroke();
        }
    }

    let mover = function (){
        console.log("iniciar");
        document.addEventListener('keydown',function (e){
            console.log("keyBoard");
            if (e.keyCode == "39"){
                console.log("keyBoard39");
                player.x+=50;
                console.log(JSON.stringify(player));
                stompClient.send("/app/movePlayer."+identificador,{},JSON.stringify(player));
            }
            if(e.keyCode == "37"){
                console.log("keyboard37");
                player.x-=50;
                console.log(JSON.stringify(player));
                stompClient.send("/app/movePlayer."+identificador,{},JSON.stringify(player));
            }
        });
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

    let connectAndSubscribe = function (){
        console.log('Connecting to ws...');
        let socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame){
           console.log('Connected' + frame);
           stompClient.send('/app/newUser.'+identificador, {});
           stompClient.subscribe('/topic/newUser.'+identificador, function (eventBody){
               let num = JSON.parse(eventBody.body);
               update(num);
           });

           stompClient.subscribe('/topic/paintPlayers.'+identificador, function (eventBody){
               let players = JSON.parse(eventBody.body);
               console.log(players);
               paintPlayers(players);
           });

           stompClient.subscribe('/topic/iniciarPartida.'+identificador, function (eventBody){
               mover();
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

        });
    }

    return{

        init: function (idSala, idUser){
            identificador = idSala;
            console.log(identificador+" "+idUser);
            connectAndSubscribe();
            makePlayer(idSala, idUser,0,0,0);
        },

        iniciarPartida: function (){
            stompClient.send('/app/iniciarPartida.'+identificador,{});
        },

    }
})();