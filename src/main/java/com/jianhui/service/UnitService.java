package com.jianhui.service;

import com.jianhui.model.Unit;
import com.jianhui.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UnitService {

    @Autowired
    UnitRepository unitRepository;

    public List<Unit> findAll(){
        return unitRepository.findAll();
    }

    public Unit findUnitByName(String name){
        return unitRepository.findByName(name);
    }

    public Optional<Unit> findUnitById(Integer id){
        return unitRepository.findById(id);
    }

    public void deleteUnitById(Integer id) {
        unitRepository.deleteById(id);
    }

    public void deleteUnitByName(String name) {
        unitRepository.deleteByName(name);
    }

    public Unit addUnit(Map<String, String> u){
        Unit unit = new Unit();
        unit.setName(u.get("name"));
        return unitRepository.save(unit);
    }
}
