/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package com.azure.samples.todo;

import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/todo/{username}")
@ApplicationScoped
public class ToDoResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ToDoService service;

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ToDoItem create(
            @PathParam("username")
            @NotNull
            @Size(min = 4, max = 14, message = "User name must be between 4 and 14 characters.") String username,
            @Valid ToDoItem item) {
        return service.addToDoItem(username, item);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(
            @PathParam("username")
            @NotNull
            @Size(min = 4, max = 14, message = "User name must be between 4 and 14 characters.") String username,
            @PathParam("id") Long id,
            @Valid ToDoItem item) {
        item.setId(id);
        service.updateToDoItem(username, item);
    }

    @DELETE
    @Path("{id}")
    public void remove(
            @PathParam("username")
            @NotNull
            @Size(min = 4, max = 14, message = "User name must be between 4 and 14 characters.") String username,
            @PathParam("id") Long id) {
        service.removeToDoItem(username, id);
    }

    @GET
    @Produces({"application/json"})
    public List<ToDoItem> getAll(
            @PathParam("username")
            @NotNull
            @Size(min = 4, max = 14, message = "User name must be between 4 and 14 characters.") String username) {
        return service.findToDoItemsByUsername(username);
    }
}
