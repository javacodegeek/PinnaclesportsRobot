package pnklsportparser

class ParserLog {
    Date createdDate = new Date()
    Integer fixtureNum
    Integer oddNum
    String sportId
    String leaguesIds
    String status

    
    static constraints = {
        createdDate(nullable:false, blank: false)
        fixtureNum(nullable:false, blank: false)
        oddNum(nullable:false, blank: false)
        sportId(nullable:false, blank: false)
        leaguesIds(nullable:false, blank: false)
        status(nullable:false, blank: false)
    }
    
    static mapping = {
        version false
        createdDate column: 'createdDate'
        fixtureNum column: 'fixtureNum'
        oddNum column: 'oddNum'
        sportId column: 'sportId'
        leaguesIds column: 'leaguesIds'
        status column: 'status'
    }
}
