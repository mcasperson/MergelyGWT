package org.jboss.pressgang.mergelygwt.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RequiresResize;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A warpper around the Mergely library - http://www.mergely.com/about.php
 */
public class Mergely extends Composite implements RequiresResize {

    private static final String MERGELY_DIV_ID_PREFIX = "mergely";
    private static final Logger LOGGER = Logger.getLogger(Mergely.class.getName());
    /** Used to generate unique element ids for Ace widgets. */
    private static int nextId = 0;
    /** The id of the div used to hold mergley */
    private final String elementId;

    private String lhs;
    private String rhs;
    private boolean lhsReadonly;
    private boolean rhsReadonly;

    public Mergely(final String lhs, final boolean lhsReadonly, final String rhs, final boolean rhsReadonly) {
        elementId =  MERGELY_DIV_ID_PREFIX + nextId++;
        this.lhs = lhs;
        this.rhs = rhs;
        this.lhsReadonly = lhsReadonly;
        this.rhsReadonly = rhsReadonly;

        final HTML html = new HTML("<div style=\"width: 100%; height: 100%;\" id=\"" + elementId + "\"");
        initWidget(html);
    }

    @Override
    public void onResize() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /** Called after a the widget is added to the DOM. Add the ACE editor */
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
            cmsettings: { lineNumbers: true },
            lhs_cmsettings: {readOnly: this.@org.jboss.pressgang.mergelygwt.client.Mergely::lhsReadonly},
            rhs_cmsettings: {readOnly: this.@org.jboss.pressgang.mergelygwt.client.Mergely::rhsReadonly},
            lhs: function(setValue) {
                setValue(this.@org.jboss.pressgang.mergelygwt.client.Mergely::lhs);
            },
            rhs: function(setValue) {
                setValue(this.@org.jboss.pressgang.mergelygwt.client.Mergely::rhs);
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
