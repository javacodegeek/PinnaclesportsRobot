package pnklsportparser

    
class LineParsingJob {
    
    ParserService parserService

    static triggers = {
        cron name: 'Trigger', startDelay: 10000, cronExpression: '0 15 01 ? * *'
    }
    def execute() {
      println "Run LineParsingJob!" 
      parserService.saveFixtures() 
      println Fixture.count()
      parserService.saveSoccerOdds() 
      println SoccerOdd.count()
    }

}

