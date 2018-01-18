package ljx.ashin.service.impl;

import ljx.ashin.bean.TBlogContent;
import ljx.ashin.dao.TBlogContentMapper;
import ljx.ashin.service.TBlogContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by AshinLiang on 2018/1/18.
 */
@Service
public class TBlogContentServiceImpl implements TBlogContentService{

    @Resource
    private TBlogContentMapper tBlogContentMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tBlogContentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TBlogContent record) {
        return tBlogContentMapper.insert(record);
    }

    @Override
    public int insertSelective(TBlogContent record) {
        return 0;
    }

    @Override
    public TBlogContent selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(TBlogContent record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(TBlogContent record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TBlogContent record) {
        return 0;
    }
}
