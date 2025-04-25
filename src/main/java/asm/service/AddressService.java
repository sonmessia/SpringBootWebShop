package asm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import asm.dto.DistrictDTO;
import asm.dto.ProvinceDTO;
import asm.dto.WardDTO;

@Service
public class AddressService {
    
    private final RestTemplate restTemplate;
    private final String API_URL = "https://provinces.open-api.vn/api";
    
    public AddressService() {
        this.restTemplate = new RestTemplate();
    }
    
    public List<ProvinceDTO> getProvinces() {
        try {
            ProvinceDTO[] response = restTemplate.getForObject(API_URL, ProvinceDTO[].class);
            return response != null ? Arrays.asList(response) : Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    public List<DistrictDTO> getDistrictsByProvince(int provinceCode) {
        try {
            // Truy vấn API để lấy tỉnh/thành phố kèm theo quận/huyện
            Map<String, Object> response = restTemplate.getForObject(
                API_URL + "/p/" + provinceCode + "?depth=2", 
                Map.class
            );
            
            if (response != null && response.containsKey("districts")) {
                List<Map<String, Object>> districts = (List<Map<String, Object>>) response.get("districts");
                List<DistrictDTO> districtDTOs = new ArrayList<>();
                
                for (Map<String, Object> district : districts) {
                    DistrictDTO dto = new DistrictDTO();
                    dto.setCode(((Number) district.get("code")).intValue());
                    dto.setName((String) district.get("name"));
                    dto.setDivision_type((String) district.get("division_type"));
                    dto.setCodename((String) district.get("codename"));
                    dto.setProvince_code(provinceCode);
                    districtDTOs.add(dto);
                }
                
                return districtDTOs;
            }
            
            return Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    public List<WardDTO> getWardsByDistrict(int districtCode) {
        try {
            // Truy vấn API để lấy quận/huyện kèm theo phường/xã
            Map<String, Object> response = restTemplate.getForObject(
                API_URL + "/d/" + districtCode + "?depth=2", 
                Map.class
            );
            
            if (response != null && response.containsKey("wards")) {
                List<Map<String, Object>> wards = (List<Map<String, Object>>) response.get("wards");
                List<WardDTO> wardDTOs = new ArrayList<>();
                
                for (Map<String, Object> ward : wards) {
                    WardDTO dto = new WardDTO();
                    dto.setCode(((Number) ward.get("code")).intValue());
                    dto.setName((String) ward.get("name"));
                    dto.setDivision_type((String) ward.get("division_type"));
                    dto.setCodename((String) ward.get("codename"));
                    dto.setDistrict_code(districtCode);
                    wardDTOs.add(dto);
                }
                
                return wardDTOs;
            }
            
            return Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}