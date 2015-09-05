package pnklsportparser

import grails.transaction.Transactional

import grails.util.Holders as cm
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*
import groovy.xml.XmlUtil
import groovy.json.*
import grails.converters.JSON

@Transactional
class ParserService {

static final URL_CLIENT_BALANCE = "client/balance"
static final URL_SPORTS = "sports"
static final URL_LEAGUES = "leagues"
static final URL_ODDS = "odds"
static final URL_FIXTURES = "fixtures"

def runParsing(String typeRun){
Fixture.executeUpdate('delete from Fixture')
this.saveFixtures()
SoccerOdd.executeUpdate('delete from SoccerOdd')
this.saveSoccerOdds()
def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")

if(typeRun == "auto"){
DV.lastAutoRun = new Date()
new ParserLog(fixtureNum: Fixture.count(),
oddNum: SoccerOdd.count(),
sportId: DV.pinnacleSportId,
leaguesIds: DV.pinnacleLeagueIds,
status: "Success"
).save(failOnError: true)

}else{
DV.lastManualRun = new Date()
new ParserLog(fixtureNum: Fixture.count(),
oddNum: SoccerOdd.count(),
sportId: DV.pinnacleSportId,
leaguesIds: DV.pinnacleLeagueIds,
status: "Success"
).save(flush: true)
}

}

def MaxScript() {
def http1 = new HTTPBuilder("http://localhost/")
def resp1 = http1.get(path: "testbot.php", contentType: TEXT)
// def json = new JsonSlurper().parseText(resp.str)
// render json.availableBalance
//render(text: resp.str , contentType: "text/json", encoding: "UTF-8")
def rr = resp1.str.value.toString().replaceAll(" ", "").replaceAll("\n|\r\n", "").trim()
if(rr.length()<10){
def json = []
} else {
def json = new JsonSlurper().parseText(rr.substring(1))

json.each{j ->                
    new RobotTask( status: null,
    errorCode: null,
    eventId: j.eventId,
    lineId: j.lineId,
    altLineId: j.altLineId,
    periodNumber: 0,
    betType: j.betType,
    minKoff: j.minKoff,
    team: j.team,
    side: j.side
    ).save(flush: true)
}
}
}

def saveFixtures() {
def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
def http = new HTTPBuilder(DV.pinnacleApiUrl)
http.headers['Authorization'] = 'Basic '+"${DV.pinnacleLogin}:${DV.pinnaclePassword}".bytes.encodeBase64()
def resp = http.get(path: this.URL_FIXTURES, query: [sportid: DV.pinnacleSportId, leagueIds: DV.pinnacleLeagueIds], contentType: TEXT)
def jdata = new JsonSlurper().parseText(resp.str)
jdata.league.each{ league ->
league.events.each{ event ->           
        new Fixture(eventId: event.id,
        leagueId: league.id,
        home: event.home,
        away: event.away,
        rotNum: event.rotNum,
        liveStatus: event.liveStatus,
        status: event.status,
        starts: event.starts,
        md5Key: (event.home + event.away + league.id + "ACCEPTED").toString().encodeAsMD5()
        ).save()
    }
}
return true
}

def saveSoccerOdds() {
def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
def http = new HTTPBuilder(DV.pinnacleApiUrl)
http.headers['Authorization'] = 'Basic '+"${DV.pinnacleLogin}:${DV.pinnaclePassword}".bytes.encodeBase64()
def resp = http.get(path: this.URL_ODDS, query: [sportid: DV.pinnacleSportId, leagueids: DV.pinnacleLeagueIds], contentType: TEXT)
def jdata = new JsonSlurper().parseText(resp.str)
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