/*
 *  Copyright 2016 Telenav, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package org.openstreetmap.josm.plugins.openstreetcam.util;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.openstreetmap.josm.Main;
import org.openstreetmap.josm.data.Bounds;
import org.openstreetmap.josm.data.coor.LatLon;
import org.openstreetmap.josm.data.osm.BBox;
import org.openstreetmap.josm.gui.MapView;
import org.openstreetmap.josm.gui.layer.OsmDataLayer;
import org.openstreetmap.josm.plugins.openstreetcam.argument.Circle;
import org.openstreetmap.josm.plugins.openstreetcam.entity.Photo;
import org.openstreetmap.josm.tools.Pair;
import com.telenav.josm.common.argument.BoundingBox;


/**
 * Utility class, holds helper methods.
 *
 * @author Beata
 * @version $Revision$
 */
public final class Util {

    private static final double POZ_DIST_DATA_LAYER = 5.0;
    private static final double POZ_DIST = 10.0;

    private static final int MIN_ZOOM = 0;
    private static final int MAX_ZOOM = 22;
    private static final int TILE_SIZE = 1024;
    private static final int ZOOM1_SCALE = 78206;
    public static final double EQUATORIAL_RADIUS = 6378137.0;
    private static final double ANGLE = Math.toDegrees(45);
    private static final double RADIUS = 0.0003;
    private static final double MAX_DISTANCE = 2.0;
    private static final int MAX_ANGLE = 360;
    private static final int EXTENSION_DISTANCE = 500;
    private static final double MIN_LON = -180.0;
    private static final double MAX_LON = 180.0;

    /* latitude interval limits */
    private static final double MIN_LAT = -90.0;
    private static final double MAX_LAT = 90.0;

    private Util() {}


    /**
     * Returns the zoom level based on the given bounds.
     *
     * @param bounds the map bounds
     * @return an integer
     */
    public static int zoom(final Bounds bounds) {
        final int zoomLevel;
        if (Main.map.mapView.getScale() >= ZOOM1_SCALE) {
            // JOSM does not return the correct bounds for the case when the zoom level is 1
            zoomLevel = 1;
        } else {
            zoomLevel = (int) Math.min(MAX_ZOOM, Math.max(MIN_ZOOM,
                    Math.round(Math.floor(Math.log(TILE_SIZE / bounds.asRect().height) / Math.log(2)))));
        }
        return zoomLevel;
    }


    /**
     * Returns the photo near to the given location. The method returns null if there is no photo nearby.
     *
     * @param photos a list of {@code Photo}s
     * @param point a {@code Point} the location where the user clicked
     * @return a {@code Photo} object
     */
    public static Photo nearbyPhoto(final List<Photo> photos, final Point point) {
        final double maxDist = Main.getLayerManager().getEditLayer() != null ? POZ_DIST_DATA_LAYER : POZ_DIST;
        Photo result = null;
        for (final Photo photo : photos) {
            final double dist = new Point2D.Double(point.getX(), point.getY())
                    .distance(Main.map.mapView.getPoint(photo.getLocation()));
            if (dist <= maxDist) {
                result = photo;
                break;
            }
        }
        return result;
    }

    /**
     * Returns the photos that are near to the selected photo.
     *
     * @param photos a list of {@code Photo}s
     * @param selectedPhoto the currently selected {@code Photo}
     * @param size the number of nearby photos to return
     * @return a set of {@code Photo}
     */
    public static Collection<Photo> nearbyPhotos(final List<Photo> photos, final Photo selectedPhoto, final int size) {
        final BBox bbox = selectedPhoto.getLocation().toBBox(RADIUS);
        final Map<Double, Photo> candidateMap = new TreeMap<>();
        for (final Photo photo : photos) {
            if (!photo.getSequenceId().equals(selectedPhoto.getSequenceId()) && bbox.bounds(photo.getLocation())) {
                final double dist = selectedPhoto.getLocation().distance(photo.getLocation());
                if (dist <= MAX_DISTANCE) {
                    candidateMap.put(dist, photo);
                }
            }
        }

        final Collection<Photo> result;
        if (size < candidateMap.size()) {
            result = new ArrayList<>(candidateMap.values()).subList(0, size);
        } else {
            result = candidateMap.values();
        }
        return result;
    }

    /**
     * Computes the end points of an arrow for the startPoint->endPoint direction.
     *
     * @param startPoint the start {@code Point} of the line
     * @param endPoint the end {@code Point} of the line
     * @param distance the distance between the endPoint and arrow points.
     * @return the arrow end points
     */
    public static Pair<LatLon, LatLon> arrowEndPoints(final LatLon startPoint, final LatLon endPoint,
            final double distance) {
        final double bearing = Math.toDegrees(startPoint.bearing(endPoint));
        final double angle1 = bearing + ANGLE;
        final double angle2 = bearing - ANGLE;
        final LatLon point1 = extrapolate(endPoint, angle1, distance);
        final LatLon point2 = extrapolate(endPoint, angle2, distance);
        return new Pair<>(point1, point2);
    }

    /**
     * Returns the coordinates of a point which is "distance" away from standPoint in the direction of "bearing".
     *
     * @param point the origin {@code Point}
     * @param bearing the direction of degrees
     * @param distance the distance in meters
     * @return a {@code Point}
     */
    private static LatLon extrapolate(final LatLon point, final double bearing, final double distance) {
        final double rlat1 = Math.toRadians(point.lat());
        final double rlon1 = Math.toRadians(point.lon());
        final double rbearing = Math.toRadians(bearing);
        final double rdistance = distance / EQUATORIAL_RADIUS;
        final double lat2 = Math.asin(
                Math.sin(rlat1) * Math.cos(rdistance) + Math.cos(rlat1) * Math.sin(rdistance) * Math.cos(rbearing));
        final double lon2 = rlon1 + Math.atan2(Math.sin(rbearing) * Math.sin(rdistance) * Math.cos(rlat1),
                Math.cos(rdistance) - Math.sin(rlat1) * Math.sin(lat2));
        return new LatLon(Math.toDegrees(lat2), Math.toDegrees(lon2));
    }

