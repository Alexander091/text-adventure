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
        $('.editButton').prop('disabled', false);
        $('.deleteButton').prop('disabled', false);
        console.log($('.selectedItemInput').val());
    });
    cy.on('tap', 'edge', function(e) {
        var edge = e.cyTarget;
        $('.selectedItemInput').val(edge.data("id"));
        $('.editButton').prop('disabled', false);
        $('.deleteButton').prop('disabled', false);
        console.log($('.selectedItemInput').val());
    });
    $('.addDialogSelectMenu').change(function() {
        if(PF('addDialogSelect').getSelectedValue() == 0) {
            $("#addDialogEdgeDiv").hide();
            $("#addDialogNodeDiv").show();
        }
        else
        {
            $("#addDialogNodeDiv").hide();
            $("#addDialogEdgeDiv").show();
        }
    });
    $('.addButton').click(function () {
        PF('addDialog').show();
    });
    $('.editButton').click(function() {
       if($('.selectedItemInput').val().substring(0,1) == 'n') {
           PF('editNodeDialog').show();
           $.getJSON("http://localhost:8080/TextAdventure/rest/node/get/" + $('.selectedItemInput').val().substring(1))
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
           $.getJSON("http://localhost:8080/TextAdventure/rest/transition/get/"+$('.selectedItemInput').val().substring(1))
               .success(function(data) {
                   console.log("node data fetched from service");
                   $(".editDialogEdgeId").val(data['id']);
                   $(".editDialogEdgeName").val(data['name']);
                   PF('editDialogNodeFromSelect').selectValue(data['sourceId']);
                   PF('editDialogNodeToSelect').selectValue(data['targetId']);
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
    $('.deleteDialogEdgeOKButton').click(function() {
        var edgeId = $('.selectedItemInput').val().substring(1);
        $.ajax({
            type: 'post',
            url: "http://localhost:8080/TextAdventure/rest/transition/delete/" + edgeId
        }).done(function() {
            console.log("edge successfully deleted");
            cy.$('#e'+edgeId).remove();
            $('.deleteDialogEdgeCloseButton').click();
        }).fail(function() {
            console.log("error on delete edge");
        })
    });
    $('.deleteDialogEdgeCloseButton').click(function() {
        PF('deleteEdgeDialog').hide();
    });
    $('.deleteDialogNodeOKButton').click(function() {
        var nodeId = $('.selectedItemInput').val().substring(1);
        $.ajax({
            type: 'post',
            url: "http://localhost:8080/TextAdventure/rest/node/delete/" + nodeId
        }).done(function() {
            console.log("node successfully deleted");
            cy.$('#n'+nodeId).remove();
            $('.deleteDialogNodeCloseButton').click();
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
            'position' : cy.$('#n'+nodeId).position('x') + ' ' + cy.$('#n'+nodeId).position('y')
        }
        $.ajax({
            type: 'post',
            data: JSON.stringify(node),
            contentType: 'application/json',
            dataType: 'json',
            url: "http://localhost:8080/TextAdventure/rest/node/update/" + nodeId
        }).done(function(data) {
            console.log("node successfully edited");
            cy.$('#n'+nodeId).data("name", data['name']);
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
            'source' : PF('editDialogNodeFromSelect').getSelectedValue(),
            'target' : PF('editDialogNodeToSelect').getSelectedValue()
        }
        $.ajax({
            type: 'post',
            data: JSON.stringify(edge),
            contentType: 'application/json',
            dataType: 'json',
            url: "http://localhost:8080/TextAdventure/rest/transition/update/" + edgeId
        }).done(function(data) {
            console.log("edge successfully edited");
            cy.$('#e'+edgeId).remove();
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
            url: "http://localhost:8080/TextAdventure/rest/node/post"
        }).done(function(data) {
            console.log("new node successfully created");
            cy.add(data);
            $('.addDialogCloseButton').click();
        }).fail(function(data) {
            console.log("error on create new node");
        })
    });
    $('.addDialogCloseButton').click(function () {
        $('.addDialogNodeName').val('');
        $('.addDialogNodeDescription').val('');
        $('.addDialogEdgeName').val('');
        PF('addDialog').hide();
    });
    $('.addDialogEdgeOKButton').click(function () {
        var edge = {
            'name' : $('.addDialogEdgeName').val(),
            'source' : PF('addDialogNodeFromSelect').getSelectedValue(),
            'target' : PF('addDialogNodeToSelect').getSelectedValue()
        }
        $.ajax({
            type: 'post',
            data: JSON.stringify(edge),
            contentType: 'application/json',
            dataType: 'json',
            url: "http://localhost:8080/TextAdventure/rest/transition/post"
        }).done(function(data) {
            console.log("new edge successfully created");
            cy.add(data);
            $('.addDialogCloseButton').click();
        }).fail(function(data) {
            console.log("error on create new edge");
        })
    });
    cy.on('tap', function(e) {
        if (e.cyTarget === cy) {
            cy.elements().removeClass('faded');
            $('.editButton').prop('disabled', true);
            $('.deleteButton').prop('disabled', true);
        }
    });

    $.getJSON("http://localhost:8080/TextAdventure/rest/quest/get/" + $('.questIdInput').val())
        .success(function(data) {
            console.log("graph data fetched from service");
            cy.add(data);
        })
        .error(function() {console.log("error on fetching graph data from service")});
}); // on dom ready

