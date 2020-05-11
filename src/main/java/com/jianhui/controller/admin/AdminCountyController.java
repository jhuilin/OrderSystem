package com.jianhui.controller.admin;

import com.jianhui.model.County;
import com.jianhui.service.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/county")
public class AdminCountyController {

    @Autowired
    CountyService countyService;

    @DeleteMapping("/deleteById/{id}")
    public void deleteCountyById(@PathVariable("id") Integer id) {
        countyService.deleteCountyById(id);
    }

    @DeleteMapping("/deleteByName/{name}")
    public void deleteCountyByName(@PathVariable("name") String name) {
        countyService.deleteCountyByName(name);
    }

    @PostMapping("/add")
    public County addCounty(@RequestBody Map<String, String> c){
        return countyService.addCounty(c);
    }


}
