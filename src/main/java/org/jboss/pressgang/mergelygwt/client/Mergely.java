package org.jboss.pressgang.mergelygwt.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RequiresResize;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A wrapper around the Mergely library - http://www.mergely.com/about.php
 */
public class Mergely extends Composite {

    private static final String MERGELY_DIV_ID_PREFIX = "mergely";
    private static final Logger LOGGER = Logger.getLogger(Mergely.class.getName());
    /** Used to generate unique element ids for Ace widgets. */
    private static int nextId = 0;
    /** The id of the div used to hold mergley */
    private final String elementId;

    private final String lhs;
    private final String rhs;
    private final boolean lhsReadonly;
    private final boolean rhsReadonly;

    private final boolean lineNumbers;

    /**
     *
     * @param lhs Initial text for the left hand side
     * @param lhsReadonly true if the left hand side should be readonly, and false otherwise
     * @param rhs Initial text for the right hand side
     * @param rhsReadonly true if the right hand side should be readonly, and false otherwise
     * @param lineNumbers true if line number should be displayed, and false otherwise
     */
    public Mergely(final String lhs, final boolean lhsReadonly, final String rhs, final boolean rhsReadonly, final boolean lineNumbers) {
        elementId =  MERGELY_DIV_ID_PREFIX + nextId++;
        this.lhs = lhs;
        this.rhs = rhs;
        this.lhsReadonly = lhsReadonly;
        this.rhsReadonly = rhsReadonly;
        this.lineNumbers = lineNumbers;

        final HTML html = new HTML("<div style=\"position: absolute; bottom: 0; top: 0; left: 0; right:0;\"><div id=\"" + elementId + "\"></div></div>");
        //final HTML html = new HTML("<div style=\"width: 100%; height: 100%; position: relative;\">"
        //        + "<div style=\"top: 0px; bottom: 0px; left: 0px; right: 0px;\" id=\"" + elementId + "\"></div>" + "</div>");
        initWidget(html);
    }

    /** Called after a the widget is added to the DOM. Add the Mergely editor */
    @Override
    protected void onLoad()
    {
        LOGGER.log(Level.INFO, "ENTER Mergely.onLoad()");
        super.onLoad();

        startMergely();
        LOGGER.log(Level.INFO, "EXIT Mergely.onLoad()");
    }

    private native void startMergely() /*-{
        if ($wnd.jQuery == undefined) {
            $wnd.alert("window.jQuery is undefined! Please make sure you have included the appropriate JavaScript files.");
            return;
        }

        $wnd.jQuery('#' + this.@org.jboss.pressgang.mergelygwt.client.Mergely::elementId).mergely({
            cmsettings: { lineNumbers: this.@org.jboss.pressgang.mergelygwt.client.Mergely::lineNumbers },
            lhs_cmsettings: {readOnly: this.@org.jboss.pressgang.mergelygwt.client.Mergely::lhsReadonly},
            rhs_cmsettings: {readOnly: this.@org.jboss.pressgang.mergelygwt.client.Mergely::rhsReadonly},
            width: 'auto',
            height: 'auto',
            lhs: function(text) {
                return function(setValue) {
                    setValue(text);
                };
            }(this.@org.jboss.pressgang.mergelygwt.client.Mergely::lhs),
            rhs: function(text) {
                return function(setValue) {
                    setValue(text);
                };
            }(this.@org.jboss.pressgang.mergelygwt.client.Mergely::rhs),
            loaded: function() {
                $wnd.jQuery('#' + this.@org.jboss.pressgang.mergelygwt.client.Mergely::elementId).mergely('resize');
            }
        });


    }-*/;

    public native String getLhs() /*-{
        return $wnd.jQuery('#' + this.@org.jboss.pressgang.mergelygwt.client.Mergely::elementId).mergely('get', 'lhs');
    }-*/;

    public native String getRhs() /*-{
        return $wnd.jQuery('#' + this.@org.jboss.pressgang.mergelygwt.client.Mergely::elementId).mergely('get', 'rhs');
    }-*/;
}
