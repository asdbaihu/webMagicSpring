package ljx.ashin.dao;

import ljx.ashin.bean.TBlogContent;

public interface TBlogContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBlogContent record);

    int insertSelective(TBlogContent record);

    TBlogContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBlogContent record);

    int updateByPrimaryKeyWithBLOBs(TBlogContent record);

    int updateByPrimaryKey(TBlogContent record);
}