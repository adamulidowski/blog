package adam.pages;

import adam.dao.api.PostDAO;
import adam.dto.PostDTO;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class HomePage extends WebPage {

    private static final long serialVersionUID = 2586676420597986366L;

    PostDTO postDTO;

    @SpringBean
    private PostDAO postDAO;

    WebMarkupContainer datacontainer;
    PageableListView<?> pageableListView;


    public HomePage() {

        createContainer();

        pageableListView = new PageableListView<PostDTO>("posts", postDAO.getAllPostsSortedByDate(), 1) {

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
        item.add(new Label("postDate", postDTO.getPostDate()));
        item.add(new Label("content", postDTO.getContent()).setEscapeModelStrings(false));
    }

    private void addContainer() {
        datacontainer.add(pageableListView);
        datacontainer.add(new AjaxPagingNavigator("nav", pageableListView));
        datacontainer.add(new AjaxPagingNavigator("nav2", pageableListView));
        datacontainer.setVersioned(false);
    }
}

