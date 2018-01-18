package ljx.ashin.service;

import ljx.ashin.bean.TBlogContent;
import ljx.ashin.dao.TBlogContentMapper;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * Created by AshinLiang on 2018/1/18.
 */
@Service
public class TBlogContentPipeLine implements PageModelPipeline<TBlogContent> {

    @Resource
    private TBlogContentService tBlogContentService;

    @Override
    public void process(TBlogContent tBlogContent, Task task) {
        System.out.println("获取到的tBlogContent为:"+tBlogContent);
        tBlogContentService.insert(tBlogContent);

    }
}
