$(function(){
    $('.radio_with_text').click(function (event) {
        var input_textes = document.querySelectorAll('[name*=' +  $(event.target).attr("name") + "_text]");
        input_textes.forEach(function(input_text) {
            input_text.setAttribute('required', 'true');
            input_text.removeAttribute('disabled');
        });
    })

    $('.radio').click(function (event) {
        if (!$(event.target).attr("class").includes('radio_with_text')) {
            var input_textes = document.querySelectorAll('[name*=' +  $(event.target).attr("name") + "_text]");
            input_textes.forEach(function(input_text) {
                input_text.setAttribute('required', 'false');
                input_text.setAttribute('disabled', 'disabled');
                input_text.value = '';
            });
        }
    })

    $('.checkbox_with_text').click(function (event) {
        checkbox_with_text(event)
    })

    $('.only2checkbox').click(function (event) {
        var allCheckbox = document.querySelectorAll("[name=" + $(event.target).attr("name") + "]");
        var count = 0;
        allCheckbox.forEach(function(checkbox) {
            if (checkbox.checked) {
                count += 1;
            }
            if (count >= 3) {
                $(event.target).prop("checked", false);
                return
            }
        });

        if ($(event.target).attr("class").includes('only2checkbox_with_text')) {
            checkbox_with_text(event)
        }
    })

    $('#save-case').click(function () {
        var form = $('#case-form form');
        var allAreFilled = true;

        document.getElementById("myform").querySelectorAll("[required=true]").forEach(function(i) {
            if (!allAreFilled) {
                return;
            }
            console.log(i);
            if (!i.value) {
                allAreFilled = false;
            }
            if (i.type === "radio") {
                var radioValueCheck = false;
                document.getElementById("myform").querySelectorAll(`[name=${i.name}]`).forEach(function(r) {
                    if (r.checked) {
                        radioValueCheck = true;
                    }
                })
                allAreFilled = radioValueCheck;
            }
            console.log(allAreFilled);
        });

        if (!allAreFilled) {
            return true;
        }

        var data = getFormData(form);
        console.log(data);
        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            method: "POST",
            url: './save_case/',
            data: JSON.stringify(data),
            complete: function (response) {
                window.location.replace(response.responseText);
            }
        });
        return false
    })
});


function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        if (indexed_array[n['name']] !== undefined) {
            var g = indexed_array[n['name']];
            if (!Array.isArray(indexed_array[n['name']])) {
                indexed_array[n['name']] = [];
                indexed_array[n['name']].push(g);
            }
            indexed_array[n['name']].push(n['value']);
        } else {
            indexed_array[n['name']] = n['value'];
        }
    });

    return indexed_array;
}


function checkbox_with_text(event) {
    var checkbox = document.querySelector(".checkbox_with_text[name=" + $(event.target).attr("name") + "]");

    if (checkbox == null) {
        checkbox = document.querySelector(".only2checkbox_with_text[name=" + $(event.target).attr("name") + "]");
    }

    var input_text = $('[name=' +  $(event.target).attr("name") + "_text]");
    if(checkbox.checked) {
        input_text.prop('required', true);
        input_text.removeAttr('disabled');
    } else {
        input_text.prop('required', false);
        input_text.prop('disabled', 'disabled');
        input_text.prop("value", "")
    }
}
