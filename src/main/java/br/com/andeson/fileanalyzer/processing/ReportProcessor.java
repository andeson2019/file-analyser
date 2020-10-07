package br.com.andeson.fileanalyzer.processing;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;
import br.com.andeson.fileanalyzer.exceptions.FileProcessingException;
import br.com.andeson.fileanalyzer.factories.SaleFactory;
import br.com.andeson.fileanalyzer.factories.ClientFactory;
import br.com.andeson.fileanalyzer.factories.SalesmenFactory;
import br.com.andeson.fileanalyzer.model.*;
import br.com.andeson.fileanalyzer.utils.DataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class ReportProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ReportProcessor.class);

    private final Set<BaseModel> reportItems = new HashSet<>();

    public ReportProcessor() {
    }

    public void process(String line) throws FileProcessingException, ConvertStringToArrayException {

        try {
            var code = line.substring(0, 3);
            DataType dataType = DataType.getValue(code);
            switch (dataType) {
                case CLIENT:
                    buildClient(line);
                    break;
                case SALESMAN:
                    buildSalesmen(line);
                    break;
                case SALE:
                    buildSale(line);
                    break;
                default:
                    throw new FileProcessingException("[Report Processor] Invalid data type.");
            }
        } catch (FileProcessingException e) {
            logger.error("[Report Processor] Error processing report: {}", e.getMessage());
            throw new FileProcessingException("[Report Processor] Invalid data type.");
        }
    }

    private void buildClient(String line) throws ConvertStringToArrayException {
        var client = new ClientFactory().create(line);
        this.reportItems.add(client);
    }

    private void buildSalesmen(String line) throws ConvertStringToArrayException {
        var salesman = new SalesmenFactory().create(line);
        this.reportItems.add(salesman);
    }

    private void buildSale(String line) throws ConvertStringToArrayException {
        var sale = new SaleFactory().create(line);
        this.reportItems.add(sale);
    }

    public String getReportData() {
        var mostExpensiveSaleId = getMostExpensiveSaleId();
        var worstSalesmanName = getWorstSalesmanName();
        var clientsNumber = getClientsNumber();
        var salesmanNumber = getSalesmanNumber();

        return new Report(salesmanNumber, clientsNumber,
                mostExpensiveSaleId, worstSalesmanName).toString();
    }

    public Long getMostExpensiveSaleId() {
        Double mostExpensivePrice = 0d;
        Long mostExpensiveSaleId = 0L;
        List<Sale> sales = getSales();
        for (var sale : sales) {
            var totalSaleValue = getTotalSaleValue(sale);
            if (mostExpensivePrice.compareTo(totalSaleValue) <= 0) {
                mostExpensiveSaleId = sale.getSaleId();
                mostExpensivePrice = totalSaleValue;
            }
        }
        return mostExpensiveSaleId;
    }

    public String getWorstSalesmanName() {
        List<Sale> sales = getSales();
        Double worstSalePrice = getTotalSaleValue(sales.get(0));
        Sale worstSale = sales.get(0);
        for(Sale sale : sales) {
            if (worstSalePrice.compareTo(getTotalSaleValue(sale)) >= 0) {
                worstSalePrice = getTotalSaleValue(sale);
                worstSale = sale;
            }
        }
        return worstSale.getSalesmanName();
    }

    public Long getClientsNumber() {
        return reportItems.stream()
                .filter(item -> item instanceof Client)
                .count();
    }

    public Long getSalesmanNumber() {
        return reportItems.stream()
                .filter(item -> item instanceof Salesman)
                .count();
    }

    public List<Sale> getSales(){
        return reportItems.stream()
                .filter(item -> item instanceof Sale)
                .map(item -> (Sale) item)
                .collect(Collectors.toList());
    }

    private Double getTotalSaleValue(Sale sale){
          return sale.getSaleItems().stream()
                    .mapToDouble(saleItem -> (saleItem.getPrice() * saleItem.getQuantity()))
                    .sum();
    }

}
