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

    @Select("select * from nomenclature where name = #{id} ")
    StorageEntity findById(Long id);

    @Select("select * from nomenclature where type_id = #{id} ")
    StorageEntity findByTypeId(Long id);

    Boolean deleteById(@Param("storageEntityId") Long storageEntityId);

    // написать метод обновления
    // !!
    // !!
}