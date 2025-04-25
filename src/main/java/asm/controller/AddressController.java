package asm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import asm.dto.DistrictDTO;
import asm.dto.ProvinceDTO;
import asm.dto.WardDTO;
import asm.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;
    
    @GetMapping("/provinces")
    public ResponseEntity<List<ProvinceDTO>> getAllProvinces() {
        return ResponseEntity.ok(addressService.getProvinces());
    }
    
    @GetMapping("/districts/{provinceCode}")
    public ResponseEntity<List<DistrictDTO>> getDistrictsByProvince(@PathVariable String provinceCode) {
        try {
            int code = Integer.parseInt(provinceCode);
            return ResponseEntity.ok(addressService.getDistrictsByProvince(code));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/wards/{districtCode}")
    public ResponseEntity<List<WardDTO>> getWardsByDistrict(@PathVariable String districtCode) {
        try {
            int code = Integer.parseInt(districtCode);
            return ResponseEntity.ok(addressService.getWardsByDistrict(code));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}