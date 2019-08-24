package cn.iot.ipro.service.impl;

import cn.iot.ipro.dao.MaterialRepository;
import cn.iot.ipro.entity.Material;
import cn.iot.ipro.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MaterialServiceImpl implements IMaterialService {
    private MaterialRepository materialRepository;

    @Autowired
    public void setMaterialRepository(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public void add(Material material) {
        materialRepository.save(material);
    }

    @Override
    public Page<Material> getList(String materialType, String materialName, Pageable pageable) {
        return materialRepository.findAllByMaterialTypeLikeOrMaterialNameLikeOrderByIdDesc("%" + materialType + "%", "%" + materialName + "%", pageable);
    }
}
