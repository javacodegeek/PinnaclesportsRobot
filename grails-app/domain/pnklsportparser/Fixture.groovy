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
    String md5Key 
    static constraints = {
        eventId(nullable:false, blank: false)
        leagueId(nullable:false, blank: false)
        home(nullable:false, blank: false)
        away(nullable:false, blank: false)
        rotNum(nullable:false, blank: false)
        liveStatus(nullable:true, blank: true)
        status(nullable:false, blank: false)
        starts(nullable:false, blank: false)
        md5Key(nullable:false, blank: false, unique: true)

    }
    
    static mapping = {
        version false
        eventId column: 'eventId', index: 'Eventid_Idx'
        leagueId column: 'leagueId'
        home column: 'home'
        away column: 'away'
        rotNum column: 'rotNum'
        liveStatus column: 'liveStatus'
        status column: 'status'
        starts column: 'starts'
        md5Key column: 'md5Key'
    }
}
