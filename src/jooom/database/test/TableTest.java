package jooom.database.test;

import jooom.database.main.DatabaseInterface;
import jooom.database.main.dto.TableDto;
import jooom.database.main.exception.table.TableAlreadyExistsException;
import jooom.database.main.util.LogUtil;

import java.io.IOException;

public class TableTest {
    private DatabaseInterface databaseInterface;

    private static final String tableName = "student";
    private static final String[] columns = new String[]{"dept_name", "name", "id", "tot_cred"};
    private static final int[] size = new int[]{0,0,11,3};
    private static final int primaryKeyIndex = 2;
    public TableTest(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    public void testAllMethods(){
        createTableTest();
    }
    public void clearTestTable() {
        databaseInterface.dropTable(tableName);
    }
    public void createTableTest(){
        createNormalTable();
        createDuplicateTable();

    }

    private void createDuplicateTable() {
        TableDto dto = new TableDto(tableName, columns, size, primaryKeyIndex, null);
        try {
            databaseInterface.createTable(dto);
        } catch(TableAlreadyExistsException e){
            LogUtil.printTestTitle("createDuplicateTable", "테스트 성공");
        } catch(IOException e){
            LogUtil.printTestTitle("createDuplicateTable", "테스트 실패");
        }
    }

    private void createNormalTable() {
        TableDto dto = new TableDto(tableName, columns, size, primaryKeyIndex, null);
        try {
            databaseInterface.createTable(dto);
        } catch(Exception e){
            LogUtil.printTestTitle("createNormalTable", "테스트 실패");
            return;
        }
        LogUtil.printTestTitle("createNormalTable", "테스트 성공");
    }
}
