package br.com.andeson.fileanalyzer.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Salesman extends BaseModel {

    private String cpf;
    private String name;
    private BigDecimal salary;

    public Salesman(String cpf, String name, BigDecimal salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Salesman{" +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salesman salesman = (Salesman) o;
        return Objects.equals(name, salesman.name) &&
                Objects.equals(cpf, salesman.cpf) &&
                Objects.equals(salary, salesman.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf, salary);
    }
}
