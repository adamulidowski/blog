package adam.pages;

import adam.dao.api.PostDAO;
import adam.dto.PostDTO;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Date;

public class CreatePost extends WebPage {

    private static final long serialVersionUID = 2586676420597986366L;

    PostDTO newPost;

    Form<Object> registryform;

    @SpringBean
    private PostDAO postDAO;

    public CreatePost() {
        newPost = new PostDTO();
        registryform = new Form<Object>("newPostForm");
        registryform.add(new TextField<String>("newTittle", new PropertyModel<String>(newPost, "tittle")));
        registryform.add(new TextArea<String>("newContent", new PropertyModel<String>(newPost, "content")));

        addsubmitButtonAndSendRegistryForm();
        add(registryform);

    }

    public void addsubmitButtonAndSendRegistryForm() {
        registryform.add(new Button("addPost") {
            private static final long serialVersionUID = 498874536367721731L;

            @Override
            public void onSubmit() {
                super.onSubmit();
                PostDTO createdPost = new PostDTO(newPost.getTittle(), new Date(), newPost.getContent());
                postDAO.save(createdPost);
                setResponsePage(HomePage.class);
            }
        });
    }
}

