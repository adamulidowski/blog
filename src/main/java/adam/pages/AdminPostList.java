package adam.pages;

import adam.dao.api.PostDAO;
import adam.dto.PostDTO;
import adam.session.UserSession;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AdminPostList extends WebPage {

    private static final long serialVersionUID = 2586676420597986366L;

    private PostDTO postDTO;

    WebMarkupContainer datacontainer;
    PageableListView<?> pageableListView;

    @SpringBean
    private PostDAO postDAO;

    public AdminPostList() {
        if (UserSession.getInstance().getUserModel() == null) {
            setResponsePage(LoginPage.class);
        }
        createContainer();
        pageableListView = new PageableListView<PostDTO>("posts", postDAO.getAll(), 5) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<PostDTO> item) {
                postDTO = item.getModelObject();
                addItems(item);
            }

        };
        addContainer();
        add(new Link<String>("signOut") {

            private static final long serialVersionUID = 2586676420597986366L;

            @Override
            public void onClick() {
                UserSession.getInstance().invalidate();
                setResponsePage(HomePage.class);
            }
        });
    }

    private void createContainer() {
        datacontainer = new WebMarkupContainer("data");
        datacontainer.setOutputMarkupId(true);
        add(datacontainer);
    }

    private void addItems(ListItem<PostDTO> item) {
        item.add(new Label("tittle", postDTO.getTittle()));
        item.add(new Label("postDate", postDTO.getPostDate()));
        item.add(new Label("postType", postDTO.getType()));
        editPost(item);
        deletePost(item, postDTO);
    }

    private void addContainer() {
        datacontainer.add(pageableListView);
        datacontainer.add(new AjaxPagingNavigator("nav", pageableListView));
        datacontainer.setVersioned(false);
    }

    private void editPost(ListItem<PostDTO> item) {
        PageParameters postInfo = new PageParameters();
        postInfo.add("postId", postDTO.getId());
        item.add(new BookmarkablePageLink<>("EditPost", EditPost.class, postInfo));
    }

    private void deletePost(ListItem<PostDTO> item, final PostDTO post) {

        Label removePrint = new Label("removePrint", "Usuń");
        final Link removePost = new Link<Object>("removePost") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                postDAO.delete(post);
                setResponsePage(AdminPostList.class);
            }
        };
        item.add(removePost.add(removePrint));
    }
}
