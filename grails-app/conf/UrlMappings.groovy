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
        "/odds"(controller:"parser", action: "getOdds")
        "/fixtures"(controller:"parser", action: "getFixtures")



        "/soccer"(controller:"parser", action: "getAllSoccerEvents")


	}
}
