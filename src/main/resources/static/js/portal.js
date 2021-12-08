

var portal = (function (){
    let stompClient = null;
    let identificador = null;
    let partida = false;
    let infoUser = JSON.parse(localStorage.getItem("id"));

    let paintPlayers = function (players){
        console.log(players);
        let canvas = document.getElementById("canvas");
        let ctx = canvas.getContext("2d");
        ctx.clearRect(0,0,canvas.width,canvas.height);
        for(let i = 0; i < players.length; i++){
            if(players[i].name != infoUser.nombre) {
                ctx.beginPath();
                ctx.arc(players[i].x, players[i].y, 10, 0, 2 * Math.PI);
                ctx.stroke();
            }
        }
    }

    let finPartida = function (name){
        if(name == infoUser.nombre) {
            location.href = 'score.html';
        }
    }

    let connectAndSubscribe = function (){
        console.log('Connecting to ws...');
        let socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame){
            console.log('Connected' + frame);
            stompClient.send('/app/paintPlayers.'+identificador, {});
            stompClient.subscribe('/topic/paintPlayers.'+identificador, function (eventBody){
                let players = JSON.parse(eventBody.body);
                console.log(players);
                paintPlayers(players);
                partida = true;
            });
            stompClient.subscribe('/topic/paintPlayer.'+identificador, function (eventBody){
                let msg = JSON.parse(eventBody.body);
                console.log(msg);
                paintPlayers(msg);
            });

            stompClient.subscribe('/topic/finPartida.'+identificador, function (eventBody){
                let name = eventBody.body;
                console.log(name);
                finPartida(name);
            })

        });
    };



    return{

        init : function (){
            identificador = infoUser.idSala;
            connectAndSubscribe();
        },

        moverPerspnaje : function (x, y){
            console.log(x, y, infoUser.name);
            if (partida){
                stompClient.send('/app/moverPersonaje.'+identificador+"/"+x+"/"+y,{}, infoUser.nombre);
            }
        },

        getPartida : function (){
            return partida;
        }

    };

    }


)();