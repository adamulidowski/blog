package adam.formatter;

import adam.dto.PostDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PostFormatter {

    public static String DEFAULT_CONTENT = "Przykład:\n" +
            "\n" +
            "[OBRAZ]link[/OBRAZ]\n" +
            "[PODPIS]Podpis[/PODPIS]\n" +
            "\n" +
            "[B]Pogrubienie[/B]\n" +
            "\n" +
            "[I]Pochylenie[/I]";

    public PostDTO formatPost(PostDTO postToFormat) {
        return new PostDTO(postToFormat.getTittle(),
                new Date(),
                formatContent(postToFormat.getContent()));
    }

    private String formatContent(String content){
        return haveAtLeastOneImage(content) ? formatWithImages(content) : formatOnlyNewLines(content);
    }

    private boolean haveAtLeastOneImage(String content){
        return content.contains("[OBRAZ]");
    }

    private String formatOnlyNewLines(String content){
        return content.replaceAll("\r\n", "<br />")
                .replaceAll("\\[I\\]", "<i>")
                .replaceAll("\\[/I\\]", "</i>")
                .replaceAll("\\[B\\]", "<b>")
                .replaceAll("\\[/B\\]", "</b>");

    }

    private String formatWithImages(String content) {
        return formatOnlyNewLines(content).replaceAll("\r\n", "<br />")
                      .replaceAll("\\[OBRAZ\\]", "<img src=\"")
                      .replaceAll("\\[/OBRAZ\\]", "\" class=\"postImg\"" + ">")
                      .replaceAll("\\[PODPIS\\]", "<p class=\"imgText\">")
                      .replaceAll("\\[/PODPIS\\]", "</p>");
    }
}
