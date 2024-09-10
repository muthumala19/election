package com.example.election.mappers;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    private ModelMapper modelMapper;

    public Mapper() {
        this.modelMapper = new ModelMapper();
    }

    public <T, R> R map(T source, Class<R> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    // Add a method to map a list of objects
    public <T, R> List<R> mapList(List<T> source, Class<R> targetClass) {
        return source.stream()
                .map(element -> map(element, targetClass))
                .collect(Collectors.toList());
    }
}
