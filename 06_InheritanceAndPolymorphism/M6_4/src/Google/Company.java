package Google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Company {
    private int salary;
    private String name;

    public String getName() {
        return name;
    }

    private int income;

    public Company(int income) {
        this.income = income;
    }

    public int getIncome() {
        return income;
    }

    private List<Employe> employees = new ArrayList<>();

    public void hire(Employe employe) {
        employees.add(employe);
    }

    public void hireAll(List<Employe> employ) {
        employees.addAll(employ);
    }

    public void fire(int procent) {
        if (procent < 100) {
            double oneProcent = employees.size() / 100.0;
            double amount = oneProcent * procent;
            int amoutInt = (int) amount;
            for (int a = 0; a < amoutInt; a++) {
                employees.remove(a);
            }
        } else {
            System.out.println("Error");
        }
    }

    public List<Employe> getLowestSalaryStaff(int count) {
        Collections.sort(employees, Comparator.comparing(Employe::getMonthSalary));
        return employees.subList(0, count);
    }

    public List<Employe> getTopSalaryStaff(int count) {
        Collections.sort(employees, Comparator.comparing(Employe::getMonthSalary).reversed());
        return employees.subList(0, count);
    }


}





