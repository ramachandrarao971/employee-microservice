package com.employee.model;

public class Payroll {
    private String employeeId;

    private String registeredBank;

    private String bankAccountNumber;

    private Double monthlySalary;


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getRegisteredBank() {
        return registeredBank;
    }

    public void setRegisteredBank(String registeredBank) {
        this.registeredBank = registeredBank;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
}
