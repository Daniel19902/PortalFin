
var app = (function (){

    let apiRegistro = registroUser;
    let apiLogin = loginUser;


    return {
        registroUserP: function (name, mail, password, edad){
            let user = {
                name: name,
                mail: mail,
                password: password,
                age: parseInt(edad)
            };
            console.log(user);
            apiRegistro.registroUserPost(user);
        },

        loginUser : function (mail, password){
            apiLogin.verificarUser(mail, password,function (error, data){
                $('#correo').hide();
                $('#password').hide();
                $('#login').hide();

                $('#name').html(data.name);
                $('#mail').html(data.mail);
                $('#age').html(data.age);
            });
        }


    };


})();