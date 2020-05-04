package com.jianhui.controller.admin;

import com.jianhui.model.County;
import com.jianhui.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/county")
public class AdminCountyController {

    @Autowired
    CountyRepository countyRepository;

    @DeleteMapping("/deleteById/{id}")
    public void deleteCountyById(@PathVariable("id") Integer id) {
        countyRepository.deleteById(id);
    }

    @DeleteMapping("/deleteByName/{name}")
    public void deleteCountyById(@PathVariable("name") String name) {
        countyRepository.deleteByName(name);
    }

    @PostMapping("/add")
    public County addCounty(@RequestBody Map<String, String> c){
        County county = new County();
        county.setName(c.get("name"));
        return countyRepository.save(county);
    }

}
