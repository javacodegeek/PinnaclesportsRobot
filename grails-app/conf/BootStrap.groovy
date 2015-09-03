import pnklsportparser.DefaultValue
import pnklsportparser.RobotTask

class BootStrap {
    def init = { servletContext ->
        if(!DefaultValue.count()){
            new DefaultValue(name: "PINNACLESPORTSROBOT",
                             pinnacleLogin: "IC748133",
                             pinnaclePassword: "rty123456!",
                             pinnacleApiUrl: "https://api.pinnaclesports.com/v1/",
                             pinnacleSportId: "29",
                             pinnacleLeagueIds: "5874",
                             parserTurn: "on",
                             parserTerm: 100,
                             stakeValue: 2,
                             minMaxStakeValue: 1000
                            ).save(failOnError: true)
        }
        if(!RobotTask.count()){
            new RobotTask(   status: null,
                             errorCode: null,
                             eventId: '',
                             lineId: '',
                             altLineId: null,                           
                             periodNumber: '',
                             betType: "",
                             team: "",
                             side: ""
                         ).save(failOnError: true)
        }
        
    }
    def destroy = {
    }
}
