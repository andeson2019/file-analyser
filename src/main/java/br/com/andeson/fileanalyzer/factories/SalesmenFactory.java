package br.com.andeson.fileanalyzer.factories;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;
import br.com.andeson.fileanalyzer.model.BaseModel;
import br.com.andeson.fileanalyzer.model.Salesman;
import br.com.andeson.fileanalyzer.utils.ArraysUtil;

import java.math.BigDecimal;

public class SalesmenFactory implements IModelFactory {
    @Override
    public BaseModel create(String data) throws ConvertStringToArrayException {
        var array = ArraysUtil.stringToArray(data, "ç");
        return new Salesman(array[1],
                array[2],
                new BigDecimal(array[3]));
    }
}
