package cn.iot.ipro.dao;

import cn.iot.ipro.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    Page<Material> findAllByMaterialTypeLikeOrMaterialNameLikeOrderByIdDesc(String materialType, String materialName, Pageable pageable);
}
