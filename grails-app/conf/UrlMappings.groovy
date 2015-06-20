class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        
        "/client/balance"(controller:"parser", action: "getBalance")
        "/sports"(controller:"parser", action: "getSports")
        "/leagues"(controller:"parser", action: "getLeagues")
        "/dv"(controller:"default", action: "getDefaultValue")
	}
}
