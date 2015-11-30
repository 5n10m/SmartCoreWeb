<%-- 
    Document   : menu
    Created on : 29-nov-2015, 22:25:00
    Author     : david
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
                    Class.forName("org.sqlite.JDBC");
                    Connection connection = null;
                     connection = DriverManager.getConnection("jdbc:sqlite://home/pti/pti.sqlite");
                     Statement statement = connection.createStatement();
%>



<html>
    <head>
        <title>Administració de dispositius</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <table>
        <tr>
            <form action="login" method="post">
                <th>Bombeta menjador:</th> 
                <td><%
                     ResultSet rs = statement.executeQuery("select state from devices where userid = 1 and id = 3;");
                     int state = -3;
                    while(rs.next()){
                        state = rs.getInt("state");
                    }
                    if (state == 0) out.print("OFF");
                    else if (state == 1) out.print("ON");
                    else if (state == 2) out.print("Auto");
             %></td>
                <td><select name=newstate>
                            <option selected VALUE=0> Off</option>
                            <option selected VALUE=1> On</option>
                            <option selected VALUE=2> Auto</option>
                </td>  
                <td><input name="menjador" type="submit" value="Enviar"></td>
            </form>
        </tr>
        <tr>
            <form action="login" method="post">
                <th>Bombeta terrassa:</th> <td><%
                     rs = statement.executeQuery("select state from devices where userid = 1 and id = 4;");
                     state = -3;
                    while(rs.next()){
                        state = rs.getInt("state");
                    }
                    if (state == 0) out.print("OFF");
                    else if (state == 1) out.print("ON");
                    else if (state == 2) out.print("Auto");
             %></td>
                <td><select name=newstate>
                            <option selected VALUE=0> Off</option>
                            <option selected VALUE=1> On</option>
                            <option selected VALUE=2> Auto</option>
                </td>
                <td><input name="terrassa" type="submit" value="Canviar estat"></td>
            </form>
        </tr>
        <tr>
            <form action="login" method="post">
                <th>Alarma:</th> <td><%
                     rs = statement.executeQuery("select state from devices where userid = 1 and id = 5;");
                     state = -3;
                    while(rs.next()){
                        state = rs.getInt("state");
                    }
                    if (state == 0) out.print("OFF");
                    else if (state == 1) out.print("ON");
             %></td>
                <td><select name=newstate>
                            <option selected VALUE=0> Off</option>
                            <option selected VALUE=1> On</option>
                </td>
                <td><input name="alarma" type="submit" value="Canviar estat"></td>
            </form>
        </tr>
        <tr>
            <form action="login" method="post">
                <th>Temperatura:</th> <td><%
                     rs = statement.executeQuery("select state from devices where userid = 1 and id = 2;");
                     state = -3;
                    while(rs.next()){
                        out.print(Integer.toString(rs.getInt("state")));
                    }
             %></td>
            </form>
        </tr>
</table>
<br>
</body>
</html>