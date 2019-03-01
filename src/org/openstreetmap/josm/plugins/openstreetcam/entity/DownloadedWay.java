/*
 * The code is licensed under the LGPL Version 3 license http://www.gnu.org/licenses/lgpl-3.0.en.html.
 *
 * The collected imagery is protected & available under the CC BY-SA version 4 International license.
 * https://creativecommons.org/licenses/by-sa/4.0/legalcode.
 *
 * Copyright (c)2019, Telenav, Inc. All Rights Reserved
 */

package org.openstreetmap.josm.plugins.openstreetcam.entity;

import java.util.List;
import org.openstreetmap.josm.data.coor.LatLon;
import org.openstreetmap.josm.data.osm.Node;


/**
 * Entity that matched a WAY Osm Element to the downloaded data. There are two cases: 1. The OsmElement has toNode and
 * fromNode so only those 2 Nodes are downloaded. 2. The OsmElement only has the osmId, therefore the download returns a
 * list of Node elements.
 *
 * @author laurad
 */
public class DownloadedWay extends OsmElement {

    private final Node matchedFromNode;
    private final Node matchedToNode;
    private final List<Node> downloadedNodes;

    public DownloadedWay(final OsmElement element, final Node matchedFromNode, final Node matchedToNode) {
        super(element.getOsmId(), element.getType(), element.getMembers(), element.getFromId(), element.getOsmId(),
                element.getTag());
        this.matchedFromNode = new Node(matchedFromNode);
        this.matchedToNode = new Node(matchedToNode);
        downloadedNodes = null;
    }

    public DownloadedWay(final OsmElement element, final List<Node> downloadedNodes) {
        super(element.getOsmId(), element.getType(), element.getMembers(), element.getFromId(), element.getOsmId(),
                element.getTag());
        this.downloadedNodes = downloadedNodes;
        matchedToNode = null;
        matchedFromNode = null;
    }

    public Node getMatchedFromNode() {
        return matchedFromNode;
    }

    public Node getMatchedToNode() {
        return matchedToNode;
    }

    public List<Node> getDownloadedNodes() {
        return downloadedNodes;
    }

    public boolean hasNodes() {
        return downloadedNodes != null && !downloadedNodes.isEmpty();
    }

    void setMatchedFromCoordinates(final LatLon newCoordinates) {
        matchedFromNode.setCoor(newCoordinates);
    }

    void setMatchedToCoordinates(final LatLon newCoordinates) {
        matchedToNode.setCoor(newCoordinates);
    }
}