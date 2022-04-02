package liga.shop.shop.core.repository;

import liga.shop.shop.core.model.StorageEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StorageRepository {

    @Insert("insert into storage (id, count, nomenclatureId) " +
            "values(#{id}, #{count}, #{nomenclatureId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(StorageEntity storageEntity);

    @Select("select * from storage")
    List<StorageEntity> findAll();

    @Select("select * from storage where id = #{id} ")
    StorageEntity findById(Long id);

    @Select("select * from storage where type_id = #{id} ")
    StorageEntity findByTypeId(Long id);

    @Update("update storage set id = #{id} count = #{count}, nomenclatureId = #{nomenclatureId}," + "where id = #{id}")
    Boolean updateById(StorageEntity storageEntity);

    @Delete("delete from storage where id = #{id}")
    Boolean deleteById(Long id);
}