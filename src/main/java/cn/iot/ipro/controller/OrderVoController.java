package cn.iot.ipro.controller;

import cn.iot.ipro.config.ResultBean;
import cn.iot.ipro.entity.Material;
import cn.iot.ipro.entity.OrderVo;
import cn.iot.ipro.entity.Orders;
import cn.iot.ipro.model.PayFor;
import cn.iot.ipro.service.IMaterialService;
import cn.iot.ipro.service.IOrderVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


/**
 * 线上点单控制器
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/orders/")
public class OrderVoController {
    private IOrderVoService orderVoService;
    private IMaterialService materialService;

    @Autowired
    public void setMaterialService(IMaterialService materialService) {
        this.materialService = materialService;
    }

    @Autowired
    public void setOrderVoService(IOrderVoService orderVoService) {
        this.orderVoService = orderVoService;
    }

    @PostMapping(value = "new")
    public ResultBean add(@RequestBody @Valid List<PayFor> payForList, BindingResult results){
        if (results.hasErrors())
            return ResultBean.error(-3,results.getFieldError().getDefaultMessage());

        if (payForList.size() <= 0)
            return ResultBean.error(-1, "参数错误");
        OrderVo orderVo = new OrderVo();
        for (PayFor payFor : payForList) {
            Orders orders = new Orders();
            orders.setOrderVo(orderVo);
            orders.setNum(payFor.getNum());
            orders.setCreatedBy(payFor.getCreatedBy());
            orders.setMaterialId(payFor.getMaterialId());
            orderVo.getOrdersList().add(orders);
        }
        orderVo.setRoomType(payForList.get(0).getRoomType());
        orderVo.setUserName(payForList.get(0).getUserName());
        orderVo.setUserPhone(payForList.get(0).getUserPhone());

        orderVo.setCreatedDate(new Date());
        orderVoService.save(orderVo);
        return ResultBean.success();
    }

    @RequestMapping(value = "single")
    public ResponseEntity getOrderVo(@RequestParam("userPhone") String userPhone) {
        OrderVo orderVo = orderVoService.getOrderVoByUserPhone(userPhone);
        if (null == orderVo)
            return ResponseEntity.ok("订单不存在");
        Map<String, Object> res = new HashMap<>();
        res.put("id", orderVo.getId());
        res.put("userName", orderVo.getUserName());
        res.put("userPhone", orderVo.getUserPhone());
        res.put("room", orderVo.getRoomType());
        List<Orders> ordersList = orderVo.getOrdersList();
        List<Map<String, Object>> list = new ArrayList<>();
        for (Orders orders : ordersList) {
            Map<String, Object> objectHashMap = new HashMap<>();
            Material material = materialService.getInfoByID(orders.getMaterialId());
            objectHashMap.put("num", orders.getNum());
            objectHashMap.put("materialName", material.getMaterialName());
            objectHashMap.put("materialPrice", material.getMaterialPrice());
            objectHashMap.put("thumbnail", material.getThumbnail());
            list.add(objectHashMap);
        }
        res.put("ordersList", list);
        return ResponseEntity.ok(res);
    }
}
