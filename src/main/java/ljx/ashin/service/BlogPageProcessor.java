package ljx.ashin.service;

import ljx.ashin.bean.TBlogContent;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import javax.annotation.Resource;
import javax.swing.text.html.HTML;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AshinLiang on 2018/1/18.
 */
@Service
public class BlogPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setCharset("utf-8").setSleepTime(1*1000);

    @Override
    public void process(Page page) {
        //全部博客的url
        String allBlogRg = "http://blog\\.sina\\.com\\.cn/s/articlelist_([0-9]{10})_0_1.html";
        //博客文章的url
        String blogContentRg = "http://blog\\.sina\\.com\\.cn/s/blog_([a-zA-Z0-9]{16}).html";
        String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";

        String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";
        Html html = page.getHtml();
        if (html.regex(allBlogRg).match()){
            System.out.println("全部博客");
            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
        }else if (html.regex(blogContentRg).match()){//成功匹配到文章的页面
            System.out.println("成功匹配到文章的页面");
            //
            TBlogContent tBlogContent = new TBlogContent();
            String title = page.getHtml().css(".SG_txta").get();
            String editTime = page.getHtml().css(".articalTitle .SG_txtc").get().toString();
            String content = page.getHtml().css(".articalContent").get().toString();

            tBlogContent.setTitle(title);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            try {
                tBlogContent.setEdittime(simpleDateFormat.parse(editTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tBlogContent.setContent(content);
            System.out.println(tBlogContent.toString());
        }else {//其他页面
            //设置skip之后，这个页面的结果不会被Pipeline处理
            page.setSkip(true);
        }
//        page.addTargetRequests(page.getHtml().links().regex("http://blog\\.sina\\.com\\.cn/s/(articlelist_|blog_)([a-zA-Z0-9]{10,16})(_0_1.html|.html)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //全部博客的url
        String allBlogRg = "http://blog\\.sina\\.com\\.cn/s/articlelist_([0-9]{10})_0_1.html";
        //博客文章的url
        String blogContentRg = "http://blog\\.sina\\.com\\.cn/s/blog_([a-zA-Z0-9]{16}).html";

        /*Pattern pattern1 = Pattern.compile(allBlogRg);
        String testContent1 = "http://blog.sina.com.cn/s/articlelist_1300871220_0_1.html";
        Matcher matcher1 = pattern1.matcher(testContent1);
        System.out.println("是否匹配:"+matcher1.matches());*/
      /*  Pattern pattern1 = Pattern.compile(blogContentRg);
        String testContent1 = "http://blog.sina.com.cn/s/blog_58ae76e80100to5q.html";
        Matcher matcher1 = pattern1.matcher(testContent1);
        System.out.println("是否匹配:"+matcher1.matches());*/
        Spider.create(new BlogPageProcessor()).addUrl("http://blog.sina.com.cn/flashsword20").thread(5).start();

    }
}
