package com.jianhui.controller.admin;

import com.jianhui.model.Type;
import com.jianhui.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/type")
public class AdminTypeController {

    @Autowired
    TypeRepository typeRepository;

    @DeleteMapping("/deleteById/{id}")
    public void deleteTypeById(@PathVariable("id") Integer id) {
        typeRepository.deleteById(id);
    }

    @DeleteMapping("/deleteByName/{name}")
    public void deleteTypeByName(@PathVariable("name") String name) {
        typeRepository.deleteByName(name);
    }

    @PostMapping("/add")
    public Type addType(@RequestBody Map<String, String> t){
        Type type = new Type();
        type.setName(t.get("name"));
        return typeRepository.save(type);
    }
}