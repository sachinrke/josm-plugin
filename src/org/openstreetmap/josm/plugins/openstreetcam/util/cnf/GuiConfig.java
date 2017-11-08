/*
 * The code is licensed under the LGPL Version 3 license http://www.gnu.org/licenses/lgpl-3.0.en.html.
 *
 * The collected imagery is protected & available under the CC BY-SA version 4 International license.
 * https://creativecommons.org/licenses/by-sa/4.0/legalcode.
 *
 * Copyright (c)2017, Telenav, Inc. All Rights Reserved
 */
package org.openstreetmap.josm.plugins.openstreetcam.util.cnf;

import com.telenav.josm.common.cnf.BaseConfig;


/**
 * Loads GUI related texts.
 *
 * @author Beata
 * @version $Revision$
 */
public final class GuiConfig extends BaseConfig {

    private static final String CONFIG_FILE = "openstreetcam_gui.properties";
    private static final GuiConfig INSTANCE = new GuiConfig();

    private final String pluginShortName;
    private final String pluginLongName;
    private final String pluginTlt;
    private final String pluginShortcutText;
    private final String pluginShortcutLongText;

    private final String prefMapViewLbl;
    private final String prefPhotoZoomLbl;
    private final String prefManualSwitchLbl;
    private final String prefImageLbl;
    private final String prefImageHighQualityLbl;
    private final String prefMouseHoverLbl;
    private final String prefMouseHoverDelayLbl;

    private final String prefTrackLbl;
    private final String prefDisplayTrackLbl;
    private final String prefAutoplayLbl;
    private final String prefAutoplayLengthLbl;
    private final String prefAutoplayDelayLbl;

    private final String prefCacheLbl;
    private final String prefMemoryLbl;
    private final String prefDiskLbl;
    private final String prefPrevNextLbl;
    private final String prefNearbyLbl;

    private final String btnPreviousTlt;
    private final String btnPreviousShortcutText;
    private final String btnNextTlt;
    private final String btnNextShortcutText;

    private final String btnClosestTlt;
    private final String btnClosestShortcutText;
    private final String btnPlayTlt;
    private final String btnPlayShortcutText;
    private final String btnStopTlt;
    private final String btnStopShortcutText;
    private final String btnLocationTlt;
    private final String btnLocationShortcutText;
    private final String btnWebPageTlt;
    private final String btnWebPageShortcutTlt;
    private final String btnDataSwitchImageTlt;
    private final String btnDataSwitchSegmentTlt;
    private final String btnDataSwitchShortcutTlt;
    private final String btnMatchedWayTlt;
    private final String btnMatchedWayShortcutTlt;

    private final String dlgFilterTitle;
    private final String dlgFilterShortcutText;
    private final String dlgFilterDateLbl;
    private final String dlgFilterUserLbl;
    private final String dlgFilterLoginWarningLbl;
    private final String dlgFilterPhotoTypeLbl;
    private final String dlgFilterDetectionLbl;
    private final String dlgFilterOsmComparisonLbl;
    private final String dlgFilterValidationStatusLbl;
    private final String dlgFilterEditStatusLbl;
    private final String dlgFilterDetectionTypeLbl;

    private final String btnOkLbl;
    private final String btnClearLbl;
    private final String btnCancelLbl;

    private final String errorTitle;
    private final String errorPhotoListText;
    private final String errorSequenceText;
    private final String errorPhotoLoadingText;
    private final String errorPhotoPageText;
    private final String errorUserPageText;
    private final String errorFeedbackPageText;
    private final String incorrectDateFilterText;
    private final String unacceptedDateFilterText;
    private final String errorSequenceSaveText;
    private final String errorSegmentListText;


    private final String warningHighQualityPhoto;
    private final String warningLoadingPhoto;

    private final String confirmDateFilterTitle;
    private final String confirmDateFilterTxt;

    private final String layerDeleteMenuItemLbl;
    private final String layerDeleteMenuItemTlt;
    private final String layerFeedbackMenuItemLbl;
    private final String layerFeedbackShortcutText;
    private final String layerPreviousMenuItemLbl;
    private final String layerNextMenuItemLbl;
    private final String layerPreferenceMenuItemLbl;
    private final String layerSaveSequenceMenuItemLbl;

    private final String infoMatchedWayTitle;
    private final String infoDownloadNextPhotosTitle;
    private final String infoDownloadPreviousPhotosTitle;
    private final String infoFileExistsTitle;
    private final String infoFileExistsText;
    private final String gpxTrackDescription;

