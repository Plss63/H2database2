package h2.test;

import java.sql.SQLException;
import java.text.ParseException;

import h2.EnterModel;

import org.junit.Test;
import org.mockito.Mockito;

public class TestMethodMenu {

    EnterModel mock;

    public void proc(int i) throws ParseException, SQLException {
        mock = Mockito.mock(EnterModel.class);
        if (i == 1)
            mock.add();
        if (i == 2)
            mock.delete();
        if (i == 3)
            mock.search();
        if (i == 4)
            mock.showall();
        if (i == 5)
            mock.update();

    }

    @Test
    public void test() throws ParseException, SQLException {

        proc(1);
        Mockito.verify(mock).add();
        proc(2);
        Mockito.verify(mock).delete();
        proc(3);
        Mockito.verify(mock).search();
        proc(4);
        Mockito.verify(mock).showall();
        proc(5);
        Mockito.verify(mock).update();
        

    }

}
