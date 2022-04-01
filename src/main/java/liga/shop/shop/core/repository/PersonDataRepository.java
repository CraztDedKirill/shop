package liga.shop.shop.core.repository;

import liga.shop.shop.core.model.PersonDataEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    Boolean deleteById(@Param("personDataId") Long personDataId);

    //реализовать update
    /// Boolean updateById(@Param("personDataEntity") PersonDataEntity personDataEntity);

}