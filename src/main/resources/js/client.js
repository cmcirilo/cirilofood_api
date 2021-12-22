function listRestaurants(){
    $.ajax({
        url: "http://localhost:8080/restaurants",
        type: "get",

        success: function(response){
            $("content").text(response);
        }
    });
}

$("button").click(listRestaurants);
