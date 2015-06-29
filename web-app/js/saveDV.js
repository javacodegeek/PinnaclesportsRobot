
$( document ).ready(function() {
    $("#btnSaveDV").click(function() {
        url = "setdv";
        data = {"test": 123};
      
        $.post(url, { name: "John", time: "2pm" })
                .done(function(data) {
                 alert("Data Loaded: " + data);
        });
    });
});