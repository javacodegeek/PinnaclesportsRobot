package pnklsportparser
import grails.util.Holders as cm
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*
import groovy.xml.XmlUtil
import groovy.json.JsonBuilder
import grails.converters.JSON
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*
    
class RobotJob {
    
    ParserService parserService

    static triggers = {
        simple startDelay: 30000, repeatInterval: 120000  
    }
    
    def execute() {
      println "Run ROBOT Job!" 
      def eventList = RobotTask.list()
      eventList
      if (eventList){
             eventList.each{ev ->
                
                    def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
       
                    def identifier = new Date().getTime().toString().encodeAsMD5() 

                    def attributes = [:]
                    attributes.uniqueRequestId = identifier
                    attributes.acceptBetterLine = "TRUE"
                    attributes.stake = DV.stakeValue
                    attributes.winRiskStake = "RISK"
                    attributes.lineId = ev.lineId
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
                    http.headers['Authorization'] = 'Basic '+"${DV.pinnacleLogin}:${DV.pinnaclePassword}".bytes.encodeBase64()      
                        http.request (POST, ContentType.JSON) { req ->
                           uri.path = "bets/place"
                            body = (attributes as JSON).toString()
                            response.success = { resp, json -> println json
                                                               def e = RobotTask.get(ev.id)
                                                               e.status = json.status
                                                               e.errorCode = json.errorCode
                                                               e.betId = json.betId
                                                               e.stakeValue = DV.stakeValue
                                                               e.save()
                    }
                    
                    
                    def evnt = SoccerOdd.findByEventId(ev.eventId)
                    def p_data = null
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
                               println p_data.moneyline
                               
                               break
                        case "SPREAD":
                               p_data.spreads
                               break
                        case "TOTAL_POINTS":
                               p_data.totals
                               break
                        case "TEAM_TOTAL_POINTS":
                               p_data.teamTotals
                               break
                    }
                    
                  
                            
                    }
                
                
             }
             
      }
      println "Stop parsing proccess." 

    }

}

