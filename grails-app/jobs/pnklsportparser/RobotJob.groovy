package pnklsportparser

    
class RobotJob {
    
    ParserService parserService

    static triggers = {
        simple startDelay: 60000, repeatInterval: 60000  
    }
    
    def execute() {
      println "Run ROBOT Job!" 
      def eventList = RobotTask.list()
      eventList
      if (eventList){
             eventList.each{ev ->
                 println ev.dump()
             }
             
      }
      println "Stop parsing proccess." 

    }

}

