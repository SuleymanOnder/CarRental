import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Suleyman on 2020-10-17.
 */
public class BaseFunctions {
    protected Connection connection;

    public BaseFunctions(Connection con) {
        this.connection = con;
    }

    protected void displayResultSet (ResultSet rs) throws SQLException {
        if (rs != null) {
            ResultSetMetaData md = rs.getMetaData ( );
            int ncols = md.getColumnCount ( );
            int nrows = 0;
            int[ ] width = new int[ncols + 1];       // kiralik olanlari diziye at
            StringBuilder b = new StringBuilder ( ); // Alanlari ayirma

            // sutun genislikleri
            for (int i = 1; i <= ncols; i++)
            {
                width[i] = md.getColumnDisplaySize (i);
                if (width[i] < md.getColumnName (i).length ( ))
                    width[i] = md.getColumnName (i).length ( );
                // isNullable( ) iade icin 1/0 true/false
                if (width[i] < 4 && md.isNullable (i) != 0)
                    width[i] = 4;
            }

            // kuruluyor +---+---... hat
            b.append ("+");
            for (int i = 1; i <= ncols; i++)
            {
                for (int j = 0; j < width[i]; j++)
                    b.append ("-");
                b.append ("+");
            }

            // Satir basina yazdiliyor
            System.out.println (b.toString ( ));
            System.out.print ("|");
            for (int i = 1; i <= ncols; i++)
            {
                System.out.print (md.getColumnName (i));
                for (int j = md.getColumnName (i).length ( ); j < width[i]; j++)
                    System.out.print (" ");
                System.out.print ("|");
            }
            System.out.println ( );
            System.out.println (b.toString ( ));

            // Icerik sonuclarini yazdirma

            while (rs.next())
            {
                ++nrows;
                System.out.print ("|");
                for (int i = 1; i <= ncols; i++)
                {
                    String s = rs.getString(i);
                    if (rs.wasNull())
                        s = "No";
                    System.out.print (s);
                    for (int j = s.length(); j < width[i]; j++)
                        System.out.print(" ");
                    System.out.print("|");
                }
                System.out.println();
            }

            // Siraya yazdirma
            System.out.println (b.toString ( ));
            System.out.println (nrows + " wrote");
        } else {
            throw new SQLException("There is no such data!");
        }
    }


    public void setAutoCommit(boolean value)
    {
        try
        {
            connection.setAutoCommit(value);
        }
        catch (SQLException e)
        {
            System.out.println("TRANSACTIONS ERROR: " + e);
        }
    }

    public void commit()    {
        try {
            connection.commit();
        }
        catch (SQLException e)        {
            System.out.println("TRANSACTIONS ERROR: " + e);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        }
        catch (SQLException e){
            System.out.println("TRANSACTIONS ERROR: " + e);
        }
    }
}
