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
        $('#transitionInfo').hide();
        $('#nodeInfo').show();
        var node = e.cyTarget;
        var neighborhood = node.neighborhood().add(node);

        cy.elements().addClass('faded');
        neighborhood.removeClass('faded');
        var nodeId = node.data("id").substring(1);
        $.getJSON("http://localhost:8080/TextAdventure/rest/node/get/"+nodeId)
            .success(function(data) {
                console.log("node data fetched from service");
                $(".nodeId").val(data['id']);
                $(".nodeName").val(data['name']);
                $(".nodeDescription").val(data['description']);
            })
            .error(function() {console.log("error on fetching node data from service")});
    });
    cy.on('tap', 'edge', function(e) {
        $('#nodeInfo').hide();
        $('#transitionInfo').show();
        var edge = e.cyTarget;
        var edgeId = edge.data("id").substring(1);
        $.getJSON("http://localhost:8080/TextAdventure/rest/transition/get/"+edgeId)
            .success(function(data) {
                console.log("node data fetched from service");
                $(".transitionId").val(data['id']);
                $(".transitionName").val(data['name']);
                $(".sourceId").val(data['sourceId']);
                $(".sourceName").val(data['sourceName']);
                $(".targetId").val(data['targetId']);
                $(".targetName").val(data['targetName']);
            })
            .error(function() {console.log("error on fetching edge data from service")});
    });
    cy.on('tap', function(e) {
        if (e.cyTarget === cy) {
            cy.elements().removeClass('faded');
        }
    });

    $.getJSON("http://localhost:8080/TextAdventure/rest/quest/get/123")
        .success(function(data) {
            console.log("graph data fetched from service");
            cy.add(data);
        })
        .error(function() {console.log("error on fetching graph data from service")});
}); // on dom ready

