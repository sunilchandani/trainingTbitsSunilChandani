package com.sunil.common;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.sunil.Client.JerseyEmployeeClient;
import com.sunil.Entities.Employee;

@ManagedBean
@SessionScoped
public class EmployeeBean {

	private Integer empId;
	private String name;
	private String department;
	private Integer age;
	private String address;
	
	private List<Employee> empList;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public void buttonAction(ActionEvent actionEvent) {
		addMessage();
	}

	@Override
	public String toString() {
		return "EmployeeBean [empId=" + empId + ", name=" + name
				+ ", department=" + department + ", age=" + age + ", address="
				+ address + "]";
	}

	public void reset(ActionEvent event) {
		this.empId = null;
		this.name = null;
		this.department = null;
		this.age = null;
		this.address = null;
		this.empList = null;
	}
	public void addMessage() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				name, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void storeEmployee() {
		Employee emp = new Employee(this.empId, this.name, this.department,
				this.age, this.address);
		
		if((new JerseyEmployeeClient()).postEmployee(emp)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Success", "Added Employee Details"));
		}
		else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error", "Employee with ID="+ this.empId + " already exists."));
		}
	}
	
	public void getEmployee() {
		Employee emp = (new JerseyEmployeeClient()).getEmployee(this.empId);
		if(emp == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error","Employee doesnot Exists."));
			return;
		}
		else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Success", null));
		}
		this.empId=emp.getEmpId();
		this.name=emp.getName();
		this.department=emp.getDepartment();
		this.age=emp.getAge();
		this.address=emp.getAddress();
	}

	public void getAllEmployee() {
		List<Employee> eList = (new JerseyEmployeeClient()).getEmployee();
		this.setEmpList(eList);
		if(eList != null) {
			FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Success", ""));
				return;
		}
		else {
				FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error","Error in fetching details."));
				return;
			}
	}
	
	public void updateEmployee() {
		Employee emp = new Employee(this.empId, this.name, this.department,
				this.age, this.address);
		if((new JerseyEmployeeClient()).updateEmployee(emp)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Success", "Updated Employee Details."));
			return;
		}
		else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error","Employee doesnot Exists."));
			return;
		}
	}
	
	public void deleteEmployee() {
		if((new JerseyEmployeeClient()).deleteEmployee(this.empId)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Success", "Deleted Employee Details"));
		}
		else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error", "Employee with ID=" + this.empId+" doesnot exits."));
		}

	}
	
}