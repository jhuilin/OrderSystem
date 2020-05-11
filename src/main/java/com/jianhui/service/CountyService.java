package com.jianhui.service;

import com.jianhui.model.County;
import com.jianhui.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CountyService {

    @Autowired
    CountyRepository countyRepository;

    public List<County> findAll(){
        return countyRepository.findAll();
    }

    public County findCountyByName(String name){
        return countyRepository.findByName(name);
    }

    public Optional<County> findCountyById(Integer id){
        return countyRepository.findById(id);
    }

    public void deleteCountyById(Integer id) {
        countyRepository.deleteById(id);
    }

    public void deleteCountyByName(String name) {
        countyRepository.deleteByName(name);
    }

    public County addCounty(Map<String, String> c){
        County county = new County();
        county.setName(c.get("name"));
        return countyRepository.save(county);
    }
}