    private GuiConfig() {
        super(CONFIG_FILE);

        pluginShortName = readProperty("plugin.name.short");
        pluginLongName = readProperty("plugin.name.long");
        pluginTlt = readProperty("plugin.tlt");
        pluginShortcutText = readProperty("plugin.shortcut.text");
        pluginShortcutLongText = readProperty("plugin.shortcut.longText");

        prefMapViewLbl = readProperty("preferences.mapView.lbl");
        prefPhotoZoomLbl = readProperty("preferences.mapView.zoom.lbl");
        prefManualSwitchLbl = readProperty("preferences.mapView.switch.lbl");
        prefImageLbl = readProperty("preference.photo.lbl");
        prefImageHighQualityLbl = readProperty("preference.photo.highQuality.lbl");
        prefMouseHoverLbl = readProperty("preference.photo.mouseHover.lbl");
        prefMouseHoverDelayLbl = readProperty("preference.photo.mouseHover.delay.lbl");
        prefTrackLbl = readProperty("preference.track.lbl");
        prefDisplayTrackLbl = readProperty("preference.track.displayTrack.lbl");
        prefAutoplayLbl = readProperty("preference.track.autoplay.lbl");
        prefAutoplayLengthLbl = readProperty("preference.track.autoplay.length.lbl");
        prefAutoplayDelayLbl = readProperty("preference.track.autoplay.delay.lbl");

        prefCacheLbl = readProperty("preference.cache.lbl");
        prefMemoryLbl = readProperty("preference.cache.memory.lbl");
        prefDiskLbl = readProperty("preference.cache.disk.lbl");
        prefPrevNextLbl = readProperty("preference.cache.prevNext.lbl");
        prefNearbyLbl = readProperty("preference.cache.nearby.lbl");

        btnPreviousTlt = readProperty("btn.previous.tlt");
        btnPreviousShortcutText = readProperty("btn.previous.shortcut.text");
        btnNextTlt = readProperty("btn.next.tlt");
        btnNextShortcutText = readProperty("btn.next.shortcut.text");
        btnPlayTlt = readProperty("btn.play.tlt");
        btnPlayShortcutText = readProperty("btn.play.shortcut.text");
        btnStopTlt = readProperty("btn.stop.tlt");
        btnStopShortcutText = readProperty("btn.stop.shortcut.text");
        btnLocationTlt = readProperty("btn.location.tlt");
        btnLocationShortcutText = readProperty("btn.location.shortcut.text");
        btnWebPageTlt = readProperty("btn.webPage.tlt");
        btnWebPageShortcutTlt = readProperty("btn.webPage.shortcut.text");
        btnClosestTlt = readProperty("btn.closest.tlt");
        btnClosestShortcutText = readProperty("btn.closest.shortcut.text");
        btnDataSwitchImageTlt = readProperty("btn.switch.image.tlt");
        btnDataSwitchSegmentTlt = readProperty("btn.switch.segment.tlt");
        btnDataSwitchShortcutTlt = readProperty("btn.switch.shortcut.text");
        btnMatchedWayShortcutTlt = readProperty("btn.matchedWay.shortcut.text");
        btnMatchedWayTlt = readProperty("btn.matchedWay.tlt");

        dlgFilterTitle = readProperty("filter.title");
        dlgFilterShortcutText = readProperty("filter.shortcut.text");
        dlgFilterDateLbl = readProperty("filter.date.lbl");
        dlgFilterUserLbl = readProperty("filter.user.lbl");
        dlgFilterLoginWarningLbl = readProperty("filter.login.warning.lbl");
        dlgFilterDetectionLbl = readProperty("filter.detection.lbl");
        dlgFilterPhotoTypeLbl = readProperty("filter.photoType.lbl");
        dlgFilterOsmComparisonLbl = readProperty("filter.osmComparison.lbl");
        dlgFilterValidationStatusLbl = readProperty("filter.validationStatus.lbl");
        dlgFilterEditStatusLbl = readProperty("filter.editStatus.lbl");
        dlgFilterDetectionTypeLbl = readProperty("filter.detectionType.lbl");

        btnOkLbl = readProperty("btn.ok.lbl");
        btnClearLbl = readProperty("btn.clear.lbl");
        btnCancelLbl = readProperty("btn.cancel.lbl");

        errorTitle = readProperty("error.title");
        errorPhotoListText = readProperty("error.photo.list");
        errorSequenceText = readProperty("error.track");
        errorPhotoLoadingText = readProperty("error.photo.loading");
        errorPhotoPageText = readProperty("error.photo.page");
        errorUserPageText = readProperty("error.user.page");
        errorFeedbackPageText = readProperty("error.feedback.page");
        errorSegmentListText = readProperty("error.segment.list");
        errorSequenceSaveText = readProperty("error.track.save");
        unacceptedDateFilterText = readProperty("error.dateFilter.unaccepted");
        incorrectDateFilterText = readProperty("error.dateFilter.incorrect");

        warningHighQualityPhoto = readProperty("warning.photo.highQuality");
        warningLoadingPhoto = readProperty("warning.photo.loading");

        confirmDateFilterTitle = readProperty("info.dateFilter.title");
        confirmDateFilterTxt = readProperty("info.dateFilter.text");

        layerDeleteMenuItemLbl = readProperty("layer.menu.delete.lbl");
        layerDeleteMenuItemTlt = readProperty("layer.menu.delete.tlt");
        layerFeedbackMenuItemLbl = readProperty("layer.menu.feedback.lbl");
        layerFeedbackShortcutText = readProperty("layer.menu.feedback.shortcut.text");
        layerPreviousMenuItemLbl = readProperty("layer.menu.previous.lbl");
        layerNextMenuItemLbl = readProperty("layer.menu.next.lbl");
        layerPreferenceMenuItemLbl = readProperty("layer.menu.preference.lbl");
        layerSaveSequenceMenuItemLbl = readProperty("layer.menu.sequence.save");

        infoMatchedWayTitle = readProperty("info.matchedWay.title");
        infoDownloadNextPhotosTitle = readProperty("info.download.next.title");
        infoDownloadPreviousPhotosTitle = readProperty("info.download.previous.title");
        infoFileExistsTitle = readProperty("info.file.exists.title");
        infoFileExistsText = readProperty("info.file.exists.text");

        gpxTrackDescription = readProperty("gpx.track.description");
    }


