package pnklsportparser


import groovyx.net.http.RESTClient
import groovy.json.*

class ParserController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() { 
        render "ewewqeqeqeqeqw"
    }
    
    def getBalance(){

     def   client = new RESTClient('https://api.pinnaclesports.com/v1/')
     client.headers['Authorization'] = 'Basic '+"LZ765565:4567erty!".bytes.encodeBase64()
      
     def resp = client.get( path : 'client/balance' )
 
     render resp.data
        
        
        
    }
        
}