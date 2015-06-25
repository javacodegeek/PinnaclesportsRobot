package pnklsportparser


import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*
import grails.util.Holders as cm
import groovy.xml.XmlUtil
import groovy.json.JsonBuilder
import grails.converters.JSON
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

import pnklsportparser.DefaultValue

class ParserController {
    
    static final URL_CLIENT_BALANCE = "client/balance"
    static final URL_SPORTS = "sports"
    static final URL_LEAGUES = "leagues"
    static final URL_ODDS = "odds"
    static final URL_FIXTURES = "fixtures"
    static final URL_LINE = "line"
    
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { 
    }
    
    def getBalance(){
      def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
      def http = new HTTPBuilder(DV.pinnacleApiUrl)
      http.headers['Authorization'] = 'Basic '+"${DV.pinnacleLogin}:${DV.pinnaclePassword}".bytes.encodeBase64()
      def resp =  http.get(path: this.URL_CLIENT_BALANCE) 
      def jsonresp = new JsonBuilder()
      jsonresp(resp)
      render jsonresp
    }
    
    def getSports(){
       def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
       def http = new HTTPBuilder(DV.pinnacleApiUrl)
       http.headers['Authorization'] = 'Basic '+"${DV.pinnacleLogin}:${DV.pinnaclePassword}".bytes.encodeBase64()
       def resp =  http.get(path: this.URL_SPORTS) 
       render XmlUtil.serialize(resp)   
    }
    
    def getLeagues(){
       def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
       def http = new HTTPBuilder(DV.pinnacleApiUrl)
       http.headers['Authorization'] = 'Basic '+"${DV.pinnacleLogin}:${DV.pinnaclePassword}".bytes.encodeBase64()
       def resp =  http.get(path: this.URL_LEAGUES, query: [sportid: DV.pinnacleSportId]) 
       render XmlUtil.serialize(resp)   
    }
    
    def getParserLogs(){
        def parserLog = ParserLog.list([max: 20, sort: "createdDate", order: "desc", offset: 0])
        def data = parserLog
        render(view: "/parserlog", model: [data: data])
    }
    
    /*def getLine(params){
       def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
       def http = new HTTPBuilder(DV.pinnacleApiUrl)
       http.headers['Authorization'] = 'Basic '+"${DV.pinnacleLogin}:${DV.pinnaclePassword}".bytes.encodeBase64()
       http.headers.'content-type' = "application/json;charset=utf-8"
        //def resp =  http.get(path: this.URL_LINE, query: [sportid: DV.pinnacleSportId, leagueId: params.leagueId, eventId: params.eventId, betType: params.betType, oddsFormat: "DECIMAL", periodNumber: params.periodNumber])
       def resp =  http.get(path: this.URL_LINE, query: [sportId: "29"])
        render(resp)
        // sportId=29&leagueId=1728&eventId=308195882&betType=SPREAD&oddsFormat=DECIMAL&periodNumber=0&team=TEAM1&handicap=-1
      /// render XmlUtil.serialize(resp)   
    }*/
    
    def getLine() {
       def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
       
       def identifier = new Date().getTime().toString().encodeAsMD5() 

       def attributes = [:]
       attributes.sportId = DV.pinnacleSportId
        
       def http = new HTTPBuilder(DV.pinnacleApiUrl)
       http.headers['Authorization'] = 'Basic '+"${DV.pinnacleLogin}:${DV.pinnaclePassword}".bytes.encodeBase64()      
      
       http.get(path:this.URL_LINE, contentType: ContentType.JSON, query: attributes) { resp, reader ->
           println  reader.text
           println resp.statusLine
       }
    }
}
