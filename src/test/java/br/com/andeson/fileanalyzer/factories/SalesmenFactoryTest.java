package br.com.andeson.fileanalyzer.factories;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;
import br.com.andeson.fileanalyzer.model.Salesman;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SalesmenFactoryTest {

    @Test
    public void clientShouldBeCreatedByLineTextFile() throws ConvertStringToArrayException {
        var salesman = new Salesman("3245678865434","Paulo", new BigDecimal("40000.99"));
        var lineSalesmen = "001ç3245678865434çPauloç40000.99";

        var salesmenFactory = new SalesmenFactory();
        var newSalesmen = (Salesman) salesmenFactory.create(lineSalesmen);
        assertEquals(salesman, newSalesmen);

    }
}
