package cn.nbmly.ipdemo.service;

import cn.nbmly.ipdemo.entity.IpAddress;
import cn.nbmly.ipdemo.mapper.IpAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IpAddressService {

    @Autowired
    private IpAddressMapper ipAddressMapper;

    public Optional<IpAddress> findByIpAddress(String ipAddress) {
        return Optional.ofNullable(ipAddressMapper.findByIpAddress(ipAddress));
    }

    public List<IpAddress> findByPort(Integer port) {
        return ipAddressMapper.findByPort(port);
    }

    public List<IpAddress> findByDescriptionLike(String keyword) {
        return ipAddressMapper.findByDescriptionLike(keyword);
    }
}