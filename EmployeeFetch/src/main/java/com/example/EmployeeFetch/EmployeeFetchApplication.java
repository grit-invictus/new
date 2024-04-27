package com.example.EmployeeFetch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


class Employee {

   int id;
	String name;

	int salary;

	public Employee(int salary, String name,int id) {

		this.name=name;
		this.salary=salary;
		this.id=id;
	}


	public void setId(int id)
	{
		this.id=id;
	}
	public int getId()
	{
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
}







@Service
class EmployeeService{
   // @Autowired
	//Employee employee;
	//public static Map<Integer,String> map ;
//	public void addEmp(int id,String name)
//	{
//		map.put(27788,"venkat");
//
//
//	}
	Map<String,Employee> map = new HashMap<>();
	public String addemp(Employee employee)
	{   String msg="";
		if(map.containsKey(employee.getName())) {

            msg= "New Employee with same id can not be added";
			throw new EmployeeAlreadyExist("employee with " + employee.getId() + "already exist");
		}

		else{
			map.put(employee.getName(),employee);
			msg = "New Employee added";
		}
		return msg;
	}




	public List<Employee> getEmployee(int lower,int higher)
	{


		List<Employee> emplist = new ArrayList<>();

		for(Map.Entry<String,Employee> h : map.entrySet())
		{
			if(h.getValue().getSalary()>lower && h.getValue().getSalary()<higher)
			{
				emplist.add(h.getValue());
			}
		}
		return emplist;
	}

	public String updateSalary(int id,int new_salary)
	{
		for(Map.Entry<String,Employee> h : map.entrySet())
		{
			if(h.getValue().getId()==id)
			{
				h.getValue().setSalary(new_salary);

			}
		}
		return "Salary of employee with id "+id +" updated";
	}
 public String deleteEmployee(int id) throws UnsupportedOperationException {
		String msg="";
	 if (!map.containsKey(id)) {
		 msg = "delete operation can not be performed";

		 throw new EmployeeDoesnotExist("Employee is with id" + id + "does not exist");
	 } else {

		 try {
			 for (Map.Entry<String, Employee> h : map.entrySet()) {
				 if (h.getValue().getId() == id) {
					 map.remove(h.getKey());
					 msg = "Employee with the " + id + "removed";
				 }

			 }
		 } catch (UnsupportedOperationException e) {
			 e.printStackTrace();
		 }

	 }
	 return msg;
 }
 public String updateEmpName(int id,String new_name)
 { String msg = "";
	 if(!map.containsKey(id))
	 {  msg = "update operation can not be performed";
		 throw new EmployeeDoesnotExist("Employee with id is doesnot exist");
	 }
	 else {
		 for (Map.Entry<String, Employee> h : map.entrySet()) {
			 if (h.getValue().getId() == id) {
				 h.getValue().setName(new_name);
                 msg = "Name of employee with id "+id +" updated";
			 }
		 }
	 }
	 return msg;
 }



 public List<Employee> listEmployee()
 {
	 List<Employee> list = new ArrayList<>();
	 for(Map.Entry<String,Employee> h : map.entrySet()) {

		 list.add(h.getValue());

	 }
	 return list;

 }


 public List<Employee> sortEmp()
 {
	// List<Employee> sortedlist = map.entrySet().stream().map(Map.Entry::getValue).sorted(Comparator.comparing(Employee::getSalary)).collect(Collectors.toList());
	List<Employee> sortedlist = map.entrySet().stream().map(Map.Entry::getValue).sorted(Comparator.comparingInt(Employee::getSalary)).collect(Collectors.toList());

	 return sortedlist;
}




}




@SpringBootApplication
public class EmployeeFetchApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeFetchApplication.class, args);
		Logger logger = Logger.getLogger(EmployeeFetchApplication.class.getName());
		ApplicationContext context = new AnnotationConfigApplicationContext(EmployeeFetchApplication.class);
		String[] beans = context.getBeanDefinitionNames();
		for(String s : beans)
		{
			logger.info( "bean loaded..."+s);
		}
		EmployeeService es = new EmployeeService();
		Class c = es.getClass();
		Field[] fields = c.getDeclaredFields();
		Method[] methods = c.getDeclaredMethods();
		Constructor[] constructor = c.getDeclaredConstructors();
		System.out.println(c);
		for(Field f : fields)
		{
			System.out.println(f);
		}
       for(Method m : methods){
		   System.out.println(m);
	   }
	   for(Constructor c1 : constructor)
	   {
		   System.out.println(c1);

	   }


	}

}
