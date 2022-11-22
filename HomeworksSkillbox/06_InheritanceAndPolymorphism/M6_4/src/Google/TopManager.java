package Google;

import java.util.concurrent.ThreadLocalRandom;

public class TopManager extends AbstractEmployee implements Employe{
    private String name;
    private int salary;

    private final int TEN_MILLION = 10000000;
    private int maximumTopManager;
    private int bonus;
    public TopManager(String name, int companyIncome){
        name = "Топ Менеджер:";
        this.name = name;
        getSalaryTopManager(companyIncome);
    }

    private void getSalaryTopManager(int salary){
        maximumTopManager = salary / HUNGRED_PROCENT * FIVE_PROCENT / HUNGRED_PROCENT * 20;
        if (salary > TEN_MILLION){
            int topManagerSalary = getRandomSalary(0, maximumTopManager);
            this.salary = topManagerSalary + (topManagerSalary / HUNGRED_PROCENT * 150);
        } else {
            this.salary = getRandomSalary(maximumTopManager/10, maximumTopManager);
        }
    }
    @Override
    public String toString() {
        return name + salary+"р";
    }

    @Override
    public int getMonthSalary() {
        return salary;
    }

    @Override
    public String getNamePosition() {
        return name;
    }

}
