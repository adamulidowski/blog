package adam.formatter;

import adam.dto.PostDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PostFormatter {

    public PostDTO formatPost(PostDTO postToFormat) {
        return new PostDTO(postToFormat.getTittle(),
                new Date(),
                formatContent(postToFormat.getContent()),
                postToFormat.getType());
    }

    public static String formatContent(String content){
        return haveAtLeastOneImage(content) ? formatWithImages(content) : formatOnlyNewLines(content);
    }

    private static boolean haveAtLeastOneImage(String content){
        return content.contains("[OBRAZ]");
    }

    private static String formatOnlyNewLines(String content){
        return content.replaceAll("\r\n", "<br />")
                .replaceAll("\\[T\\]", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                .replaceAll("\\[I\\]", "<i>")
                .replaceAll("\\[/I\\]", "</i>")
                .replaceAll("\\[B\\]", "<b>")
                .replaceAll("\\[/B\\]", "</b>")
                .replaceAll("\\[/C\\]", "</center>")
                .replaceAll("\\[C\\]", "<center>")
                .replaceAll("\\[CODE\\]", "<pre class=\"preFormatter\">")
                .replaceAll("\\[/CODE\\]", "</pre>")
                .replaceAll("\\[PODPIS\\]", "<p class=\"imgText\">")
                .replaceAll("\\[/PODPIS\\]", "</p>")
                .replaceAll("<iframe", "<center><iframe")
                .replaceAll("</iframe>", "</iframe></center>")
                .replaceAll("width=\"[0-9]*\"", "width=\"70%\"")
                .replaceAll("height=\"[0-9]*\"", "height=\"350\"");
    }

    private static String formatWithImages(String content) {
        return formatOnlyNewLines(content).replaceAll("\r\n", "<br />")
                      .replaceAll("\\[OBRAZ\\]", "<img src=\"")
                      .replaceAll("\\[/OBRAZ\\]", "\" class=\"postImg\"" + ">");
    }
}
