package de.wwu.scdh.xpath.icu.oxygen;

import ro.sync.exml.plugin.workspace.WorkspaceAccessPluginExtension;
import ro.sync.exml.workspace.api.standalone.StandalonePluginWorkspace;


/**
 * See {@link https://www.oxygenxml.com/doc/versions/22.1/ug-editor/topics/oxygen-plugin.html}
 *
 * We can use the most fundamental interface. Nothing to implement
 * here.
 */
public class IcuXPathPluginExtension implements WorkspaceAccessPluginExtension {

    @Override
    public void applicationStarted(StandalonePluginWorkspace pluginWorkspace) {
    }

    @Override
    public boolean applicationClosing() {
	return true;
    }

}
