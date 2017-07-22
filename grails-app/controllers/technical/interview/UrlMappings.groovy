package technical.interview

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"randomNumbersStorage")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
