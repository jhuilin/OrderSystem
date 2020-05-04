package com.jianhui.controller.admin;

import com.jianhui.model.Unit;
import com.jianhui.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/unit")
public class AdminUnitController {

    @Autowired
    UnitRepository unitRepository;

    @DeleteMapping("/deleteById/{id}")
    public void deleteUnitById(@PathVariable("id") Integer id) {
        unitRepository.deleteById(id);
    }

    @DeleteMapping("/deleteByName/{name}")
    public void deleteUnitByName(@PathVariable("name") String name) {
        unitRepository.deleteByName(name);
    }

    @PostMapping("/add")
    public Unit addCounty(@RequestBody Map<String, String> u){
        Unit unit = new Unit();
        unit.setName(u.get("name"));
        return unitRepository.save(unit);
    }
}