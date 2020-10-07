package br.com.andeson.fileanalyzer.factories;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;
import br.com.andeson.fileanalyzer.model.Sale;
import br.com.andeson.fileanalyzer.model.SaleItem;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SaleFactoryTest {

    private static List<SaleItem> items;

    @Before
    public void setUp() throws Exception {
        items = new ArrayList<>();
        items.add(new SaleItem(1L, 10, 100D));
        items.add(new SaleItem(2L, 30, 2.50D));
        items.add(new SaleItem(3L, 40, 3.10D));
    }

    @Test
    public void saleShouldBeCreatedByLineTextFile() throws ConvertStringToArrayException {
        var sale = new Sale(Long.parseLong("003"), Long.parseLong("10"),
                items, "Pedro");

        var lineSale = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";

        var saleFactory = new SaleFactory();
        var newSale = (Sale) saleFactory.create(lineSale);
        assertEquals(sale, newSale);

    }
}

