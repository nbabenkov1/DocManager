/**
 * Created by N.Babenkov on 20.04.2018.
 */
$(function () {
    var currentMonth = new Date().getMonth();
    var selectMonth = $("#select_month");
    var uploadForm = $("#upload_form");
    var file = $("#file");
    var fileNameP = $("#file_name");
    var messageDiv = $("#message_div");
    var submitButton = $("#submit_button");
    var selectDoc = $("#select_doc");
    var selectPeriod = $("#select_period");
    var docHeaderTab = $("#docTab");
    var actsHeaderTab = $("#actsTab");

    messageDiv.hide();
    changeMonthOptions();

    submitButton.click(function (e) {
        var docType = selectDoc.find("option:selected").val();
        var month = selectMonth.find("option:selected").val();
        var period = selectPeriod.find("option:selected").val();
        var urlAdd = "./upload/" + docType + "/" + month + "/" + period;

        var IE = '\v' == 'v'; //checks if browser is IE
        // var IE = true;
        if (IE) {
            uploadForm.attr("action", urlAdd);
            uploadForm.attr("method", "post");
            // uploadForm.attr("target", "_blank");
            uploadForm.submit();
        }
        else {
            e.preventDefault();
            var data = new FormData();

            $.each(file[0].files, function (i, fileObj) {
                data.append('file', fileObj);
            });

            if (checkSelectedOptions())
                sendAjaxToServlet(urlAdd,
                    "POST",
                    null,
                    data,
                    success,
                    error
                );
        }

    });

    //disables options of upcoming months in <select>
    function changeMonthOptions() {
        selectMonth.fadeIn("fast");
        selectMonth.children('option').hide();

        if (currentMonth == 11) {
            selectMonth.children("option:eq(" + 1 + "), :eq(" + 12 + "), :eq(" + 11 + ")").show();
        }
        else if (currentMonth == 0) {
            selectMonth.children("option:eq(" + 1 + "), :eq(" + 2 + "), :eq(" + 12 + ")").show();
        }
        else {
            selectMonth.children("option:eq(" + currentMonth + "), :eq(" + (currentMonth + 1) + "), :eq(" + (currentMonth + 2) + ")").show();
        }

        selectMonth.find("option:first-child").attr("selected", "selected");
    }

    // uploadForm.submit(function (e) {
    //     e.preventDefault();
    // });

    //prevents servlet call if user didn't choose month, year or input file
    function checkSelectedOptions() {
        if (!selectMonth.find("option:selected").val()) {
            showMessage("Не выбран месяц!");
            return false;
        } else if (!file.val()) {
            showMessage("Не выбран файл!");
            return false;
        } else if (!(file.val().replace(/^.*\./, '').toLowerCase() === 'txt')) {
            showMessage("Выбранный файл не является txt!");
            return false;
        }
        return true;
    }

    function showMessage(message) {
        if (messageDiv.is(":hidden")) {
            messageDiv.text(message);
            messageDiv.show("fast").delay(2500).hide("normal");
        }
    }

    file.change(function () {
        var fileName = file.val().split('\\').pop();
        fileNameP.text(fileName);
    });

    function sendAjaxToServlet(url, method, headers, data, success, error) {
        $.ajax({
            url: url,
            type: method,
            processData: false,
            contentType: false,
            enctype: "multipart/form-data",
            headers: headers,
            data: data,
            success: success
        }).done(success).fail(error)
    }

    function success(xhr) {
        showMessage("Файл успешно загружен");
    }

    function error(xhr, status, error) {
        showMessage(xhr.responseText)
    }

    docHeaderTab.click(function () {
        window.location.replace("./")
    });
    actsHeaderTab.click(function () {
        window.location.replace("../ActUI")
    });
});