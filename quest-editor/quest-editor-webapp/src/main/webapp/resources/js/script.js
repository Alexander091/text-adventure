$(document).ready(function() { // on dom ready
    var cy = cytoscape({
        container: document.getElementById('cy'),
        style: cytoscape.stylesheet()
            .selector('node')
            .css({
                'content': 'data(name)',
                'text-valign': 'center',
                'color': 'white',
                'text-outline-width': 2,
                'text-outline-color': '#888'
            })
            .selector('edge')
            .css({
                'target-arrow-shape': 'triangle'
            })
            .selector(':selected')
            .css({
                'background-color': 'black',
                'line-color': 'black',
                'target-arrow-color': 'black',
                'source-arrow-color': 'black'
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
        PF('addNodeDialog').show();
    });
    $('.addEdgeButton').click(function () {
        PF('addEdgeDialog').show();
        $.getJSON("http://localhost:8080/TextAdventure/rest/command/node/get")
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
    $('.editButton').click(function() {
       if($('.selectedItemInput').val().substring(0,1) == 'n') {
           PF('editNodeDialog').show();
           $.getJSON("http://localhost:8080/TextAdventure/rest/command/node/get/" + $('.selectedItemInput').val())
               .success(function (data) {
                   console.log("node data fetched from service");
                   $(".editDialogNodeId").val(data['id']);
                   $(".editDialogNodeName").val(data['name']);
                   $(".editDialogNodeDescription").val(data['description']);
               })
               .error(function () {
                   console.log("error on fetching node data from service")
               });
       } else if($('.selectedItemInput').val().substring(0,1) == 'e') {
           PF('editEdgeDialog').show();
           $.getJSON("http://localhost:8080/TextAdventure/rest/command/transition/get/"+$('.selectedItemInput').val())
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
            url: "http://localhost:8080/TextAdventure/rest/command/undo"
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
            url: "http://localhost:8080/TextAdventure/rest/command/save"
        }).done(function(data) {
            if(data["response"]=="success") {
                console.log("save success");
                PF('waitForSavingDialog').hide();
            }
            console.log("error on save");
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
            url: "http://localhost:8080/TextAdventure/rest/command/transition/delete/" + edgeId
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
            url: "http://localhost:8080/TextAdventure/rest/command/node/delete/" + nodeId
        }).done(function(data) {
            if(data.response=='success'){
                console.log("node successfully deleted");
                cy.$('#'+nodeId).remove();
                $('.deleteDialogNodeCloseButton').click();
            }
            if(data.response=='error'){
                console.log("error on deleting node");
                $('.deleteDialogNodeCloseButton').click();
                alert("you can't delete start node");
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
        var nodeId = $('.editDialogNodeId').val();
        var node = {
            'name' : $('.editDialogNodeName').val(),
            'description' : $('.editDialogNodeDescription').val(),
            'questId' : $('.questIdInput').val(),
            'position' : cy.$('#'+nodeId).position('x') + ' ' + cy.$('#'+nodeId).position('y')
        }
        $.ajax({
            type: 'post',
            data: JSON.stringify(node),
            contentType: 'application/json',
            dataType: 'json',
            url: "http://localhost:8080/TextAdventure/rest/command/node/update/" + nodeId
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
            url: "http://localhost:8080/TextAdventure/rest/command/transition/update/" + edgeId
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
        var node = {
            'name' : $('.addDialogNodeName').val(),
            'description' : $('.addDialogNodeDescription').val(),
            'questId' : $('.questIdInput').val(),
            'position' : '100 100'
        }
        $.ajax({
            type: 'post',
            data: JSON.stringify(node),
            contentType: 'application/json',
            dataType: 'json',
            url: "http://localhost:8080/TextAdventure/rest/command/node/post"
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
            url: "http://localhost:8080/TextAdventure/rest/command/transition/post"
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
    });
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

    $.getJSON("http://localhost:8080/TextAdventure/rest/command/quest/get/" + $('.questIdInput').val())
        .success(function(data) {
            console.log("graph data fetched from service");
            cy.add(data);
        })
        .error(function() {console.log("error on fetching graph data from service")});
}); // on dom ready

