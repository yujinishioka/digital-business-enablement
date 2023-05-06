package restControler;

import java.util.Objects;

public class Employee {
	  private String name;
	  private String role;

	  Employee() {}

	  Employee(String name, String role) {
	    this.name = name;
	    this.role = role;
	  }

	  
	  public String getName() {
	    return this.name;
	  }

	  public String getRole() {
	    return this.role;
	  }


	  public void setName(String name) {
	    this.name = name;
	  }

	  public void setRole(String role) {
	    this.role = role;
	  }

	@Override
	public int hashCode() {
		return Objects.hash(name, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(name, other.name) && Objects.equals(role, other.role);
	}
}
