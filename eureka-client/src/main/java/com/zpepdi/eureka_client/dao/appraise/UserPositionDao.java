package com.zpepdi.eureka_client.dao.appraise;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.User;

import java.util.List;

@Repository
public interface UserPositionDao {

    void add(@Param("id") Integer id , @Param("list") List list);

    void del(Integer id);

    void upd( List list);

    List<Integer> queryByUserId(User user);
}
