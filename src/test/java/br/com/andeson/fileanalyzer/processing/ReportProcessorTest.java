package br.com.andeson.fileanalyzer.processing;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;
import br.com.andeson.fileanalyzer.exceptions.FileProcessingException;
import br.com.andeson.fileanalyzer.model.Report;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ReportProcessorTest {

    private static List<String> reportLines;
    private static ReportProcessor reportProcessor;

    public ReportProcessorTest() {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        reportProcessor = new ReportProcessor();
        reportLines = new ArrayList<>();
        reportLines.add("001ç1234567891234çPedroç50000");
        reportLines.add("001ç3245678865434çPauloç40000.99");
        reportLines.add("002ç2345675434544345çJose da SilvaçRural");
        reportLines.add("002ç2345675433444345çEduardo PereiraçRural");
        reportLines.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
        reportLines.add("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");
    }

    @Before
    public void setUp() throws Exception {
        reportLines.forEach(line -> {
            try {
                reportProcessor.process(line);
            } catch (ConvertStringToArrayException | FileProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        reportLines.clear();
    }

    @Test
    public void clientsMustHaveBeenCreated() {
        assertEquals(reportProcessor.getClientsNumber().longValue(), 2);
    }

    @Test
    public void salesmenMustHasBeenCreated() {
        assertEquals(reportProcessor.getSalesmanNumber().longValue(), 2);
    }

    @Test
    public void salesMustHasBeenCreated() {
        assertEquals(reportProcessor.getSales().size(), 2);
    }

    @Test
    public void testMostExpensiveSaleIdResult() {
        assertEquals(reportProcessor.getMostExpensiveSaleId().longValue(), 10L);
    }

    @Test
    public void testWorstSalesmanResult() {
        assertEquals(reportProcessor.getWorstSalesmanName(), "Paulo");
    }

    @Test
    public void testReportResult() {
        var report = new Report(2L, 2L, 10L, "Paulo").toString();
        assertEquals(reportProcessor.getReportData(), report);
    }

    @Test
    public void mustHaveThrowExceptionWhenArrayLengthFileLineInvalid() {
        var line = "001ç1234567891234çPedroç";
        var exception = assertThrows(ConvertStringToArrayException.class, () -> {
            reportProcessor.process(line);
        });
        assertEquals(exception.getMessage(), "Array length is invalid");
    }

    @Test
    public void mustHaveThrowExceptionWhenCodeFieldInvalid() {
        var line = "004ç1234567891234çPedroç50000";
        var exception = assertThrows(NoSuchElementException.class, () -> {
            reportProcessor.process(line);
        });
        assertEquals(exception.getMessage(), "No value present");
    }

}
