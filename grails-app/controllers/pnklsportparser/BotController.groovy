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


class BotController {
    static final URL_PLACE_BET = "bets/place"

    def index() { }
    
    def makeBet() {
       def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
       
       def identifier = new Date().getTime().toString().encodeAsMD5() 

       def attributes = [:]
       attributes.uniqueRequestId = identifier
       attributes.acceptBetterLine = "TRUE"
       attributes.stake = DV.stakeValue
       attributes.winRiskStake = "RISK"
       attributes.lineId = params.lineId
       attributes.sportId = DV.pinnacleSportId
       attributes.eventId = params.eventId
       attributes.periodNumber = periodNumber
       attributes.betType = params.betType
       if (params.betType == "SPREAD" || params.betType == "MONEYLINE" || params.betType == "TEAM_TOTAL_POINTS"){
                  attributes.team = params.team
       }
       if (params.betType == "TOTAL_POINTS" || params.betType == "TEAM_TOTAL_POINTS"){
                  attributes.side = params.side
       }
       attributes.oddsFormat = "DECIMAL"
        
       def http = new HTTPBuilder(DV.pinnacleApiUrl)
       http.headers['Authorization'] = 'Basic '+"${DV.pinnacleLogin}:${DV.pinnaclePassword}".bytes.encodeBase64()      
           http.request (POST, ContentType.JSON) { req ->
              uri.path = this.URL_PLACE_BET
               body = (attributes as JSON).toString()
               response.success = { resp, json -> return json}
               response.failure = { resp, json -> return json}
       }
    }
    
   def newRobotTask() {
       new RobotTask(status: params.status,
                     evgentId: params.evgentId,
                     lineId: params.lineId,
                     stakeValue: params.stakeValue,
                     periodNumber: params.periodNumber,
                     betType: params.betType,
                     team: params.betType,
                     side: params.side,
                     home: params.home,
                     away: params.away,
                     resultStakeValue: params.resultStakeValue
                    ).save()
        
}