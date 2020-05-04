package com.jianhui.controller;


import com.jianhui.model.Unit;
import com.jianhui.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    UnitRepository unitRepository;

    @GetMapping("allUnits")
    public List<Unit> findAll(){
        return unitRepository.findAll();
    }

    @GetMapping("{name}")
    public Unit findUnitByName(@PathVariable String name){
        return unitRepository.findByName(name);
    }

//    @DeleteMapping("/admin/deleteById/{id}")
//    public void deleteUnitById(@PathVariable("id") Integer id) {
//        unitRepository.deleteById(id);
//    }
//
//    @DeleteMapping("/admin/deleteByName/{name}")
//    public void deleteUnitByName(@PathVariable("name") String name) {
//        unitRepository.deleteByName(name);
//    }
//
//    @PostMapping("/admin/add")
//    public Unit addCounty(@RequestBody Map<String, String> u){
//        Unit unit = new Unit();
//        unit.setName(u.get("name"));
//        return unitRepository.save(unit);
//    }
}

