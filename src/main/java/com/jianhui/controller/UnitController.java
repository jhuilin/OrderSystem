package com.jianhui.controller;


import com.jianhui.model.Unit;
import com.jianhui.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    UnitService unitService;

    @GetMapping("allUnits")
    public List<Unit> findAll(){
        return unitService.findAll();
    }

    @GetMapping("{name}")
    public Unit findUnitByName(@PathVariable String name){
        return unitService.findUnitByName(name);
    }

    @GetMapping("searchById/{id}")
    public Optional<Unit> findUnitById(@PathVariable Integer id){
        return unitService.findUnitById(id);
    }


}

