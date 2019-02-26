package adam;

import adam.pages.*;
import adam.session.UserSession;
import org.apache.wicket.Session;
import org.apache.wicket.bean.validation.BeanValidationConfiguration;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 */
public class WicketApplication extends WebApplication {
    public WicketApplication() {

    }

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new UserSession(request);
    }

    @Override
    public void init() {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        new BeanValidationConfiguration().configure(this);
        mountPage("/Home", HomePage.class);
        mountPage("/CreatePost", CreatePost.class);
        mountPage("/ListOfPosts", ListOfPosts.class);
        mountPage("/AdminPostList", AdminPostList.class);
        mountPage("/SinglePost", SinglePost.class);
        mountPage("/EditPost", EditPost.class);
        mountPage("/LoginPage", LoginPage.class);
    }
}
