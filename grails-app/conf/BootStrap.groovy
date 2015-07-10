import pnklsportparser.DefaultValue
import pnklsportparser.RobotTask

class BootStrap {
    def init = { servletContext ->
        if(!DefaultValue.count()){
            new DefaultValue(name: "PINNACLESPORTSROBOT",
                             pinnacleLogin: "LZ765565",
                             pinnaclePassword: "4567erty!",
                             pinnacleApiUrl: "https://api.pinnaclesports.com/v1/",
                             pinnacleSportId: "29",
                             pinnacleLeagueIds: "1835,6310",
                             parserTurn: "on",
                             parserTerm: 100,
                             stakeValue: 300,
                             minMaxStakeValue: 1000
                            ).save(failOnError: true)
        }
        if(!RobotTask.count()){
            new RobotTask(   status: null,
                             errorCode: null,
                             eventId: 480523373,
                             lineId: 210722608,
                             altLineId: null,
                             periodNumber: 0,
                             betType: "MONEYLINE",
                             team: "Team1",
                             side: null
                         ).save(failOnError: true)
        }
        
    }
    def destroy = {
    }
}
