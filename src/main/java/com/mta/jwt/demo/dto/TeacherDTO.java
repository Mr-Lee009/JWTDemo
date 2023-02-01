package com.mta.jwt.demo.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class TeacherDTO extends BaseDTO<Integer> {
    public String name;
    public String phone;

    public TeacherDTO(Integer id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public TeacherDTO() {

    }

    public static void main(String[] args) throws JsonProcessingException {
        TeacherDTO teacherDTO = new TeacherDTO(1, "ducla", "113");
        TeacherDTO teacherDTO2 = new TeacherDTO(2, "ducla", "113");

        System.out.println(teacherDTO.toJson());
        System.out.println(teacherDTO2.toJson());

        List<TeacherDTO> list = new ArrayList<>();
        list.add(teacherDTO2);
        list.add(teacherDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(list));
    }
}
