package pnklsportparser

class RobotTask {

    Date     dateCreate = new Date() 
    Date     dateModify = new Date()
    String   status
    String   errorCode
    Integer  eventId
    Integer  lineId
    Integer  betId
    Integer  altLineId
    Integer  stakeValue
    Integer  periodNumber   
    String   betType
    String   team
    String   side
    Float    minKoff
    
    

    static constraints = {
        dateCreate(nullable:true, blank: true)
        dateModify(nullable:true, blank: true)
        status(nullable:true, blank: true)
        errorCode(nullable:true, blank: true)
        eventId(nullable:true, blank: true)
        lineId(nullable:true, blank: true)
        betId(nullable:true, blank: true)
        altLineId(nullable:true, blank: true)
        stakeValue(nullable:true, blank: true)
        periodNumber  (nullable:true, blank: true) 
        betType(nullable:true, blank: true)
        team(nullable:true, blank: true)
        side(nullable:true, blank: true)
        minKoff(nullable:true, blank: true)
    }
    
    static mapping = {
        version false
        dateCreate column: 'dateCreate'
        dateModify column: 'dateModify'
        status column: 'status'
        errorCode column: 'errorCode'
        eventId column: 'evgentId'
        lineId column: 'lineId'
        betId column: 'betId'
        altLineId column: 'altLineId'
        stakeValue column: 'stakeValue'
        periodNumber column: 'periodNumber'
        betType column: 'betType'
        team column: 'team'
        side column: 'side'
        minKoff column: 'minKoff'
    }
    
}
