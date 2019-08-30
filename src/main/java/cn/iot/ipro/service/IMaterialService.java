package cn.iot.ipro.service;

import cn.iot.ipro.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMaterialService {
    void add(Material material);
    Page<Material> getList(String materialType, String materialName, Pageable pageable);
    Material getInfoByID(Long id);
}
