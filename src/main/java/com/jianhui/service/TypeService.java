package com.jianhui.service;

import com.jianhui.model.Type;
import com.jianhui.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    public List<Type> findAll(){
        return typeRepository.findAll();
    }

    public Type findTypeByName(String name){
        return typeRepository.findByName(name);
    }

    public Optional<Type> findTypeById(Integer id){
        return typeRepository.findById(id);
    }

    public void deleteTypeById(Integer id) {
        typeRepository.deleteById(id);
    }

    public void deleteTypeByName(String name) {
        typeRepository.deleteByName(name);
    }

    public Type addType(Map<String, String> t){
        Type type = new Type();
        type.setName(t.get("name"));
        return typeRepository.save(type);
    }
}
