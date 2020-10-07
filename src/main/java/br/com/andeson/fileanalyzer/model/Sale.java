package br.com.andeson.fileanalyzer.model;

import java.util.List;
import java.util.Objects;

public class Sale extends BaseModel{

    private Long id;
    private Long saleId;
    private List<SaleItem> saleItems;
    private String salesmanName;

    public Sale(Long id, Long saleId, List<SaleItem> saleItems, String salesmanName) {
        this.id = id;
        this.saleId = saleId;
        this.saleItems = saleItems;
        this.salesmanName = salesmanName;
    }

    public Long getId() {
        return id;
    }

    public Long getSaleId() {
        return saleId;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public Double getTotalSaleValue() {
        return saleItems.stream()
                .mapToDouble(SaleItem::getPrice)
                .sum();
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id='" + id + '\'' +
                ", saleId='" + saleId + '\'' +
                ", salesmanName='" + salesmanName + '\'' +
                ", items=" + saleItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id) &&
                Objects.equals(saleId, sale.saleId) &&
                Objects.equals(salesmanName, sale.salesmanName) &&
                Objects.equals(saleItems, sale.saleItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saleId, salesmanName, saleItems);
    }
}
