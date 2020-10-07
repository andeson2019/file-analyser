package br.com.andeson.fileanalyzer.factories;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;
import br.com.andeson.fileanalyzer.model.SaleItem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SaleItemFactoryTest {

    @Test
    public void saleItemShouldBeCreatedByLineTextFile() throws ConvertStringToArrayException {
        var saleItem = new SaleItem(1L, 10,100D);
        var lineSaleItem = "1-10-100";

        var saleItemFactory = new SaleItemFactory();
        var newSale = (SaleItem) saleItemFactory.create(lineSaleItem);
        assertEquals(saleItem, newSale);

    }
}
