package com.jianhui.controller;

import com.jianhui.model.County;
import com.jianhui.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/county")
public class CountyController {

    @Autowired
    CountyRepository countyRepository;

    @GetMapping("allCounties")
    public List<County> findAll(){
        return countyRepository.findAll();
    }

    @GetMapping("{name}")
    public County findCountyByName(@PathVariable String name){
        return countyRepository.findByName(name);
    }

//    @DeleteMapping("admin/deleteById/{id}")
//    public void deleteCountyById(@PathVariable("id") Integer id) {
//        countyRepository.deleteById(id);
//    }
//
//    @DeleteMapping("admin/deleteByName/{name}")
//    public void deleteCountyById(@PathVariable("name") String name) {
//        countyRepository.deleteByName(name);
//    }
//
//    @PostMapping("admin/add")
//    public County addCounty(@RequestBody Map<String, String> c){
//        County county = new County();
//        county.setName(c.get("name"));
//        return countyRepository.save(county);
//    }

}
