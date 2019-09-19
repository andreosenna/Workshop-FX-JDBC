package model.Services;

import java.util.ArrayList;
import java.util.List;

import model.Entities.Department;

public class DepartmentService {
	
public List<Department> findAll(){
	List<Department> list = new ArrayList<>();
	list.add(new Department(1,"Vendas"));
	list.add(new Department(2,"Compras"));
	list.add(new Department(3,"Administração"));
	
	return list;
	
}
}
