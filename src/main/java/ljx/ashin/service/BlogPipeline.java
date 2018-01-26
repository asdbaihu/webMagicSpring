package ljx.ashin.service;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.selector.Selector;

import java.util.Date;
import java.util.Map;

/**
 * Created by Ashin Liang on 2018/1/26.
 */
public class BlogPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("对应的URL:"+resultItems.getRequest().getUrl());
        System.out.println("标题为:"+resultItems.get("title"));
        System.out.println("日期为:"+resultItems.get("editTime"));
        System.out.println("内容为:"+resultItems.get("content"));
        System.out.println(new Date());
        /*for (Map.Entry<String,Object> entry: resultItems.getAll().entrySet()){
            System.out.println("key值:"+entry.getKey());

            Selectable selector = (Selectable) entry.getValue();
//            String value = selector.css("text").toString();
            System.out.println("value值:"+entry.getValue());
        }*/
    }
}
