package groupproject2025;
// LiveExample 10.1
import java.util.Scanner;

public class TestLoanClass {
  public static void main(String[] args) {
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    // Enter yearly interest rate
    System.out.print(
      "Enter annual interest rate, for example, 8.25: ");
    double annualInterestRate = input.nextDouble();

    // Enter number of years
    System.out.print("Enter number of years as an integer: ");
    int numberOfYears = input.nextInt();

    // Enter loan amount
    System.out.print("Enter loan amount, for example, 120000.95: ");
    double loanAmount =  input.nextDouble();
    
    //11/9
    // Enter extra payment amount
    System.out.print("Enter extra payment amount, for example, 1000.00: ");
    double extraPaymentAmount =  input.nextDouble();

    // Create Loan object
    Loan loan =
      new Loan(annualInterestRate, numberOfYears, loanAmount);

    // Display loan date, monthly payment, and total payment, and total interest
    System.out.printf("The loan was created on %s%n" +
      "The monthly payment is %.2f%nThe total payment is %.2f%nThe total interest is %.2f%n%n",
      loan.getLoanDate().toString(),
      loan.getMonthlyPayment(), 
      loan.getTotalPayment(),
      loan.getTotalInterest());
    
    extraPayFirstMonth(loan, extraPaymentAmount);
    System.out.println();
    extraPayEveryMonth(loan, extraPaymentAmount);
  }
  
  /** Extra payment first month only */
  public static void extraPayFirstMonth(Loan l, double extraPay) {
	  System.out.printf("Extra payment only first month $%.2f%n", extraPay);
	  double myLoanAmount =l.getLoanAmount();
	  double myMonthlyInterestRate = l.getAnnualInterestRate()/1200;
	  double myMonthlyPayment = l.getMonthlyPayment();
	  int month= 0;
	  double interest = 0.0;
	  double principal = 0.0;
	  double totalInterest = 0.0;

	  // Month 1: Apply extra payment
	  month++;
	  interest = myLoanAmount * myMonthlyInterestRate;
	  totalInterest += interest;
	  principal = myMonthlyPayment - interest;
	  
	  // Principal + Extra Payment
	  myLoanAmount -= (principal + extraPay);
	  
	  // Remaining months: Standard monthly payment
	  while (myLoanAmount >= myMonthlyPayment) {
		  // to do A1 same as A2
		  month++;
		  interest = myLoanAmount * myMonthlyInterestRate;
		  totalInterest += interest;
		  principal = myMonthlyPayment - interest;
		  myLoanAmount -= principal;
	  }
	  
	  // Final payment (less than a full monthly payment)
	  if (myLoanAmount > 0) { // Condition changed from < myMonthlyPayment to > 0 to handle final payment
		  // to do B1 same as B2
		  month++;
		  interest = myLoanAmount * myMonthlyInterestRate;
		  totalInterest += interest;
		  // No principal calculation needed, as the final payment covers the remaining balance + interest
	  }
	  
	  double interestSaved = l.getTotalInterest() - totalInterest;
	  /*
	  System.out.println("total interest " + totalInterest);
	  System.out.println("interest saved " + interestSaved);
	  System.out.println("total month " + month);
	  */
	  System.out.printf("Total interest $%.2f%nTotal interest saved $%.2f%nTotal months %d %n",
			  totalInterest, interestSaved, month);	  
  }

  /** Extra payment every month */
  public static void extraPayEveryMonth(Loan l, double extraPay) {
	  System.out.printf("Extra payment every month $%.2f%n", extraPay);
	  double myLoanAmount =l.getLoanAmount();
	  double myMonthlyInterestRate = l.getAnnualInterestRate()/1200;
	  double myMonthlyPayment = l.getMonthlyPayment();
	  int month= 0;
	  double interest = 0.0;
	  double principal = 0.0;
	  double totalInterest = 0.0;
	  
	  // While loop: Payments with extraPay continue as long as the remaining balance
	  // is enough to cover the full payment (monthly + extra)
	  while (myLoanAmount >= (myMonthlyPayment + extraPay)) {
		  // to do A2 same as A1
		  // calculate the new month's interest based on myLoanAmount and myMonthlyInterestRate
		  interest = myLoanAmount * myMonthlyInterestRate;
		  // calculate the principal paid this month from myMonthlyPayment minus the new month's interest
		  principal = myMonthlyPayment - interest;
		  // calculate the new myLoanAmount after minus this month's principal, then minus extra payment
		  myLoanAmount -= (principal + extraPay);
		  // add the interest paid this month to totalInterest
		  totalInterest += interest;
		  // increment the month count
		  month++;
	  }
	  
	  // While loop: Payments continue with ONLY the standard monthly payment
	  while (myLoanAmount >= myMonthlyPayment) {
		  // to do B2 same as B1
		  // calculate the new month's interest based on myLoanAmount and myMonthlyInterestRate
		  interest = myLoanAmount * myMonthlyInterestRate;
		  // calculate the principal paid this month from myMonthlyPayment minus the new month's interest
		  principal = myMonthlyPayment - interest;
		  // calculate the new myLoanAmount after minus this month's principal
		  myLoanAmount -= principal;
		  // add the interest paid this month to totalInterest
		  totalInterest += interest;
		  // increment the month count
		  month++;
	  }
	  
	  // Final payment (less than a full monthly payment)
	  if (myLoanAmount > 0) { // Condition changed from < myMonthlyPayment to > 0 to handle final payment
		  // calculate the new month's interest based on myLoanAmount and myMonthlyInterestRate
		  interest = myLoanAmount * myMonthlyInterestRate;
		  // add the interest paid this month to totalInterest
		  totalInterest += interest;
		  // increment the month count
		  month++;
		  // The remaining loan amount is covered by a final payment of myLoanAmount + interest
	  }
	  
	  double interestSaved = l.getTotalInterest() - totalInterest;
	  System.out.printf("Total interest $%.2f%nTotal interest saved $%.2f%nTotal months %d %n",
			  totalInterest, interestSaved, month);	  
  }
}