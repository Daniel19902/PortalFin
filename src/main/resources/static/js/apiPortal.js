
var apiPortal = (function (){

    return {

        verificarUser : function (name, password,callback){
            $.get("/api/portal/login/"+name+"/"+password, function (data){
                callback(null,data);
            }).fail(function (jqXHR, textStatus){
                $('#password-bad').html("El nombre de usuario o la contraseña no son correctos");
            });
        },

        crearUser: function (data){
            return  $.ajax({
                url:"/api/portal/crearUser",
                type: 'POST',
                data: JSON.stringify(data),
                contentType: "application/json"

            }).fail(function (jqXHR, textStatus){
                console.log("error al crear un usuario:");
            });
        },

        getSalas : function (callback){
            $.get("/api/portal/salas", function (data){
                console.log(data);
                callback(null, data);
            });

        },

        cacheUser : function (data){
            return $.ajax({
                url:"/api/portal/cacheUser",
                type: 'POST',
                data: JSON.stringify(data),
                contentType: "application/json"
            }).fail(function (jqXHR, textStatus){
                console.log("error en el POST:"+jqXHR+" "+textStatus);
            });
        },

        getCacheUser: function (callback){
            $.get("/api/portal/getCacheUser", function (data){
               callback(null, data);
            });
        },


        crearSala: function (data){
            console.log(data);
            return $.ajax({
                url: "/api/portal/sala",
                type: 'POST',
                data: JSON.stringify(data),
                contentType: "application/json"
            }).fail(function (jqXHR, textStatus){
                console.log("error en el POST:"+jqXHR+" "+textStatus);
            });
        },

        updateSalaUser: function (idUser, idSala){
            return $.ajax({
               url: "api/portal/asignarSala/"+idUser+"/"+idSala,
               type: 'PUT',
               contentType: "application/json"
            }).fail(function (jqXHR, textStatus){
                console.log("error en el POST:"+jqXHR+" "+textStatus);
            });
        },

        setSala: function (idSala){
            return $.ajax({
                url: "api/portal/setIdSala/"+idSala,
                type: 'POST',
                contentType: "application/json"
            }).fail(function (jqXHR, textStatus){
                console.log("error en el POST:"+jqXHR+" "+textStatus);
            });
        },

        getSala : function (callback){
            $.get("/api/portal/getSalaUser", function (data){
                callback(null, data);
            })
        },

        setHistorial : function (historial){
            return $.ajax({
                url: "api/portal/add/historial",
                type: 'POST',
                data: JSON.stringify(historial),
                contentType: "application/json"
            }).fail(function (jqXHR, textStatus){
                console.log("error en el POST:"+jqXHR+" "+textStatus);
            });
        },

        getHistorial : function (idUser, callback){
            $.get("/api/portal/getHistorial/"+idUser, function (data){
                callback(null, data);
            })
        }
    }

})();