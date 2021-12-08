


var historial =  (function (){

    let api = apiPortal;
    let infoUser = JSON.parse(localStorage.getItem("id"));



    return{

        init : function (){

            api.getHistorial(infoUser.nombre, function (error, data){
                let html = "";
                data.map( function (historial){
                    html += "<tr>";
                    html += "<td>" +historial.nombre+ "</td>";
                    html += "<td>" +historial.podio+ "</td>";
                    html += "<td>" +historial.oro+ "</td>";
                    html += "<td>" +historial.experiencia+ "</td>";
                    html += "</tr>"
                });
                $('#table-historial').html(html);
            });
        }
    }

})();