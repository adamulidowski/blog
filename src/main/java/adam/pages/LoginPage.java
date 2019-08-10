package adam.pages;

import adam.session.UserModel;
import adam.session.UserSession;
import com.google.common.hash.Hashing;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import java.nio.charset.StandardCharsets;


public class LoginPage extends WebPage {

    private static final long serialVersionUID = 2586676420597986366L;

    public LoginPage() {
        if(UserSession.getInstance().getUserModel()!=null){
            setResponsePage(AdminPostList.class);
        }
        final UserModel userModel = new UserModel();

        Form<Object> form = new Form<Object>("form");

        form.add(new TextField<String>("userName", new PropertyModel<String>(userModel, "name")));
        form.add(new PasswordTextField("pswd", new PropertyModel<String>(userModel, "pswd")));

        form.add(new Button("submit") {
            private static final long serialVersionUID = 498874536367721731L;

            @Override
            public void onSubmit() {
                super.onSubmit();

                String typed = Hashing.sha256()
                        .hashString(userModel.getPswd(), StandardCharsets.UTF_8)
                        .toString();
                String expected ="dcc0bd36ea166de3bb90171e5a20276c7882e727c2ab6249122c4ebc942877b9";
                if (userModel.getName().equalsIgnoreCase("programista3546") &&
                        typed.equals(expected)) {
                    UserSession.getInstance().setUserModel(userModel);
                    setResponsePage(AdminPostList.class);
                }
            }
        });
        add(form);
    }
}
