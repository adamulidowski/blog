package adam.pages;

import adam.dao.api.PostDAO;
import adam.dto.PostDTO;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class SinglePost extends WebPage {

    @SpringBean
    private PostDAO postDAO;

    private static final long serialVersionUID = 2586676420597986366L;

    public SinglePost(PageParameters pageParameters) {
        super(pageParameters);

        int id = Integer.parseInt(pageParameters.get("postId").toString());
        PostDTO postDTO = postDAO.getById(id);

        add(new Label("tittle", postDTO.getTittle()));
        add(new Label("postDate", postDTO.getPostDate() + " | " + postDTO.getType()));
        add(new Label("content", postDTO.getContent()).setEscapeModelStrings(false));
    }
}
