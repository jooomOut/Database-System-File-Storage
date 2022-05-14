package jooom.database.main.service;

import jooom.database.main.dto.TableDto;
import jooom.database.main.exception.TableAlreadyExistsException;

import java.io.IOException;

public interface TableManager{
    static String DICTIONARY_PATH = "data_dictionary";
    static String RECORD_PATH = "record";

    public void createTable(TableDto tableDto) throws IOException, TableAlreadyExistsException;
}