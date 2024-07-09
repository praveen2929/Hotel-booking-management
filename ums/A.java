package com.ums;
//
//public class A {
//    int x = 10;
//    public static void main(String[] args) {
//        A a1 = new A();
//         int val = a1.test();
//        System.out.println(val);
//
//    }
//    public int test() {
//       int x = example();
//       return x;
//    }
//    public int example(){
//        B b1 = new B();
//        int y = b1.test1();
//        return y;
//
//    }
//}

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class A {
    public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.setId(1);
        e1.setName("John");
        Employee e2 = new Employee();
        e2.setId(2);
        e2.setName("Adam");

        List<Employee> employees= new ArrayList<Employee>();
        employees.add(e1);
        employees.add(e2);

        List<EmployeeDto> dtos = employees.stream().map(e -> convertToDto(e)).collect(Collectors.toList());
        System.out.println(dtos);
    }
    static EmployeeDto convertToDto(Employee e){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(e.getId());
        employeeDto.setName(e.getName());
        return employeeDto;
    }
}

