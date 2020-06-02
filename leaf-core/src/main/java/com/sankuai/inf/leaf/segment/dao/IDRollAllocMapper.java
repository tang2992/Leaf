package com.sankuai.inf.leaf.segment.dao;

import com.sankuai.inf.leaf.segment.model.LeafAlloc;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IDRollAllocMapper {

    @Select("SELECT biz_tag, roll_key, max_id, step, update_time FROM leaf_roll_alloc")
    @Results(value = {
            @Result(column = "biz_tag", property = "key"),
            @Result(column = "roll_key", property = "rollKey"),
            @Result(column = "max_id", property = "maxId"),
            @Result(column = "step", property = "step"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<LeafAlloc> getAllLeafAllocs();

    @Select("SELECT biz_tag, roll_key, max_id, step FROM leaf_roll_alloc WHERE biz_tag = #{tag}")
    @Results(value = {
            @Result(column = "biz_tag", property = "key"),
            @Result(column = "roll_key", property = "rollKey"),
            @Result(column = "max_id", property = "maxId"),
            @Result(column = "step", property = "step")
    })
    LeafAlloc getLeafAlloc(@Param("tag") String tag);

    @Update("UPDATE leaf_roll_alloc SET max_id = max_id + step WHERE biz_tag = #{tag} AND roll_key = #{rollKey}")
    void updateMaxId(@Param("tag") String tag);

    @Update("UPDATE leaf_roll_alloc SET max_id = max_id + #{step} WHERE biz_tag = #{key} AND roll_key = #{rollKey}")
    void updateMaxIdByCustomStep(@Param("leafAlloc") LeafAlloc leafAlloc);

    @Select("SELECT CONCAT(biz_tag, '-', roll_key) FROM leaf_roll_alloc")
    List<String> getAllTags();
}
