package controllers;


import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class ForceHttps extends Action.Simple {

    @Override
    public F.Promise<Result> call(Http.Context ctx) throws Throwable {

        if (!ctx.request().secure()) {
            return F.Promise.promise(() -> redirect("https://" + ctx.request().host().replace("www.","")
                    + ctx.request().uri()));
        }

        return delegate.call(ctx);
    }

}