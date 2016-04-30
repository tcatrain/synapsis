package org.synapsis.core.library.server;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.synapsis.core.library.business.ILibraryBusiness;
import org.synapsis.core.library.business.impl.DefaultLibraryBusiness;
import org.synapsis.core.library.dao.ILibraryDAO;
import org.synapsis.core.library.dao.impl.MemoryLibraryDAO;
import org.synapsis.core.library.resource.ILibraryCollectionResource;
import org.synapsis.core.library.resource.ILibraryResource;
import org.synapsis.core.library.resource.impl.RestLibraryCollectionResource;
import org.synapsis.core.library.resource.impl.RestLibraryResource;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.util.HashMap;

public class SynapsisCoreModule extends ServletModule {

    private static final String SYNAPSIS_CORE_BASE_PACKAGE = "org.synapsis.core";

    @Override
    protected void configureServlets() {
        bind(DefaultServlet.class).in(Singleton.class);

        bind(ILibraryDAO.class).to(MemoryLibraryDAO.class).in(Singleton.class);
        bind(ILibraryBusiness.class).to(DefaultLibraryBusiness.class).in(Singleton.class);
        bind(ILibraryResource.class).to(RestLibraryResource.class).in(Singleton.class);
        bind(ILibraryCollectionResource.class).to(RestLibraryCollectionResource.class).in(Singleton.class);

        bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
        bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
        params.put(PackagesResourceConfig.PROPERTY_PACKAGES, SYNAPSIS_CORE_BASE_PACKAGE);
        serve("/*").with(GuiceContainer.class, params);
    }

}
