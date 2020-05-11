package com.jianhui.controller;

import com.jianhui.model.Type;
import com.jianhui.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping("allTypes")
    public List<Type> findAll(){
        return typeService.findAll();
    }

    @GetMapping("{name}")
    public Type findTypeByName(@PathVariable String name){
        return typeService.findTypeByName(name);
    }

    @GetMapping("searchById/{id}")
    public Optional<Type> findTypeById(@PathVariable Integer id){
        return typeService.findTypeById(id);
    }

}
