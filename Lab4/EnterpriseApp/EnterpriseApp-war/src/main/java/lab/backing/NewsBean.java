package lab.backing;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.ObjectMessage;
import lab.ejb.NewsItem;
import lab.ejb.NewsItemFacadeLocal;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class NewsBean implements Serializable {
    @Inject
    private NewsItemFacadeLocal facade;
    private String headingText;
    private String bodyText;
    @Inject
    private JMSContext context;
    @Resource(lookup="java:app/jms/NewsQueue")
    private jakarta.jms.Queue queue;

    void sendNewsItem(String heading, String body) {
        String combinedText = heading + "|" + body;
        context.createProducer().send(queue, combinedText);
    }

    public String submitNews()
    {
        sendNewsItem(getHeadingText(), getBodyText());
        return null;
    }

    public List<NewsItem> getNewsItems()
    {
        return facade.getAllNewsItems();
    }

    public String getHeadingText() {
        return headingText;
    }

    public void setHeadingText(String headingText) {
        this.headingText = headingText;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }
}
