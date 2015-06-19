package pnklsportparser

class SoccerOdd {
    Integer eventId
    Integer leagueId
    String period0 
    String period1 
    String period2 
    
    static constraints = {
        eventId(nullable:false, blank: false)
        leagueId(nullable:false, blank: false)
        period0(nullable:true, blank: true)
        period1(nullable:true, blank: true)
        period2(nullable:true, blank: true)
    }
    
    static mapping = {
        version false
        eventId column: 'eventId', index: 'Eventid1_Idx'
        leagueId column: 'leagueId'
        period0 column: 'period0', sqlType: "text"
        period1 column: 'period1', sqlType: "text"
        period2 column: 'period2', sqlType: "text"
    }
}
