package pnklsportparser
import grails.util.Holders as cm
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*
import groovy.xml.XmlUtil
import groovy.json.*
import grails.converters.JSON
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*
    
class MaxJob {
    
    ParserService parserService

    static triggers = {
        simple startDelay: 20000, repeatInterval: 30000  
    }
    
    def execute() {
      println "MaxJob Job!"
      
        parserService.MaxScript()
      
      println "Stop MaxJob." 

    }

}

