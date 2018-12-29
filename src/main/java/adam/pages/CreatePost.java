package adam.pages;

import adam.dao.api.PostDAO;
import adam.dto.PostDTO;
import adam.formatter.PostFormatter;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import static adam.formatter.PostFormatter.DEFAULT_CONTENT;


public class CreatePost extends WebPage {

    private static final long serialVersionUID = 2586676420597986366L;

    private PostDTO newPost;

    private Form<Object> registryForm;

    @SpringBean
    private PostDAO postDAO;
    @SpringBean
    private PostFormatter postFormatter;



    public CreatePost() {
        newPost = new PostDTO();
        newPost.setContent(DEFAULT_CONTENT);
        registryForm = new Form<Object>("newPostForm");
        registryForm.add(new TextField<String>("newTittle", new PropertyModel<String>(newPost, "tittle")));
        registryForm.add(new TextArea<String>("newContent", new PropertyModel<String>(newPost, "content")));

        addSubmitButtonAndSendRegistryForm();
        add(registryForm);

    }

    private void addSubmitButtonAndSendRegistryForm() {
        registryForm.add(new Button("addPost") {
            private static final long serialVersionUID = 498874536367721731L;

            @Override
            public void onSubmit() {
                super.onSubmit();
                postDAO.save(postFormatter.formatPost(newPost));
                setResponsePage(HomePage.class);
            }
        });
    }

}
