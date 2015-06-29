package pnklsportparser

class DefaultController {

    def index() { }
    
    def getDefaultValue(){
        def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
        def data = DV
        println data.dump()
        render(view: "/defaultvalue", model: [data: data])
    }
    
    def setDefaultValue(){        
        def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
        DV.pinnacleLogin = params.pinnacleLogin
        DV.pinnaclePassword = params.pinnaclePassword
        DV.pinnacleApiUrl = params.pinnacleApiUrl
        DV.pinnacleSportId = params.pinnacleSportId
        DV.pinnacleLeagueIds = params.pinnacleLeagueIds
        DV.parserTurn = params.parserTurn
        DV.parserTerm = Integer.parseInt(params.parserTerm)
        DV.stakeValue = Integer.parseInt(params.stakeValue)
        DV.minMaxStakeValue = Integer.parseInt(params.minMaxStakeValue)

        DV.save()
        render "Success!"

    }
}
