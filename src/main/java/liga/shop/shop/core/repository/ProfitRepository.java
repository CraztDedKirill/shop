package liga.shop.shop.core.repository;

import liga.shop.shop.core.model.ProfitEntity;
import liga.shop.shop.core.model.ShoppingEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProfitRepository {

    @Select("select * from profit")
    List<ProfitEntity> findAll();

    @Select("select * from profit where id = #{id} ")
    ProfitEntity findById(Long id);

    @Update("update profit set id = #{id} amount = #{amount}, date = #{date}," + "where id = #{id}")
    Boolean updateById( ProfitEntity profitEntity);

    @Insert("insert into profit (id, amount, date) " +
            "values(#{id}, #{amount}, #{date})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(ProfitEntity profitEntity);

}
