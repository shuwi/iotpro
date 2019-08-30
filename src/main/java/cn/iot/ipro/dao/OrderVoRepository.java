package cn.iot.ipro.dao;

import cn.iot.ipro.entity.OrderVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderVoRepository extends JpaRepository<OrderVo, Long> {
    OrderVo getByUserPhoneOrderByIdDesc(String userPhone);
}
