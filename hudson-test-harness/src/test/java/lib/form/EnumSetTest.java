/*******************************************************************************
 *
 * Copyright (c) 2004-2010 Oracle Corporation.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     
 *
 *******************************************************************************/ 

package lib.form;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import hudson.model.BallColor;
import hudson.util.FormValidation;
import net.sf.json.JSONObject;
import org.jvnet.hudson.test.HudsonTestCase;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.util.EnumSet;

/**
 * @author Kohsuke Kawaguchi
 */
public class EnumSetTest extends HudsonTestCase {
    EnumSet<BallColor> f;

    public EnumSetTest() {
        f = EnumSet.of(BallColor.BLUE, BallColor.RED);
    }

    @DataBoundConstructor
    public EnumSetTest(EnumSet<BallColor> colors) {
        f = colors;
    }

    public void test1() throws Exception {
        HtmlPage p = createWebClient().goTo("self/test1");
        HtmlForm f = p.getFormByName("config");
        submit(f);
    }

    public FormValidation doSubmitTest1(StaplerRequest req) throws Exception {
        JSONObject f = req.getSubmittedForm();
        System.out.println(f);
        EnumSetTest r = req.bindJSON(EnumSetTest.class,f);
        System.out.println(r.f);
        return FormValidation.ok();
    }
}
