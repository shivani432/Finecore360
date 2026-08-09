package com.fincore.fincore360.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fincore.fincore360.entity.Department;
import com.fincore.fincore360.entity.Designation;
import com.fincore.fincore360.entity.Employee;
import com.fincore.fincore360.service.DepartmentService;
import com.fincore.fincore360.service.DesignationService;
import com.fincore.fincore360.service.EmployeeService;

import jakarta.validation.Valid;
// Add Designation form मध्ये Designation object वापरण्यासाठी

@Controller
public class HomeController {

@Autowired
private EmployeeService employeeService;
@Autowired
private DepartmentService departmentService;
@Autowired
private DesignationService designationService;
// Designation चे save, update, delete, search इत्यादी काम Service कडून करून घेण्यासाठी

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

    model.addAttribute("departments",
            departmentService.getAllDepartments());

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

    model.addAttribute("departments",
        departmentService.getAllDepartments());

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

// =====================================
// Marathi : Department List Page
// English : Open Department Page
// =====================================
@GetMapping("/departments")
public String departmentPage(Model model) {

    model.addAttribute("departments",
            departmentService.getAllDepartments());

    return "department";
}

        // =====================================
// Marathi : Open Add Department Page
// English : Open Add Department Form
// =====================================
@GetMapping("/departments/add")
public String addDepartment(Model model) {

    model.addAttribute("department", new Department());

    return "add-department";
}

        // =====================================
// Marathi : Save Department
// English : Save Department
// =====================================
// =====================================
// Marathi : Save / Update Department
// English : Save or Update Department
// =====================================
@PostMapping("/departments/save")
public String saveDepartment(@Valid Department department,
                             BindingResult result) {

    if (result.hasErrors()) {
        return "add-department";
    }

    departmentService.saveDepartment(department);

    return "redirect:/departments";
}

        // =====================================
// Marathi : Delete Department
// English : Delete Department
// =====================================
@GetMapping("/departments/delete/{id}")
public String deleteDepartment(@PathVariable Long id,
                               Model model) {

    try {

        departmentService.deleteDepartment(id);

    } catch (RuntimeException e) {

        model.addAttribute("errorMessage", e.getMessage());

        model.addAttribute("departments",
                departmentService.getAllDepartments());

        return "department";
    }

    return "redirect:/departments";
}

        
      // =====================================
// Marathi : Search Department
// English : Search Department
// =====================================
@GetMapping("/departments/search")
public String searchDepartment(
        @RequestParam("departmentName") String departmentName,
        Model model) {

    if (departmentName == null || departmentName.trim().isEmpty()) {

        model.addAttribute("departments",
                departmentService.getAllDepartments());

    } else {

        model.addAttribute("departments",
                departmentService.searchDepartment(departmentName));
    }

    return "department";
}  

        // =====================================
// Marathi : Open Edit Department Page
// English : Open Edit Department Form
// =====================================
@GetMapping("/departments/edit/{id}")
public String editDepartment(@PathVariable Long id,
                             Model model) {

    model.addAttribute("department",
            departmentService.getDepartmentById(id));

    return "add-department";
}

// =====================================
// Marathi : Designation List Page उघडणे
// English : Open Designation List Page
// =====================================
@GetMapping("/designations")
// Browser मध्ये /designations URL open झाल्यावर हा method चालतो
public String designationPage(Model model) {

    // Database मधून सर्व Designations घेऊन HTML page ला पाठवतो
    model.addAttribute("designations",
            designationService.getAllDesignations());

    // designation.html हा Thymeleaf page browser मध्ये दाखवतो
    return "designation";
}

// =====================================
// Marathi : Add Designation Page उघडणे
// English : Open Add Designation Form
// =====================================
@GetMapping("/designations/add")
// Browser मध्ये /designations/add open झाल्यावर हा method चालतो
public String addDesignation(Model model) {

    // नवीन Designation साठी रिकामा object तयार करतो
    model.addAttribute("designation", new Designation());

    // Add Designation चा Thymeleaf page दाखवतो
    return "add-designation";
}

// =====================================
// Marathi : Designation Save करणे
// English : Save Designation
// =====================================
@PostMapping("/designations/save")
// Form मधून आलेली POST request handle करण्यासाठी
public String saveDesignation(
        @Valid Designation designation,
        BindingResult result) {

    // Designation मध्ये validation error असेल तर
    // पुन्हा Add Designation form दाखवतो
    if (result.hasErrors()) {
        return "add-designation";
    }

    // Validation successful असल्यास
    // Designation database मध्ये save करतो
    designationService.saveDesignation(designation);

    // Save झाल्यानंतर Designation List page वर redirect करतो
    return "redirect:/designations";
}
// =====================================
// Marathi : Edit Designation Page उघडणे
// English : Open Edit Designation Form
// =====================================
@GetMapping("/designations/edit/{id}")
// URL मधून Designation ची ID घेऊन Edit page उघडतो
public String editDesignation(
        @PathVariable Integer id,
        Model model) {

    // ID वापरून database मधून existing Designation मिळवतो
    model.addAttribute("designation",
            designationService.getDesignationById(id));

    // Existing data भरलेला Add/Edit form दाखवतो
    return "add-designation";
}

// =====================================
// Marathi : Designation Delete करणे
// English : Delete Designation
// =====================================
@GetMapping("/designations/delete/{id}")
// Delete button वर click केल्यावर हा URL चालतो
public String deleteDesignation(@PathVariable Integer id) {

    // URL मधून आलेल्या ID चा Designation delete करण्याचे
    // actual काम Service ला देतो
    designationService.deleteDesignation(id);

    // Delete झाल्यानंतर पुन्हा Designation List page वर जातो
    return "redirect:/designations";
}

// =====================================
// Marathi : Designation Search करणे
// English : Search Designation
// =====================================
@GetMapping("/designations/search")
// Search form मधून आलेली GET request handle करण्यासाठी
public String searchDesignation(
        @RequestParam("designationName") String designationName,
        Model model) {

    // Search box रिकामा असेल तर सर्व Designations दाखवतो
    if (designationName == null || designationName.trim().isEmpty()) {

        model.addAttribute("designations",
                designationService.getAllDesignations());

    } else {

        // Search text दिला असेल तर matching Designations मिळवतो
        model.addAttribute("designations",
                designationService.searchDesignation(designationName));
    }

    // Search result Designation List page वर दाखवतो
    return "designation";
}
}