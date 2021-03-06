/**
 * Created by N.Babenkov on 24.11.2017.
 */
$(function () {
    var currentMonth = new Date().getMonth();
    var currentYear = new Date().getFullYear();

    var selectMonth = $("#select_month");
    var selectYear = $("#select_year");
    var selectMonthEnd = $("#select_monthEnd");
    var selectYearEnd = $("#select_yearEnd");
    var selectDoc = $("#select_doc");
    var downloadForm = $("#download_form");
    var messageDiv = $("#message_div");
    var file = $("#file");
    var singleDiv = $("#single_contract_div");
    var fileDiv = $("#file_div");
    var singleButton = $("#single_contract_tab");
    var multipleButton = $("#multiple_contract");
    var fileButton = $("#file_tab");
    var districtInput = $("#district_input");
    var contractInput = $("#contract_input");
    var fileLabel = $("#upload_label");
    var tabButton = $(".tab_button");
    var fileNameP = $("#file_name");
    var submitButton = $("#submit_button");
    var actsHeaderTab = $("#actsTab");
    var uploadHeaderTab = $("#uploadingTab");

    submitButton.click(function(){
        downloadForm.submit();
    });

    selectYearEnd.hide();
    selectMonth.hide();
    selectMonthEnd.hide();
    messageDiv.hide();
    fileDiv.hide();
    singleButton.addClass("selected_button");

    setYearOptions();

    selectYear.change(function () {
        selectYearEnd.find("option").not(":first").remove();
        selectYearEnd.find("option:first-child").attr("selected", "selected");
        setYearEndOptions();
        changeMonthOptions();
    });

    selectMonth.change(function(){
        changeMonthEndOptions();
    });

    selectYearEnd.change(function(){
       changeMonthEndOptions();
    });

    fileButton.click(function (e) {
        singleDiv.hide();
        fileDiv.show();
        districtInput.find("option:first-child").attr("selected", "selected");
        contractInput.val("Номер договора");
    });

    singleButton.click(function (e) {
        fileDiv.hide();
        singleDiv.show();
    });

    multipleButton.click(function(e){
        e.preventDefault();
        if ($(this).hasClass("selected_button")) {
            selectYearEnd.hide();
            selectMonthEnd.hide();
            $(this).removeClass("selected_button");
        } else {
            selectYearEnd.show();
            changeMonthEndOptions();
            $(this).addClass("selected_button");
        }
    });

    tabButton.click(function (e) {
        e.preventDefault();
        var currentId = $(this).attr("id");
        tabButton.not("#" + currentId).removeClass("selected_button");
        $(this).addClass("selected_button");
    });

    //prevents servlet call if user didn't choose month, year or input file
    downloadForm.submit(function (e) {
        e.preventDefault();
        if (!selectYear.find("option:selected").val()) {
            showMessage("Не выбран год!");
            return;
        } else if (!selectMonth.find("option:selected").val()) {
            showMessage("Не выбран месяц!");
            return;
        }

        if (singleDiv.is(":hidden")) {
            if (!file.val()) {
                showMessage("Не выбран файл!");
                return;
            } else if (!(file.val().replace(/^.*\./, '').toLowerCase() == 'xlsx')) {
                showMessage("Выбранный файл не является xlsx!");
                return;
            }
            downloadForm.attr("method", "post");
            var doctype = selectDoc.find("option:selected").val();
            downloadForm.attr("action", "./parse/"+ doctype + "/get/fromxls");
            this.submit();
        }
        else {
            if ((contractInput.val()=="Номер договора") || (districtInput.val()=="")
                || (contractInput.val()=="")) {
                showMessage("Не введены данные о договоре");
                return;
            }
            downloadForm.attr("method", "get");
            var doctype = selectDoc.find("option:selected").val();
            var dist = districtInput.find("option:selected").val();
            var contract = contractInput.val();
            downloadForm.attr("action", "./parse/" + doctype + "/get/" + dist + "/" + contract);
            this.submit();
        }
    });

    function showMessage(message) {
        if (messageDiv.is(":hidden")) {
            messageDiv.text(message);
            messageDiv.show("fast").delay(2500).hide("normal");
        }
    }

    //disables options of upcoming months in <select>
    function changeMonthOptions() {
        selectMonth.fadeIn("fast");
        selectMonth.find("option:first-child").attr("selected", "selected");

        if (selectYear.find("option:selected").text() == currentYear) {
            selectMonth.find("option:gt(" + (currentMonth+1) + ")").attr("disabled", "disabled");
        } else {
            selectMonth.find("option:not(:first-child):disabled").attr("disabled", false);
        }

        changeMonthEndOptions();
    }

    function changeMonthEndOptions() {
        var selectedOptionYear = selectYear.find("option:selected");
        var selectedOptionYearEnd = selectYearEnd.find("option:selected");
        var selectedOptionMonth = selectMonth.find("option:selected");
        if ((selectedOptionMonth.val() == "") || (selectedOptionYearEnd.val() == "")){
            selectMonthEnd.hide();
            return;
        }
        selectMonthEnd.fadeIn("fast");
        selectMonthEnd.find("option:first-child").attr("selected", "selected");

        selectMonthEnd.find("option:not(:first-child):disabled").attr("disabled", false);

        if(selectedOptionYear.text() == selectedOptionYearEnd.text())
            selectMonthEnd.find("option:lt(" + selectedOptionMonth.index() + ")").attr("disabled", "disabled");


        if (selectYearEnd.find("option:selected").text() == currentYear) {
            selectMonthEnd.find("option:gt(" + currentMonth + ")").attr("disabled", "disabled");
        } /*else {
            selectMonthEnd.find("option:not(:first-child):disabled").attr("disabled", false);
        }*/
    }

    //sets current and previous year as options
    function setYearOptions() {
        var years = [];
        if (currentMonth > 0) {
            for (var i = 2015; i <= currentYear; i++)
                years.push(i);
            // years = [2015, currentYear];
        }
        else {
            for (var i = 2015; i <= currentYear-1; i++)
                years.push(i);
            // years = [2015, currentYear - 1];
        }

        $.each(years, function (index, value) {
            selectYear.append($("<option></option>").attr("value", value).text(value));
        });
    }

    function setYearEndOptions() {
        selectMonthEnd.find("option:first-child").attr("selected", "selected");
        var years;
        var selectedStartYear = selectYear.find("option:selected").text();
        if (selectedStartYear === currentYear)
            years = [currentYear];
        else if (currentMonth > 0)
            years = [currentYear - 1, currentYear];
        else if (selectedStartYear === currentYear - 1)
            years = [currentYear-1];
        else
            years = [currentYear - 2, currentYear - 1];

        $.each(years, function (index, value) {
            selectYearEnd.append($("<option></option>").attr("value", value).text(value));
        });
    }

    //prevents entering non-numeric characters into text input
    contractInput.keydown(function (e) {
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
            // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
            // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
            // let it happen, don't do anything
            return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });

    file.change(function () {
        var fileName = file.val().split('\\').pop();
        if (fileName !== fileLabel.val()) {
            fileNameP.text(fileName);
        }
    });

    contractInput.focus(function () {
        if (contractInput.val() === "Номер договора")
            contractInput.val("");
    });

    uploadHeaderTab.click(function () {
       window.location.replace("./upload")
    });
    actsHeaderTab.click(function () {
       window.open("../ActUI")
    });

});


