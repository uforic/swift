/*
 * Copyright (C) 2012 Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.facebook.swift.parser.model;

import com.facebook.swift.parser.visitor.DocumentVisitor;
import com.facebook.swift.parser.visitor.Visitable;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.util.List;

import org.antlr.runtime.Token;

import static com.google.common.base.Preconditions.checkNotNull;

public class ThriftMethod implements Visitable
{
    private final String name;
    private final ThriftType returnType;
    private final List<ThriftField> arguments;
    private final boolean oneway;
    private final List<ThriftField> throwsFields;
    private final List<TypeAnnotation> annotations;
    private final Token token;
    public ThriftMethod(
            String name,
            ThriftType returnType,
            List<ThriftField> arguments,
            boolean oneway,
            List<ThriftField> throwsFields,
            List<TypeAnnotation> annotations,
            Token token)
    {
        this.name = checkNotNull(name, "name");
        this.returnType = checkNotNull(returnType, "returnType");
        this.arguments = ImmutableList.copyOf(checkNotNull(arguments, "arguments"));
        this.oneway = oneway;
        this.throwsFields = ImmutableList.copyOf(
                MoreObjects.firstNonNull(throwsFields, ImmutableList.<ThriftField>of()));
        this.annotations = ImmutableList.copyOf(checkNotNull(annotations, "annotations"));
        this.token = token;
    }

    public String getName()
    {
        return name;
    }

    public ThriftType getReturnType()
    {
        return returnType;
    }

    public List<ThriftField> getArguments()
    {
        return arguments;
    }

    public boolean isOneway()
    {
        return oneway;
    }

    public List<ThriftField> getThrowsFields()
    {
        return throwsFields;
    }

    public List<TypeAnnotation> getAnnotations()
    {
        return annotations;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public void visit(final DocumentVisitor visitor) throws IOException
    {
        Visitable.Utils.visit(visitor, this);
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("returnType", returnType)
                .add("arguments", arguments)
                .add("oneway", oneway)
                .add("throwsFields", throwsFields)
                .add("annotations", annotations)
                .toString();
    }
}
