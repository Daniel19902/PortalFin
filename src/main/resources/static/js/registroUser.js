
var registroUser = (function (){

    let hola = true;

    return {
        registroUserPost: function (data){
            console.log(data, "data");
            let post = $.ajax({
                url:"/api/portal",
                type: 'POST',
                data: JSON.stringify(data),
                contentType: "application/json"
            }).fail(function (jqXHR, textStatus){
                console.log("error en el POST:"+jqXHR+" "+textStatus);
            });
            return post;
        },

        hola: hola

    }
})();