    /**
     * Returns the middle point between start and end location.
     *
     * @param start the start location
     * @param end the end location
     * @return a {@code LatLon}
     */
    public static LatLon midPoint(final LatLon start, final LatLon end) {
        return new LatLon((end.lat() + start.lat()) / 2, (end.lon() + start.lon()) / 2);
    }

    /**
     * Verifies if the given mapView contains or not the given coordinate. If the {@code OsmDataLayer} is active and has
     * data, then the coordinate is search in the available bounds.
     *
     * @param mapView the current {@code MapView}
     * @param latLon the coordinate to be checked
     * @return boolean
     */
    public static boolean containsLatLon(final MapView mapView, final LatLon latLon) {
        boolean contains = false;
        if ((Main.getLayerManager().getActiveLayer() instanceof OsmDataLayer)
                && Main.getLayerManager().getEditLayer() != null
                && !mapView.getLayerManager().getEditLayer().data.getDataSourceBounds().isEmpty()) {
            for (final Bounds bounds : Main.getLayerManager().getEditLayer().data.getDataSourceBounds()) {
                if (bounds.contains(latLon)) {
                    contains = true;
                    break;
                }
            }
        } else {
            final Point point = mapView.getPoint(latLon);
            contains = mapView.contains(point);
        }
        return contains;
    }

    /**
     * Returns a list of circles representing the current search area. The method takes into consideration also the edit
     * layer bounds.
     *
     * @return list of {@code Circle}s
     */
    public static List<Circle> currentCircles() {
        final List<Circle> result = new ArrayList<>();
        if (Main.getLayerManager().getEditLayer() != null
                && (Main.getLayerManager().getActiveLayer() instanceof OsmDataLayer)) {
            final List<Bounds> osmDataLayerBounds = Main.getLayerManager().getEditLayer().data.getDataSourceBounds();
            if (osmDataLayerBounds != null && !osmDataLayerBounds.isEmpty()) {
                for (final Bounds bounds : osmDataLayerBounds) {
                    if (Main.map.mapView.getRealBounds().intersects(bounds)) {
                        result.add(new Circle(bounds));
                    }
                }
            } else {
                result.add(new Circle(Main.map.mapView.getRealBounds()));
            }
        } else {
            result.add(new Circle(Main.map.mapView.getRealBounds()));
        }
        return result;
    }

    /**
     * Returns a list of bounding boxes, representing the current search area. The method takes into consideration also
     * the edit layer bounds.
     *
     * @return a list of {@code BoundingBox}
     */
    public static List<BoundingBox> currentBoundingBoxes() {
        final List<BoundingBox> result = new ArrayList<>();
        if (Main.getLayerManager().getEditLayer() != null
                && (Main.getLayerManager().getActiveLayer() instanceof OsmDataLayer)) {
            final List<Bounds> osmDataLayerBounds = Main.getLayerManager().getEditLayer().data.getDataSourceBounds();
            if (osmDataLayerBounds != null && !osmDataLayerBounds.isEmpty()) {
                for (final Bounds osmBounds : osmDataLayerBounds) {
                    if (Main.map.mapView.getRealBounds().intersects(osmBounds)) {
                        result.add(new BoundingBox(osmBounds.getMax().lat(), osmBounds.getMin().lat(),
                                osmBounds.getMax().lon(), osmBounds.getMin().lon()));
                    }
                }
            } else {
                final Bounds bounds = Main.map.mapView.getRealBounds();
                final BoundingBox bbox = new BoundingBox(bounds.getMax().lat(), bounds.getMin().lat(),
                        bounds.getMax().lon(), bounds.getMin().lon());
                final double distance = EXTENSION_DISTANCE * Main.map.mapView.getScale();
                result.add(extendBoundingBox(bbox, (int) distance));
            }
        } else {
            final Bounds bounds = Main.map.mapView.getRealBounds();
            final BoundingBox bbox = new BoundingBox(bounds.getMax().lat(), bounds.getMin().lat(),
                    bounds.getMax().lon(), bounds.getMin().lon());
            final double distance = EXTENSION_DISTANCE * Main.map.mapView.getScale();
            result.add(extendBoundingBox(bbox, (int) distance));
        }
        return result;
    }

    /**
     * Extends a bounding box with the given meters in vertical and horizontal direction.
     *
     * @param bbox a {@code BoundingBox} represents a searching area
     * @param meters represents the expansion
     * @return a {@code BoundingBox}
     */
    public static BoundingBox extendBoundingBox(final BoundingBox bbox, final int meters) {
        final double verticalExpansion = (meters * MAX_ANGLE) / (2 * Math.PI * EQUATORIAL_RADIUS);
        final double horizontalExpansion = (meters * MAX_ANGLE) / (2 * Math.PI * EQUATORIAL_RADIUS
                * Math.cos(Math.toRadians(Math.max(Math.abs(bbox.getSouth()), Math.abs(bbox.getNorth())))));

        final double minLat = Math.max(MIN_LAT, bbox.getSouth() - verticalExpansion);
        final double minLon = Math.max(MIN_LON, bbox.getWest() - horizontalExpansion);
        final double maxLat = Math.min(MAX_LAT, bbox.getNorth() + verticalExpansion);
        final double maxLon = Math.min(MAX_LON, bbox.getEast() + horizontalExpansion);
        return new BoundingBox(maxLat, minLat, maxLon, minLon);
    }
}