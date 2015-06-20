package pnklsportparser

class DefaultController {

    def index() { }
    
    def getDefaultValue(){
        def DV = DefaultValue.findByName("PINNACLESPORTSROBOT")
        def data = DV
        println data.dump()
        render(view: "/defaultvalue", model: [data: data])
    }
}
