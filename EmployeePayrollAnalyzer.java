import java.util.Scanner;
 
 
class Employee {

   private String id;

   private String name;

   private double hoursWorked;

   private double hourlyRate;

   private static int employeeCount = 0;

   public Employee(String id, String name, double hoursWorked, double hourlyRate) {

       if (hoursWorked < 0)

           hoursWorked = 0;

       if (hourlyRate < 0)

           hourlyRate = 0;

this.id = id;

       this.name = name;

       this.hoursWorked = hoursWorked;

       this.hourlyRate = hourlyRate;

       employeeCount++;

   }

   public double getGrossPay() {

       if (hoursWorked <= 40) {

           return hoursWorked * hourlyRate;

       } else {

           double regularPay = 40 * hourlyRate;

           double overtimePay = (hoursWorked - 40) * hourlyRate * 1.5;

           return regularPay + overtimePay;

       }

   }


   public double getTax() {

       double gross = getGrossPay();

       if (gross <= 10000)

           return gross * 0.05;

       else if (gross <= 20000)

           return gross * 0.10;

       else

           return gross * 0.15;

   }

   public double getNetPay() {

       return getGrossPay() - getTax();

   }

   public String getName() {

       return name;

   }

   public static int getEmployeeCount() {

       return employeeCount;

   }


   public void displayPayroll() {

       System.out.printf("\nID: %s\n", id);

       System.out.println("Name: " + name);

       System.out.printf("Gross Pay: %.2f\n", getGrossPay());

       System.out.printf("Tax: %.2f\n", getTax());

       System.out.printf("Net Pay: %.2f\n", getNetPay());

   }

}
 
 
public class EmployeePayrollAnalyzer {

   public static void main(String[] args) {

       Scanner sc = new Scanner(System.in);

       int n;

       do {

           System.out.print("Enter number of employees (1-10): ");

           n = sc.nextInt();

       } while (n < 1 || n > 10);

       Employee[] employees = new Employee[n];

       for (int i = 0; i < n; i++) {

           System.out.println("\nEmployee " + (i + 1));

           System.out.print("ID: ");

           String id = sc.next();

           sc.nextLine();

           System.out.print("Full Name: ");

           String name = sc.nextLine();

           System.out.print("Hours Worked: ");

           double hours = sc.nextDouble();

           System.out.print("Hourly Rate: ");

           double rate = sc.nextDouble();

           employees[i] = new Employee(id, name, hours, rate);

       }


       double totalNet = 0;

       double highestNet = employees[0].getNetPay();

       String highestEmployee = employees[0].getName();

       System.out.println("\n==== PAYROLL SUMMARY ====");

       for (Employee emp : employees) {

           emp.displayPayroll();

           totalNet += emp.getNetPay();

           if (emp.getNetPay() > highestNet) {

               highestNet = emp.getNetPay();

               highestEmployee = emp.getName();

           }

       }


       double averageNet = totalNet / n;

       System.out.println("\n==== OVERALL RESULTS ====");

       System.out.println("Highest Net Pay Employee: " + highestEmployee);

       System.out.printf("Highest Net Pay: %.2f\n", highestNet);

       System.out.printf("Average Net Pay: %.2f\n", averageNet);

       System.out.println("Employee Count: " + Employee.getEmployeeCount());

       sc.close();

   }

}
 
