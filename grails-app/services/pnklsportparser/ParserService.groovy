package pnklsportparser

import grails.transaction.Transactional

import grails.util.Holders as cm
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*
import groovy.xml.XmlUtil
import groovy.json.JsonBuilder
import grails.converters.JSON

@Transactional
class ParserService {

    static final URL_CLIENT_BALANCE = "client/balance"
    static final URL_SPORTS = "sports"
    static final URL_LEAGUES = "leagues"
    static final URL_ODDS = "odds"
    static final URL_FIXTURES = "fixtures"
    
    
    def runParsing(){
        Fixture.executeUpdate('delete from Fixture')
        this.saveFixtures()
        SoccerOdd.executeUpdate('delete from SoccerOdd')
        this.saveSoccerOdds()
        def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
        DV.lastAutoRun = new Date()
        DV.lastManualRun = new Date()
    }
    
    def saveFixtures() {
       def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
       def http = new HTTPBuilder(DV.pinnacleApiUrl)
       http.headers['Authorization'] = 'Basic '+"${DV.pinnacleLogin}:${DV.pinnaclePassword}".bytes.encodeBase64()      
       def resp =  http.get(path: this.URL_FIXTURES, query: [sportid: DV.pinnacleSportId, leagueIds: DV.pinnacleLeagueIds]) 
       def jsonresp = new JsonBuilder()
       def jdata = jsonresp(resp)    
       jdata.league.each{ league ->
            league.events.each{ event ->
                new Fixture(eventId: event.id,
                            leagueId: league.id,
                            home: event.home,
                            away: event.away,
                            rotNum: event.rotNum,
                            liveStatus: event.liveStatus,
                            status: event.status,
                            starts: event.starts
                ).save()
            }
       }
       return true
    }
    
    def saveSoccerOdds() {
       def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
       def http = new HTTPBuilder(DV.pinnacleApiUrl)
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
       return true
    }
}
