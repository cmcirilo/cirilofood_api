function list() {
  $.ajax({
    url: "http://localhost:8080/forms-payment",
    type: "get",

    success: function(response) {
      loadTable(response);
    }
  });
}

function loadTable(formsPayment) {
  $("#table tbody tr").remove();

  $.each(formsPayment, function(i, formPayment) {
    var linha = $("<tr>");

     var linkAcao = $("<a href='#'>")
            .text("Remove")
            .click(function(event) {
              event.preventDefault();
              remove(formPayment);
            });

    linha.append(
      $("<td>").text(formPayment.id),
      $("<td>").text(formPayment.description),
      $("<td>").append(linkAcao)
    );

    linha.appendTo("#table");
  });
}

function save() {
  var formPaymentJson = JSON.stringify({
    "description": $("#field-description").val()
  });

  console.log(formPaymentJson);

  $.ajax({
    url: "http://localhost:8080/forms-payment",
    type: "post",
    data: formPaymentJson,
    contentType: "application/json",

    success: function(response) {
      alert("Form Payment saved");
      list();
    },

    error: function(error) {
      if (error.status >= 400 && error.status <= 499) {
        var problem = JSON.parse(error.responseText);
        alert(problem.userMessage);
      } else {
        alert("Error saving form payment");
      }
    }
  });
}

function remove(formPayment){
    var url = "http://localhost:8080/forms-payment/" + formPayment.id;

  $.ajax({
    url: url,
    type: "delete",

    success: function(response) {
      list();

      alert("Form Payment removed!");
    },

    error: function(error) {
      if (error.status >= 400 && error.status <= 499) {
        var problem = JSON.parse(error.responseText);
        alert(problem.userMessage);
      } else {
        alert("Error removing form payment");
      }
    }
  });
}


$("#btn-list").click(list);
$("#btn-save").click(save);
