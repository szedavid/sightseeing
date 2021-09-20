$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/tours"
    }).then(function(data, status, jqxhr) {
        $('#first-tour-name').append(data[0]);
        console.log(jqxhr);
    });
});