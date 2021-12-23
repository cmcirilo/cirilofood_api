function listRestaurants(){
    $.ajax({
        url: "http://localhost:8080/restaurants",
        type: "get",

        success: function(response){
            $("#content").text(JSON.stringify(response));
        }
    });
}

function closeRestaurants(){
    $.ajax({
        url: "http://localhost:8080/restaurants/1/close",
        type: "put",

        success: function(response){
            alert("Restaurtant was closed.")
        }
    });
}

$("button").click(listRestaurants);
