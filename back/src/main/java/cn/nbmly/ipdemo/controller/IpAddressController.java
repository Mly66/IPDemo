package cn.nbmly.ipdemo.controller;

import cn.nbmly.ipdemo.entity.IpAddress;
import cn.nbmly.ipdemo.service.IpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ip")
@CrossOrigin(origins = "*")
public class IpAddressController {

    @Autowired
    private IpAddressService ipAddressService;

    @GetMapping("/search")
    public ResponseEntity<?> searchIpAddress(@RequestParam String ipAddress) {
        return ipAddressService.findByIpAddress(ipAddress)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/port")
    public ResponseEntity<List<IpAddress>> searchByPort(@RequestParam Integer port) {
        List<IpAddress> results = ipAddressService.findByPort(port);
        return results.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(results);
    }

    @GetMapping("/search/description")
    public ResponseEntity<List<IpAddress>> searchByDescription(@RequestParam String keyword) {
        List<IpAddress> results = ipAddressService.findByDescriptionLike(keyword);
        return results.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(results);
    }
}