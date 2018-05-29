<%--
  Created by IntelliJ IDEA.
  User: N.Babenkov
  Date: 20.04.2018
  Time: 8:12
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Загрузить новые файлы</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/CSS/master.css">
</head>
<script src="http://proxy-gf.esbt.loc/lib/1.5/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/JS/uploadingFilesScript.js"></script>
<body>
<div class="mainwrapper">
    <div class="header">
        <!--<img id="headerlogo" src="IMG/logo50.png">-->
        <h1>Загрузка новых файлов</h1>
    </div>
    <div class="leftPanel">
        <form id="upload_form" enctype="multipart/form-data">
            <div id="form_div">
                <select name="documentType" id="select_doc" class="select_option input_elem">
                    <option value="sf" selected>Счет-фактура</option>
                    <option value="listConsumption">Ведомость</option>
                    <option value="acts">Акты</option>
                    <option value="c4eta">Счет</option>
                </select>
                <select name="period" id="select_period" class="select_option input_elem">
                    <option value="itog" selected>Итог</option>
                    <option value="after_itog">После итога</option>
                    <option value="before_itog">До итога</option>
                    <option value="avans">Аванс</option>
                </select>
                <br/>
                <br/>
                <select name="month" id="select_month" class="select_option input_elem">
                    <option value="" selected disabled>Выберите месяц</option>
                    <option value="01">Январь</option>
                    <option value="02">Февраль</option>
                    <option value="03">Март</option>
                    <option value="04">Апрель</option>
                    <option value="05">Май</option>
                    <option value="06">Июнь</option>
                    <option value="07">Июль</option>
                    <option value="08">Август</option>
                    <option value="09">Сентябрь</option>
                    <option value="10">Октябрь</option>
                    <option value="11">Ноябрь</option>
                    <option value="12">Декабрь</option>
                </select>
                <br/>
                <div class="tab_div" id="file_div">
                    <span id="i_hate_ie8" class="input_elem" title="Загрузить файл"><input type="file" name="file"
                                                                                           id="file"
                                                                                           accept=".txt"></span>
                    <span id="file_name_span"><p id="file_name">Файл не выбран</p></span>
                </div>
                <div id=submit_div>
                    <input id="submit_button" type="button" class="submit_button button" value="Отправить">
                </div>
            </div>
        </form>
    </div>
    <div id="message_div"></div>
</div>
</body>
</html>
