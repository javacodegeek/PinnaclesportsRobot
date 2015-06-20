package pnklsportparser

class DefaultValue {
    String name
    String pinnacleLogin
    String pinnaclePassword
    String pinnacleApiUrl
    String pinnacleSportId
    String pinnacleLeagueIds
    String parserTurn
    Integer parserTerm
    Integer stakeValue
    Integer minMaxStakeValue
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
        stakeValue(nullable:false, blank: false)
        minMaxStakeValue(nullable:false, blank: false)
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
        stakeValue column: 'stakeValue'
        minMaxStakeValue column: 'minMaxStakeValue'
        lastAutoRun column: 'lastAutoRun'
        lastManualRun column: 'lastManualRun'
    }
    
}
