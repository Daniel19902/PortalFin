

var loginUser = (function (){

    return {
        verificarUser : function (mail, password,callback){
            $.get("/api/portal/"+mail+"/"+password, function (data){
                callback(null,data);
            }).fail(function (){
                alert("usuario no se encuentra registrado");
            });
        }
    }
})();