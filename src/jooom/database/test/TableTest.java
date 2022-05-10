package jooom.database.test;

import jooom.database.DatabaseInterface;
import jooom.database.dto.TableDto;
import jooom.database.exception.TableAlreadyExistsException;

import java.io.IOException;

public class TableTest {
    private DatabaseInterface databaseInterface;

    public TableTest(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    public void testAllMethods(){
        createTableTest();
    }
    public void createTableTest(){
        createNormalTable();
        createDuplicateTable();

    }

    private void createDuplicateTable() {
        String tableName = "createTableTest";
        String[] columns = new String[]{"A", "B", "C", "D"};
        int[] size = new int[]{3,0,3,4};
        int primaryKeyIndex = 1;
        TableDto dto = new TableDto(tableName, columns, size, primaryKeyIndex, null);
        try {
            databaseInterface.createTable(dto);
        } catch(TableAlreadyExistsException e){
            System.out.println("createDuplicateTable - 테스트 통과");
        } catch(IOException e){
            System.out.println("createDuplicateTable - 테스트 실패");
        }
    }

    private void createNormalTable() {
        String tableName = "createTableTest";
        String[] columns = new String[]{"FFF", "AWE", "C", "D"};
        int[] size = new int[]{3,0,3,4};
        int primaryKeyIndex = 2;
        TableDto dto = new TableDto(tableName, columns, size, primaryKeyIndex, null);
        try {
            databaseInterface.createTable(dto);
        } catch(Exception e){
            System.out.println("createNormalTable - 테스트 실패");
            return;
        }
        System.out.println("createNormalTable - 테스트 성공");
    }
}