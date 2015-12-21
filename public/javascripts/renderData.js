$(document).ready(function() {

    $("#responseFormNew").validate();
    $("#fieldFormNew").validate();
    $("#fieldFormEdit").validate();

    // Create a response
    $("#responseFormNew").submit(function(event) {
        /* stop form from submitting normally */
        event.preventDefault();
        var count = 0;
        var data = {};
        var key, value;
        var allInputs = $(":input");
        for (var i = 0; i < allInputs.length; i++) {
            if ((allInputs[i].getAttribute("class") == "required valid" || allInputs[i].getAttribute("class") == "required error"
                || allInputs[i].getAttribute("class") == "required hasDatepicker error")
                && (allInputs[i].value == null || allInputs[i].value == "")) {
                return false;
            }
            key = allInputs[i].id;
            if (key != "responseReset" && key != "responseSubmit") {
                if (allInputs[i].type == "select-one")
                    value = allInputs[i].value.split("_")[0];
                else if (allInputs[i].type == "checkbox")
                    value = $("#" + key).is(':checked');
                else if (allInputs[i].type == "radio") {
                    if (allInputs[i].checked) {
                        continue;
                    }
                    value = $("label[for=" + key + "]")[0].firstChild.data;
                    key = key.split("_")[0];
                }
                else value = allInputs[i].value;
                data[key] = value;
            }
        }
        appRoutes.controllers.ResponseController.createResponse().ajax({
            data : JSON.stringify(data),
            contentType : 'application/json',
            success:function(result){
                $(function() {
                    var status = {"status" : "succeed"};
                    ws.send(JSON.stringify(status));
                });
                $("head").hide();
                $("body").hide();
                $("html").append(result);
            },
            error: function(xhr, textStatus, errorThrown) {
                $(function() {
                    var status = {"status" : "failed"};
                    ws.send(JSON.stringify(status));
                });
                alert('Error!  Status = ' + xhr.status);
            }
        });
    });

    $(function() {
        showText();
    });

    // on type select
    $("#fieldTypeId").change(function() {
        showText();
    });

    function showText() {
        var type = $("#fieldTypeId").val();
        if (type != null && type.length > 0 && (type == "COMBO_BOX" || type == "RADIO_BUTTON"))
            $("#fieldOptions").show();
        else $("#fieldOptions").hide();
    }

    // Create a field
    $("#fieldFormNew").submit(function(event) {
        /* stop form from submitting normally */
        event.preventDefault();
        var allInputs = $(":input");
        for (var i = 0; i < allInputs.length; i++) {
            if ((allInputs[i].getAttribute("class") == "required valid" || allInputs[i].getAttribute("class") == "required error")
                && (allInputs[i].value == null || allInputs[i].value == "")) {
                return false;
            }
        }
        var fieldItems = [];
        if ($("#fieldOptions").is(":visible")) {
            var stringToSplit = $("#fieldItemsId").val().split("\n");
            for (var i = 0; i < stringToSplit.length; i++) {
                var item = {'item': stringToSplit[i]};
                fieldItems.push(item);
            }
        }
        var data = {
            'label' : $("#fieldLabelId").val(),
            'type' : $("#fieldTypeId").val(),
            'required' : $("#fieldRequiredId").is(':checked') ,
            'alive' : $("#fieldAliveId").is(':checked'),
            'fieldItems' : fieldItems
        };
        appRoutes.controllers.FieldController.createField().ajax({
            data : JSON.stringify(data),
            contentType : 'application/json',
            success:function(result){
                window.location.replace(result.url);
            },
            error: function(xhr, textStatus, errorThrown) {
                //alert('Error!  Status = ' + xhr.status);
            }
        });
    });

    // Edit a field
    $("#fieldFormEdit").submit(function(event) {
        /* stop form from submitting normally */
        event.preventDefault();
        var allInputs = $(":input");
        for (var i = 0; i < allInputs.length; i++) {
            if ((allInputs[i].getAttribute("class") == "required valid" || allInputs[i].getAttribute("class") == "required error")
                && (allInputs[i].value == null || allInputs[i].value == "")) {
                return false;
            }
        }
        var fieldItems = [];
        if ($("#fieldOptions").is(":visible")) {
            var stringToSplit = $("#fieldItemsId").val().split("\n");
            for (var i = 0; i < stringToSplit.length; i++) {
                var item = {'item': stringToSplit[i]};
                fieldItems.push(item);
            }
        }
        var target = $("#editFieldId");
        var id = target.data('id');
        var data = {
            'label' : $("#fieldLabelId").val(),
            'type' : $("#fieldTypeId").val(),
            'required' : $("#fieldRequiredId").is(':checked') ,
            'alive' : $("#fieldAliveId").is(':checked'),
            'fieldItems' : fieldItems
        };
        appRoutes.controllers.FieldController.editField(id).ajax({
            data : JSON.stringify(data),
            contentType : 'application/json',
            success:function(result){
                window.location.href = result.url;
            },
            error: function(xhr, textStatus, errorThrown) {
            }
        });
    });

    // Delete field
    $(document).on ('click', '.deleteField', function(e) {
        var result = confirm("Confirm delete field?");
        if (result) {
            var target = $(e.target);
            var id = target.data('id');
            appRoutes.controllers.FieldController.delete(id).ajax( {
                success : function ( data ) {
                    target.closest("tr").remove();
                },
                error: function(xhr, textStatus, errorThrown) {
                    //alert('Error!  Status = ' + xhr.status);
                }
            });
        }
    });
});

