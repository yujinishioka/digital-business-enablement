package restControler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController
public class EmployeeController {

	private List<Employee> employeeList = new ArrayList<>();
	
    @PostConstruct
    private void postConstruct() {
    	Employee boss = new Employee("john", "boss");
    	Employee developer = new Employee("duke", "developer");
    	employeeList.add(boss);
    	employeeList.add(developer);
    }
	
    
    @GetMapping("/employees")
    ResponseEntity<List<Employee>> all() {
      return ResponseEntity.ok(employeeList);
    }
   
    
    
    
	  @PostMapping("/employees")
	  public ResponseEntity<Employee> newEmployee(@RequestBody Employee newEmployee) {
		  validateEmployee(newEmployee);
		  employeeList.add(newEmployee);
		  return ResponseEntity.ok(newEmployee);
	  }

	  private void validateEmployee(Employee newEmployee) {
		if(newEmployee.getName() == null) {
			throw new IllegalArgumentException("Employee without name");
		} else if(newEmployee.getRole() == null) {
			throw new IllegalArgumentException("Employee without role");
		}
		
	}

	  @GetMapping("/employees/{id}")
	  public ResponseEntity<Employee> one(@PathVariable Integer id) {
	    Employee employee = employeeList.get(id);
		if(employee == null) {
			 ResponseEntity.notFound();
	    }
	    return ResponseEntity.ok(employee);
	  }

	  @PutMapping("/employees/{id}")
	  public ResponseEntity<Employee> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Integer id) {
		  Employee employee = employeeList.get(id);
			if(employee == null) {
				 ResponseEntity.notFound();
		    }
			employeeList.set(id, newEmployee);
			return ResponseEntity.ok(newEmployee);
	  }

	  @DeleteMapping("/employees/{id}")
	  public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id) {
		  Employee employee = employeeList.get(id);
			if(employee == null) {
				 ResponseEntity.notFound();
		    }
			this.employeeList.remove(employee);
			return ResponseEntity.ok().build();
	  }
}
