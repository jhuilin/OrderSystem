package com.jianhui.controller.admin;

import com.jianhui.model.Unit;
import com.jianhui.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/unit")
public class AdminUnitController {

    @Autowired
    UnitService unitService;

    @DeleteMapping("/deleteById/{id}")
    public void deleteUnitById(@PathVariable("id") Integer id) {
        unitService.deleteUnitById(id);
    }

    @DeleteMapping("/deleteByName/{name}")
    public void deleteUnitByName(@PathVariable("name") String name) {
        unitService.deleteUnitByName(name);
    }

    @PostMapping("/add")
    public Unit addUnit(@RequestBody Map<String, String> u){
        return unitService.addUnit(u);
    }
}