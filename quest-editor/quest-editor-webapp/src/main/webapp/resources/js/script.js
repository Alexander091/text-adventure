$(document).ready(function() { // on dom ready
    $('#cy').height(window.innerHeight-165);
    var cy = cytoscape({
        container: document.getElementById('cy'),
        height: window.innerHeight,
        style: cytoscape.stylesheet()
            .selector('node')
            .css({
                'content': 'data(name)',
                'text-valign': 'center',
                'color': 'white',
                'text-outline-width': 2,
                'text-outline-color': '#888',
                'background-color' : '#1484E6',
            })
            .selector('edge')
            .css({
                'target-arrow-shape': 'triangle',
                'line-color' : '#1484E6',
                'target-arrow-color': '#1484E6',
                'source-arrow-color': '#1484E6',
                'opacity': 0.8,
            })
            .selector(':selected')
            .css({
                'background-color': '#e69700',
                'line-color': '#e69700',
                'target-arrow-color': '#e69700',
                'source-arrow-color': '#e69700',
            })
            .selector('.faded')
            .css({
                'opacity': 0.25,
                'text-opacity': 0
            })
    });
    cy.on('tap', 'node', function(e) {
        var node = e.cyTarget;
        $('.selectedItemInput').val(node.data("id"));
        //$('.editButton').prop('disabled', false);
        //$('.deleteButton').prop('disabled', false);
        PF('editButton').enable();
        PF('deleteButton').enable();
        console.log($('.selectedItemInput').val());
    });
    cy.on('tap', 'edge', function(e) {
        var edge = e.cyTarget;
        $('.selectedItemInput').val(edge.data("id"));
        //$('.editButton').prop('disabled', false);
        //$('.deleteButton').prop('disabled', false);
        PF('editButton').enable();
        PF('deleteButton').enable();
        console.log($('.selectedItemInput').val());
    });
    //$('.addDialogSelectMenu').change(function() {
    //    if(PF('addDialogSelect').getSelectedValue() == 0) {
    //        $("#addDialogEdgeDiv").hide();
    //        $("#addDialogNodeDiv").show();
    //    }
    //    else
    //    {
    //        $("#addDialogNodeDiv").hide();
    //        $("#addDialogEdgeDiv").show();
    //    }
    //});
    $('.addNodeButton').click(function () {
        $('#addDialogActionsTable tr').remove();
        $('.editDialogActionOKButton').css("display", "none");
        $('.addDialogActionOKButton').css("display", "inline");
        PF('addNodeDialog').show();
    });
    $('.addEdgeButton').click(function () {
        PF('addEdgeDialog').show();
        $.getJSON("/TextAdventure/rest/command/node/get")
            .success(function (data) {
                $("#addDialogNodeToMenu").empty();
                $("#addDialogNodeFromMenu").empty();
                for(var i in data) {
                    $("#addDialogNodeToMenu").append($("<option />").val(data[i].data.id).text(data[i].data.name));
                    $("#addDialogNodeFromMenu").append($("<option />").val(data[i].data.id).text(data[i].data.name));
                }
            })
            .error(function () {
                console.log("error on fetching edge data from service");
            });
    });
    $('.addActionButton').click(function () {
        PF('addActionDialog').show();
        var actionTypeSelect = $("#actionTypeMenu");
        $.getJSON("/TextAdventure/rest/command/actionTypes/get")
            .success(function (data) {
                actionTypeSelect.empty();
                $("#actionResourceMenu").empty();
                for(var i in data)
                    actionTypeSelect.append($("<option />").val(data[i].id).text(data[i].name));
                loadIntoAddDialogResourceSelect();
            })
            .error(function () {
                console.log("error on fetching types of action from service");
            });
    });
    $('#actionTypeMenu').change(function()  {
        loadIntoAddDialogResourceSelect();
    })
    var loadIntoAddDialogResourceSelect=function() {
        $.getJSON("/TextAdventure/rest/command/resource/get/"+$('.questIdInput').val()+','+$('#actionTypeMenu').val())
            .success(function (data) {
                $("#actionResourceMenu").empty();
                for(var i in data)
                    $("#actionResourceMenu").append($("<option />").val(data[i].id).text(data[i].name));
            })
            .error(function () {
                console.log("error on fetching resources from service");
            });
    }
    $('.editButton').click(function() {
       if($('.selectedItemInput').val().substring(0,1) == 'n') {
           $('.addDialogActionOKButton').css("display", "none");
           $('.editDialogActionOKButton').css("display", "inline");
           var table = $('#editDialogActionsTable');
           $('#editDialogActionsTable tr').remove();
           $.getJSON("/TextAdventure/rest/command/node/get/" + $('.selectedItemInput').val())
               .success(function (data) {
                   console.log("node data fetched from service");
                   $(".editDialogNodeId").val(data['id']);
                   $(".editDialogNodeName").val(data['name']);
                   $(".editDialogNodeDescription").val(data['description']);
                   var actions = data.actions;
                   for(var i = 0; i<actions.length; i++) {
                       table.append( '<tr ' + 'action_id = \"' + actions[i].id + '\"'
                           + 'resource_id=\"' +  actions[i].resource_id + '\" '
                           +  'action_type_id=\"' + actions[i].action_type_id + '\"><td>'
                           + actions[i].action_type_name + ': ' + actions[i].resource_name
                           + '</td><td><button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only deleteActionButton" type="button" onclick="deleteClosestRow(this)"><span class="ui-button-text ui-c">-</span></button></td></tr>' );
                   }
                   PF('editNodeDialog').show();
               })
               .error(function () {
                   console.log("error on fetching node data from service")
               });
       } else if($('.selectedItemInput').val().substring(0,1) == 'e') {
           PF('editEdgeDialog').show();
           $.getJSON("/TextAdventure/rest/command/transition/get/"+$('.selectedItemInput').val())
               .success(function(data) {
                   console.log("transition data fetched from service");
                   $(".editDialogEdgeId").val(data.transition.id);
                   $(".editDialogEdgeName").val(data.transition.name);
                   $("#editDialogNodeToMenu").empty();
                   $("#editDialogNodeFromMenu").empty();
                   for(var i in data.nodes) {
                       $("#editDialogNodeFromMenu").append($("<option />").val(data.nodes[i].id).text(data.nodes[i].name));
                       $("#editDialogNodeToMenu").append($("<option />").val(data.nodes[i].id).text(data.nodes[i].name));
                   }
                   $("#editDialogNodeFromMenu").val(data.transition.sourceId);
                   $("#editDialogNodeToMenu").val(data.transition.targetId);
                   //PF('editDialogNodeFromSelect').selectValue(data['sourceId']);
                   //PF('editDialogNodeToSelect').selectValue(data['targetId']);
               })
               .error(function() {console.log("error on fetching edge data from service")});
       }
    });
    $('.deleteButton').click(function() {
        if($('.selectedItemInput').val().substring(0,1) == 'n') {
            PF('deleteNodeDialog').show();
        } else if ($('.selectedItemInput').val().substring(0,1) == 'e') {
            PF('deleteEdgeDialog').show();
        }
    });
    $('.undoButton').click(function() {
        $.ajax({
            type: 'post',
            url: "/TextAdventure/rest/command/undo"
        }).done(function(data) {
            if(data["response"]=="success")
            console.log("undo success");
        }).fail(function() {
            console.log("error on undo");
        })
    });
    $('.saveButton').click(function() {
        PF('waitForSavingDialog').show();
        var outputArray = [];
        var nodesArray = cy.nodes();
        for(var i=0; i<nodesArray.length; i++)
            outputArray.push({"id": nodesArray[i].data().id, "position": nodesArray[i].position('x') + " " + nodesArray[i].position('y')});
        $.ajax({
            type: 'post',
            data: JSON.stringify(outputArray),
            url: "/TextAdventure/rest/command/save"
        }).done(function(data) {
            var responseArray = data.response;
            if(responseArray[0] == "success" && responseArray.length==1) {
                PF('messages').renderMessage({
                    "summary": "Successfully saved",
                    "severity": "info"
                });
                console.log("save success");
            }
            else {
                var totalMessage = "";
                for(var i = 0; i<responseArray.length; i++)
                {
                    switch(responseArray[i]) {
                        case "invalid_start": {
                            totalMessage += "Invalid start stage\r\n"; break;
                        }
                        case "invalid_end": {
                            totalMessage += "Invalid finish stage\r\n"; break;
                        }
                        case "invalid_start_and_end": {
                            totalMessage += "Invalid start and finish stage\r\n"; break;
                        }
                        case "connectivity_error": {
                            totalMessage += "Connectivity error\r\n"; break;
                        }
                    }
                }
                PF('messages').renderMessage({
                    "summary": "Error on saving",
                    "detail": totalMessage,
                    "severity": "error"
                });
                console.log("error on save");
            }
            PF('waitForSavingDialog').hide();
        }).fail(function() {
            console.log("error on save");
        })
    });
    $('.exitButton').click(function() {
       PF('exitDialog').show();
    });
    $('.deleteDialogEdgeOKButton').click(function() {
        var edgeId = $('.selectedItemInput').val();
        $.ajax({
            type: 'post',
            url: "/TextAdventure/rest/command/transition/delete/" + edgeId
        }).done(function(data) {
            if(data.response=='success'){
                console.log("edge successfully deleted");
                cy.$('#'+edgeId).remove();
                $('.deleteDialogEdgeCloseButton').click();
            }
            else
                console.log("error on delete edge");
        }).fail(function() {
            console.log("error on delete edge");
        })
    });
    $('.deleteDialogEdgeCloseButton').click(function() {
        PF('deleteEdgeDialog').hide();
    });
    $('.deleteDialogNodeOKButton').click(function() {
        var nodeId = $('.selectedItemInput').val();
        $.ajax({
            type: 'post',
            url: "/TextAdventure/rest/command/node/delete/" + nodeId
        }).done(function(data) {
            if(data.response=='success'){
                console.log("node successfully deleted");
                cy.$('#'+nodeId).remove();
                $('.deleteDialogNodeCloseButton').click();
            }
            else if(data.response=='error'){
                console.log("error on deleting node");
                $('.deleteDialogNodeCloseButton').click();
                PF('messages').renderMessage({
                    "summary": "You can't delete start node",
                    "severity": "error"
                });
            }
            else
                console.log("error on delete node");
        }).fail(function() {
            console.log("error on delete node");
        })
    });
    $('.deleteDialogNodeCloseButton').click(function() {
        PF('deleteNodeDialog').hide();
    });
    $('.editDialogNodeOKButton').click(function() {
        var actions = [];
        var table = $('#editDialogActionsTable');
        var rows = $('tr', table);
        for(var i = 0; i<rows.length; i++) {
            actions.push( {
                'action_id' : null,
                'resource_id': rows.eq(i).attr('resource_id'),
                'action_type_id': rows.eq(i).attr('action_type_id')
            });
        }
        var nodeId = $('.editDialogNodeId').val();
        var node = {
            'name' : $('.editDialogNodeName').val(),
            'description' : $('.editDialogNodeDescription').val(),
            'questId' : $('.questIdInput').val(),
            'position' : cy.$('#'+nodeId).position('x') + ' ' + cy.$('#'+nodeId).position('y'),
            'actions' : actions
        }
        $.ajax({
            type: 'post',
            data: JSON.stringify(node),
            contentType: 'application/json',
            dataType: 'json',
            url: "/TextAdventure/rest/command/node/update/" + nodeId
        }).done(function(data) {
            console.log("node successfully edited");
            cy.$('#'+nodeId).data("name", data['name']);
            $('.editDialogNodeCloseButton').click();
        }).fail(function(data) {
            console.log("error on edit node");
        })
    });
    $('.editDialogNodeCloseButton').click(function() {
        $('.editDialogNodeId').val('');
        $('.editDialogNodeName').val('');
        $('.editDialogNodeDescription').val('');
        PF('editNodeDialog').hide();
    });
    $('.editDialogEdgeOKButton').click(function() {
        var edgeId = $('.editDialogEdgeId').val();
        var edge = {
            'name' : $('.editDialogEdgeName').val(),
            'source' : $("#editDialogNodeFromMenu").val(),
            'target' : $("#editDialogNodeToMenu").val()
        }
        $.ajax({
            type: 'post',
            data: JSON.stringify(edge),
            contentType: 'application/json',
            dataType: 'json',
            url: "/TextAdventure/rest/command/transition/update/" + edgeId
        }).done(function(data) {
            console.log("edge successfully edited");
            cy.$('#'+edgeId).remove();
            cy.add(data);
            $('.editDialogEdgeCloseButton').click();
        }).fail(function(data) {
            console.log("error on edit edge");
        })
    });
    $('.editDialogEdgeCloseButton').click(function() {
        $('.editDialogEdgeName').val('');
        $('.editDialogEdgeName').val('');
        PF('editEdgeDialog').hide();
    })
    $('.addDialogNodeOKButton').click(function () {
        var actions = [];
        var panPos = cy.pan();
        var width = cy.width();
        var zoom = cy.zoom();
        var height = cy.height();
        var table = $('#addDialogActionsTable');
        var rows = $('tr', table);
        for(var i = 0; i<rows.length; i++) {
            actions.push( {
                'action_id' : null,
                'resource_id': rows.eq(i).attr('resource_id'),
                'action_type_id': rows.eq(i).attr('action_type_id')
            });
        }
        var node = {
            'name' : $('.addDialogNodeName').val(),
            'description' : $('.addDialogNodeDescription').val(),
            'questId' : $('.questIdInput').val(),
            'position' : (-panPos.x/zoom + (width/zoom)/2) + " " + (-panPos.y/zoom + (height/zoom)/2),
            'actions' : actions
        }

        $.ajax({
            type: 'post',
            data: JSON.stringify(node),
            contentType: 'application/json',
            dataType: 'json',
            url: "/TextAdventure/rest/command/node/post"
        }).done(function(data) {
            console.log("new node successfully created");
            cy.add(data);
            $('.addDialogNodeCloseButton').click();
        }).fail(function(data) {
            console.log("error on create new node");
        })
    });
    $('.addDialogNodeCloseButton').click(function () {
        $('.addDialogNodeName').val('');
        $('.addDialogNodeDescription').val('');
        PF('addNodeDialog').hide();
    });
    $('.addDialogEdgeOKButton').click(function () {
        var edge = {
            'name' : $('.addDialogEdgeName').val(),
            'source' : $("#addDialogNodeFromMenu").val(),
            'target' : $("#addDialogNodeToMenu").val()
        }
        $.ajax({
            type: 'post',
            data: JSON.stringify(edge),
            contentType: 'application/json',
            dataType: 'json',
            url: "/TextAdventure/rest/command/transition/post"
        }).done(function(data) {
            console.log("new edge successfully created");
            cy.add(data);
            $('.addDialogEdgeCloseButton').click();
        }).fail(function(data) {
            console.log("error on create new edge");
        })
    });
    $('.addDialogEdgeCloseButton').click(function () {
        $('.addDialogEdgeName').val('');
        PF('addEdgeDialog').hide();
    })
    $('.addDialogActionOKButton').click(function() {
        var table = $("#addDialogActionsTable");
        var resourceSelect = $('#actionResourceMenu');
        var actionTypeSelect = $('#actionTypeMenu');
        var actionTypeSelected = $('#actionTypeMenu option:selected');
        var sameAction = false;
        var rows = $('tr', table);
        for(var i = 0; i<rows.length; i++) {
            if(rows[i].getAttribute("resource_id") == resourceSelect.val())
                sameAction=true;
        }
        if(!sameAction) {
            table.append('<tr ' + 'action_id=\"' + null + '\"'
                + 'resource_id=\"' + resourceSelect.val() + '\" '
                + 'action_type_id=\"' + actionTypeSelect.val() + '\"><td>'
                + $('#actionTypeMenu option:selected').text() + ': ' + $('#actionResourceMenu option:selected').text()
                + '</td><td><button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only deleteActionButton" type="button" onclick="deleteClosestRow(this)"><span class="ui-button-text ui-c">-</span></button></td></tr>');
            $('.addDialogActionCloseButton').click();
        }
        else
            PF('messages').renderMessage({
                "summary": "Such action exists",
                "severity": "error"
            });
    })
    $('.editDialogActionOKButton').click(function() {
        var table = $("#editDialogActionsTable");
        var resourceSelect = $('#actionResourceMenu');
        var sameAction = false;
        var rows = $('tr', table);
        for(var i = 0; i<rows.length; i++) {
            if(rows[i].getAttribute("resource_id") == resourceSelect.val())
                sameAction=true;
        }
        if(!sameAction){
            var actionTypeSelect = $('#actionTypeMenu');
            var actionTypeSelected = $('#actionTypeMenu option:selected');
            table.append( '<tr ' + 'action_id=\"' + null + '\"'
                + 'resource_id=\"' +  resourceSelect.val() + '\" '
                +  'action_type_id=\"' + actionTypeSelect.val() + '\"><td>'
                + $('#actionTypeMenu option:selected').text() + ': ' + $('#actionResourceMenu option:selected').text()
                + '</td><td><button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only deleteActionButton" type="button" onclick="deleteClosestRow(this)"><span class="ui-button-text ui-c">-</span></button></td></tr>' );
            $('.addDialogActionCloseButton').click();
        }
        else
            PF('messages').renderMessage({
                "summary": "Such action exists",
                "severity": "error"
            });
    })
    $('.addDialogActionCloseButton').click(function() {
        PF('addActionDialog').hide();
    })
    $('.exitDialogCloseButton').click(function () {
        PF('exitDialog').hide();
    });
    cy.on('tap', function(e) {
        if (e.cyTarget === cy) {
            cy.elements().removeClass('faded');
            //$('.editButton').prop('disabled', true);
            //$('.deleteButton').prop('disabled', true);
            PF('editButton').disable();
            PF('deleteButton').disable();
        }
    });

    $.getJSON("/TextAdventure/rest/command/quest/get/" + $('.questIdInput').val())
        .success(function(data) {
            console.log("graph data fetched from service");
            cy.add(data);
        })
        .error(function() {console.log("error on fetching graph data from service")});
}); // on dom ready

