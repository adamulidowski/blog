package adam.session;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class UserSession extends WebSession {

    private static final long serialVersionUID = 2586676420597986366L;

    private UserModel userModel;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public UserSession(Request request) {
        super(request);
    }

    public static UserSession getInstance() {
        return (UserSession) Session.get();
    }
}
