package adam.pages;

import adam.panels.PostsPanel;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {

    private static final long serialVersionUID = 2586676420597986366L;


    public HomePage() {
        add(new PostsPanel("programmingPosts", "Programowanie"));
        add(new PostsPanel("sportPosts", "Sport"));
        add(new PostsPanel("popculturePosts", "Popkultura"));
    }
}

