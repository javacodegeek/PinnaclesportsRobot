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
       /*def http = new HTTPBuilder(DV.pinnacleApiUrl)
       http.headers['Authorization'] = 'Basic '+"${DV.pinnacleLogin}:${DV.pinnaclePassword}".bytes.encodeBase64()
       def resp =  http.get(path: this.URL_ODDS, query: [sportid: DV.pinnacleSportId, leagueIds: DV.pinnacleLeagueIds]) 
       def jsonresp = new JsonBuilder()
       def jdata = jsonresp(resp)    
       jdata.leagues.each{ league ->
            league.events.each{ event ->
                new SoccerOdd(eventId: event.id,
                              leagueId: league.id,
                              period0: (event.periods[0] as JSON).toString(),
                              period1: (event.periods[1] as JSON).toString(),
                              period2: (event.periods[2] as JSON).toString()
                ).save()
            }
       }
       */
      
       def identifier = new Date().getTime().toString().encodeAsMD5() 
       def attributes = [uniqueRequestId: identifier,
                         acceptBetterLine: "TRUE",
                         stake: 0.01,
                         winRiskStake: "RISK",
                         lineId: 208982220,
                         sportId: 29,
                         eventId: 477221579,
                         periodNumber: 0,
                         betType: "MONEYLINE",
                         team:"DRAW",
                         oddsFormat:"DECIMAL"
                        ]
                               
           def http = new HTTPBuilder(DV.pinnacleApiUrl)
           http.headers['Authorization'] = 'Basic '+"${DV.pinnacleLogin}:${DV.pinnaclePassword}".bytes.encodeBase64()      
           http.request (POST, ContentType.JSON) { req ->
              uri.path = this.URL_PLACE_BET
               body = (attributes as JSON).toString()
               response.success = { resp, json -> println json}
               response.failure = { resp, json -> println json}
           }
        }
}