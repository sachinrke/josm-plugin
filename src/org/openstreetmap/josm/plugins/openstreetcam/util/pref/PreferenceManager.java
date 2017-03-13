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
package org.openstreetmap.josm.plugins.openstreetcam.util.pref;

import static org.openstreetmap.josm.plugins.openstreetcam.util.pref.Keys.DISPLAY_TRACK_FLAG;
import static org.openstreetmap.josm.plugins.openstreetcam.util.pref.Keys.FILTERS_CHANGED;
import static org.openstreetmap.josm.plugins.openstreetcam.util.pref.Keys.HIGH_QUALITY_PHOTO_FLAG;
import static org.openstreetmap.josm.plugins.openstreetcam.util.pref.Keys.JOSM_AUTH_METHOD;
import static org.openstreetmap.josm.plugins.openstreetcam.util.pref.Keys.JOSM_BASIC_VAL;
import static org.openstreetmap.josm.plugins.openstreetcam.util.pref.Keys.JOSM_OAUTH_SECRET;
import static org.openstreetmap.josm.plugins.openstreetcam.util.pref.Keys.MANUAL_SWITCH_DATA_TYPE;
import static org.openstreetmap.josm.plugins.openstreetcam.util.pref.Keys.MAP_VIEW_PHOTO_ZOOM;
import org.openstreetmap.josm.plugins.openstreetcam.argument.CacheSettings;
import org.openstreetmap.josm.plugins.openstreetcam.argument.DataType;
import org.openstreetmap.josm.plugins.openstreetcam.argument.ListFilter;
import org.openstreetmap.josm.plugins.openstreetcam.argument.MapViewSettings;
import org.openstreetmap.josm.plugins.openstreetcam.argument.PhotoSettings;
import org.openstreetmap.josm.plugins.openstreetcam.argument.PreferenceSettings;


/**
 * Utility class, manages save and load (put & get) operations of the preference variables. The preference variables are
 * saved into a global preference file. Preference variables are static variables which can be accessed from any plugin
 * class. Values saved in this global file, can be accessed also after a JOSM restart.
 *
 * @author Beata
 * @version $Revision$
 */
public final class PreferenceManager {

    private static final PreferenceManager INSTANCE = new PreferenceManager();

    private final LoadManager loadManager = new LoadManager();
    private final SaveManager saveManager = new SaveManager();

    private PreferenceManager() {}


    public static PreferenceManager getInstance() {
        return INSTANCE;
    }

    /**
     * Loads the photo search error suppress flag. If this value is true, then all the future photo search service
     * errors will be suppressed.
     *
     * @return a boolean value
     */
    public boolean loadPhotosErrorSuppressFlag() {
        return loadManager.loadPhotosErrorSuppressFlag();
    }

    /**
     * Saves the photo search error suppress flag.
     *
     * @param flag a boolean value
     */
    public void savePhotosErrorSuppressFlag(final boolean flag) {
        saveManager.savePhotosErrorSuppressFlag(flag);
    }

    /**
     * Loads the sequence error suppress flag. If this value is true, then all the future sequence retrieve errors will
     * be suppressed.
     *
     * @return a boolean value
     */
    public boolean loadSequenceErrorSuppressFlag() {
        return loadManager.loadSequenceErrorSuppressFlag();
    }

    /**
     * Saves the sequence error suppress flag.
     *
     * @param flag a boolean value
     */
    public void saveSequenceErrorSuppressFlag(final boolean flag) {
        saveManager.saveSequenceErrorSuppressFlag(flag);
    }

    /**
     * Saves the 'filtersChanged' flag to the preference file.
     *
     * @param changed a boolean value
     */
    public void saveFiltersChangedFlag(final boolean changed) {
        saveManager.saveFiltersChangedFlag(changed);
    }

    /**
     * Loads the list filters from the preference file.
     *
     * @return a {@code ListFilter}
     */
    public ListFilter loadListFilter() {
        return loadManager.loadListFilter();
    }

    /**
     * Saves the list filter to the preference file.
     *
     * @param filter a {@code ListFilter} represents the current filter settings
     */
    public void saveListFilter(final ListFilter filter) {
        saveManager.saveListFilter(filter);
    }

