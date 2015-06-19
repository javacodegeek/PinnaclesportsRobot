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
}
