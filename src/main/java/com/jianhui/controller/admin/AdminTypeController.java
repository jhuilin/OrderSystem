package com.jianhui.controller.admin;

import com.jianhui.model.Type;
import com.jianhui.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/type")
public class AdminTypeController {

    @Autowired
    TypeService typeService;

    @DeleteMapping("/deleteById/{id}")
    public void deleteTypeById(@PathVariable("id") Integer id) {
        typeService.deleteTypeById(id);
    }

    @DeleteMapping("/deleteByName/{name}")
    public void deleteTypeByName(@PathVariable("name") String name) {
        typeService.deleteTypeByName(name);
    }

    @PostMapping("/add")
    public Type addType(@RequestBody Map<String, String> t){
        return typeService.addType(t);
    }
}