    public static GuiConfig getInstance() {
        return INSTANCE;
    }


    public String getPluginShortName() {
        return pluginShortName;
    }

    public String getPluginLongName() {
        return pluginLongName;
    }

    public String getPluginTlt() {
        return pluginTlt;
    }

    public String getPrefMapViewLbl() {
        return prefMapViewLbl;
    }

    public String getPrefPhotoZoomLbl() {
        return prefPhotoZoomLbl;
    }

    public String getPrefManualSwitchLbl() {
        return prefManualSwitchLbl;
    }

    public String getPrefImageLbl() {
        return prefImageLbl;
    }

    public String getPrefImageHighQualityLbl() {
        return prefImageHighQualityLbl;
    }

    public String getPrefDisplayTrackLbl() {
        return prefDisplayTrackLbl;
    }

    public String getPrefMouseHoverLbl() {
        return prefMouseHoverLbl;
    }

    public String getPrefMouseHoverDelayLbl() {
        return prefMouseHoverDelayLbl;
    }

    public String getPrefTrackLbl() {
        return prefTrackLbl;
    }

    public String getPrefAutoplayLbl() {
        return prefAutoplayLbl;
    }

    public String getPrefAutoplayLengthLbl() {
        return prefAutoplayLengthLbl;
    }

    public String getPrefAutoplayDelayLbl() {
        return prefAutoplayDelayLbl;
    }

    public String getPrefCacheLbl() {
        return prefCacheLbl;
    }

    public String getPrefMemoryLbl() {
        return prefMemoryLbl;
    }

    public String getPrefDiskLbl() {
        return prefDiskLbl;
    }

    public String getPrefPrevNextLbl() {
        return prefPrevNextLbl;
    }

    public String getPrefNearbyLbl() {
        return prefNearbyLbl;
    }

    public String getBtnPreviousTlt() {
        return btnPreviousTlt;
    }

    public String getBtnNextTlt() {
        return btnNextTlt;
    }

    public String getBtnPlayTlt() {
        return btnPlayTlt;
    }

    public String getBtnStopTlt() {
        return btnStopTlt;
    }

    public String getBtnLocationTlt() {
        return btnLocationTlt;
    }

    public String getBtnWebPageTlt() {
        return btnWebPageTlt;
    }

    public String getBtnDataSwitchImageTlt() {
        return btnDataSwitchImageTlt;
    }

    public String getBtnDataSwitchSegmentTlt() {
        return btnDataSwitchSegmentTlt;
    }

    public String getDlgFilterTitle() {
        return dlgFilterTitle;
    }

    public String getDlgFilterDateLbl() {
        return dlgFilterDateLbl;
    }

    public String getDlgFilterUserLbl() {
        return dlgFilterUserLbl;
    }

    public String getDlgFilterLoginWarningLbl() {
        return dlgFilterLoginWarningLbl;
    }

    public String getDlgFilterDetectionLbl() {
        return dlgFilterDetectionLbl;
    }

    public String getDlgFilterPhotoTypeLbl() {
        return dlgFilterPhotoTypeLbl;
    }

