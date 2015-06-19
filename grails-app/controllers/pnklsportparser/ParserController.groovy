package pnklsportparser


import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*
import grails.util.Holders as cm
import groovy.xml.XmlUtil
import groovy.json.JsonBuilder

import pnklsportparser.DefaultValue

class ParserController {
    
    static final URL_CLIENT_BALANCE = "client/balance"
    static final URL_SPORTS = "sports"
    static final URL_LEAGUES = "leagues"
    static final URL_ODDS = "odds"
    static final URL_FIXTURES = "fixtures"
    
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
}