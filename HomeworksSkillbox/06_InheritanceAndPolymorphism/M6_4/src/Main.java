import Google.Company;
import Google.Employe;
import Google.Manager;
import Google.Operator;
import Google.TopManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    static int min = 5000000;
    static int max = 10000000;
    public static int random(int minimum, int maximum) {
        return ThreadLocalRandom.current().nextInt(minimum, maximum); }
    //google.hire(new Manager("ss", income));
    public static int income = random(min, max);
    public static void main(String[] args) {
        Company google = new Company(income);
        List<Employe> newEmployees = new ArrayList<>();
        //1)Создайте и наймите в компанию:180 операторов Operator, 80 менеджеров по продажам Manager, 10 топ менеджеров TopManager
        for (int a = 0; a < 180; a++){
            newEmployees.add(new Operator("ss", income));
            google.hire(new Operator("ss", income));
        }
        for (int a = 0; a < 80; a++){
            newEmployees.add(new Manager("ss", income));
            google.hire(new Manager("ss", income));
        }
        for (int a = 0; a < 10; a++){
            newEmployees.add(new TopManager("ss", income));
            google.hire(new TopManager("ss", income));
        }

        //2)Распечатайте список состоящий из 10-15 самых высоких зарплат в компании
        System.out.println(google.getTopSalaryStaff(15).toString());

        //3)Распечатайте список из 30 самых низких зарплат в компании
        System.out.println(google.getLowestSalaryStaff(30).toString());

        //4)Увольте 50% сотрудников
        google.fire(50);
        //5)Распечатайте список из 10-15 самых высоких зарплат в компании
        System.out.println(google.getTopSalaryStaff(15).toString());
        //6)Распечатайте список из 30 самых низких зарплат в компании
        System.out.println(google.getLowestSalaryStaff(30).toString());



    }

}
