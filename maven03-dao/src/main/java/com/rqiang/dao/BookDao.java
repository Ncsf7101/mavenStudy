package com.rqiang.dao;

import com.rqiang.domain.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BookDao {
    @Insert("insert into tb_book (type, name, description) values (#{type}, #{name}, #{description})")
    int save(Book book);
    @Delete("delete from tb_book where id=#{id}")
    int delete(Integer id);
    @Update("update tb_book set type = #{type}, name = #{name}, description = #{description} where id = #{id}")
    int update(Book book);
    @Select("select * from tb_book where id=#{id}")
    Book getById(Integer id);
    @Select("select * from tb_book;")
    List<Book> getAll();
}
