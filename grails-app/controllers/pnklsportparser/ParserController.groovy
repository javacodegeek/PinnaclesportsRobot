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
      def http = new HTTPBuilder('https://api.pinnaclesports.com/v1/')
      http.headers['Authorization'] = 'Basic '+"$cm.config.pinnaclesports.login:$cm.config.pinnaclesports.password".bytes.encodeBase64()
      def resp =  http.get(path: this.URL_CLIENT_BALANCE) 
      def jsonresp = new JsonBuilder()
      jsonresp(resp)
      //render XmlUtil.serialize(resp)
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
       def http = new HTTPBuilder('https://api.pinnaclesports.com/v1/')
       http.headers['Authorization'] = 'Basic '+"$cm.config.pinnaclesports.login:$cm.config.pinnaclesports.password".bytes.encodeBase64()
       def resp =  http.get(path: this.URL_SPORTS) 
       render XmlUtil.serialize(resp)   
    }
    
    def getAllSoccerEvents(){
     
    }
}