package adam.session;

import java.io.Serializable;

public class UserModel implements Serializable {

    private static final long serialVersionUID = 2586676420597986366L;

    private String name;
    private String pswd;

    public UserModel(String name, String pswd) {
        this.name = name;
        this.pswd = pswd;
    }

    public UserModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
}
