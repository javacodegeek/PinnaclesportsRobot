package pnklsportparser
import grails.util.Holders as cm
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*
import groovy.xml.XmlUtil
import groovy.json.*
import grails.converters.JSON
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*
    
class RobotJob {
    
    ParserService parserService

    static triggers = {
        simple startDelay: 15000, repeatInterval: 15000 
    }
    
    def execute() {
      println "Run ROBOT Job!" 
      def eventList = RobotTask.list()
      
      if (eventList){
             eventList.each{ev ->
                
                            def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")

                            def identifier = new Date().getTime().toString().encodeAsMD5() 
                            def fx = Fixture.findByEventId(ev.eventId);
                            if ((ev.status == null)||((ev.status == "PROCESSED_WITH_ERROR")&&(ev.errorCode == "ABOVE_MAX_BET_AMOUNT")&&(fx.liveStatus == "1"))){
                                
                                
                                        def attributes = [:]
                                        attributes.uniqueRequestId = identifier
                                        attributes.acceptBetterLine = "TRUE"
                                        attributes.stake = DV.stakeValue
                                        attributes.winRiskStake = "RISK"
                                        attributes.lineId = ev.lineId
                                        if(ev.altLineId) {attributes.altLineId = ev.altLineId}
                                        attributes.sportId = DV.pinnacleSportId
                                        attributes.eventId = ev.eventId
                                        attributes.periodNumber = ev.periodNumber
                                        attributes.betType = ev.betType
                                        if (ev.betType == "SPREAD" || ev.betType == "MONEYLINE" || ev.betType == "TEAM_TOTAL_POINTS"){
                                                   attributes.team = ev.team
                                        }
                                        if (ev.betType == "TOTAL_POINTS" || ev.betType == "TEAM_TOTAL_POINTS"){
                                                   attributes.side = ev.side
                                        }
                                        attributes.oddsFormat = "DECIMAL"

                                        def http = new HTTPBuilder(DV.pinnacleApiUrl)
                                        def result = null
                                            def js = (attributes as JSON).toString()
                                            /*
                                            def process = [ 'bash', '-c', "curl --user ${DV.pinnacleLogin}:${DV.pinnaclePassword} -i --header 'Content-Type: application/json' -H 'Accept: text/json' --request 'POST' --data '$js' https://api.pinnaclesports.com/v1/bets/place" ].execute().text
                                            def jsontext = process.substring(process.indexOf('{'))
                                            def jsonr = new JsonSlurper().parseText(jsontext)                 
                                             println jsonr
                                             def e = RobotTask.get(ev.id)
                                             e.status = jsonr.status
                                             e.errorCode = jsonr.errorCode
                                             e.betId = jsonr.betId
                                             e.stakeValue = DV.stakeValue
                                             e.save()
                                             */
                                         

                                        def evnt = SoccerOdd.findByEventId(ev.eventId)
                                        def p_data = null
                                        def koff = null
                                        switch (ev.periodNumber){
                                            case 0:
                                                   p_data = evnt.period0
                                                   break
                                            case 1:
                                                   p_data = evnt.period1
                                                   break
                                            case 2:
                                                   p_data = evnt.period2
                                                   break       
                                        }
                                        p_data = JSON.parse(p_data)
                                        switch (ev.betType) {
                                            case "MONEYLINE":
                                                   if(p_data.moneyline){

                                                     switch(ev.team){
                                                        case "Team1":
                                                              koff = p_data.moneyline.home
                                                              break
                                                        case "Team2":
                                                              koff = p_data.moneyline.away
                                                              break
                                                        case "Draw":
                                                              koff = p_data.moneyline.draw
                                                              break       
                                                     }       
                                                   }
                                                   break
                                            case "SPREAD":
                                                   p_data.spreads.each{p ->
                                                       if(!ev.altLineId && !p.altLineId){
                                                           switch(ev.team){
                                                            case "Team1":
                                                              koff = p.home
                                                              break
                                                            case "Team2":
                                                              koff = p.away
                                                              break 
                                                            }
                                                       }
                                                       if(ev.altLineId == p.altLineId){
                                                           switch(ev.team){
                                                            case "Team1":
                                                              koff = p.home
                                                              break
                                                            case "Team2":
                                                              koff = p.away
                                                              break 
                                                            }
                                                       }
                                                   }
                                                   break
                                            case "TOTAL_POINTS":
                                                   p_data.totals.each{p ->
                                                       if(!ev.altLineId && !p.altLineId){
                                                           switch(ev.side){
                                                            case "under":
                                                              koff = p.under
                                                              break
                                                            case "over":
                                                              koff = p.over
                                                              break 
                                                            }
                                                       }
                                                       if(ev.altLineId == p.altLineId){
                                                           switch(ev.side){
                                                            case "under":
                                                              koff = p.under
                                                              break
                                                            case "over":
                                                              koff = p.over
                                                              break 
                                                            }
                                                       }
                                                   }
                                                   break
                                            case "TEAM_TOTAL_POINTS":
                                                   if(p_data.teamTotal){
                                                     switch(ev.team){
                                                        case "Team1":
                                                              switch(ev.side){
                                                                case "under":
                                                                 koff = p_data.teamTotal.home.under
                                                                 break
                                                                case "over":
                                                                 koff = p_data.teamTotal.home.over
                                                                break 
                                                                }
                                                              break
                                                        case "Team2":
                                                              switch(ev.side){
                                                                case "under":
                                                                 koff = p_data.teamTotal.away.under
                                                                 break
                                                                case "over":
                                                                 koff = p_data.teamTotal.away.over
                                                                break 
                                                                }
                                                              break

                                                     }       
                                                   }

                                                   break
                                        }
                                        println koff
                                                def dk = null
                                                if (koff > 0){
                                                    dk = koff/100
                                                }
                                        println dk  
                                        
                        
                            if ((dk >= 1.8d)&&(dk >= (double)(ev.mminKoff))){
                             
                                            def process = [ 'bash', '-c', "curl --user ${DV.pinnacleLogin}:${DV.pinnaclePassword} -i --header 'Content-Type: application/json' -H 'Accept: text/json' --request 'POST' --data '$js' https://api.pinnaclesports.com/v1/bets/place" ].execute().text
                                            def jsontext = process.substring(process.indexOf('{'))
                                            def jsonr = new JsonSlurper().parseText(jsontext)                 
                                             println jsonr
                                             def e = RobotTask.get(ev.id)
                                             e.status = jsonr.status
                                             e.errorCode = jsonr.errorCode
                                             e.betId = jsonr.betId
                                             e.stakeValue = DV.stakeValue
                                             e.save()
                            }
                    
                    
                                                                             
                     }
                     
             }    
             
      }
      println "Stop parsing proccess." 

    }

}

