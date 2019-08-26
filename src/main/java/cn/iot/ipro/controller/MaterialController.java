package cn.iot.ipro.controller;

import cn.iot.ipro.config.ResultBean;
import cn.iot.ipro.entity.Material;
import cn.iot.ipro.service.IMaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/material/")
public class MaterialController {
    private IMaterialService materialService;
    private static final Logger logger = LoggerFactory.getLogger(MaterialController.class);
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
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return ResponseEntity.ok(materialService.getList(materialType, materialName, pageable));
    }

    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
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
        logger.info("base64：" + data);
        return data;
    }

}