    public String getDlgFilterOsmComparisonLbl() {
        return dlgFilterOsmComparisonLbl;
    }

    public String getDlgFilterValidationStatusLbl() {
        return dlgFilterValidationStatusLbl;
    }

    public String getDlgFilterEditStatusLbl() {
        return dlgFilterEditStatusLbl;
    }

    public String getDlgFilterDetectionTypeLbl() {
        return dlgFilterDetectionTypeLbl;
    }

    public String getBtnOkLbl() {
        return btnOkLbl;
    }

    public String getBtnClearLbl() {
        return btnClearLbl;
    }

    public String getBtnCancelLbl() {
        return btnCancelLbl;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getErrorPhotoListText() {
        return errorPhotoListText;
    }

    public String getErrorSequenceText() {
        return errorSequenceText;
    }

    public String getErrorPhotoLoadingText() {
        return errorPhotoLoadingText;
    }

    public String getErrorPhotoPageText() {
        return errorPhotoPageText;
    }

    public String getErrorUserPageText() {
        return errorUserPageText;
    }

    public String getErrorFeedbackPageText() {
        return errorFeedbackPageText;
    }

    public String getIncorrectDateFilterText() {
        return incorrectDateFilterText;
    }

    public String getUnacceptedDateFilterText() {
        return unacceptedDateFilterText;
    }

    public String getErrorSequenceSaveText() {
        return errorSequenceSaveText;
    }

    public String getErrorSegmentListText() {
        return errorSegmentListText;
    }

    public String getWarningHighQualityPhoto() {
        return warningHighQualityPhoto;
    }

    public String getWarningLoadingPhoto() {
        return warningLoadingPhoto;
    }

    public String getConfirmDateFilterTitle() {
        return confirmDateFilterTitle;
    }

    public String getConfirmDateFilterTxt() {
        return confirmDateFilterTxt;
    }

    public String getLayerDeleteMenuItemLbl() {
        return layerDeleteMenuItemLbl;
    }

    public String getLayerDeleteMenuItemTlt() {
        return layerDeleteMenuItemTlt;
    }

    public String getLayerFeedbackMenuItemLbl() {
        return layerFeedbackMenuItemLbl;
    }

    public String getLayerPreviousMenuItemLbl() {
        return layerPreviousMenuItemLbl;
    }

    public String getLayerNextMenuItemLbl() {
        return layerNextMenuItemLbl;
    }

    public String getPluginShortcutText() {
        return pluginShortcutText;
    }

    public String getPluginShortcutLongText() {
        return pluginShortcutLongText;
    }

    public String getBtnPreviousShortcutText() {
        return btnPreviousShortcutText;
    }

    public String getBtnNextShortcutText() {
        return btnNextShortcutText;
    }

    public String getBtnClosestTlt() {
        return btnClosestTlt;
    }

    public String getBtnClosestShortcutText() {
        return btnClosestShortcutText;
    }

    public String getBtnPlayShortcutText() {
        return btnPlayShortcutText;
    }

    public String getBtnStopShortcutText() {
        return btnStopShortcutText;
    }

    public String getBtnLocationShortcutText() {
        return btnLocationShortcutText;
    }

    public String getBtnWebPageShortcutTlt() {
        return btnWebPageShortcutTlt;
    }

    public String getBtnDataSwitchShortcutTlt() {
        return btnDataSwitchShortcutTlt;
    }

    public String getDlgFilterShortcutText() {
        return dlgFilterShortcutText;
    }

    public String getLayerFeedbackShortcutText() {
        return layerFeedbackShortcutText;
    }

    public String getBtnMatchedWayTlt() {
        return btnMatchedWayTlt;
    }

    public String getBtnMatchedWayShortcutTlt() {
        return btnMatchedWayShortcutTlt;
    }

    public String getInfoMatchedWayTitle() {
        return infoMatchedWayTitle;
    }

    public String getInfoDownloadNextPhotosTitle() {
        return infoDownloadNextPhotosTitle;
    }

    public String getInfoDownloadPreviousPhotosTitle() {
        return infoDownloadPreviousPhotosTitle;
    }

    public String getLayerPreferenceMenuItemLbl() {
        return layerPreferenceMenuItemLbl;
    }

    public String getLayerSaveSequenceMenuItemLbl() {
        return layerSaveSequenceMenuItemLbl;
    }

    public String getInfoFileExistsTitle() {
        return infoFileExistsTitle;
    }

    public String getInfoFileExistsText() {
        return infoFileExistsText;
    }

    public String getGpxTrackDescription() {
        return gpxTrackDescription;
    }
}