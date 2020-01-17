package com.ssz.mess.mapper;

import java.util.List;

 import com.ssz.mess.pojo.Store;
import com.ssz.mess.pojo.vo.StoreVo;
import org.apache.ibatis.annotations.Param;
import com.ssz.mess.pojo.StoreExample;
import org.apache.ibatis.annotations.Select;

public interface StoreMapper {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Store getStoreById(Integer id);


//    @Select("select * from store where store_id = #{id}")
    Store getStoreByStoreId(Integer id);

//    /**
//     * 关联其他表查询
//     * @param id
//     * @return
//     */
//    StoreVo getStoreWithOthers(Integer id);

    long countByExample(StoreExample example);

    int deleteByExample(StoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    List<Store> selectByExample(StoreExample example);

    Store selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Store record, @Param("example") StoreExample example);

    int updateByExample(@Param("record") Store record, @Param("example") StoreExample example);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
}