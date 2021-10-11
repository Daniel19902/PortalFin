
var app = (function (){

    let apiRegistro = registroUser;
    let apiLogin = loginUser;
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
                dataUser = data;
                console.log(dataUser);
                console.log(dataUser.name);
                $("#main").hide();
                $('#info-user').show();
                $('#nameUser').html("Welcome "+dataUser.name);
            });
        },
    };
})();