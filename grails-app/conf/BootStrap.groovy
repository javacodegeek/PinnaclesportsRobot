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
                             pinnacleLeagueIds: "1980",
                             parserTurn: "on",
                             parserTerm: 100,
                             stakeValue: 300,
                             minMaxStakeValue: 1000
                            ).save(failOnError: true)
        }
        if(!RobotTask.count()){
            new RobotTask(   status: null,
                             errorCode: null,
                             eventId: '488029618',
                             lineId: '216552524',
                             altLineId: null,                           
                             periodNumber: 0,
                             betType: "MONEYLINE",
                             team: "Team1",
                             side: "under"
                         ).save(failOnError: true)
        }
        
    }
    def destroy = {
    }
}
