package com.jianhui.controller;

import com.jianhui.model.Type;
import com.jianhui.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    TypeRepository typeRepository;

    @GetMapping("allTypes")
    public List<Type> findAll(){
        return typeRepository.findAll();
    }

    @GetMapping("{name}")
    public Type findTypeByName(@PathVariable String name){
        return typeRepository.findByName(name);
    }

//    @DeleteMapping("/admin/deleteById/{id}")
//    public void deleteTypeById(@PathVariable("id") Integer id) {
//        typeRepository.deleteById(id);
//    }
//
//    @DeleteMapping("/admin/deleteByName/{name}")
//    public void deleteTypeById(@PathVariable("name") String name) {
//        typeRepository.deleteByName(name);
//    }
//
//    @PostMapping("/admin/add")
//    public Type addType(@RequestBody Map<String, String> t){
//        Type type = new Type();
//        type.setName(t.get("name"));
//        return typeRepository.save(type);
//    }
}
