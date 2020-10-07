package br.com.andeson.fileanalyzer.factories;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;
import br.com.andeson.fileanalyzer.model.BaseModel;

public interface IModelFactory {

   BaseModel create(String data) throws ConvertStringToArrayException;

}
