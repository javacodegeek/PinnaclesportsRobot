package pnklsportparser

class Fixture {
    
    Integer eventId
    Integer leagueId
    String home
    String away
    String rotNum
    Integer liveStatus
    String status
    String starts

    static constraints = {
        eventId(nullable:false, blank: false)
        leagueId(nullable:false, blank: false)
        home(nullable:false, blank: false)
        away(nullable:false, blank: false)
        rotNum(nullable:false, blank: false)
        liveStatus(nullable:true, blank: true)
        status(nullable:false, blank: false)
        starts(nullable:false, blank: false)
    }
    
    static mapping = {
        id generator: 'assigned'
        version false
        eventId column: 'eventId', index: 'Eventid_Idx'
        leagueId column: 'leagueId'
        home column: 'home'
        away column: 'away'
        rotNum column: 'rotNum'
        liveStatus column: 'liveStatus'
        status column: 'status'
        starts column: 'starts'
    }
}
