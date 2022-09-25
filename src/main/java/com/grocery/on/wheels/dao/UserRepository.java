package com.grocery.on.wheels.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.grocery.on.wheels.model.User;

@Mapper
public interface UserRepository {

    @Select("select * from users")
    public List<User> findAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    public User findById(long id);

    @Delete("DELETE FROM users WHERE id = #{id}")
    public int deleteById(long id);

    @Insert("INSERT INTO users(id, firstName, lastName,emailId) " +
         " VALUES (#{id}, #{firstName}, #{lastName}, #{emailId})")
    public int insert(User user);

    @Update("Update users set firstName=#{firstName}, " +
         " lastName=#{lastName}, emailId=#{emailId} where id=#{id}")
    public int update(User user);
}