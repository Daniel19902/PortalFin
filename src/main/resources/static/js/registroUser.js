
var registroUser = (function (){

    return {
        registroUserPost: function (data){
            return  $.ajax({
                url:"/api/portal",
                type: 'POST',
                data: JSON.stringify(data),
                contentType: "application/json"

            }).fail(function (jqXHR, textStatus){
                console.log("error en el POST:"+jqXHR+" "+textStatus);
            });
        },

    }
})();