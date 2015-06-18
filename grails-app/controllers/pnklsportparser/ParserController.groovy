package pnklsportparser


import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*
import grails.util.Holders as cm
import groovy.xml.XmlUtil
import groovy.json.JsonBuilder


class ParserController {
    
    static final URL_CLIENT_BALANCE = "client/balance"
    static final URL_SPORTS = "sports"
    static final URL_LEAGUES = "leagues"
    static final URL_ODDS = "odds"
    static final URL_FIXTURES = "fixtures"

    
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { 
    }
    
    /**
     @return data in json format 
        {
             "availableBalance":49354.37,
             "outstandingTransactions":533.84,
             "givenCredit":50000.0,
             "currency":"CAD"
        }
     **/
    def getBalance(){
      def http = new HTTPBuilder(cm.config.pinnaclesports.apiurl)
      http.headers['Authorization'] = 'Basic '+"$cm.config.pinnaclesports.login:$cm.config.pinnaclesports.password".bytes.encodeBase64()
      def resp =  http.get(path: this.URL_CLIENT_BALANCE) 
      def jsonresp = new JsonBuilder()
      jsonresp(resp)
      render jsonresp
    }
    /**
     @return data in xml format
            <?xml version="1.0" encoding="UTF-8"?><rsp status="ok">
              <sports>
                <sport feedContents="0" id="1">Badminton</sport>
                <sport feedContents="0" id="2">Bandy</sport>
                <sport feedContents="1" id="3">Baseball</sport>
                <sport feedContents="1" id="4">Basketball</sport>
                <sport feedContents="1" id="6">Boxing</sport>
                <sport feedContents="1" id="8">Cricket</sport>
                <sport feedContents="0" id="9">Curling</sport>
                <sport feedContents="0" id="10">Darts</sport>
                <sport feedContents="0" id="11">Darts (Legs)</sport>
                <sport feedContents="1" id="12">E Sports</sport>
                <sport feedContents="0" id="14">Floorball</sport>
                <sport feedContents="1" id="15">Football</sport>
                <sport feedContents="0" id="16">Futsal</sport>
                <sport feedContents="1" id="17">Golf</sport>
                <sport feedContents="0" id="18">Handball</sport>
                <sport feedContents="0" id="19">Hockey</sport>
                <sport feedContents="0" id="20">Horse Racing</sport>
                <sport feedContents="1" id="22">Mixed Martial Arts</sport>
                <sport feedContents="0" id="23">Other Sports</sport>
                <sport feedContents="0" id="24">Politics</sport>
                <sport feedContents="1" id="26">Rugby League</sport>
                <sport feedContents="1" id="27">Rugby Union</sport>
                <sport feedContents="1" id="28">Snooker</sport>
                <sport feedContents="1" id="29">Soccer</sport>
                <sport feedContents="0" id="30">Softball</sport>
                <sport feedContents="0" id="31">Squash</sport>
                <sport feedContents="1" id="32">Table Tennis</sport>
                <sport feedContents="1" id="33">Tennis</sport>
                <sport feedContents="1" id="34">Volleyball</sport>
                <sport feedContents="0" id="35">Volleyball (Points)</sport>
                <sport feedContents="0" id="36">Water Polo</sport>
                <sport feedContents="1" id="39">Aussie Rules</sport>
                <sport feedContents="0" id="40">Alpine Skiing</sport>
                <sport feedContents="0" id="41">Biathlon</sport>
                <sport feedContents="0" id="42">Ski Jumping</sport>
                <sport feedContents="0" id="43">Cross Country</sport>
                <sport feedContents="0" id="44">Formula 1</sport>
                <sport feedContents="1" id="45">Cycling</sport>
                <sport feedContents="0" id="46">Bobsleigh</sport>
                <sport feedContents="0" id="47">Figure Skating</sport>
                <sport feedContents="0" id="48">Freestyle Skiing</sport>
                <sport feedContents="0" id="49">Luge</sport>
                <sport feedContents="0" id="50">Nordic Combined</sport>
                <sport feedContents="0" id="51">Short Track</sport>
                <sport feedContents="0" id="52">Skeleton</sport>
                <sport feedContents="0" id="53">Snow Boarding</sport>
                <sport feedContents="0" id="54">Speed Skating</sport>
              </sports>
            </rsp>
     **/
    def getSports(){
       def http = new HTTPBuilder(cm.config.pinnaclesports.apiurl)
       http.headers['Authorization'] = 'Basic '+"$cm.config.pinnaclesports.login:$cm.config.pinnaclesports.password".bytes.encodeBase64()
       def resp =  http.get(path: this.URL_SPORTS) 
       render XmlUtil.serialize(resp)   
    }
    /**
         @return data in xml format
            <?xml version="1.0" encoding="UTF-8"?><rsp status="ok">
              <sportId>3</sportId>
              <leagues>
                <league feedContents="0" homeTeamType="Team2" id="214" allowRoundRobins="1">MLB Alternate Runlines</league>
                <league feedContents="0" homeTeamType="Team2" id="216" allowRoundRobins="0">All Star Friendlies</league>
                <league feedContents="0" homeTeamType="Team2" id="217" allowRoundRobins="1">Asia Series</league>
                <league feedContents="0" homeTeamType="Team2" id="218" allowRoundRobins="1">Asian Games - World</league>
                <league feedContents="1" homeTeamType="Team2" id="220" allowRoundRobins="1">NCAA Baseball</league>
                <league feedContents="0" homeTeamType="Team2" id="225" allowRoundRobins="1">European Championships</league>
                <league feedContents="0" homeTeamType="Team2" id="231" allowRoundRobins="0">Italy - Serie A1</league>
                <league feedContents="0" homeTeamType="Team2" id="232" allowRoundRobins="1">Japan - All Star</league>
                <league feedContents="1" homeTeamType="Team2" id="233" allowRoundRobins="1">Japan - Central League</league>
                <league feedContents="0" homeTeamType="Team2" id="234" allowRoundRobins="1">Japan - Inter League</league>
                <league feedContents="0" homeTeamType="Team2" id="235" allowRoundRobins="1">Japan Baseball LIVE</league>
                <league feedContents="1" homeTeamType="Team2" id="236" allowRoundRobins="1">Japan - Pacific League</league>
                <league feedContents="0" homeTeamType="Team2" id="237" allowRoundRobins="1">Nippon Series (Championship)</league>
                <league feedContents="0" homeTeamType="Team2" id="238" allowRoundRobins="0">Konami Cup Asia Series</league>
                <league feedContents="1" homeTeamType="Team2" id="6227" allowRoundRobins="1">Korea Professional Baseball</league>
                <league feedContents="0" homeTeamType="Team2" id="241" allowRoundRobins="1">Little League World Series</league>
                <league feedContents="0" homeTeamType="Team2" id="244" allowRoundRobins="0">Mexican League</league>
                <league feedContents="1" homeTeamType="Team2" id="246" allowRoundRobins="1">MLB</league>
                <league feedContents="0" homeTeamType="Team2" id="5425" allowRoundRobins="1">MLB - Pre Season</league>
                <league feedContents="0" homeTeamType="Team2" id="251" allowRoundRobins="1">Baseball Games</league>
                <league feedContents="0" homeTeamType="Team2" id="252" allowRoundRobins="1">Olympic Qualifier</league>
                <league feedContents="0" homeTeamType="Team2" id="255" allowRoundRobins="1">Pan American Games</league>
                <league feedContents="0" homeTeamType="Team2" id="256" allowRoundRobins="1">Campeonato Nacional de Beisbol Mayor</league>
                <league feedContents="0" homeTeamType="Team2" id="257" allowRoundRobins="1">Serie Del Caribe</league>
                <league feedContents="0" homeTeamType="Team2" id="11689" allowRoundRobins="0">testing 5</league>
                <league feedContents="0" homeTeamType="Team2" id="264" allowRoundRobins="1">WNCAA Softball</league>
                <league feedContents="0" homeTeamType="Team2" id="265" allowRoundRobins="0">World Cup Baseball</league>
                <league feedContents="0" homeTeamType="Team2" id="266" allowRoundRobins="1">World Baseball Classic</league>
              </leagues>
            </rsp>
     **/
    def getLeagues(){
       def http = new HTTPBuilder(cm.config.pinnaclesports.apiurl)
       http.headers['Authorization'] = 'Basic '+"$cm.config.pinnaclesports.login:$cm.config.pinnaclesports.password".bytes.encodeBase64()
       def resp =  http.get(path: this.URL_LEAGUES, query: [sportid: cm.config.betsparams.sportid]) 
       render XmlUtil.serialize(resp)   
    }
    def getOdds(){
       def http = new HTTPBuilder(cm.config.pinnaclesports.apiurl)
       http.headers['Authorization'] = 'Basic '+"$cm.config.pinnaclesports.login:$cm.config.pinnaclesports.password".bytes.encodeBase64()
       def resp =  http.get(path: this.URL_ODDS, query: [sportid: cm.config.betsparams.sportid, leagueIds: cm.config.betsparams.leagueIds]) 
       def jsonresp = new JsonBuilder()
       jsonresp(resp)
       render jsonresp
    }
    def getFixtures(){
       def http = new HTTPBuilder(cm.config.pinnaclesports.apiurl)
       http.headers['Authorization'] = 'Basic '+"$cm.config.pinnaclesports.login:$cm.config.pinnaclesports.password".bytes.encodeBase64()
       def resp =  http.get(path: this.URL_FIXTURES, query: [sportid: cm.config.betsparams.sportid, leagueIds: cm.config.betsparams.leagueIds]) 
       def jsonresp = new JsonBuilder()
       def jdata = jsonresp(resp)    
       /*jdata.league.each{ league ->
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
       println Fixture.findAll().size()*/
       //render jsonresp(resp)
    }
}