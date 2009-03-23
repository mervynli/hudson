/*
 * The MIT License
 *
 * Copyright (c) 2004-2009, Sun Microsystems, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package hudson.diagnosis;

import hudson.Extension;
import hudson.model.Hudson;
import hudson.model.PeriodicWork;
import org.jvnet.animal_sniffer.IgnoreJRERequirement;

import java.util.logging.Logger;

/**
 * Periodically checks the disk usage of <tt>HUDSON_HOME</tt>,
 * and activate {@link HudsonHomeDiskUsageMonitor} if necessary.
 *
 * @author Kohsuke Kawaguchi
 */
@Extension
public class HudsonHomeDiskUsageChecker extends PeriodicWork {
    public long getRecurrencePeriod() {
        return HOUR;
    }

    @IgnoreJRERequirement
    protected void doRun() {
        try {
            long free = Hudson.getInstance().getRootDir().getUsableSpace();
            long total = Hudson.getInstance().getRootDir().getTotalSpace();
            if(free<=0 || total<=0) {
                // information unavailable. pointless to try.
                LOGGER.info("HUDSON_HOME disk usage information isn't available. aborting to monitor");
                cancel();
                return;
            }

            LOGGER.fine("Monitoring disk usage of HUDSON_HOME. total="+total+" free="+free);

            // if it's more than 90% full and less than 1GB, activate
            // it's AND and not OR so that small Hudson home won't get a warning,
            // and similarly, if you have a 1TB disk, you don't get a warning when you still have 100GB to go.
            HudsonHomeDiskUsageMonitor.get().activated = (total/free>10 && free<1024L*1024*1024);
        } catch (LinkageError _) {
            // pre Mustang
            LOGGER.info("Not on JDK6. Cannot monitor HUDSON_HOME disk usage");
            cancel();
            return;
        }
    }

    private static final Logger LOGGER = Logger.getLogger(HudsonHomeDiskUsageChecker.class.getName());
}