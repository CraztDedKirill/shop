package liga.shop.shop.core.repository;

import liga.shop.shop.core.model.ProfitEntity;
import liga.shop.shop.core.model.ShoppingEntity;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ShoppingRepository {

    @Insert("insert into shopping (id, count, type_id) " +
            "values(#{id}, #{count}, #{type_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(ShoppingEntity shoppingEntity);

    @Select("select * from shopping")
    List<ShoppingEntity> findAll();

    @Select("select * from shopping where id = #{id} ")
    ShoppingEntity findById(Long id);

    @Delete("delete from shopping where id = #{id}")
    Boolean deleteById(Long id);

    @Update("update shopping set id = #{id} amount = #{amount}, type_id = #{type_id}," + "where id = #{id}")
    Boolean updateById(ShoppingEntity shoppingEntity);

    @Update("select * from shopping where date = #{localDate}")
    List<ShoppingEntity> findALlByDate(LocalDate localDate);

}
