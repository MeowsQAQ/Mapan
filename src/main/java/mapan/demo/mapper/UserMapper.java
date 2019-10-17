package mapan.demo.mapper;

import mapan.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(account_id,name,token,gmt_create,gmt_modified,avatar_url,login,bio,location) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl},#{login},#{bio},#{location})")
    void insert(User user);

    @Select("select * from user where account_id = #{accountId}")
    List<User> selectByAccountId(User user);

    @Update("UPDATE user SET bio=#{bio},gmt_modified=#{gmtModified},location=#{location},name=#{name},token=#{token},avatar_url=#{avatarUrl} WHERE account_id=#{accountId};")
    void update(User user);

    @Select("select * from user where token=#{token}")
    List<User> findByToken(@Param("token") String token);
    @Select("select * from user where id = #{ownerId}")
    User selectByPrimaryKey(@Param("ownerId") Long ownerId);
    @Select("select * from user where id = #{fileUserId}")
    User findFilebyUserId(@Param("fileUserId") Integer fileUserId);
}
