package org.slf4j.impl;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.sonatype.gossip.Gossip;
import org.sonatype.gossip.Log;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.spi.LoggerFactoryBinder;

/**
 * Maven Gossip boostrap.
 *
 * @author Tamas Cservenak
 * @since 3.3.9
 */
public final class StaticLoggerBinder
    implements LoggerFactoryBinder
{
    private static final org.slf4j.impl.StaticLoggerBinder SINGLETON = new org.slf4j.impl.StaticLoggerBinder();

    /**
     * @since 1.1
     * @return {@link #SINGLETON}
     */
    public static org.slf4j.impl.StaticLoggerBinder getSingleton()
    {
        return SINGLETON;
    }

    private final ILoggerFactory factory = new ILoggerFactory()
    {
        @Override
        public Logger getLogger( final String name )
        {
            return Log.getLogger( name );
        }
    };

    public ILoggerFactory getLoggerFactory()
    {
        return factory;
    }

    public String getLoggerFactoryClassStr()
    {
        return Gossip.class.getName();
    }
}