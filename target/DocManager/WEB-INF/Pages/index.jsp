<%--
  Created by IntelliJ IDEA.
  User: N.Babenkov
  Date: 11.10.2017
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Формирование документов</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/CSS/master.css">
</head>
<script src="http://proxy-gf.esbt.loc/lib/1.5/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/JS/indexPageScript.js"></script>
<body>
<div class="mainwrapper">
    <div class="header">
        <!--<img id="headerlogo" src="IMG/logo50.png">-->
        <span class="header_span"><h1>Формирование документов</h1></span>
        <span id="actsTab" class="header_span menu_span">Акты</span>
        <span id="uploadingTab" class="header_span menu_span">Загрузить новые<br/>файлы</span>
    </div>
    <div class="leftPanel">
        <form id="download_form" method="post" enctype="multipart/form-data" target="_blank">

            <div id="form_div">
                <div id="menu_tabs">
                    <button class="tab_button button left_menu" id="single_contract_tab">Ввести</button>
                    <button class="tab_button button left_menu" id="file_tab">Файл</button>
                    <%--<button class="button left_menu" id="multiple_contract">Период</button>--%>
                </div>
                <div class="tab_div" id="file_div">
                    <span id="i_hate_ie8" class="input_elem" title="Загрузить файл">
                        <input type="file" name="file" id="file" accept=".xlsx">
                    </span>
                    <span id="file_name_span">
                        <p id="file_name">Файл не выбран</p>
                    </span>
                </div>
                <div class="tab_div" id="single_contract_div">
                    <select name="district" id="district_input" class="input_elem">
                        <option value="" selected disabled>Участок</option>
                        <option value="11">11</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                    </select>
                    <input maxlength="4" title="Номер договора" type="text"
                           class="text_input input_elem" id="contract_input"
                           name="contract" value="Номер договора">
                </div>
                <div id=submit_div>
                    <input id="submit_button" type="button" class="submit_button button" value="Отправить">
                </div>
                <br>
                <div class="static_div">
                    <select name="year" id="select_year" class="select_option input_elem">
                        <option value="" selected disabled>Выберите год</option>
                    </select>
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
                    <br>
<%--                    <select name="yearEnd" id="select_yearEnd" class="select_option input_elem">
                        <option value="" selected disabled>Выберите год</option>
                    </select>
                    <select name="monthEnd" id="select_monthEnd" class="select_option input_elem">
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
                    <br>--%>
                    <select name="documentType" id="select_doc" class="select_option input_elem">
                        <option value="sf" selected>Счет-фактура</option>
                        <option value="ved">Ведомость</option>
                        <option value="acts">Акты</option>
                        <option value="c4eta">Счет</option>
                    </select>
                    <select name="period" id="select_period" class="select_option input_elem">
                        <option value="itog" selected>Итог</option>
                        <option value="after_itog">После итога</option>
                        <option value="before_itog">До итога</option>
                        <option value="avans">Аванс</option>
                    </select>
                </div>
            </div>
        </form>
    </div>
</div>
<div id="message_div"></div>
</body>
</html>
