package cn.nbmly.ipdemo.mapper;

import cn.nbmly.ipdemo.entity.IpAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface IpAddressMapper {

        @Select("SELECT id, ip_address, port, description, created_at " +
                        "FROM ip_address " +
                        "WHERE ip_address = #{ipAddress}")
        IpAddress findByIpAddress(String ipAddress);

        @Select("SELECT id, ip_address, port, description, created_at " +
                        "FROM ip_address " +
                        "WHERE port = #{port} " +
                        "ORDER BY created_at DESC")
        List<IpAddress> findByPort(Integer port);

        @Select("SELECT id, ip_address, port, description, created_at " +
                        "FROM ip_address " +
                        "WHERE description LIKE CONCAT('%', #{keyword}, '%') " +
                        "ORDER BY created_at DESC")
        List<IpAddress> findByDescriptionLike(String keyword);
}