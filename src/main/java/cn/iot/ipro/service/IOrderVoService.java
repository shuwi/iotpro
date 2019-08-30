package cn.iot.ipro.service;

import cn.iot.ipro.entity.OrderVo;

public interface IOrderVoService {
    void save(OrderVo orderVo);
    OrderVo getOrderVoByUserPhone(String userPhone);
}
