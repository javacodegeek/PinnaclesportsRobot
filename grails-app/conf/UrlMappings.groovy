class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        
        "500"(view:'/error')
        
        "/test"(controller:"parser", action: "getTest")
        "/client/balance"(controller:"parser", action: "getBalance")
        "/sports"(controller:"parser", action: "getSports")
        "/leagues"(controller:"parser", action: "getLeagues")
        "/dv"(controller:"default", action: "getDefaultValue")
        "/setdv"(controller:"default", action: "setDefaultValue")
        "/parserlogs"(controller:"parser", action: "getParserLogs")
        
        "/line"(controller:"parser", action: "getLine")
        "/bets/place"(controller:"bot", action: "makeBet")
        "/bets"(controller:"bot", action: "getBets")
	}
}
