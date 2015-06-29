package pnklsportparser

class RobotTask {

    Date     dateCreate
    Date     dateModify
    String   status
    Integer  evgentId
    Integer  lineId
    Integer  stakeValue
    Integer  periodNumber   
    String   betType
    String   team
    String   side
    String   home
    String   away
        
    /*
     * Status = WAIT_LIVE | SUCCESS | DECLINE
     * */
    
    static constraints = {
        dateCreate(nullable:true, blank: true)
        dateModify(nullable:true, blank: true)
        status(nullable:true, blank: true)
        evgentId(nullable:true, blank: true)
        lineId(nullable:true, blank: true)
        stakeValue(nullable:true, blank: true)
        periodNumber  (nullable:true, blank: true) 
        betType(nullable:true, blank: true)
        team(nullable:true, blank: true)
        side(nullable:true, blank: true)
        home(nullable:true, blank: true)
        away(nullable:true, blank: true)
    }
    
    static mapping = {
        version false
        dateCreate column: 'dateCreate'
        dateModify column: 'dateModify'
        status column: 'status'
        evgentId column: 'evgentId'
        lineId column: 'lineId'
        stakeValue column: 'stakeValue'
        periodNumber column: 'periodNumber'
        betType column: 'betType'
        team column: 'team'
        side column: 'side'
        home column: 'home'
        away column: 'away'
    }
    
}
