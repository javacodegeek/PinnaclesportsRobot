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

@Transactional
class ParserService {

    static final URL_CLIENT_BALANCE = "client/balance"
    static final URL_SPORTS = "sports"
    static final URL_LEAGUES = "leagues"
    static final URL_ODDS = "odds"
    static final URL_FIXTURES = "fixtures"
    
    def saveFixtures() {
       def http = new HTTPBuilder(cm.config.pinnaclesports.apiurl)
       http.headers['Authorization'] = 'Basic '+"$cm.config.pinnaclesports.login:$cm.config.pinnaclesports.password".bytes.encodeBase64()
       def resp =  http.get(path: this.URL_FIXTURES, query: [sportid: cm.config.betsparams.sportid, leagueIds: cm.config.betsparams.leagueIds]) 
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
}
