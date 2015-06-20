package pnklsportparser

class DefaultValue {
    String name
    String pinnacleLogin
    String pinnaclePassword
    String pinnacleApiUrl
    String pinnacleSportId
    String pinnacleLeagueIds
    String parserTurn
    String parserTerm
    Date   lastAutoRun 
    Date   lastManualRun
    
    
    static constraints = {
        name(nullable:false, blank: false)
        pinnacleLogin(nullable:false, blank: false)
        pinnaclePassword(nullable:false, blank: false)
        pinnacleApiUrl(nullable:false, blank: false)
        pinnacleSportId(nullable:false, blank: false)
        pinnacleLeagueIds(nullable:false, blank: false)
        parserTurn(nullable:false, blank: false)
        parserTerm(nullable:false, blank: false)
        lastAutoRun(nullable:true, blank: true)
        lastManualRun(nullable:true, blank: true)
    }
    
    static mapping = {
        version false
        name column: 'name'
        pinnacleLogin column: 'pinnacleLogin'
        pinnaclePassword column: 'pinnaclePassword'
        pinnacleApiUrl column: 'pinnacleApiUrl'
        pinnacleSportId column: 'pinnacleSportId'
        pinnacleLeagueIds column: 'pinnacleLeagueIds'
        parserTurn column: 'parserTurn'
        parserTerm column: 'parserTerm'
        lastAutoRun column: 'lastAutoRun'
        lastManualRun column: 'lastManualRun'
    }
    
}
