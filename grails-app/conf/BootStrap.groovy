import pnklsportparser.DefaultValue

class BootStrap {
    def init = { servletContext ->
        if(!DefaultValue.count()){
            new DefaultValue(name: "PINNACLESPORTSROBOT",
                             pinnacleLogin: "LZ765565",
                             pinnaclePassword: "4567erty!",
                             pinnacleApiUrl: "https://api.pinnaclesports.com/v1/",
                             pinnacleSportId: "29",
                             pinnacleLeagueIds: "1872,2620",
                             parserTurn: "on",
                             parserTerm: 1800
                            ).save(failOnError: true)
        }
    }
    def destroy = {
    }
}
