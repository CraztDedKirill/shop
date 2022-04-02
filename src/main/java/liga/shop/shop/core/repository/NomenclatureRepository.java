package liga.shop.shop.core.repository;

import liga.shop.shop.core.model.NomenclatureEntity;
import org.apache.ibatis.annotations.*;


import java.util.List;


@Mapper
public interface NomenclatureRepository {

    @Select("select * from nomenclature")
    List<NomenclatureEntity> findAll();

    @Select("select * from nomenclature where type_id = #{id} ")
    List<NomenclatureEntity> findAllByTypeId(Long id);

    @Select("select * from nomenclature where name = #{name} ")
    NomenclatureEntity findByName(String name);

    @Select("select * from nomenclature where id = #{id} ")
    NomenclatureEntity findById(Long id);

    @Insert("insert into nomenclature (id, name, availability, price, describe, type_id) " +
            "values(#{id}, #{name}, #{availability}, #{price}, #{describe}, #{type_id} )")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(NomenclatureEntity nomenclatureEntity);

    @Delete("delete from nomenclature where id = #{id}")
    Boolean deleteById(Long id);

    @Update("update nomenclature set  id = #{id} name = #{name}, availability = #{availability}," +
            " price = #{price}, describe = #{describe}, type_id = #{type_id}," + "where id = #{id}")
    Boolean updateById(NomenclatureEntity nomenclatureEntity);


}
