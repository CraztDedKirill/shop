package liga.shop.shop.core.repository;

import liga.shop.shop.core.model.LogEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

@Mapper
public interface LogRepository {

        @Insert("insert into log (time, method_name, class_name, args) " +
                "values(#{time}, #{methodName}, #{className}, #{args}) ")
        @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
        int insert(LogEntity logEntity);

        @Select("select id from log where time = #{time}")
        long findLogId(LocalDateTime time);

}

