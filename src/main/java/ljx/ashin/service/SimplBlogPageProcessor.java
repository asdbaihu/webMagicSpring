package ljx.ashin.service;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * Created by Ashin Liang on 2018/1/26.
 */
public class SimplBlogPageProcessor implements PageProcessor {
    private static final String URL_LIST="http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";
    private static final String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";

    private Site site = Site.me().setDomain("blog.sina.com.cn")
            .setSleepTime(3*1000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    private Html html = null;

    @Override
    public void process(Page page) {
        html = page.getHtml();//当前页面
        //如果当前页面是列表页,将列表页的URL和文章页的URL放入到队列中
        if (page.getUrl().regex(URL_LIST).match()){
            //获取所有的文章页的URL
            List<String> urlPostList = html.xpath("//div[@class='articleList']").links().regex(URL_POST).all();

            //获取所有的列表页的URL
            List<String> urlListList = html.links().regex(URL_LIST).all();

            // 放入到队列中
            page.addTargetRequests(urlPostList);
            page.addTargetRequests(urlListList);
            //pipline不处理当前的页面
            page.setSkip(true);
        }else {//如果当前页面是文章页，抽取出需要的信息字段
            page.putField("title", html.xpath("//div[@class='articalTitle']/h2/text()"));
            page.putField("editTime",html.xpath("//div[@class='articalTitle']/span/text()"));
            page.putField("content",html.xpath("//div[@class='articalContent']/text()"));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new SimplBlogPageProcessor())
                .addUrl("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html")
                .addPipeline(new BlogPipeline()).run();
    }
}
