
$( document ).ready(function() {
    $("#btnSaveDV").click(function() {
        url = "setdv";
        data = {"pinnacleLogin": $('#pinnacleLogin').val(),
                "pinnaclePassword": $('#pinnaclePassword').val(),
                "pinnacleApiUrl": $('#pinnacleApiUrl').val(),
                "pinnacleSportId": $('#pinnacleSportId').val(),
                "pinnacleLeagueIds": $('#pinnacleLeagueIds').val(),
                "parserTurn": $('#parserTurn').val(),
                "parserTerm": $('#parserTerm').val(),
                "stakeValue": $('#stakeValue').val(),
                "minMaxStakeValue": $('#minMaxStakeValue').val()
               };
      
        $.post(url, data)
                .done(function(data) {
                 alert("Data Loaded: " + data);
        });
    });
});