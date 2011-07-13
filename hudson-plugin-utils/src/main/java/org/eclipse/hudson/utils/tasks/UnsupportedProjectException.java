/**
 * The MIT License
 *
 * Copyright (c) 2010-2011 Sonatype, Inc. All rights reserved.
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

package org.eclipse.hudson.utils.tasks;

import hudson.model.AbstractProject;

/**
 * Indicates that an operation on a sub-class of {@link AbstractProject} is not supported by a project type.
 * 
 * @author Jamie Whitehouse
 * @since 2.1.0
 */
public class UnsupportedProjectException
    extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    private final AbstractProject project;

    public UnsupportedProjectException( final AbstractProject project )
    {
        super(String.format( "Unsupported project '%s' of type %s.", project.getFullDisplayName(), project.getClass()));
        this.project = project;
    }
    
    public UnsupportedProjectException( final AbstractProject project, final String moreDetail )
    {
        super(String.format( "Unsupported project '%s' of type %s : %s.", project.getFullDisplayName(), project.getClass(), moreDetail));
        this.project = project;
    }

    public AbstractProject getProject()
    {
        return project;
    }
}