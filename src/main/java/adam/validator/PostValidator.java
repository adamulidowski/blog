package adam.validator;

import org.springframework.stereotype.Component;

@Component
public class PostValidator {

    public boolean tittleIsBad(String tittle){
        return tittle == null || tittle.length() < 5;
    }

    public boolean contentIsBad(String content){
        return content == null || content.length() < 30;
    }
}
