package jooom.database.test;

import jooom.database.main.DatabaseInterface;
import jooom.database.main.dto.TableDto;
import jooom.database.main.exception.record.DuplicateKeyException;
import jooom.database.main.exception.table.WrongTableDataException;
import jooom.database.main.util.LogUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordTest {
    private DatabaseInterface databaseInterface;
    private int recordNum = 0;
    public RecordTest(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    public void testAllMethods(){
        insertRecordTest();
    }

    private void insertRecordTest(){
        String tableName = "student";
        //for (int i = 0 ; i<400 ; i++)
        insertTest(tableName);
        searchTest(tableName);

    }

    private void insertTest(String tableName) {
        insertNormalRecord(tableName);
        insertNormalRecordWithNull(tableName);
        insertDuplicateRecord(tableName);
    }


    private void searchTest(String tableName) {
        // 단일 검색
        searchRecordByNotNull(tableName, "20173156");
        searchRecordWithNull(tableName, "20175382");
        // 컬럼 겅색
        searchRecords(tableName);
    }

    private void insertNormalRecord(String tableName) {
        Map<String, String> columns = new HashMap<>();
        columns.put("id", "20173156");
        columns.put("tot_cred", "132");
        columns.put("dept_name", "industrial security");
        columns.put("name", "kim jun ki");

        String beforeTest = "insertNormalRecord - 입력 값";
        String afterTest = "insertNormalRecord - 출력 값";
        TableDto tableDto = databaseInterface.getTableData(tableName);
        LogUtil.showRecordData(beforeTest, tableName, columns, tableDto.getColumns());
        try {
            databaseInterface.insert(tableName, columns);
            List<Map<String,String>> ret= databaseInterface.searchColumns(tableName, tableDto.getColumns());
            LogUtil.showRecordData(afterTest, tableName, ret, tableDto.getColumns());
        } catch(WrongTableDataException | DuplicateKeyException e) {
            LogUtil.printTestTitle("insertNormalRecord", "테스트 실패");
            return;
        }
        LogUtil.printTestTitle("insertNormalRecord", "테스트 성공");
        recordNum++;
    }

    private void insertNormalRecordWithNull(String tableName) {
        Map<String, String> columns = new HashMap<>();
        columns.put("id", "20175382");
        columns.put("tot_cred", "132");
        columns.put("name", "JJJ");

        String beforeTest = "insertNormalRecordWithNull - 입력 값";
        String afterTest = "insertNormalRecordWithNull - 출력 값";
        TableDto tableDto = databaseInterface.getTableData(tableName);
        LogUtil.showRecordData(beforeTest, tableName, columns, tableDto.getColumns());
        try {
            databaseInterface.insert(tableName, columns);
            List<Map<String,String>> ret= databaseInterface.searchColumns(tableName, tableDto.getColumns());
            LogUtil.showRecordData(afterTest, tableName, ret, tableDto.getColumns());
        } catch(WrongTableDataException | DuplicateKeyException e) {
            LogUtil.printTestTitle("insertNormalRecordWithNull", "테스트 실패");
            return;
        }
        LogUtil.printTestTitle("insertNormalRecordWithNull", "테스트 성공");
        recordNum++;
    }

    private void insertDuplicateRecord(String tableName) {
        Map<String, String> columns = new HashMap<>();
        columns.put("id", "20173156");
        columns.put("tot_cred", "132");
        columns.put("dept_name", "industrial security");
        columns.put("name", "kim jun ki");

        String beforeTest = "insertDuplicateRecord - 입력 값";
        String afterTest = "insertDuplicateRecord - 출력 값";
        TableDto tableDto = databaseInterface.getTableData(tableName);
        LogUtil.showRecordData(beforeTest, tableName, columns, tableDto.getColumns());
        try {
            List<Map<String,String>> ret= databaseInterface.searchColumns(tableName, tableDto.getColumns());
            LogUtil.showRecordData("insertDuplicateRecord - 삽입 전", tableName, ret, tableDto.getColumns());
            databaseInterface.insert(tableName, columns);
        } catch(DuplicateKeyException e) {
            List<Map<String,String>> ret= databaseInterface.searchColumns(tableName, tableDto.getColumns());
            LogUtil.showRecordData(afterTest, tableName, ret, tableDto.getColumns());
            LogUtil.printTestTitle("insertDuplicateRecord", "테스트 성공");
            return;
        }
        LogUtil.printTestTitle("insertDuplicateRecord", "테스트 실패");
    }

    private void searchRecordByNotNull(String tableName, String searchKey) {
        Map<String,String> result = databaseInterface.search(tableName, searchKey);

        String testName = "searchRecordByNotNull";
        TableDto tableDto = databaseInterface.getTableData(tableName);
        LogUtil.showRecordData(testName, tableName, result, tableDto.getColumns());
        if (!result.isEmpty()) { LogUtil.printTestTitle("searchRecordByNotNull", "테스트 성공");
        } else {
            LogUtil.printTestTitle("searchRecordByNotNull", "테스트 실패");
        }
    }

    private void searchRecordWithNull(String tableName, String searchKey) {
        Map<String,String> result = databaseInterface.search(tableName, searchKey);

        String testName = "searchRecordWithNull";
        TableDto tableDto = databaseInterface.getTableData(tableName);
        LogUtil.showRecordData(testName, tableName, result, tableDto.getColumns());
        if (result.isEmpty()) {LogUtil.printTestTitle("searchRecordWithNull", "테스트 실패");
        } else {
            LogUtil.printTestTitle("searchRecordWithNull", "테스트 성공");
        }
    }


    private void searchRecords(String tableName) {
        String[] columns = new String[]{"id", "tot_cred"};
        List<Map<String,String>> ret = databaseInterface.searchColumns(tableName, columns);

        String testName = "searchRecordWithNull";
        TableDto tableDto = databaseInterface.getTableData(tableName);
        LogUtil.showRecordData(testName, tableName, ret, tableDto.getColumns());
        if(recordNum == ret.size()){
            LogUtil.printTestTitle("searchRecords", "테스트 성공");
        } else {
            LogUtil.printTestTitle("searchRecords", "테스트 실패");
        }
    }

}
