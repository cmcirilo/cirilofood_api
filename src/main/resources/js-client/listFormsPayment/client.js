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

    linha.append(
      $("<td>").text(formPayment.id),
      $("<td>").text(formPayment.description)
    );

    linha.appendTo("#table");
  });
}


$("#btn-list").click(list);
