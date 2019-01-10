package adam.pages;

import adam.dao.api.PostDAO;
import adam.dto.PostDTO;
import adam.formatter.PostFormatter;
import adam.validator.PostValidator;
import org.apache.wicket.extensions.markup.html.form.select.Select;
import org.apache.wicket.extensions.markup.html.form.select.SelectOption;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class CreatePost extends WebPage {

    private static final long serialVersionUID = 2586676420597986366L;

    private PostDTO newPost;

    private Form<Object> registryForm;

    @SpringBean
    private PostDAO postDAO;
    @SpringBean
    private PostFormatter postFormatter;
    @SpringBean
    private PostValidator postValidator;

    private Label wrongTittle;
    private Label wrongContent;


    public CreatePost() {
        newPost = new PostDTO();
        registryForm = new Form<>("newPostForm");
        initializeMessages();
        registryForm.add(new TextField<>("newTittle", new PropertyModel<String>(newPost, "tittle")));
        registryForm.add(new TextArea<>("newContent", new PropertyModel<String>(newPost, "content")));
        Select languages = new Select("type", new PropertyModel<String>(newPost, "type"));
        registryForm.add(languages);
        addSelectTypes(languages);
        addSubmitButtonAndSendRegistryForm();
        add(registryForm);

    }

    private void addSelectTypes(Select languages) {
        languages.add(new SelectOption<>("programming", new Model<String>("Programowanie")));
        languages.add(new SelectOption<>("sport", new Model<String>("Sport")));
        languages.add(new SelectOption<>("popculture", new Model<String>("Popkultura")));
    }

    private void initializeMessages() {
        wrongTittle = new Label("wrongTittle", new Model<>("Tytuł musi posiadać minimum 5 znaków!"));
        wrongContent = new Label("wrongContent", new Model<>("Treść musi posiadać minimum 30 znaków!"));
        wrongTittle.setVisible(false);
        wrongContent.setVisible(false);
        registryForm.add(wrongTittle);
        registryForm.add(wrongContent);
    }

    private void addSubmitButtonAndSendRegistryForm() {
        registryForm.add(new Button("addPost") {
            private static final long serialVersionUID = 498874536367721731L;

            @Override
            public void onSubmit() {
                super.onSubmit();
                validateAndSavePost();
            }
        });
    }

    private void validateAndSavePost() {
        if (postValidator.contentIsBad(newPost.getContent()) && postValidator.tittleIsBad(newPost.getTittle())) {
            wrongContent.setVisible(true);
            wrongTittle.setVisible(true);
        } else if (postValidator.tittleIsBad(newPost.getTittle())) {
            wrongTittle.setVisible(true);
            wrongContent.setVisible(false);
        } else if (postValidator.contentIsBad(newPost.getContent())) {
            wrongContent.setVisible(true);
            wrongTittle.setVisible(false);
        } else {
            postDAO.save(postFormatter.formatPost(newPost));
            setResponsePage(HomePage.class);
        }
    }
}
