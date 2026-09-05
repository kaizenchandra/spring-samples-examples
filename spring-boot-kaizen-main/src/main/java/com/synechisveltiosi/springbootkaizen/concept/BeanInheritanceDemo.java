package com.synechisveltiosi.springbootkaizen.concept;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * Demonstrates Spring Bean Inheritance where child beans inherit and can override properties from parent beans.
 * This pattern is useful for reducing configuration duplication and maintaining consistency across related beans.
 */
@Component
public class BeanInheritanceDemo {

    abstract static class BaseEmployee {
        protected String department;
        protected double baseSalary;

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public double getBaseSalary() {
            return baseSalary;
        }

        public void setBaseSalary(double baseSalary) {
            this.baseSalary = baseSalary;
        }
    }

    @Component
    static class Manager extends BaseEmployee {
        private double bonus;

        public Manager() {
            this.department = "Management";
            this.baseSalary = 80000.0;
            this.bonus = 10000.0;
        }

        public double calculateTotalSalary() {
            return baseSalary + bonus;
        }
    }

    @Component
    static class Developer extends BaseEmployee {
        private String programmingLanguage;

        public Developer() {
            this.department = "Engineering";
            this.baseSalary = 70000.0;
            this.programmingLanguage = "Java";
        }

        public String getProgrammingLanguage() {
            return programmingLanguage;
        }
    }

    @Component
    static class EmployeeService {
        private final Manager manager;
        private final Developer developer;

        public EmployeeService(Manager manager, Developer developer) {
            this.manager = manager;
            this.developer = developer;
        }

        @PostConstruct
        public void init() {
            printEmployeeDetails();
        }

        public void printEmployeeDetails() {
            System.out.println("Manager Details:");
            System.out.println("Department: " + manager.getDepartment());
            System.out.println("Total Salary: " + manager.calculateTotalSalary());

            System.out.println("\nDeveloper Details:");
            System.out.println("Department: " + developer.getDepartment());
            System.out.println("Base Salary: " + developer.getBaseSalary());
            System.out.println("Programming Language: " + developer.getProgrammingLanguage());
        }
    }
}
