package cn.iot.ipro.controller;

import cn.iot.ipro.config.ResultBean;
import cn.iot.ipro.entity.Material;
import cn.iot.ipro.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/material/")
public class MaterialController {
    private IMaterialService materialService;

    @Autowired
    public void setMaterialService(IMaterialService materialService) {
        this.materialService = materialService;
    }

    @RequestMapping(value = "new")
    @ResponseBody
    public ResultBean add(@RequestBody @Valid Material material, BindingResult results) {
        if (results.hasErrors()) {
            return ResultBean.error(-2, Objects.requireNonNull(results.getFieldError()).getDefaultMessage());
        }
        material.setCreated(new Date());
        materialService.add(material);
        return ResultBean.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "list")
    @ResponseBody
    public ResponseEntity getList(@RequestParam("materialType") String materialType, @RequestParam("materialName") String materialName, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        System.err.println(materialType);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return ResponseEntity.ok(materialService.getList(materialType, materialName, pageable));
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        String data = "";
        if (!file.isEmpty()) {
            try {
                BASE64Encoder encoder = new BASE64Encoder();
                // 通过base64来转化图片
                data = encoder.encode(file.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

}
