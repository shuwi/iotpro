package cn.iot.ipro.service.impl;

import cn.iot.ipro.dao.OrderVoRepository;
import cn.iot.ipro.entity.OrderVo;
import cn.iot.ipro.service.IOrderVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderVoServiceImpl implements IOrderVoService {
    private OrderVoRepository orderVoRepository;

    @Autowired
    public void setOrderVoRepository(OrderVoRepository orderVoRepository) {
        this.orderVoRepository = orderVoRepository;
    }

    @Override
    public void save(OrderVo orderVo) {
        orderVoRepository.save(orderVo);
    }
    @Override
    public OrderVo getOrderVoByUserPhone(String userPhone){
        return orderVoRepository.getByUserPhoneOrderByIdDesc(userPhone);
    }
}
