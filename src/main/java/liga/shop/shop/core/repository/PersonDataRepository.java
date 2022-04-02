package liga.shop.shop.core.repository;

import liga.shop.shop.core.model.PersonDataEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PersonDataRepository {

    @Select("select * from person_data")
    List<PersonDataEntity> findAll();

    @Select("select * from person_data where id = #{id} ")
    PersonDataEntity findById(Long id);

    @Select("select * from nomenclature where name = #{name} ")
    PersonDataEntity findByName(String name);

    @Select("SELECT * FROM person_data WHERE email = #{email}")
    PersonDataEntity findByEmail(String email);

    @Insert("insert into person_data (id, first_name, last_name, email, password, role)" + "values( #{id}, #{firstName}, #{lastName}, #{email}, #{password}), #{role}")
    int insert(PersonDataEntity personDataEntity);

    @Delete("delete from person_data where id = #{id}")
    Boolean deleteById(Long id);

    @Update("update person_Data set  id = #{id} firstName = #{firstName}, lastName = #{lastName}," +
            " email = #{email}, password = #{password}, role = #{role}," + "where id = #{id}")
    Boolean updateById(PersonDataEntity personDataEntity);

}