<%--
  Created by IntelliJ IDEA.
  User: N.Babenkov
  Date: 25.10.2017
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ActsParser</title>
</head>
<body>
<form class="formClass" id="download_form" action="storeOnServer" method="post" enctype="multipart/form-data">
    <input type="hidden" name="documentType" value="Acts">
    <input type="file" name="file" id="file" accept=".txt">
    <input id="upload_button" class="styleButton" type="submit" value="Загрузить">
</form>
<%--скачивание актов--%>
<%--<form id="download_form" action="downloadActs" method="get" target="_blank">--%>
        <%--<select name="districtNo" id="select_dist">--%>
            <%--<option id="hint" value="" disabled selected>Выбрать номер района</option>--%>
            <%--<option value="11">11</option>--%>
            <%--<option value="13">13</option>--%>
            <%--<option value="14">14</option>--%>
            <%--<option value="15">15</option>--%>
            <%--<option value="16">16</option>--%>
        <%--</select>--%>
        <%--<input id="download" class="styleButton" type="submit" value="Открыть">--%>
<%--</form>--%>

<a href="../ActUI">Назад</a>

</body>
</html>
