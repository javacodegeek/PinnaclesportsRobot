package pnklsportparser

    
class ParsingJob {
    
    ParserService parserService

    static triggers = {
        simple startDelay: 60000, repeatInterval: 30000  
    }
    
    def execute() {
      println "Run LineParsingJob!" 
      parserService.runParsing()
    }

}

