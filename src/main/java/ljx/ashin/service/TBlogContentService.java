package ljx.ashin.service;

import ljx.ashin.bean.TBlogContent;

/**
 * Created by AshinLiang on 2018/1/18.
 */
public interface TBlogContentService {
    public int deleteByPrimaryKey(Integer id);

    public int insert(TBlogContent record);

    public int insertSelective(TBlogContent record);

    public TBlogContent selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(TBlogContent record);

    public int updateByPrimaryKeyWithBLOBs(TBlogContent record);

    public int updateByPrimaryKey(TBlogContent record);
}
