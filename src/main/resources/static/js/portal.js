

var portal = (function (){
    let stompClient = null;
    let identificador = null;
    let partida = false;
    let infoUser = JSON.parse(localStorage.getItem("id"));
    let skin = JSON.parse(localStorage.getItem("skinEscogida"));
    let skinJuego = "goku";
    let img = new Image();

    let paintPlayers = function (players){
        console.log(players);
        let canvas = document.getElementById("canvas");
        let ctx = canvas.getContext("2d");
        ctx.clearRect(0,0,canvas.width,canvas.height);
        for(let i = 0; i < players.length; i++){
            let img = new Image();
            console.log(img.sizes);
            img.src = "assets/"+skin+"2.png";
            if(players[i].name != infoUser.nombre) {
                ctx.beginPath();
                ctx.drawImage(img, players[i].x, players[i].y);
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
        },

        test : function (){
            console.log("entre");
            let canvas = document.getElementById("test");
            let ctx = canvas.getContext("2d");
            let img = new Image();
            img.src = "assets/goku.gif";
            ctx.drawImage(img, 500, 10);
        },

        darSkin : function (){
            if(skin == "naruto"){
                skinJuego = "personaje2";
            }
            else if(skin == "pikachu"){
                skinJuego = "personaje3";
            }
            else if(skin == "goku"){
                skinJuego = "personaje4";
            }else {
                skinJuego = "personaje1";
            }
            console.log("entre skin");
            return skinJuego;
        }

    };

    }


)();