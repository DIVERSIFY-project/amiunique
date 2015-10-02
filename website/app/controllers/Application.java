package controllers;

import play.Routes;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result home() {
        return ok(home.render());
    }

    public static Result privacy() {
        return ok(privacy.render());
    }

    public static Result faq(){
        return ok(faq.render());
    }

    public static Result links() {
        return ok(links.render());
    }

    public static Result about(){
        return ok(about.render());
    }

    public static Result tools() {
        return ok(tools.render());
    }

    public static Result jsRoutes(){
        response().setContentType("text/javascript");
        return ok(Routes.javascriptRouter("jsRoutes", routes.javascript.FPController.addFingerprint(),
                routes.javascript.StatsController.percentages()));
    }

}
