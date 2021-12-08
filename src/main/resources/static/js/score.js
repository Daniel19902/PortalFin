

var score = (function (){


    let api = apiPortal;
    let infoUser = JSON.parse(localStorage.getItem("id"));
    let bonusOroExp = null;

    let infoPodioPlayers = function (){
        let html = "";
        api.infoPodio(infoUser.idSala, function (error, data){
            bonusOroExp = data;
            data.map(function (player){
                html += "<tr>";
                html += "<td>" +player.name+ "</td>";
                html += "<td>" +player.oro+ "</td>";
                html += "<td>" +player.expe+ "</td>";
                html += "<td>" +player.podio+ "</td>";
                html += "</tr>"
            });
            $('#info-score').html(html);
        });

    }

    let setHistorial = function (player){
        let historial = {
            id: infoUser.id,
            nombre: player.name,
            podio: player.podio,
            oro: player.oro,
            experiencia: player.expe
        };

        api.setHistorial(historial).then( function (){
            location.href = "user.html";
        });
    }

    return{

        init : function (){
            infoPodioPlayers();
        },

        bonus : function (){
            bonusOroExp.map(function (player){
                if(player.name == infoUser.nombre){
                    api.updateBonus(player.name, player.oro, player.expe).then(function (data){
                        localStorage.removeItem("id");
                        localStorage.setItem("id", JSON.stringify(data));
                        setHistorial(player);
                        //location.href = "user.html";
                    });
                }
            })
        }



    }

})();