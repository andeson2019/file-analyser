package br.com.andeson.fileanalyzer.model;

public class Report{

    private Long salesmenNumber;
    private Long clientsNumber;
    private Long mostExpensiveSaleId;
    private String worstSalesman;

    public Report(Long salesmenNumber, Long clientsNumber, Long mostExpensiveSaleId, String worstSalesman) {
        this.salesmenNumber = salesmenNumber;
        this.clientsNumber = clientsNumber;
        this.mostExpensiveSaleId = mostExpensiveSaleId;
        this.worstSalesman = worstSalesman;
    }

    @Override
    public String toString() {
        return "Quantidade de clientes: " + clientsNumber + '\n' +
                "Quantidade de vendedores: " + salesmenNumber + '\n' +
                "Id venda mais cara: " + mostExpensiveSaleId + '\n' +
                "Pior vendedor: " + worstSalesman + '\n';
    }
}
