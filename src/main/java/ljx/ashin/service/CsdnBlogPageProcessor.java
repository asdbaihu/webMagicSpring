package ljx.ashin.service;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashin Liang on 2018/1/26.
 */
public class CsdnBlogPageProcessor implements PageProcessor {

    private static final String USER_NAME = "qq598535550";//用户名
    private static final String URL_MAIN_RG = "http://blog.csdn.net/qq598535550";//主页
    private static final String URL_POST_RG = "http://blog\\.csdn\\.net/qq598535550/article/details/\\d+";//文章页的规则
    private static final String BASE_URL = "http://blog.csdn.net";

    private Html html = null;

    private Site site = Site.me()
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
            .setDomain("http://blog.csdn.net").setCharset("utf-8")
            .setSleepTime(3*1000);

    @Override
    public void process(Page page) {
        html = page.getHtml();
        if (html.regex(URL_MAIN_RG).match()){//如果是主页
            //获取到所有的详情页的URL
            List<String> detailUrlList = html.xpath("//li[@class='blog-detail']/li[@class='blog-unit'/]").links().regex("/qq598535550/article/details/\\d+").all();
            List<String> detailParseUrlList = new ArrayList<String>();
            for (String detailUrl:detailUrlList ){
                detailUrl = BASE_URL+detailUrl;
                detailParseUrlList.add(detailUrl);
            }
            page.addTargetRequests(detailParseUrlList);
            page.setSkip(true);
        }else {//文章的详情页
            String title = html.xpath("//article/h1[@class='csdn_top'/text()]").toString();
            String date =  html.xpath("//article/div[@class='artical_tag']/span[@class='time']/text()").toString();
            List<String> tagList = html.xpath("//article/ul[@class='article_tags']/a/text()").all();
            String tags = "";
            for (int i = 0; i < tagList.size(); i++) {
                if (i==0){
                    tags = tagList.get(i);
                }
                tags =tags+","+ tagList.get(i);
            }
        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }
}
