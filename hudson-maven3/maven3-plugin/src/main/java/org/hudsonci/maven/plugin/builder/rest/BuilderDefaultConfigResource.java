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

package org.hudsonci.maven.plugin.builder.rest;

import org.hudsonci.maven.model.config.BuildConfigurationDTO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.hudsonci.maven.plugin.Constants;
import org.hudsonci.maven.plugin.builder.MavenBuilderDescriptor;
import org.hudsonci.maven.plugin.builder.MavenBuilderService;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

/**
 * Provides access to {@link MavenBuilderDescriptor}'s default {@link BuildConfigurationDTO} resources.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 2.1.0
 */
@Named
@Path(Constants.URI_PREFIX + "/builderDefaultConfig")
@Produces({APPLICATION_JSON, APPLICATION_XML})
public class BuilderDefaultConfigResource
{
    private final MavenBuilderService mavenBuilderService;

    @Inject
    public BuilderDefaultConfigResource(final MavenBuilderService mavenBuilderService) {
        this.mavenBuilderService = checkNotNull(mavenBuilderService);
    }

    @GET
    public BuildConfigurationDTO getBuilderDefaultConfiguration() {
        return mavenBuilderService.getBuilderDefaultConfiguration();
    }

    @PUT
    public void setBuilderDefaultConfiguration(final BuildConfigurationDTO defaults) {
        mavenBuilderService.setBuilderDefaultConfiguration(defaults);
    }

    @DELETE
    public void resetBuilderDefaultConfiguration() {
        mavenBuilderService.resetBuilderDefaultConfiguration();
    }
}

