/*
 * Copyright 2019 Grabtaxi Holdings PTE LTE (GRAB), All rights reserved.
 *
 * Use of this source code is governed by an MIT-style license that can be found in the LICENSE file.
 *
 */
package org.openstreetmap.josm.plugins.openstreetcam.entity;

import org.openstreetmap.josm.data.coor.LatLon;


/**
 * Builder for the {@code Photo} business entity.
 *
 * @author beataj
 * @version $Revision$
 */
public class PhotoBuilder {

    private Long id;
    private Long sequenceId;
    private Integer sequenceIndex;
    private LatLon point;
    private String name;
    private String largeThumbnailName;
    private String thumbnailName;
    private String oriName;
    private Long timestamp;
    private Double heading;
    private String username;
    private Long wayId;
    private String shotDate;


    public PhotoBuilder() {}

    public void id(final Long id) {
        this.id = id;
    }

    public void sequenceId(final Long sequenceId) {
        this.sequenceId = sequenceId;
    }

    public void sequenceIndex(final Integer sequenceIndex) {
        this.sequenceIndex = sequenceIndex;
    }

    public void point(final LatLon point) {
        this.point = point;
    }

    public void point(final Double latitude, final Double longitude) {
        this.point = new LatLon(latitude, longitude);
    }

    public void name(final String name) {
        this.name = name;
    }

    public void largeThumbnailName(final String largeThumbnailName) {
        this.largeThumbnailName = largeThumbnailName;
    }

    public void thumbnailName(final String thumbnailName) {
        this.thumbnailName = thumbnailName;
    }

    public void timestamp(final Long timestamp) {
        this.timestamp = timestamp;
    }

    public void heading(final Double heading) {
        this.heading = heading;
    }

    public void username(final String username) {
        this.username = username;
    }

    public void wayId(final Long wayId) {
        this.wayId = wayId;
    }

    public void shotDate(final String shotDate) {
        this.shotDate = shotDate;
    }

    public void oriName(final String oriName) {
        this.oriName = oriName;
    }

    Long getId() {
        return id;
    }

    Long getSequenceId() {
        return sequenceId;
    }

    Integer getSequenceIndex() {
        return sequenceIndex;
    }

    LatLon getPoint() {
        return point;
    }

    String getName() {
        return name;
    }

    String getLargeThumbnailName() {
        return largeThumbnailName;
    }

    String getThumbnailName() {
        return thumbnailName;
    }

    Long getTimestamp() {
        return timestamp;
    }

    Double getHeading() {
        return heading;
    }

    String getUsername() {
        return username;
    }

    Long getWayId() {
        return wayId;
    }

    String getShotDate() {
        return shotDate;
    }

    String getOriName() {
        return oriName;
    }

    public Photo build() {
        // any photo should have a coordinate
        return point != null ? new Photo(this) : null;
    }
}