    /**
     * Loads the user's OpenStreetCam plugin related preference settings from the preference file.
     *
     * @return a {@code PreferenceSettings} object
     */
    public PreferenceSettings loadPreferenceSettings() {
        return new PreferenceSettings(loadMapViewSettings(), loadPhotoSettings(), loadCacheSettings());
    }

    public MapViewSettings loadMapViewSettings() {
        return loadManager.loadMapViewSettings();
    }

    /**
     * Loads the photo settings from the preference file.
     *
     * @return a {@code PhotoSettings}
     */
    public PhotoSettings loadPhotoSettings() {
        return loadManager.loadPhotoSettings();
    }

    /**
     * Loads the cache preference settings.
     *
     * @return a {@code CacheSettings}
     */
    public CacheSettings loadCacheSettings() {
        return loadManager.loadCacheSettings();
    }

    /**
     * Saves the user's preference settings to the preference file.
     *
     * @param preferenceSettings a {@code PreferenceSettings} represents the newly set preference settings
     */
    public void savePreferenceSettings(final PreferenceSettings preferenceSettings) {
        if (preferenceSettings != null) {
            saveManager.saveMapViewSettings(preferenceSettings.getMapViewSettings());
            saveManager.savePhotoSettings(preferenceSettings.getPhotoSettings());
            saveManager.saveCacheSettings(preferenceSettings.getCacheSettings());
        }
    }

    /**
     * Loads the layer appearance status from the preference file.
     *
     * @return a boolean
     */
    public boolean loadLayerOpenedFlag() {
        return loadManager.loadLayerOpenedFlag();
    }

    /**
     * Saves the layer appearance status to the preference file.
     *
     * @param isLayerOpened represents the layer showing/hiding status
     */
    public void saveLayerOpenedFlag(final boolean isLayerOpened) {
        saveManager.saveLayerOpenedFlag(isLayerOpened);
    }

    /**
     * Loads the panel appearance status from the preference file.
     *
     * @return a boolean
     */
    public boolean loadPanelOpenedFlag() {
        return loadManager.loadPanelOpenedFlag();
    }

    /**
     * Saves the panel appearance status to the preference file
     *
     * @param isPanelOpened represents the panel showing/hiding status
     */
    public void savePanelOpenedFlag(final boolean isPanelOpened) {
        saveManager.savePanelOpenedFlag(isPanelOpened);
    }

    /**
     * Verifies if the authentication method has changed or not.
     *
     * @param key a {@code String} represents the key associated with the authentication preference change event
     * @param value a {@code String} represents the value associated with the authentication preference change event
     * @return true if the authentication method has changed; false otherwise
     */
    private boolean hasAuthMethodChanged(final String key, final String value) {
        return (JOSM_AUTH_METHOD.equals(key) && JOSM_BASIC_VAL.equals(value)) || JOSM_OAUTH_SECRET.equals(key);
    }

    public boolean isFiltersChangedKey(final String value) {
        return FILTERS_CHANGED.equals(value);
    }

    private boolean isMapViewZoomKey(final String value) {
        return MAP_VIEW_PHOTO_ZOOM.equals(value);
    }

    public boolean isHighQualityPhotoFlag(final String value) {
        return HIGH_QUALITY_PHOTO_FLAG.equals(value);
    }

    public boolean isDisplayTackFlag(final String value) {
        return DISPLAY_TRACK_FLAG.equals(value);
    }

    public boolean dataDownloadPreferencesChanged(final String key, final String newValue) {
        return isFiltersChangedKey(key) || isMapViewZoomKey(key) || hasAuthMethodChanged(key, newValue)
                || hasManualSwitchDataTypeChanged(key, newValue);
    }

    private boolean hasManualSwitchDataTypeChanged(final String key, final String newValue) {
        return MANUAL_SWITCH_DATA_TYPE.equals(key) && newValue != null;
    }

    public void saveManualSwitchDataType(final DataType dataType) {
        saveManager.saveManualSwitchDataType(dataType);
    }

    public DataType loadManualSwitchDataType() {
        return loadManager.loadManualSwitchDataType();
    }
}