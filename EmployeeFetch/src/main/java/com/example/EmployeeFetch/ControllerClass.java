package com.example.EmployeeFetch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import com.example.EmployeeFetch.Employee;
import com.example.EmployeeFetch.EmployeeService;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ControllerClass {
   @Autowired
   EmployeeService empservice;



   @RequestMapping(value="/addEmployees", method=RequestMethod.POST)
   public String addEmployees(Employee employee)
   {
       return empservice.addemp(employee);
   }

  @RequestMapping(value="/updateSalary",method=RequestMethod.PUT)
  public String update_salary(@RequestParam int id,@RequestParam int new_salary)
  {
      return empservice.updateSalary(id,new_salary);
  }

  @RequestMapping(value="updateEmployeeName", method=RequestMethod.PUT)
  public String update_name(@RequestParam int id, @RequestParam String new_name)
  {
      return empservice.updateEmpName(id,new_name);
  }

 @GetMapping(value="/deleteEmployee/{id}")
 public String delete_emp(@RequestParam int id)
  {
      return empservice.deleteEmployee(id);
  }


    @GetMapping("/getEmpofSalaryRange")
    public List<Employee> getData(@RequestParam int min, @RequestParam int max)
    {
        return empservice.getEmployee(min,max);


    }

    @GetMapping("/listEmployee")
    public List<Employee> get_all_emp()
    {
        return empservice.listEmployee();
    }
//    @GetMapping("/listEmployee/{id}")
//    public List<Employee> getEmployeesById(@PathVariable int id) {
//        return empservice.listEmployee(id);
//    }




    @GetMapping("/sortonsalary")
    public List<Employee> sort_on_salary()
    {
        return empservice.sortEmp();
    }

}
