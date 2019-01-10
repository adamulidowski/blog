package adam.panels;

import adam.dao.api.PostDAO;
import adam.dto.PostDTO;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PostsPanel extends Panel {

    PostDTO postDTO;

    @SpringBean
    private PostDAO postDAO;

    WebMarkupContainer datacontainer;
    PageableListView<?> pageableListView;


    private static final long serialVersionUID = 4219213416788785651L;

    public PostsPanel(String id, String postType){
        super(id);
        createContainer();

        pageableListView = new PageableListView<PostDTO>("posts", postDAO.getAllPostsSortedByDate(postType), 1) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<PostDTO> item) {
                postDTO = item.getModelObject();
                addItems(item);
            }

        };
        addContainer();
    }

    private void createContainer() {
        datacontainer = new WebMarkupContainer("data");
        datacontainer.setOutputMarkupId(true);
        add(datacontainer);
    }

    private void addItems(ListItem<PostDTO> item) {
        item.add(new Label("tittle", postDTO.getTittle()));
        item.add(new Label("postDate", postDTO.getPostDate() +" | " + postDTO.getType()));
        item.add(new Label("content", postDTO.getContent()).setEscapeModelStrings(false));
    }

    private void addContainer() {
        datacontainer.add(pageableListView);
        datacontainer.add(new AjaxPagingNavigator("nav", pageableListView));
        datacontainer.add(new AjaxPagingNavigator("nav2", pageableListView));
        datacontainer.setVersioned(false);
    }
}
