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
    
    def saveSoccerOdds() {
       def http = new HTTPBuilder(cm.config.pinnaclesports.apiurl)
       http.headers['Authorization'] = 'Basic '+"$cm.config.pinnaclesports.login:$cm.config.pinnaclesports.password".bytes.encodeBase64()
       def resp =  http.get(path: this.URL_ODDS, query: [sportid: cm.config.betsparams.sportid, leagueIds: cm.config.betsparams.leagueIds]) 
       def jsonresp = new JsonBuilder()
       def jdata = jsonresp(resp)    
       jdata.leagues.each{ league ->
            league.events.each{ event ->
                println event.periods[0] as JSON
                def test = new SoccerOdd(eventId: event.id,
                              leagueId: league.id,
                              period0: '{"cutoff":"2015-06-19T23:29:00Z","lineId":208286330,"maxMoneyline":10000,"maxSpread":50000,"maxTeamTotal":4000,"maxTotal":20000,"moneyline":{"away":1155,"draw":453,"home":-316},"number":0,"spreads":[{"away":-108,"hdp":-1.5,"home":-101},{"altLineId":1572643626,"away":-212,"hdp":-2,"home":181},{"altLineId":1572643628,"away":-144,"hdp":-1.75,"home":129},{"altLineId":1572643630,"away":123,"hdp":-1.25,"home":-136},{"altLineId":1572643632,"away":180,"hdp":-1,"home":-211}],"teamTotal":{"away":{"over":104,"points":0.5,"under":-117},"home":{"over":-117,"points":2,"under":104}},"totals":[{"over":-103,"points":2.75,"under":-108},{"altLineId":1572643627,"over":-184,"points":2.25,"under":155},{"altLineId":1572643629,"over":-131,"points":2.5,"under":116},{"altLineId":1572643631,"over":129,"points":3,"under":-148},{"altLineId":1572643633,"over":159,"points":3.25,"under":-189}]}',
                              period1: '{"cutoff":"2015-06-19T23:29:00Z","lineId":208286330,"maxMoneyline":10000,"maxSpread":50000,"maxTeamTotal":4000,"maxTotal":20000,"moneyline":{"away":1155,"draw":453,"home":-316},"number":0,"spreads":[{"away":-108,"hdp":-1.5,"home":-101},{"altLineId":1572643626,"away":-212,"hdp":-2,"home":181},{"altLineId":1572643628,"away":-144,"hdp":-1.75,"home":129},{"altLineId":1572643630,"away":123,"hdp":-1.25,"home":-136},{"altLineId":1572643632,"away":180,"hdp":-1,"home":-211}],"teamTotal":{"away":{"over":104,"points":0.5,"under":-117},"home":{"over":-117,"points":2,"under":104}},"totals":[{"over":-103,"points":2.75,"under":-108},{"altLineId":1572643627,"over":-184,"points":2.25,"under":155},{"altLineId":1572643629,"over":-131,"points":2.5,"under":116},{"altLineId":1572643631,"over":129,"points":3,"under":-148},{"altLineId":1572643633,"over":159,"points":3.25,"under":-189}]}',
                              period2: '{"cutoff":"2015-06-19T23:29:00Z","lineId":208286330,"maxMoneyline":10000,"maxSpread":50000,"maxTeamTotal":4000,"maxTotal":20000,"moneyline":{"away":1155,"draw":453,"home":-316},"number":0,"spreads":[{"away":-108,"hdp":-1.5,"home":-101},{"altLineId":1572643626,"away":-212,"hdp":-2,"home":181},{"altLineId":1572643628,"away":-144,"hdp":-1.75,"home":129},{"altLineId":1572643630,"away":123,"hdp":-1.25,"home":-136},{"altLineId":1572643632,"away":180,"hdp":-1,"home":-211}],"teamTotal":{"away":{"over":104,"points":0.5,"under":-117},"home":{"over":-117,"points":2,"under":104}},"totals":[{"over":-103,"points":2.75,"under":-108},{"altLineId":1572643627,"over":-184,"points":2.25,"under":155},{"altLineId":1572643629,"over":-131,"points":2.5,"under":116},{"altLineId":1572643631,"over":129,"points":3,"under":-148},{"altLineId":1572643633,"over":159,"points":3.25,"under":-189}]}'
                )
                test.save()
                println test as JSON
            }
       }
       return true
    }
}
