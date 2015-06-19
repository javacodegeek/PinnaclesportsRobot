package pnklsportparser

class DefaultValue {
    
    String pinnacleLogin
    String pinnaclePassword
    String pinnacleApiUrl
    String pinnacleSportId
    String pinnacleLeagueIds
    String parserTurn //on, off
    Integer parserTerm //period in seconds for eyample 30*60 = 1800
    
    static constraints = {
        pinnacleLogin(nullable:false, blank: false)
        pinnaclePassword(nullable:false, blank: false)
        pinnacleApiUrl(nullable:false, blank: false)
        pinnacleSportId(nullable:false, blank: false)
        pinnacleLeagueIds(nullable:false, blank: false)
        parserTurn(nullable:false, blank: false)
        parserTerm(nullable:false, blank: false)
    }
    
    static mapping = {
        version false
        pinnacleLogin column: 'pinnacleLogin'
        pinnacleLogin column: 'pinnaclePassword'
        pinnacleLogin column: 'pinnacleApiUrl'
        pinnacleLogin column: 'pinnacleSportId'
        pinnacleLogin column: 'pinnacleLeagueIds'
        pinnacleLogin column: 'parserTurn'
        pinnacleLogin column: 'parserTerm'
    }
}
