package pnklsportparser

    
class LineParsingJob {
    
    ParserService parserService


    static triggers = {
        cron name: 'Trigger', startDelay: 10000, cronExpression: '0 50 23 ? * *'
    }
    def execute() {
      println "Run LineParsingJob!"
      parserService.saveFixtures() 
      println Fixture.findAll().size()
      
        
     


    }

}

