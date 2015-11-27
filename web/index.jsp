<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.

http://fiberparty.upc.es:8080/PTIRestBackend/webresources/login
-->
<html>
    <head>
        <title>SmartCore</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>SmartCore</h1>
        <br>
        <h2>Introdueixi les seves credencials</h2>
        <form action="login" method="post">
            <table>
                <tr>
                    <th>User:</th> <td><input name="user" size="16" maxlength="16"></td>
                </tr>
                <tr>
                  <th>Password:</th> <td><input name="password" type="password" size="16" maxlength="16"></td>
                </tr>
              </table>
            <br>
            <input name="Login" type="submit" value="Enviar">
        </form>
    </body>
</html>