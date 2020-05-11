package com.jianhui.controller;

import com.jianhui.model.County;
import com.jianhui.service.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/county")
public class CountyController {

    @Autowired
    CountyService countyService;

    @GetMapping("allCounties")
    public List<County> findAll(){
        return countyService.findAll();
    }

    @GetMapping("{name}")
    public County findCountyByName(@PathVariable String name){
        return countyService.findCountyByName(name);
    }

    @GetMapping("searchById/{id}")
    public Optional<County> findCountyById(@PathVariable Integer id){
        return countyService.findCountyById(id);
    }

}
