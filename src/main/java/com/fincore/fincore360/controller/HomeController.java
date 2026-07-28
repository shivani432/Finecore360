package com.fincore.fincore360.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fincore.fincore360.entity.Employee;
import com.fincore.fincore360.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
public class HomeController {

    @Autowired
private EmployeeService employeeService;

    // =====================================
// मराठी : Home Page उघडणे
// English : Open Home Page
// =====================================
@GetMapping("/")
public String home(@RequestParam(defaultValue = "0") int page,
                   Model model) {

  model.addAttribute("employees",
        employeeService.getAllEmployees());

    model.addAttribute("totalSalary",
            employeeService.getTotalSalary());

    model.addAttribute("totalEmployees",
            employeeService.getTotalEmployees());

    model.addAttribute("totalDepartments",
            employeeService.getTotalDepartments());

    model.addAttribute("averageSalary",
            employeeService.getAverageSalary());
    model.addAttribute("departments",
            employeeService.getAllDepartments());

    return "index";
}
    
     @GetMapping("/add")
public String addEmployee(Model model) {

    model.addAttribute("employee", new Employee());

    return "add-employee";
}

@PostMapping("/save")
public String saveEmployee(@Valid Employee employee,
                           BindingResult result) {

    if (result.hasErrors()) {
        return "add-employee";
    }

    employeeService.saveEmployee(employee);

    return "redirect:/";
}

// =====================================
// मराठी : Edit Employee Page उघडणे
// English : Open Edit Employee Page
// =====================================
@GetMapping("/edit/{id}")
public String editEmployee(@PathVariable int id, Model model) {

    // =====================================
    // मराठी : ID वापरून Employee मिळवणे
    // English : Get Employee By ID
    // =====================================
    Employee employee = employeeService.getEmployeeById(id);

    // =====================================
    // मराठी : Employee HTML Page ला पाठवणे
    // English : Send Employee to View
    // =====================================
    model.addAttribute("employee", employee);

    return "add-employee";
}

    // =====================================
// मराठी : Employee Delete करणे
// English : Delete Employee
// =====================================
@GetMapping("/delete/{id}")
public String deleteEmployee(@PathVariable int id) {

    // =====================================
    // मराठी : Employee Delete करणे
    // English : Delete Employee
    // =====================================
    employeeService.deleteEmployee(id);

    return "redirect:/";
}
@GetMapping("/search")
public String searchEmployee(@RequestParam("keyword") String keyword,
                             Model model) {

    if (keyword == null || keyword.trim().isEmpty()) {

       model.addAttribute("employees",
        employeeService.getAllEmployees());

        model.addAttribute("totalSalary",
                employeeService.getTotalSalary());

        model.addAttribute("totalEmployees",
                employeeService.getTotalEmployees());

        model.addAttribute("totalDepartments",
                employeeService.getTotalDepartments());

        model.addAttribute("averageSalary",
                employeeService.getAverageSalary());
        
    } else {

        model.addAttribute("employees",
                employeeService.searchEmployee(keyword));
    }
    model.addAttribute("departments",
        employeeService.getAllDepartments());
    
    return "index";
}

@GetMapping("/filter")
public String filterDepartment(@RequestParam("department") String department,
                               Model model) {

    if (department == null || department.trim().isEmpty()) {

        model.addAttribute("employees",
                employeeService.getAllEmployees());

    } else {

        model.addAttribute("employees",
                employeeService.filterByDepartment(department));
    }

    model.addAttribute("totalSalary",
            employeeService.getTotalSalary());

    model.addAttribute("totalEmployees",
            employeeService.getTotalEmployees());

    model.addAttribute("totalDepartments",
            employeeService.getTotalDepartments());

    model.addAttribute("averageSalary",
            employeeService.getAverageSalary());
    model.addAttribute("departments",
        employeeService.getAllDepartments());

    return "index";
}
@GetMapping("/sort")
public String sortEmployee(@RequestParam("order") String order,
                           Model model) {

    if (order.equals("asc")) {
        model.addAttribute("employees",
                employeeService.sortByNameAsc());
    } else {
        model.addAttribute("employees",
                employeeService.sortByNameDesc());
    }

    model.addAttribute("totalSalary",
            employeeService.getTotalSalary());

    model.addAttribute("totalEmployees",
            employeeService.getTotalEmployees());

    model.addAttribute("totalDepartments",
            employeeService.getTotalDepartments());

    model.addAttribute("averageSalary",
            employeeService.getAverageSalary());
    model.addAttribute("departments",
        employeeService.getAllDepartments());

    return "index";
}
}