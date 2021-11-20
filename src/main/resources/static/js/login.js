


var login = (function (){

    let api = apiPortal;

    return{

        loginUser : function (name, password){
            console.log("entreLogin");
            api.verificarUser(name, password, function (error, data){
                console.log(data);
                localStorage.setItem("id", JSON.stringify(data));
                location.href = 'user.html';
            });
        },

        crearUser : function (name, mail, password){
            let user = {
                name: name,
                mail: mail,
                password: password
            }
            api.crearUser(user).then( function (data){
                location.href = 'loginUser.html';
            });
        }


    }

})();